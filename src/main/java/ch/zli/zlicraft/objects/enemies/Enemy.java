package ch.zli.zlicraft.objects.enemies;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public abstract class Enemy {

    public Monster entity;
    public EntityType entityType;

    public Enemy() {

    }

    public void spawn(Player player) {
        Location destination = player.getLocation().add(player.getLocation().getDirection().multiply(-2));
        entity = (Monster) player.getWorld().spawnEntity(destination, entityType);
        entity.setTarget(player);
        entity.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(1000);
        entity.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 255));
        equip();
    }

    public abstract void equip();

}
