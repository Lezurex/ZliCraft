package ch.zli.zlicraft.objects;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

public class Character {
    private Player player;
    private int health;
    private int hunger;
    private int armor;
    private int weapon;

    public Character(Player player, int health, int hunger, int armor, int weapon) {
        this.player = player;
        this.health = health;
        this.hunger = hunger;
        this.armor = armor;
        this.weapon = weapon;
    }

    public void setArmor(Material boots, Material leggings, Material chestPlate, Material helmet) {
        this.player.getInventory().setBoots(new ItemStack(boots, 1));
        this.player.getInventory().setLeggings(new ItemStack(leggings, 1));
        this.player.getInventory().setChestplate(new ItemStack(chestPlate, 1));
        this.player.getInventory().setHelmet(new ItemStack(helmet, 1));
    }

    public void setWeapon(Material sword, Material shield) {
        boolean foundSword = false;
        for (ItemStack item : this.player.getInventory().getContents()) {
            if (item == null) continue;
            System.out.println(item.toString());
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
                    case LEGACY_AIR:
                        continue;
                }
                if (breakLoop) break;
            } catch (Exception exception) {

            }
        }
        if (!foundSword) {
            this.player.getInventory().addItem(new ItemStack(sword, 1));
            System.out.println("Found no sword");
        }
        this.player.getInventory().setItemInOffHand(new ItemStack(shield, 1));
    }
    public void update() {
        this.player.updateInventory();
    }

    public int getHealth() {
        return this.health;
    }
    public int getHunger() {
        return this.hunger;
    }
    public int getArmor() {
        return this.armor;
    }
    public int getWeapon() {
        return this.weapon;
    }
}