package ch.zli.zlicraft.listener;

import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.joinMessage(Component.text("§a+ §1" + event.getPlayer().getName() + " §7ist beigetreten."));
    }

}
