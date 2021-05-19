package ch.zli.zlicraft.listener;

import ch.zli.zlicraft.objects.Character;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.joinMessage(Component.text("§a+ §1" + event.getPlayer().getName() + " §7joined the game."));
        String playername = event.getPlayer().getName();
        Character character = new Character(Bukkit.getPlayer(playername), 6, 16, 0, 0);
        character.upgrade();
    }
}