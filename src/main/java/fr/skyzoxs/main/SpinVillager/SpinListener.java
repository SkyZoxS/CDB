package fr.skyzoxs.main.SpinVillager;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class SpinListener implements Listener {

    private final Spin spin;

    public SpinListener(Spin spin) {
        this.spin = spin;
    }

    // Start spin for player
    @EventHandler
    public void onVillagerInteract(PlayerInteractAtEntityEvent event) {
        if (!(event.getRightClicked() instanceof Villager villager)) return;
        if (!villager.hasMetadata("spin-npc")) return;

        event.setCancelled(true);
        Player player = event.getPlayer();
        spin.spin(player);
    }

    //Cancel interaction with item on the spin menu
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        if (event.getView().getTitle().contains("Roulette")) {
            event.setCancelled(true); // Empêche toute interaction
        }
    }

    @EventHandler
    public void onVillagerDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();

        // Ton tableau de metadata
        String[] metaData = {
                "spin-npc", "basic_blocks", "decorator", "garden",
                "neither", "end", "food", "resources", "wood",
                "points_trader","trader_name"
        };

        // Si c'est un villageois et qu'il a au moins un des metadata → on annule les dégâts
        if (entity instanceof Villager) {
            for (String key : metaData) {
                if (entity.hasMetadata(key)) {
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }
}
