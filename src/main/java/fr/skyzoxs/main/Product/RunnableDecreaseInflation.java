package fr.skyzoxs.main.Product;

import fr.skyzoxs.main.Trader.PointsTraderVillager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class RunnableDecreaseInflation extends BukkitRunnable {

    private final HashMap<String, PointsTraderVillager> traders;

    public RunnableDecreaseInflation(HashMap<String, PointsTraderVillager> traders) {
        this.traders = traders;
    }

    @Override
    public void run() {
        for (Map.Entry<String, PointsTraderVillager> entry : this.traders.entrySet()) {
            PointsTraderVillager trader = entry.getValue();
            trader.refreshHisNeeds();

            for (Player online : Bukkit.getOnlinePlayers()) {
                InventoryView view = online.getOpenInventory();
                if (view != null && view.getTopInventory() != null && trader.isMyInventory(view.getTopInventory())) {
                    online.closeInventory();
                    online.openInventory(trader.createInventory());
                }
            }
        }
    }
}