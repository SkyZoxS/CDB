package fr.skyzoxs.main;

import fr.skyzoxs.main.SpinVillager.ResetSpin;
import fr.skyzoxs.main.SpinVillager.Spin;
import fr.skyzoxs.main.SpinVillager.SpinGUI;
import fr.skyzoxs.main.SpinVillager.SpinListener;
import fr.skyzoxs.main.reward.RewardManager;
import fr.skyzoxs.main.utils.VillagerSpawner;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("Plugin is starting...");
        try {
            VillagerSpawner.removeVillager();

            // Spawn du villageois
            VillagerSpawner spawner = new VillagerSpawner(this);
            spawner.SpinVillager();

            // Initialisation
            saveResource("rewardItem.yml", false); // Copie depuis le jar vers plugins/CDB/
            RewardManager rewardManager = new RewardManager(getDataFolder());
            SpinGUI spinGUI = new SpinGUI(this, rewardManager);
            Spin spin = new Spin(getConfig(), rewardManager, this);

            // Listener
            getServer().getPluginManager().registerEvents(new SpinListener(this, spin), this);

            getLogger().info("Spin villager has been enabled!");
        }catch(Exception e) {
            System.out.println("Spin villager could not be enabled!");
        }
        getCommand("resetspin").setExecutor(new ResetSpin(this));


    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        VillagerSpawner.removeVillager();
        System.out.println("Plugin is stopping...");
    }
}
