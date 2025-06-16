package fr.skyzoxs.main.Product;

import fr.skyzoxs.main.Grade.ShowGrade;
import fr.skyzoxs.main.Points.GlobalContri;
import fr.skyzoxs.main.Points.PointsScoreboard;
import fr.skyzoxs.main.Trader.PointsTraderVillager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Sell implements Listener {

    private final GlobalContri globalContri;
    private final HashMap<String, PointsTraderVillager> traders;

    public Sell(GlobalContri globalContri, HashMap<String, PointsTraderVillager> traders) {
        this.globalContri = globalContri;
        this.traders = traders;
    }

    private String idTrader(Inventory cliked_inventory) {
        String result = "";
        Iterator<Map.Entry<String, PointsTraderVillager>> it = this.traders.entrySet().iterator();
        while (it.hasNext() && result.isEmpty()) {
            Map.Entry<String, PointsTraderVillager> pair = it.next();
            if (pair.getValue().isMyInventory(cliked_inventory)) {
                result = pair.getKey();
            }
        }
        return result;
    }

    private ArrayList<Integer> indicesPresentMaterial(Inventory inventory, Material wanted_material) {
        if (inventory == null) {
            throw new IllegalArgumentException("The inventory cannot be null.");
        }
        if (wanted_material == null) {
            throw new IllegalArgumentException("The wanted items cannot be null.");
        }

        ArrayList<Integer> result = new ArrayList<>();
        ItemStack[] items = inventory.getContents();
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                Material present_material = items[i].getType();
                if (present_material.equals(wanted_material)) {
                    result.add(i);
                }
            }
        }
        return result;
    }


    private boolean haveEnoughItems(Inventory inventory, ArrayList<Integer> indices_items, int desired_amount) {
        if (inventory == null) {
            throw new IllegalArgumentException("The inventory cannot be null.");
        }
        if (desired_amount <= 0) {
            throw new IllegalArgumentException("The desired amount must be strictly positive.");
        }
        if (indices_items.isEmpty()) {
            return false;
        }

        int i = 0;
        while (i < indices_items.size() && desired_amount > 0) {
            ItemStack item = inventory.getItem(indices_items.get(i));
            if (item != null) {
                desired_amount -= item.getAmount();
            }
            i++;
        }

        return desired_amount <= 0;
    }

    private boolean removeMaterialInPlayerInventory(Inventory player_inventory, Material removed_material, int desired_amount) {
        if (player_inventory == null) {
            throw new IllegalArgumentException("The player's inventory cannot be null.");
        }
        if (removed_material == null) {
            throw  new IllegalArgumentException("The removed material cannot be null.");
        }
        if (desired_amount <= 0) {
            throw new IllegalArgumentException("The desired amount must be strictly positive.");
        }

        ArrayList<Integer> items_indices = indicesPresentMaterial(player_inventory, removed_material);
        if (!haveEnoughItems(player_inventory, items_indices, desired_amount)) {
            return false;
        }

        int i = 0;
        while (i < items_indices.size() && desired_amount > 0) {
            ItemStack item = player_inventory.getItem(items_indices.get(i));
            if (item != null && item.getType().equals(removed_material)) {
                int actual_amount = item.getAmount();
                if (actual_amount >= desired_amount) {
                    item.setAmount(actual_amount - desired_amount);
                    desired_amount = 0;
                }
                else {
                    item.setAmount(0);
                    desired_amount -= actual_amount;
                }
            }
            i++;
        }

        return true;
    }


    @EventHandler
    public void sellItem(InventoryClickEvent event) {
        if (event == null) {
            return;
        }

        Inventory inventory = event.getClickedInventory();
        if (inventory == null) {
            return;
        }



        String id_trader = this.idTrader(inventory);
        if (id_trader.isEmpty() || !this.traders.containsKey(id_trader)) {
            if (event.getClick() == ClickType.SHIFT_LEFT) {
                Inventory destination = event.getView().getTopInventory();
                String id_trader_dest = this.idTrader(destination);
                if (!id_trader_dest.isEmpty() && this.traders.containsKey(id_trader_dest)) {
                    event.setCancelled(true);
                }
            }
            return;
        }

        ItemStack wanted_item = event.getCurrentItem();
        if (wanted_item == null) {
            return;
        }
        Material wanted_material = wanted_item.getType();

        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();
        Inventory player_inventory = player.getInventory();
        int slot_position = event.getRawSlot();

        PointsTraderVillager trader = this.traders.get(id_trader);
        int amount_required = trader.getAmountRequired(slot_position);



        boolean is_removed = false;

        if (!wanted_material.equals(Material.PLAYER_HEAD)) {
            ArrayList<Material> accepted_materials = trader.getAcceptedMaterials(slot_position);
            Iterator<Material> iterator = accepted_materials.iterator();
            while (iterator.hasNext() && !is_removed) {
                is_removed = this.removeMaterialInPlayerInventory(
                        player_inventory,
                        iterator.next(),
                        amount_required
                );
            }
        }

        // Verify if the clicked item was successfully removed.
        if (is_removed) {
            // Updates the player's inventory.
            player.updateInventory();

            // Tells the trader he made a trade.
            trader.madeASales(slot_position);

            // Refresh the player's inventory.
            player.openInventory(trader.createInventory());

            // Retrieves the price of the chosen item.
            int price = trader.getPrice(slot_position);

            // Increases the contribution of the player who sold his items.
            this.globalContri.increaseContribution(String.valueOf(player.getUniqueId()), id_trader, price);

            // Updates the player's scoreboard.
            PointsScoreboard.updatePersonalPoints(player, this.globalContri);
            for (Player online : Bukkit.getOnlinePlayers()) {
                PointsScoreboard.updateGlobalPoints(online, this.globalContri);
            }

            // Updates the player's list name.
            ShowGrade.setPlayerPointsGrade(player, this.globalContri);

            //
            System.out.printf(
                    "Le joueur %s a vendu %d de %s.\n",
                    player.getDisplayName(),
                    amount_required,
                    wanted_material
            );
        }
        else {
            player.sendRawMessage(
                    String.format(
                            "Impossible de vendre l'item suivant : %s.",
                            wanted_material
                    )
            );
        }
    }

    @EventHandler
    public void cannotDragItemInPointsTraderInventory(InventoryDragEvent event) {
        Inventory destination = event.getView().getTopInventory();
        String id_trader = this.idTrader(destination);
        if (!id_trader.isEmpty() && this.traders.containsKey(id_trader)) {
            event.setCancelled(true);
        }
    }
}
