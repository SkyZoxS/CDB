package fr.skyzoxs.main.Product;

import fr.skyzoxs.main.Trader.PointsTraderVillager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class Shop implements Listener {

    private final HashMap<String, PointsTraderVillager> traders;

    public Shop(HashMap<String, PointsTraderVillager> traders) {
        this.traders = traders;
    }

    @EventHandler
    public void openShop(PlayerInteractEntityEvent event) {
        if (event == null) {
            return;
        }

        Entity entity = event.getRightClicked();
        Player player = event.getPlayer();

        if (entity instanceof Villager) {
            if (entity.hasMetadata("points_trader")) {
                if (entity.hasMetadata("trader_name")) {
                    String id = (String) entity.getMetadata("trader_name").get(0).value();
                    PointsTraderVillager trader = this.traders.get(id);
                    Inventory inventory = trader.createInventory();
                    if (inventory != null) {
                        player.openInventory(inventory);
                    }
                    event.setCancelled(true);
                }
            }
        }
    }
}
