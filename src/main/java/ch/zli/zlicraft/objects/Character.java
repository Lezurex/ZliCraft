package ch.zli.zlicraft.objects;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Character {
    private final Player player;
    private int armor;
    private int weapon;

    public Character(Player player, int armor, int weapon) {
        this.player = player;
        this.armor = armor;
        this.weapon = weapon;
    }

    public void setArmor(Material boots, Material leggings, Material chestPlate, Material helmet) {
        this.player.getInventory().setBoots(new ItemStack(boots, 1));
        this.player.getInventory().setLeggings(new ItemStack(leggings, 1));
        this.player.getInventory().setChestplate(new ItemStack(chestPlate, 1));
        this.player.getInventory().setHelmet(new ItemStack(helmet, 1));
    }
    public void setWeapon( Material sword, Material shield) {
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
                        item.setType(sword);
                        foundSword = true;
                        breakLoop = true;
                        break;
                }
                if (breakLoop) break;
            } catch (Exception ignored) {

            }
        }
        if (!foundSword) {
            this.player.getInventory().addItem(new ItemStack(sword, 1));
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

    public void setArmorlvl() {
        this.armor += 1;
    }

    public void setWeaponlvl() {
        this.weapon += 1;
    }
}