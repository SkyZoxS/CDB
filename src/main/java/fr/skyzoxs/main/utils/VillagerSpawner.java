package fr.skyzoxs.main.utils;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import java.util.List;

//Create a custom Villager with custom metadata, name and location
public class VillagerSpawner {

    private final Plugin plugin;

    static double[][] villagerLocations = {
            {100.5, 64, 100.5}, //Spin

    };


    //Builder for VillagerSpawner
    public VillagerSpawner(Plugin plugin) {
        this.plugin = plugin;
    }

    //Remove villager
    public static void removeVillager() {
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity instanceof Villager) {
                    // On vérifie toutes les métadatas
                    List<String> knownMetaKeys = List.of("spin-npc"); // List of Villager
                    for (String key : knownMetaKeys) {
                        if (entity.hasMetadata(key)) {
                            Location loc = entity.getLocation();
                            Chunk chunk = loc.getChunk();
                            if (!chunk.isLoaded()) {
                                chunk.load();
                            }
                            entity.setInvulnerable(false);
                            entity.remove();
                            break;
                        }
                    }
                }
            }
        }
    }

    //Create SpinVillager
    public void SpinVillager() {
        Location loc = new Location(Bukkit.getWorld("world"), villagerLocations[0][0], villagerLocations[0][1], villagerLocations[0][2]);
        World world = loc.getWorld();

        if(world == null){
            throw new NullPointerException("world is cannot be null.");
        }



        //Make the villager with custom name, invulnerability and silent.
        world.getBlockAt(loc.clone().subtract(0, 1, 0)).setType(Material.QUARTZ_BLOCK);

        Villager villager = world.spawn(loc, Villager.class);

        villager.setCustomName("§dSpin");
        villager.setCustomNameVisible(true);
        villager.setInvulnerable(true);
        villager.setAI(false);
        villager.setSilent(true);
        villager.setProfession(Villager.Profession.NITWIT);

        //Add a tag
        villager.setMetadata("spin-npc", new FixedMetadataValue(plugin, true));

    }

}
