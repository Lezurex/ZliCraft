package ch.zli.zlicraft.objects.enemies;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class ZombieEnemy extends Enemy {

    public ZombieEnemy() {
        entityType = EntityType.ZOMBIE;
    }

    @Override
    public void equip() {
        Objects.requireNonNull(entity.getEquipment()).setHelmet(new ItemStack(Material.IRON_HELMET, 1));
        entity.getEquipment().setItemInMainHand(new ItemStack(Material.STONE_SWORD, 1));
        entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999, 2));
        entity.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 1));
    }
}
