package ch.zli.zlicraft.listener;

import ch.zli.zlicraft.objects.BlockArea;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockModListener implements Listener {

    public BlockModListener() {
        BlockArea.loadAreas();
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (decide(event.getBlock())) {
            event.getPlayer().sendMessage("§cYou can't break blocks here!");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null) return;
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_AIR) return;
        if (decide(event.getClickedBlock())) {
            event.getPlayer().sendMessage("§cYou can't modify blocks here!");
            event.setCancelled(true);
        }
    }

    private boolean decide(Block block) {
        BlockArea foundArea = BlockArea.getBlockAreas().stream()
                .filter(blockArea -> blockArea.isInArea(block.getLocation())).findFirst().orElse(null);
        return foundArea != null;
    }

}
