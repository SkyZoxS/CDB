package fr.skyzoxs.main.Food;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.HashSet;
import java.util.Set;

public class FoodListener implements Listener {

    private final Set<Material> badFoods = new HashSet<>();

    public FoodListener(JavaPlugin plugin) {
        // Exemple de nourritures "toxiques"
        badFoods.add(Material.PORKCHOP);         // Porc cru
        badFoods.add(Material.CHICKEN);          // Poulet cru
        badFoods.add(Material.ROTTEN_FLESH);     // Chair putréfiée
        badFoods.add(Material.SPIDER_EYE);       // Œil d’araignée
        badFoods.add(Material.POISONOUS_POTATO); // Patate empoisonnée
        badFoods.add(Material.RABBIT);           // Lapin cru


        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBadFoodEat(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        Material food = event.getItem().getType();



        if (badFoods.contains(food) && player.getHealth() > 4.0) {
            player.sendMessage("§cCe n'était pas une bonne idée de manger ça...");
            player.damage(1.0); // 2 cœurs de dégâts
            player.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 180, 20));
            player.getWorld().strikeLightningEffect(player.getLocation()); // Juste pour le fun
        }
    }
}

