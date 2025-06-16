package fr.skyzoxs.main;

import fr.skyzoxs.main.Grade.GradeChat;
import fr.skyzoxs.main.Grade.ShowGrade;
import fr.skyzoxs.main.Points.Points;
import fr.skyzoxs.main.Points.PointsManager;
import fr.skyzoxs.main.Points.PointsScoreboard;
import fr.skyzoxs.main.SpinVillager.ResetSpin;
import fr.skyzoxs.main.SpinVillager.Spin;
import fr.skyzoxs.main.SpinVillager.SpinData;
import fr.skyzoxs.main.SpinVillager.SpinListener;
import fr.skyzoxs.main.SpinVillager.reward.RewardManager;
import fr.skyzoxs.main.utils.Join;
import fr.skyzoxs.main.utils.VillagerSpawner;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {


    private PointsManager pointsManager;
    private static Points points;
    private SpinData dataManager;  // <-- ajout

    @Override
    public void onEnable() {

        System.out.println("Plugin is starting...");
        World world = Bukkit.getWorld("world");
        if(world != null) {

            //Initialisation du points
            pointsManager = new PointsManager(this);
            points = pointsManager.loadPoints();

            //Initialisation du joueur
            getServer().getPluginManager().registerEvents(new Listener() {
                @EventHandler
                public void onJoin(PlayerJoinEvent event) {
                    Player player = event.getPlayer();
                    pointsManager.createIfAbsent(player.getUniqueId());
                    ShowGrade.setPlayerPointsGrade(player, points);
                }
            }, this);

            try {
                //Suppression des anciens villageois
                VillagerSpawner.removeVillager(world);

                // Spawn du villageois
                VillagerSpawner spawner = new VillagerSpawner(this);
                spawner.SpinVillager();

                // Initialisation DataManager
                dataManager = new SpinData(this);

                // Initialisation RewardManager
                RewardManager rewardManager = new RewardManager(getDataFolder(), this);


                // Initialisation Spin
                Spin spin = new Spin(dataManager, rewardManager, this);

                // Listener
                getServer().getPluginManager().registerEvents(new SpinListener(spin), this);

                getLogger().info("Spin villager has been enabled!");
            } catch (Exception e) {
                System.out.println("Spin villager could not be enabled!");
            }
        }

        //Initialisation de la commande resetspin [joueur]
        getCommand("resetspin").setExecutor(new ResetSpin(dataManager));

        //Initialisation du Grade et du Join
            Bukkit.getPluginManager().registerEvents(new Join(points), this);
            Bukkit.getPluginManager().registerEvents(new GradeChat(points), this);

        //Actualisation du Scoreboard et du Grade
        Bukkit.getScheduler().runTaskTimer(this, () -> {

            // Pour chaque joueur en ligne, on update le scoreboard
            for (Player player : Bukkit.getOnlinePlayers()) {
                PointsScoreboard.setScoreBoard(player, points);
                ShowGrade.setPlayerPointsGrade(player, points);
            }
        }, 0L, 20L);

    }


    @Override
    public void onDisable() {
        World world = Bukkit.getWorld("world");
    if(world != null) {
        //Supprime l'ancien villageois
        VillagerSpawner.removeVillager(world   );
    }
        System.out.println("Plugin is stopping...");
    }


}
