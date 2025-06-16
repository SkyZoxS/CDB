package fr.skyzoxs.main.Points;

import fr.skyzoxs.main.Grade.ShowGrade;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class PointsManager {
    private final File file;
    private final FileConfiguration config;
    private final JavaPlugin plugin;


    public PointsManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), "points.yml");
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.config = YamlConfiguration.loadConfiguration(file);
    }


    public void savePoints(Points points) {
        config.set("globalPoints", points.getGlobalPoints());

        for (String player : points.getContributorsName()) {
            HashMap<String, Integer> trades = points.getContributors().get(player);
            for (String trader : trades.keySet()) {
                int amount = trades.get(trader);
                config.set("players." + player + "." + trader, amount);
            }
        }

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Load information
    public Points loadPoints() {
        int globalPoints;

        if (config.contains("globalPoints")) {
            globalPoints = config.getInt("globalPoints");
        } else {
            globalPoints = 0;
            config.set("globalPoints", 0);  // <-- On le crée si absent
            saveConfig();                   // <-- Sauvegarde immédiate
        }

        Points points = new Points(globalPoints);

        if (config.isConfigurationSection("players")) {
            for (String player : config.getConfigurationSection("players").getKeys(false)) {
                for (String trader : config.getConfigurationSection("players." + player).getKeys(false)) {
                    int value = config.getInt("players." + player + "." + trader);
                    points.setOneTradeOfAContributor(player, trader, value);
                }
            }
        }

        return points;
    }

    //Save config
    private void saveConfig() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Add player in points.yml if absent
    public  void createIfAbsent(UUID uuid) {
        String playerId = uuid.toString();
        if (!config.contains("players." + playerId)) {
            config.set("players." + playerId + ".default", 0); // `default` = aucun trader encore
            try {
                config.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
