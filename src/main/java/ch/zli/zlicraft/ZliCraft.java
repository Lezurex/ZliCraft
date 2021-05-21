package ch.zli.zlicraft;

import ch.zli.zlicraft.commands.NPCCommand;
import ch.zli.zlicraft.commands.TestCommand;
import ch.zli.zlicraft.listener.JoinListener;
import ch.zli.zlicraft.listener.NPCInteractListener;
import ch.zli.zlicraft.objects.Quest;
import net.jitse.npclib.NPCLib;
import net.jitse.npclib.api.NPC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public final class ZliCraft extends JavaPlugin {

    private static ZliCraft INSTANCE;

    private NPCLib npcLib;
    private List<NPC> npcs = new ArrayList<>();
    private Map<Player, NPC> lastClickedNpcs = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic

        INSTANCE = this;

        this.npcLib = new NPCLib(this);

        initListeners();
        initCommands();

        getDataFolder().mkdirs();

        Quest.loadQuests();

        Bukkit.getLogger().log(Level.INFO, "ZLICraft loaded!");
        System.out.println("ZLICraft loaded!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void initCommands() {
        getCommand("test").setExecutor(new TestCommand());
        getCommand("npc").setExecutor(new NPCCommand());
    }

    private void initListeners() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new NPCInteractListener(), this);
    }

    public static ZliCraft getInstance() {
        return INSTANCE;
    }

    public List<NPC> getNpcs() {
        return npcs;
    }

    public Map<Player, NPC> getLastClickedNpcs() {
        return lastClickedNpcs;
    }

    public NPCLib getNpcLib() {
        return npcLib;
    }
}
