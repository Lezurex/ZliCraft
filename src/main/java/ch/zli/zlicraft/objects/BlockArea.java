package ch.zli.zlicraft.objects;

import ch.zli.zlicraft.ZliCraft;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Defines an area where breaking, modifying and placing blocks is blocked.<br>
 *
 * <strong>Format of JSON-file</strong>:
 * <pre>
 * [
 *      {
 *          "world": "worldName",
 *          "min": { x, y, z },
 *          "max": { x, y, z }
 *       }
 * ]
 * </pre>
 */
public class BlockArea {

    private static final List<BlockArea> blockAreas = new ArrayList<>();

    private final Location min;
    private final Location max;

    public BlockArea(Location min, Location max) {
        this.min = min;
        this.max = max;
    }

    /**
     * Checks if a {@link Location location} is inside the {@link BlockArea block area}
     *
     * @param location {@link Location location} to check
     * @return Whether or not the {@link Location location} is in the {@link BlockArea block area}
     */
    public boolean isInArea(Location location) {
        if (location.getX() >= min.getX() && location.getY() >= min.getY() && location.getZ() >= min.getZ()) {
            return location.getX() <= max.getX() && location.getY() <= max.getY() && location.getZ() <= max.getZ();
        }
        return false;
    }

    /**
     * Creates a new block area from a {@link JsonObject} <strong>and adds it to the internal list ({@link #getBlockAreas()})</strong>
     *
     * @param obj {@link JsonObject} with required data
     * @return Newly created {@link BlockArea}
     */
    public static BlockArea fromJsonObject(JsonObject obj) {
        JsonObject min = obj.getAsJsonObject("min");
        JsonObject max = obj.getAsJsonObject("max");
        BlockArea blockArea = new BlockArea(new Location(Bukkit.getWorld(obj.get("world").getAsString()), min.get("x")
                .getAsDouble(), min
                .get("y").getAsDouble(), min.get("z").getAsDouble()), new Location(Bukkit
                .getWorld(obj.get("world").getAsString()), max.get("x").getAsDouble(), max
                .get("y").getAsDouble(), max.get("z").getAsDouble()));
        blockAreas.add(blockArea);
        return blockArea;
    }

    /**
     * Loads all blocked areas from the JSON configuration file
     */
    public static void loadAreas() {
        Bukkit.getLogger().log(Level.INFO, "Loading blocked areas...");

        Stream<File> stream = Arrays.stream(Objects.requireNonNull(ZliCraft.getInstance().getDataFolder().listFiles()));
        File configFile = stream.filter(file -> file.getName().equals("blockedAreas.json")).findFirst().orElse(null);

        if (configFile != null) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(configFile));
                String fileContent = br.lines().collect(Collectors.joining());
                JsonArray jsonArray = new JsonParser().parse(fileContent).getAsJsonArray();
                jsonArray.forEach(jsonElement -> fromJsonObject(jsonElement.getAsJsonObject()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        Bukkit.getLogger().log(Level.INFO, "Blocked areas loaded!");
    }

    public static List<BlockArea> getBlockAreas() {
        return blockAreas;
    }
}
