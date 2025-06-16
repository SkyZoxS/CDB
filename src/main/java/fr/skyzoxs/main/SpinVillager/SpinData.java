package fr.skyzoxs.main.SpinVillager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class SpinData {

        private final Plugin plugin;
        private File dataFile;
        private FileConfiguration dataConfig;

        public SpinData(Plugin plugin) {
            this.plugin = plugin;
            createDataFile();
        }

        //Create data file to save last spin
        private void createDataFile() {
            dataFile = new File(plugin.getDataFolder(), "data.yml");
            if (!dataFile.exists()) {
                try {
                    plugin.getDataFolder().mkdirs(); // Création dossier plugin si absent
                    dataFile.createNewFile();       // Création fichier data.yml
                } catch (IOException e) {
                    plugin.getLogger().severe("Impossible de créer data.yml !");
                    e.printStackTrace();
                }
            }
            dataConfig = YamlConfiguration.loadConfiguration(dataFile);
        }

        public FileConfiguration getDataConfig() {
            return dataConfig;
        }

        //Save data.yml
        public void saveDataConfig() {
            try {
                dataConfig.save(dataFile);
            } catch (IOException e) {
                plugin.getLogger().severe("Impossible de sauvegarder data.yml !");
                e.printStackTrace();
            }
        }
}




