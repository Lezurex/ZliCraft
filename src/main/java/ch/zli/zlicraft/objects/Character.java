package ch.zli.zlicraft.objects;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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

    public void setArmor(Material boots, Material leggings, Material chestplate, Material helmet) {
        ItemStack[] armor = new ItemStack[4];
        armor[0] = new ItemStack(boots, 1);
        armor[1] = new ItemStack(leggings, 1);
        armor[2] = new ItemStack(chestplate, 1);
        armor[3] = new ItemStack(helmet, 1);

        this.player.getInventory().setArmorContents(armor);
    }

    public void setWeapon(Material sword, Material shield) {
        ItemStack[] weapons = new ItemStack[2];
        weapons[0] = new ItemStack(sword, 1);
        weapons[1] = new ItemStack(shield, 1);

        this.player.getInventory().setContents(weapons);
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

    public void setArmorlvl() {
        this.armor += 1;
    }

    public void setWeaponlvl() {
        this.weapon += 1;
    }
}