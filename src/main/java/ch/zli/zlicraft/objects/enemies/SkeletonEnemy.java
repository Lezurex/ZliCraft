package ch.zli.zlicraft.objects.enemies;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SkeletonEnemy extends Enemy{

    public SkeletonEnemy() {
        entityType = EntityType.SKELETON;
    }

    @Override
    public void equip() {
        entity.getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
        entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999, 1));
        entity.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 1));
    }
}
