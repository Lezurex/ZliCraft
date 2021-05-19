package ch.zli.zlicraft.listener;

import ch.zli.zlicraft.ZliCraft;
import net.jitse.npclib.api.events.NPCInteractEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCInteractListener implements Listener {

    @EventHandler
    public void onInteract(NPCInteractEvent event) {
        ZliCraft.getInstance().getLastClickedNpcs().put(event.getWhoClicked(), event.getNPC());
    }

}
