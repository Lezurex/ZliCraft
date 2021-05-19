package ch.zli.zlicraft;

import ch.zli.zlicraft.commands.TestCommand;
import ch.zli.zlicraft.listener.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class ZliCraft extends JavaPlugin {

    private static ZliCraft INSTANCE;

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("FUck off");
        INSTANCE = this;

        initListeners();
        initCommands();

        Bukkit.getLogger().log(Level.INFO, "ZLICraft loaded!");
        System.out.println("ZLICraft loaded!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("FUck off");
    }

    private void initCommands() {
        getCommand("test").setExecutor(new TestCommand());
        getCommand("quest").setExecutor(new TestCommand());
    }

    private void initListeners() {
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
    }

    public static ZliCraft getINSTANCE() {
        return INSTANCE;
    }
}
