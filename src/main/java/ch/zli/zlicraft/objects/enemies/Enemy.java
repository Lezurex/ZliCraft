package ch.zli.zlicraft.objects.enemies;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public abstract class Enemy {

    /**
     * {@link Monster} object of the entity, once spawned
     */
    public Monster entity;

    /**
     * {@link EntityType} of the mob which should be spawned (set in constructor in child)
     */
    public EntityType entityType;

    public Enemy() {

    }

    /**
     * Spawns the enemy behind the player and sets some attributes
     * @param player The player behind which the enemy should be spawned
     */
    public void spawn(Player player) {
        Location destination = player.getLocation().add(player.getLocation().getDirection().multiply(-2));
        entity = (Monster) player.getWorld().spawnEntity(destination, entityType);
        entity.setTarget(player);
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_FOLLOW_RANGE)).setBaseValue(1000);
        entity.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 255));
        equip();
    }

    /**
     * Is executed after the entity has been created
     */
    public abstract void equip();

}
