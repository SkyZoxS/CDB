package fr.skyzoxs.main.reward;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

//Load reward
public class RewardManager {
    private final List<RewardItem> rewardItems = new ArrayList<>();

    //Create every rewards from rewardItem.yml
    public RewardManager(File dataFolder){
        File file = new File(dataFolder, "rewardItem.yml");
        if (!file.exists()) {
            System.out.println("[Roulette] Le fichier rewardItem.yml n'existe pas dans " + file.getAbsolutePath());
            return;
        }
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        List<?> sections = config.getMapList("rewards");
        if (sections.isEmpty()) {
            System.out.println("[Roulette] Aucune récompense trouvée dans rewardItem.yml");
        } else {
            System.out.println("[Roulette] Chargement de " + sections.size() + " récompenses.");
        }
        for (var section : config.getMapList("rewards")) {
            try {
                String id = (String) section.get("id");
                String name = (String) section.get("name");
                Material material = Material.valueOf((String) section.get("material"));
                List<String> lore = (List<String>) section.get("lore");
                int chance = (int) section.get("chance");

                rewardItems.add(new RewardItem(id, name, material, lore, chance));
                System.out.println("[Roulette] Item chargé : " + id + ", " + name);
            } catch (Exception e) {
                System.out.println("[Roulette] Erreur de chargement d’un item.");
            }
        }
    }

    //Getter for rewards
    public List<RewardItem> getRewards() {
        return rewardItems;
    }

}
