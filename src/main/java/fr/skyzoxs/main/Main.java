package fr.skyzoxs.main;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Plugin is starting...");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Plugin is stopping...");
    }
}
