package ch.zli.zlicraft;

import ch.zli.zlicraft.commands.NPCCommand;
import ch.zli.zlicraft.commands.SkeletonCommand;
import ch.zli.zlicraft.commands.TestCommand;
import ch.zli.zlicraft.commands.ZombieCommand;
import ch.zli.zlicraft.listener.JoinListener;
import ch.zli.zlicraft.listener.NPCInteractListener;
import ch.zli.zlicraft.objects.NoPlayerCharacter;
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

    private NPCLib npcLib;
    private final Map<NPC, NoPlayerCharacter> npcs = new HashMap<>();
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
    }

    /**
     * Initializes and registers all listeners
     */
    private void initListeners() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new NPCInteractListener(), this);
    }

    public static ZliCraft getInstance() {
        return INSTANCE;
    }

    public Map<NPC, NoPlayerCharacter> getNpcs() {
        return npcs;
    }

    public Map<Player, NPC> getLastClickedNpcs() {
        return lastClickedNpcs;
    }

    public NPCLib getNpcLib() {
        return npcLib;
    }
}
