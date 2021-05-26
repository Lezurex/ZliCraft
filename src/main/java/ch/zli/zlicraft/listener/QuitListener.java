package ch.zli.zlicraft.listener;

import ch.zli.zlicraft.objects.Character;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Character character = Character.getCharacters().stream().filter(c -> c.getPlayer() == event.getPlayer())
                .findFirst().orElse(null);
        if (character != null) {
            character.saveData();
            Character.getCharacters().remove(character);
        }
    }

}
