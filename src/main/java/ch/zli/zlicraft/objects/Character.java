package ch.zli.zlicraft.objects;

import ch.zli.zlicraft.ZliCraft;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class Character {
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
    JSONArray saveJson;

    /**
     * @param player Owner of this character profile
     * @param armor See {@link #armor}
     * @param weapon See {@link #player}
     */
    public Character(Player player, int armor, int weapon) {
        if (armor < 0 || armor > 6) throw new IllegalArgumentException("Armor has to have value between 0 and 6!");
        if (weapon < 0 || weapon > 6) throw new IllegalArgumentException("Weapon has to have value between 0 and 6!");
        this.player = player;
        this.armor = armor;
        this.weapon = weapon;
    }

    /**
     * Sets the armor of the {@link #player}
     * @param boots Boots
     * @param leggings Leggings
     * @param chestPlate Chestplate
     * @param helmet Helmet
     */
    public void setArmor(Material boots, Material leggings, Material chestPlate, Material helmet) {
        this.player.getInventory().setBoots(new ItemStack(boots, 1));
        this.player.getInventory().setLeggings(new ItemStack(leggings, 1));
        this.player.getInventory().setChestplate(new ItemStack(chestPlate, 1));
        this.player.getInventory().setHelmet(new ItemStack(helmet, 1));
    }

    /**
     * Replaces and updates the weapon of the {@link #player}
     * @param weapon Weapon (usually a sword)
     * @param shield Shield (air or shield)
     */
    public void setWeapon( Material weapon, Material shield) {
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
        saveData();
    }

    public void incrementWeapon() {
        this.weapon += 1;
        saveData();
    }

    public void saveData() {
        File[] files = ZliCraft.getInstance().getDataFolder().listFiles();
        File levelfile = Arrays.stream(files).filter(file -> file.getName().equalsIgnoreCase("level.json")).findFirst().orElse(null);

        //Read JSON
        JSONParser jp = new JSONParser();
        try (BufferedReader br = new BufferedReader(new FileReader(levelfile))) {
            Object readObj = jp.parse(br);
            JSONArray levelArray = (JSONArray) readObj;
            this.saveJson = levelArray;
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        //Write to JSON
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(levelfile))) {

            JSONObject writeObj = new JSONObject();
            writeObj.put("name", this.player.getPlayer().getName());
            writeObj.put("weaponLevel", this.weapon);
            writeObj.put("armorLevel", this.armor);

            JSONArray writeJson = this.saveJson;

            for (Object obj: writeJson) {
            }
            writeJson.add(writeObj);

            System.out.println(writeJson);
            bw.write(writeJson.toJSONString());
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}