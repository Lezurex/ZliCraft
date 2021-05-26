package ch.zli.zlicraft.commands;

import ch.zli.zlicraft.objects.Character;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class QuestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            Character character = Character.getCharacters().stream().filter(c -> c.getPlayer() == player)
                    .findFirst().orElse(null);
            if (character != null) {
                if (character.getActiveQuest() != null)
                    character.getActiveQuest().announceQuest(player);
            }
        }
        return false;
    }
}
