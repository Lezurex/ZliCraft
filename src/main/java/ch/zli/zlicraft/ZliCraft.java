package ch.zli.zlicraft;

import ch.zli.zlicraft.commands.*;
import ch.zli.zlicraft.listener.*;
import ch.zli.zlicraft.objects.Quest;
import net.jitse.npclib.NPCLib;
import net.jitse.npclib.api.NPC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public final class ZliCraft extends JavaPlugin {

    private static ZliCraft INSTANCE;
    public static final String DIEGO_PREFIX = "§a[§6Diego§a]§r ";

    private NPCLib npcLib;
    private NPC diego;
    // Only relevant for /npc command
    private final Map<Player, NPC> lastClickedNpcs = new HashMap<>();

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
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * Initializes and registers all commands
     */
    private void initCommands() {
        getCommand("test").setExecutor(new TestCommand());
        getCommand("npc").setExecutor(new NPCCommand());
        getCommand("zombie").setExecutor(new ZombieCommand());
        getCommand("skeleton").setExecutor(new SkeletonCommand());
        getCommand("quest").setExecutor(new QuestCommand());
    }

    /**
     * Initializes and registers all listeners
     */
    private void initListeners() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new QuitListener(), this);
        pluginManager.registerEvents(new NPCInteractListener(), this);
        pluginManager.registerEvents(new BlockModListener(), this);
        pluginManager.registerEvents(new QuestListeners(), this);
    }

    public static ZliCraft getInstance() {
        return INSTANCE;
    }

    public NPC getDiego() {
        return diego;
    }

    public void setDiego(NPC diego) {
        this.diego = diego;
    }

    public Map<Player, NPC> getLastClickedNpcs() {
        return lastClickedNpcs;
    }

    public NPCLib getNpcLib() {
        return npcLib;
    }
}
