package ch.zli.zlicraft.objects;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Character
{
    private Player player;
    int health;
    int hunger;
    int armour;
    int weapon;

    public Character(Player player, int health, int hunger, int armour, int weapon)
    {
        this.player = player;
        this.health = health;
        this.hunger = hunger;
        this.armour = armour;
        this.weapon = weapon;
    }

    public void upgrade()
    {
        ItemStack[] armor = {};
        armor[0] = new ItemStack(Material.LEATHER_BOOTS, 1);
        armor[1] = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        armor[2] = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        armor[3] = new ItemStack(Material.LEATHER_HELMET, 1);
        this.player.getInventory().setArmorContents(armor);
    }
}