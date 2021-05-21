package ch.zli.zlicraft.listener;

import ch.zli.zlicraft.ZliCraft;
import ch.zli.zlicraft.objects.Character;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws IOException, ParseException {
        event.joinMessage(Component.text("§a+ §1" + event.getPlayer().getName() + " §7joined the game."));
        Character character = new Character(Bukkit.getPlayer(event.getPlayer().getName()), 6, 6);

        character.equipment();
        something();

        ZliCraft.getInstance().getNpcs().forEach(npc -> npc.show(event.getPlayer()));
    }

    public void something() {

        File[] files = ZliCraft.getInstance().getDataFolder().listFiles();

        List<File> quests = new ArrayList<>();
        for (File file : files) {
            if (file.getName().toLowerCase().endsWith(".json")) {
                quests.add(file);
            }
        }
    }
}