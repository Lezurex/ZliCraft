package ch.zli.zlicraft.listener;

import ch.zli.zlicraft.ZliCraft;
import ch.zli.zlicraft.objects.Character;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.joinMessage(Component.text("§a+ §1" + event.getPlayer().getName() + " §7joined the game."));
        Character character = Character.loadPlayer(event.getPlayer());
        assert character != null;
        character.equipment();
        character.saveData();

        ZliCraft.getInstance().getNpcs().keySet().forEach(npc -> npc.show(event.getPlayer()));
    }
}