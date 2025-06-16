package fr.skyzoxs.main.utils;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import java.util.List;

//Create a custom Villager with custom metadata, name and location
public class SpinVillager {

    private final Plugin plugin;

    // Locations of each villager
    static double[][] villagerLocations = {
            {100.5, 64, 100.5}, //Spin

    };

    public SpinVillager(Plugin plugin){
        this.plugin = plugin;
    }


    //Remove villager
    public static void removeSpin(World world) {
            List<Entity> entities = world.getEntities();

            for (double[] location : villagerLocations) {
                world.setChunkForceLoaded((int) (location[0]/16), (int) (location[1]/16), true);
            }
            for (Entity entity : entities) {
                if (entity.getType().equals(EntityType.VILLAGER)) {

                    // On vérifie toutes les métadatas
                    List<String> knownMetaKeys = List.of("spin-npc"); // List of Villager

                    for (String key : knownMetaKeys) {
                        if (entity.hasMetadata(key)) {
                            do {
                                entity.setInvulnerable(false);
                                entity.remove();
                            }while(!entity.isDead());
                        }
                    }
                }
            }

    }

    //Create SpinVillager
    public  void SpawnSpin() {
        Location loc = new Location(Bukkit.getWorld("world"), villagerLocations[0][0], villagerLocations[0][1], villagerLocations[0][2]);
        World world = loc.getWorld();

        if(world == null){
            throw new NullPointerException("world is cannot be null.");
        }



        //Make the villager with custom name, invulnerability and silent.
        world.getBlockAt(loc.clone().subtract(0, 1, 0)).setType(Material.QUARTZ_BLOCK);

        Villager villager = world.spawn(loc, Villager.class);

        villager.setCustomName("§dMaurice la roue"); //Custom name
        villager.setCustomNameVisible(true); //Set name visible
        villager.setInvulnerable(true); // Set invulnerable
        villager.setAI(false); // Set AI false
        villager.setSilent(true); // Set silent
        villager.setProfession(Villager.Profession.NITWIT);

        //Add a tag
        villager.setMetadata("spin-npc", new FixedMetadataValue(plugin, true));

    }
}
