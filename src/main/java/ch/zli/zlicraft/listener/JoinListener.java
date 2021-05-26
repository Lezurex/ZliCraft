package ch.zli.zlicraft.listener;

import ch.zli.zlicraft.ZliCraft;
import ch.zli.zlicraft.objects.Character;
import net.jitse.npclib.api.NPC;
import net.jitse.npclib.api.skin.MineSkinFetcher;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Collections;


public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.joinMessage(Component.text("§a+ §1" + event.getPlayer().getName() + " §7joined the game."));
        Character character = Character.loadPlayer(event.getPlayer());
        assert character != null;
        character.equipment();
        character.saveData();

        NPC diego = ZliCraft.getInstance().getDiego();
        if (diego == null) {
            // Spawn Diego
            Location location = new Location(Bukkit.getWorld("world"), 101805.5, 82, 32547.5, -90, 0);
            MineSkinFetcher.fetchSkinFromIdSync(963076162, skin -> {
                ZliCraft.getInstance()
                        .setDiego(ZliCraft.getInstance().getNpcLib().createNPC(Collections.singletonList("§6Diego"))
                                .setLocation(location)
                                .setSkin(skin)
                                .create()
                        );
            });
        }
        ZliCraft.getInstance().getDiego().show(event.getPlayer());
    }
}