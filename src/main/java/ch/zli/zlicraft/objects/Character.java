package ch.zli.zlicraft.objects;

import ch.zli.zlicraft.ZliCraft;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.inventory.ItemStack;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Character {

    private static final List<Character> characters = new ArrayList<>();

    private final Player player;

    /**
     * Armor level
     * (0: nothing, 1: leather, 2: gold, 3: chainmail, 4: iron, 5: diamond, 6: netherite)
     */
    private int armor;

    /**
     * Weapon level
     * (0: nothing, 1: wood, 2: gold, 3: stone, 4: iron, 5: diamond, 6: netherite)
     * Even levels > 0 add a shield
     */
    private int weapon;

    private Quest activeQuest;

    /**
     * @param player Owner of this character profile
     * @param armor  See {@link #armor}
     * @param weapon See {@link #player}
     */
    public Character(Player player, int armor, int weapon) {
        if (armor < 0 || armor > 6) throw new IllegalArgumentException("Armor has to be a value between 0 and 6!");
        if (weapon < 0 || weapon > 6) throw new IllegalArgumentException("Weapon has to be a value between 0 and 6!");
        this.player = player;
        this.armor = armor;
        this.weapon = weapon;
        characters.add(this);
    }

    public Character(Player player, int armor, int weapon, Quest activeQuest) {
        this(player, armor, weapon);
        this.activeQuest = activeQuest;
    }

    public Character(Player player) {
        this(player, 0, 0);
    }

    public static Character loadPlayer(Player player) {
        File levelfile = new File(ZliCraft.getInstance().getDataFolder().getAbsolutePath() + "/level.json");
        if (!levelfile.exists()) {
            try {
                levelfile.createNewFile();
                BufferedWriter bw = new BufferedWriter(new FileWriter(levelfile));
                bw.write("[]");
                bw.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(levelfile));
            JsonParser jsonParser = new JsonParser();
            JsonArray jsonArray = jsonParser.parse(br).getAsJsonArray();

            Character character = null;
            for (JsonElement jsonElement : jsonArray) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                if (jsonObject.get("uuid").getAsString().equals(player.getUniqueId().toString())) {
                    character = fromJsonObject(jsonObject, player);
                }
            }
            if (character == null) {
                character = new Character(player);
            }
            return character;
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static Character fromJsonObject(JsonObject jsonObject, Player player) {
        Character character = new Character(player, jsonObject.get("armorLevel").getAsInt(), jsonObject
                .get("weaponLevel").getAsInt());
        if (jsonObject.get("quest").getAsInt() >= 0) {
            Quest quest = Quest.getById(jsonObject.get("quest").getAsInt());
            if (quest != null) {
                character.setActiveQuest(quest);
            }
        }
        return character;
    }

    /**
     * Sets the armor of the {@link #player}
     *
     * @param boots      Boots
     * @param leggings   Leggings
     * @param chestPlate Chestplate
     * @param helmet     Helmet
     */
    public void setArmor(Material boots, Material leggings, Material chestPlate, Material helmet) {
        this.player.getInventory().setBoots(new ItemStack(boots, 1));
        this.player.getInventory().setLeggings(new ItemStack(leggings, 1));
        this.player.getInventory().setChestplate(new ItemStack(chestPlate, 1));
        this.player.getInventory().setHelmet(new ItemStack(helmet, 1));
    }

    /**
     * Replaces and updates the weapon of the {@link #player}
     *
     * @param weapon Weapon (usually a sword)
     * @param shield Shield (air or shield)
     */
    public void setWeapon(Material weapon, Material shield) {
        boolean foundSword = false;
        for (ItemStack item : this.player.getInventory().getContents()) {
            if (item == null) continue;
            boolean breakLoop = false;
            try {
                System.out.println(item.getType());
                switch (item.getType()) {
                    case WOODEN_SWORD:
                    case STONE_SWORD:
                    case IRON_SWORD:
                    case GOLDEN_SWORD:
                    case DIAMOND_SWORD:
                    case NETHERITE_SWORD:
                        item.setType(weapon);
                        foundSword = true;
                        breakLoop = true;
                        break;
                }
                if (breakLoop) break;
            } catch (Exception ignored) {

            }
        }
        if (!foundSword) {
            this.player.getInventory().addItem(new ItemStack(weapon, 1));
            System.out.println("Found no sword");
        }
        this.player.getInventory().setItemInOffHand(new ItemStack(shield, 1));
    }

    public void equipment() {
        switch (this.weapon) {
            case 0:
                this.setWeapon(Material.AIR, Material.AIR);
                break;
            case 1:
                this.setWeapon(Material.WOODEN_SWORD, Material.AIR);
                break;
            case 2:
                this.setWeapon(Material.GOLDEN_SWORD, Material.SHIELD);
                break;
            case 3:
                this.setWeapon(Material.STONE_SWORD, Material.AIR);
                break;
            case 4:
                this.setWeapon(Material.IRON_SWORD, Material.SHIELD);
                break;
            case 5:
                this.setWeapon(Material.DIAMOND_SWORD, Material.AIR);
                break;
            case 6:
                this.setWeapon(Material.NETHERITE_SWORD, Material.SHIELD);
                break;
            default:
                this.player.sendMessage("Something went wrong!");
                break;
        }

        switch (this.armor) {
            case 0:
                this.setArmor(Material.AIR, Material.AIR, Material.AIR, Material.AIR);
                break;
            case 1:
                this.setArmor(Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET);
                break;
            case 2:
                this.setArmor(Material.GOLDEN_BOOTS, Material.GOLDEN_LEGGINGS, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_HELMET);
                break;
            case 3:
                this.setArmor(Material.CHAINMAIL_BOOTS, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_HELMET);
                break;
            case 4:
                this.setArmor(Material.IRON_BOOTS, Material.IRON_LEGGINGS, Material.IRON_CHESTPLATE, Material.IRON_HELMET);
                break;
            case 5:
                this.setArmor(Material.DIAMOND_BOOTS, Material.DIAMOND_LEGGINGS, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_HELMET);
                break;
            case 6:
                this.setArmor(Material.NETHERITE_BOOTS, Material.NETHERITE_LEGGINGS, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_HELMET);
                break;
            default:
                this.player.sendMessage("Something went wrong!");
                break;
        }
    }

    public void incrementArmor() {
        this.armor += 1;
        equipment();
        saveData();
    }

    public void incrementWeapon() {
        this.weapon += 1;
        equipment();
        saveData();
    }

    public void nextQuest() {
        if (this.activeQuest == null) {
            setActiveQuest(Quest.getById(1));
            activeQuest.announceQuest(player);
        } else {
            if (activeQuest.isComplete()) {
                if (activeQuest.getType().equalsIgnoreCase("armor")) incrementArmor();
                else incrementWeapon();
                setActiveQuest(Quest.getById(activeQuest.getId() + 1));
                if (activeQuest != null) {
                    // Diego wither
                    if (activeQuest.getId() == 12) {
                        Bukkit.getScheduler().runTaskLater(ZliCraft.getInstance(), () -> {
                            Location location = ZliCraft.getInstance().getDiego().getLocation();
                            Wither wither = (Wither) location.getWorld().spawnEntity(location, EntityType.WITHER);
                            wither.setCustomName("§6Diego à la ZLI");
                            wither.setTarget(player);
                            ZliCraft.getInstance().getDiego().hide(player);
                        }, 20 * 5);
                    }
                    activeQuest.announceQuest(player);
                } else {
                    // All quests finished!
                    player.sendMessage(ZliCraft.DIEGO_PREFIX + "§a§lCongratulations! §r§aYou have finished all my quests!");
                }
            } else {
                player.sendMessage(ZliCraft.DIEGO_PREFIX + "§cYou haven't finished your current quest yet!");
            }
        }
    }

    public void saveData() {
        File levelfile = new File(ZliCraft.getInstance().getDataFolder().getAbsolutePath() + "/level.json");
        if (!levelfile.exists()) {
            try {
                levelfile.createNewFile();
                BufferedWriter bw = new BufferedWriter(new FileWriter(levelfile));
                bw.write("[]");
                bw.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        //Read JSON
        JsonArray levelArray = new JsonArray();
        JsonParser jp = new JsonParser();
        try (BufferedReader br = new BufferedReader(new FileReader(levelfile))) {
            Object readObj = jp.parse(br);
            levelArray = (JsonArray) readObj;
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Write to JSON
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(levelfile))) {
            JsonObject writeObj = new JsonObject();
            writeObj.addProperty("uuid", this.player.getPlayer().getUniqueId().toString());
            writeObj.addProperty("weaponLevel", this.weapon);
            writeObj.addProperty("armorLevel", this.armor);
            writeObj.addProperty("quest", activeQuest != null ? activeQuest.getId() : -1);

            JsonArray writeJson = new JsonArray();

            int count = 0;

            for (Object obj : levelArray) {
                JsonObject jsonObj = (JsonObject) obj;
                if (jsonObj.get("uuid").getAsString().equals(this.player.getPlayer().getUniqueId().toString())) {
                    jsonObj.addProperty("armorLevel", this.armor);
                    jsonObj.addProperty("weaponLevel", this.weapon);
                    jsonObj.addProperty("quest", activeQuest != null ? activeQuest.getId() : -1);
                    count++;
                }
                writeJson.add(jsonObj);
            }

            if (count == 0) {
                writeJson.add(writeObj);
            }

            bw.write(writeJson.toString());
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Quest getActiveQuest() {
        return activeQuest;
    }

    public void setActiveQuest(Quest activeQuest) {
        this.activeQuest = activeQuest;
        saveData();
    }

    public Player getPlayer() {
        return player;
    }

    public int getArmor() {
        return armor;
    }

    public int getWeapon() {
        return weapon;
    }

    public static List<Character> getCharacters() {
        return characters;
    }
}