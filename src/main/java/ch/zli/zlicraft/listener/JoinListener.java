package ch.zli.zlicraft.listener;

import ch.zli.zlicraft.objects.Character;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.joinMessage(Component.text("§a+ §1" + event.getPlayer().getName() + " §7joined the game."));
        Character character = new Character(Bukkit.getPlayer(event.getPlayer().getName()), 20, 20, 5, 4);

        switch (character.getArmor()) {
            case 0:
                character.setArmor(null, null, null, null);
                break;
            case 1:
                character.setArmor(Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET);
                break;
            case 2:
                character.setArmor(Material.GOLDEN_BOOTS, Material.GOLDEN_LEGGINGS, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_HELMET);
                break;
            case 3:
                character.setArmor(Material.CHAINMAIL_BOOTS, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_HELMET);
                break;
            case 4:
                character.setArmor(Material.IRON_BOOTS, Material.IRON_LEGGINGS, Material.IRON_CHESTPLATE, Material.IRON_HELMET);
                break;
            case 5:
                character.setArmor(Material.DIAMOND_BOOTS, Material.DIAMOND_LEGGINGS, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_HELMET);
//                character.setWeapon(Material.DIAMOND_SWORD, Material.SHIELD);
                break;
            case 6:
                character.setArmor(Material.NETHERITE_BOOTS, Material.NETHERITE_LEGGINGS, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_HELMET);
                break;
        }
    }
}