package ch.zli.zlicraft.listener;

import ch.zli.zlicraft.objects.Character;
import net.jitse.npclib.api.events.NPCInteractEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCInteractListener implements Listener {

    @EventHandler
    public void onInteract(NPCInteractEvent event) {
        Character character = Character.getCharacters().stream().filter(c -> c.getPlayer() == event.getWhoClicked())
                .findFirst().orElse(null);
        if (character != null) {
            character.nextQuest();
        }
    }

}
