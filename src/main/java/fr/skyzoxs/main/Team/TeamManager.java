package fr.skyzoxs.main.Team;

import fr.skyzoxs.main.Points.GlobalContri;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class TeamManager {

    private final File file;
    private final FileConfiguration config;
    private final JavaPlugin plugin;

    public TeamManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), "teams.yml");
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

    public void createTeam(String teamName, String color, List<UUID> members) {
        if (config.contains("teams." + teamName)) return;

        config.set("teams." + teamName + ".color", color);
        List<String> memberStrings = new ArrayList<>();
        for (UUID uuid : members) {
            memberStrings.add(uuid.toString());
        }
        config.set("teams." + teamName + ".members", memberStrings);
        saveConfig();
    }

    public boolean deleteTeam(String teamName) {
        if (config.contains("teams." + teamName)) {
            config.set("teams." + teamName, null); // Supprime la section entière
            saveConfig();
            return true;
        }
        return false;
    }


    public void saveConfig() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, List<UUID>> getAllTeams() {
        Map<String, List<UUID>> teams = new HashMap<>();
        if (config.isConfigurationSection("teams")) {
            for (String team : config.getConfigurationSection("teams").getKeys(false)) {
                List<String> uuids = config.getStringList("teams." + team + ".members");
                List<UUID> members = new ArrayList<>();
                for (String uuidStr : uuids) {
                    members.add(UUID.fromString(uuidStr));
                }
                teams.put(team, members);
            }
        }
        return teams;
    }

    public  String getTeamOfPlayer(UUID uuid) {
        if (!config.isConfigurationSection("teams")) return null;

        for (String team : config.getConfigurationSection("teams").getKeys(false)) {
            List<String> uuids = config.getStringList("teams." + team + ".members");
            if (uuids.contains(uuid.toString())) {
                return team;
            }
        }
        return null;
    }

    public int getTeamPoints(String teamName, GlobalContri globalContri) {
        List<UUID> members = getAllTeams().get(teamName);
        if (members == null) return 0;

        int total = 0;
        for (UUID uuid : members) {
            total += globalContri.getPlayerPoints(uuid.toString());
        }
        return total;
    }

    public String getTeamColor(String teamName) {
        return config.getString("teams." + teamName + ".color", "WHITE");
    }

}
