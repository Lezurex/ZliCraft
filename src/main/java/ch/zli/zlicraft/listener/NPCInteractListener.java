package ch.zli.zlicraft.listener;

import ch.zli.zlicraft.ZliCraft;
import net.jitse.npclib.api.events.NPCInteractEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCInteractListener implements Listener {

    @EventHandler
    public void onInteract(NPCInteractEvent event) {
        ZliCraft.getInstance().getLastClickedNpcs().put(event.getWhoClicked(), event.getNPC());
        ZliCraft.getInstance().getNpcs().get(event.getNPC());

        System.out.println("Upgraded");
        for (Player player : Bukkit.getOnlinePlayers()) {

            player.sendMessage("Wunderschöne guete morge mitenand");
        }
    }

}
