package ch.zli.zlicraft.commands;

import ch.zli.zlicraft.ZliCraft;
import net.jitse.npclib.api.NPC;
import net.jitse.npclib.api.skin.MineSkinFetcher;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class NPCCommand implements CommandExecutor {

    private static final String basicUsage = "§cUsage: /npc ";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("add")) {
                    if (args.length > 1) {
                        MineSkinFetcher.fetchSkinFromIdSync(1814317925, skin -> {
                            NPC npc = ZliCraft.getInstance().getNpcLib().createNPC()
                                    .setLocation(player.getLocation())
                                    .setSkin(skin)
                                    .setText(Collections.singletonList(args[1].replaceAll("&", "§")));
                            npc.create();
                            npc.show(player);
                            ZliCraft.getInstance().getNpcs().add(npc);
                        });
                    } else
                        player.sendMessage(basicUsage + "add <Name>");
                } else if (args[0].equalsIgnoreCase("remove")) {
                    if (args.length > 1) {
                        Map<Player, NPC> lastClicked = ZliCraft.getInstance().getLastClickedNpcs();
                        if (lastClicked.containsKey(player)) {
                            NPC npc = lastClicked.get(player);
                            if (ChatColor.stripColor(npc.getText().get(0)).equals(args[1])) {
                                npc.destroy();
                                player.sendMessage("§aNPC removed!");
                            } else
                                player.sendMessage("§cEntered name does not match first line of NPC hologram!");
                        } else
                            player.sendMessage("§cYou need to click an NPC first!");
                    } else
                        player.sendMessage(basicUsage + "remove <confirm Name>");
                }
            } else {
                player.sendMessage(basicUsage + "[ add | remove ]");
            }
        }
        return false;
    }
}
