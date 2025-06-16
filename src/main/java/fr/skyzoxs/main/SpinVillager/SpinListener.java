package fr.skyzoxs.main.SpinVillager;

import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.plugin.Plugin;

public class SpinListener implements Listener {

    private final Spin spin;

    public SpinListener(Plugin plugin, Spin spin) {
        this.spin = spin;
    }

    @EventHandler
    public void onVillagerInteract(PlayerInteractAtEntityEvent event) {
        if (!(event.getRightClicked() instanceof Villager villager)) return;
        if (!villager.hasMetadata("spin-npc")) return;

        event.setCancelled(true);
        Player player = event.getPlayer();
        spin.spin(player);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        if (event.getView().getTitle().contains("Roulette")) {
            event.setCancelled(true); // Empêche toute interaction
        }
    }


    @EventHandler
    public void onVillagerDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Villager villager)) return;
        if (villager.hasMetadata("spin-npc")) {
            event.setCancelled(true);
        }
    }
}
