package fr.skyzoxs.main;

import fr.skyzoxs.main.Grade.*;
import fr.skyzoxs.main.Points.*;
import fr.skyzoxs.main.Product.*;

import fr.skyzoxs.main.SpinVillager.*;
import fr.skyzoxs.main.SpinVillager.reward.RewardManager;
import fr.skyzoxs.main.Trader.*;
import fr.skyzoxs.main.utils.Join;
import fr.skyzoxs.main.utils.SpinVillager;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Main extends JavaPlugin {

    private static Main instance;
    private PointsManager pointsManager;
    private static HashMap<String, PointsTraderVillager> traders;
    private static GlobalContri globalContri;
    private SpinData dataManager;  // <-- ajout
    // Locations of each villager
    private static final double[][] locations = {
            { 149.5, 64.0, 149.5 }, // Basic
            { 148.5, 64.0, 148.5 }, //nether
            { 147.5, 64.0, 147.5 }, //end
            { 146.5, 64.0, 146.5 }, //decorator
            { 145.5, 64.0, 145.5 }, //food
            { 144.5, 64.0, 144.5 }, //garden
            { 143.5, 64.0, 143.5 }, //hunter
            { 142.5, 64.0, 142.5 }, //resource
            { 141.5, 64.0, 141.5 }, //wood
    };


    @Override
    public void onEnable() {
        instance = this;


        System.out.println("Plugin is starting...");
        World world = Bukkit.getWorld("world");
        if(world != null) {

            Map<String, Location> locations = Map.ofEntries(
                    Map.entry("basic_blocks", new Location(world, 149.5, 64.0, 149.5, 180.0F, 0.0F)),
                    Map.entry("neither", new Location(world, 148.5, 64.0, 148.5, 90.0F, 0.0F)),
                    Map.entry("end", new Location(world, 147.5, 64.0, 147.5, -180.0F, 0.0F)),
                    Map.entry("decorator", new Location(world, 146.5, 64.0, 146.5, 0.0F, 0.0F)),
                    Map.entry("food", new Location(world, 145.5, 64.0, 145.5, 0.0F, 0.0F)),
                    Map.entry("garden", new Location(world, 144.5, 64.0, 144.5, -66.66F, 0.0F)),
                    Map.entry("hunter", new Location(world, 143.5, 64.0, 143.5, -90.0F, 0.0F)),
                    Map.entry("resources", new Location(world, 142.5, 64.0, 142.5, -90.0F, 0.0F)),
                    Map.entry("wood", new Location(world, 141.5, 64.0, 141.5, 90.0F, 0.0F))
            );

            //Suppression de tout les pnjs
            this.removeAllEntityPointsTrader(world);



            // Creates all point trader.
            for (Map.Entry<String, Location> entry : locations.entrySet()) {
                world.getBlockAt(entry.getValue().clone().subtract(0, 1, 0)).setType(Material.QUARTZ_BLOCK);
                world.setChunkForceLoaded((int) (entry.getValue().getBlockX()/16), (int) (entry.getValue().getBlockZ()/16), true);
                PointsTraderVillager trader = Main.traders.get(entry.getKey());
                trader.create(world, entry.getValue(), Villager.Profession.NITWIT);
            }

            long timeInSeconds = 10800L;
            long timeInTicks = 20 * timeInSeconds;
            RunnableDecreaseInflation runnable = new RunnableDecreaseInflation(Main.traders);
            runnable.runTaskTimer(Main.instance, timeInTicks, timeInTicks);

            //Initialisation du points
            pointsManager = new PointsManager(this);
            globalContri = pointsManager.loadPoints();

            //Initialisation du joueur
            getServer().getPluginManager().registerEvents(new Listener() {
                @EventHandler
                public void onJoin(PlayerJoinEvent event) {
                    Player player = event.getPlayer();
                    pointsManager.createIfAbsent(player.getUniqueId());
                    ShowGrade.setPlayerPointsGrade(player, globalContri);
                }
            }, this);

            try {
                //Suppression de spin
                SpinVillager.removeSpin(world);

                // Spawn du villageois
                SpinVillager spinVillager = new SpinVillager(this);
                spinVillager.SpawnSpin();

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
            Bukkit.getPluginManager().registerEvents(new Shop(Main.traders), Main.instance);
            Bukkit.getPluginManager().registerEvents(new Sell(Main.globalContri, Main.traders), Main.instance);
            Bukkit.getPluginManager().registerEvents(new Join(globalContri), this);
            Bukkit.getPluginManager().registerEvents(new GradeChat(globalContri), this);

        //Actualisation du Scoreboard et du Grade
        Bukkit.getScheduler().runTaskTimer(this, () -> {

            // Pour chaque joueur en ligne, on update le scoreboard
            for (Player player : Bukkit.getOnlinePlayers()) {
                PointsScoreboard.setScoreBoard(player, globalContri);
                ShowGrade.setPlayerPointsGrade(player, globalContri);
            }
        }, 0L, 20L);

    }



    private void removeAllEntityPointsTrader(World world) {
        if (world == null) {
            throw new IllegalArgumentException("World cannot be null.");
        }

        NamespacedKey pointsTraderKey = new NamespacedKey(Main.getInstance(), "points_trader");

        List<Entity> entities = world.getEntities();
        System.out.println("Removing " + entities.size() + " entities");
        for (Entity entity : entities) {
            System.out.println("Checking entity: " + entity);

            if (entity.getType() == EntityType.VILLAGER) {
                Villager villager = (Villager) entity;
                if (villager.getPersistentDataContainer().has(pointsTraderKey, PersistentDataType.INTEGER)) {
                    System.out.println("Removing villager with points_trader tag: " + villager.getCustomName());
                    world.setChunkForceLoaded(villager.getLocation().getBlockX() / 16, villager.getLocation().getBlockZ() / 16, true);
                    villager.setInvulnerable(false);
                    villager.remove();
                }
            } else if (entity.getType() != EntityType.PLAYER) {
                Location e_loc = entity.getLocation();
                for (double[] coords : Main.locations) {
                    if (e_loc.getX() == coords[0] && e_loc.getY() == coords[1] && e_loc.getZ() == coords[2]) {
                        entity.remove();
                        break;
                    }
                }
            }
        }
    }


    @Override
    public void onDisable() {
        World world = Bukkit.getWorld("world");
    if(world != null) {
        //Supprime de spin
        SpinVillager.removeSpin(world   );
        this.removeAllEntityPointsTrader(world);
    }
        System.out.println("Plugin is stopping...");
    }

    @Override
    public void onLoad() {
        Main.instance = this;

        Main.globalContri = new GlobalContri();

        Main.traders = new HashMap<>();

        Main.traders.put("basic_blocks", new BasicBlocksVillager("basic_blocks", "§2Recycleur"));
        Main.traders.put("decorator", new DecoratorVillager("decorator", "§bDécorateur"));
        Main.traders.put("garden", new GardenVillager("garden", "§aJardinier"));
        Main.traders.put("neither", new NetherVillager("neither", "§4Spécialiste du Nether"));
        Main.traders.put("end", new EndVillager("end", "§eSpécialiste de l'End"));
        Main.traders.put("hunter", new HostileLootVillager("hunter", "§cChasseur"));
        Main.traders.put("food", new FoodVillager("food", "§6Restaurateur"));
        Main.traders.put("resources", new ResourcesVillager("resources", "§8Mineur"));
        Main.traders.put("wood", new WoodVillager("wood", "§2Bucheron"));


    }

    public static Main getInstance() {
        return instance;
    }
}
