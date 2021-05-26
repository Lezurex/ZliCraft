package ch.zli.zlicraft.listener;

import ch.zli.zlicraft.objects.Character;
import ch.zli.zlicraft.objects.tasks.KillTask;
import ch.zli.zlicraft.objects.tasks.MineTask;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class QuestListeners implements Listener {

    @EventHandler
    public void onEntityKill(EntityDeathEvent event) {
        if (event.getEntity().getKiller() != null) {
            Player player = event.getEntity().getKiller();
            Character character = getCharacter(player);
            if (character != null) {
                if (character.getActiveQuest().getTask() instanceof KillTask) {
                    KillTask killTask = (KillTask) character.getActiveQuest().getTask();
                    if (event.getEntityType() == killTask.getEntityType()) {
                        character.getActiveQuest().getTask().incrementProgress(1);
                        character.getActiveQuest().announceQuest(player);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            Character character = getCharacter(player);
            if (character != null) {
                if (character.getActiveQuest().getTask() instanceof MineTask) {
                    MineTask mineTask = (MineTask) character.getActiveQuest().getTask();
                    if (event.getItem().getItemStack().getType() == mineTask.getMaterial()) {
                        character.getActiveQuest().getTask().incrementProgress(event.getItem().getItemStack().getAmount());
                        character.getActiveQuest().announceQuest(player);
                    }
                }
            }
        }
    }

    private Character getCharacter(Player player) {
        return Character.getCharacters().stream().filter(c -> c.getPlayer() == player)
                .findFirst().orElse(null);
    }

}
