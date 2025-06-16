package fr.skyzoxs.main;

import fr.skyzoxs.main.SpinVillager.ResetSpin;
import fr.skyzoxs.main.SpinVillager.Spin;
import fr.skyzoxs.main.SpinVillager.SpinData;
import fr.skyzoxs.main.SpinVillager.SpinListener;
import fr.skyzoxs.main.reward.RewardManager;
import fr.skyzoxs.main.utils.VillagerSpawner;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private SpinData dataManager;  // <-- ajout

    @Override
    public void onEnable() {
        System.out.println("Plugin is starting...");
        World world = Bukkit.getWorld("world");
        if(world != null) {


            try {
                VillagerSpawner.removeVillager(world);

                // Spawn du villageois
                VillagerSpawner spawner = new VillagerSpawner(this);
                spawner.SpinVillager();

                // Initialisation DataManager
                dataManager = new SpinData(this);

                // Initialisation RewardManager
                RewardManager rewardManager = new RewardManager(getDataFolder(), this);


                // Initialisation Spin avec DataManager au lieu de getConfig()
                Spin spin = new Spin(dataManager, rewardManager, this);

                // Listener
                getServer().getPluginManager().registerEvents(new SpinListener(this, spin), this);

                getLogger().info("Spin villager has been enabled!");
            } catch (Exception e) {
                System.out.println("Spin villager could not be enabled!");
            }
        }

        getCommand("resetspin").setExecutor(new ResetSpin(dataManager));
    }

    @Override
    public void onDisable() {
        World world = Bukkit.getWorld("world");
    if(world != null) {
        VillagerSpawner.removeVillager(world   );
    }
        System.out.println("Plugin is stopping...");
    }

    // Ajoute un getter au cas où tu en aurais besoin ailleurs
    public SpinData getDataManager() {
        return dataManager;
    }
}
