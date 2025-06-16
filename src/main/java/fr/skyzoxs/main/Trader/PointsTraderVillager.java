package fr.skyzoxs.main.Trader;

import fr.skyzoxs.main.Main;
import fr.skyzoxs.main.Product.Product;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class PointsTraderVillager {

    public static final int UNIQUE = 1;
    public static final int TINY_STACK = 16;
    public static final int STACK = 64;

    protected String id;
    protected String display_name;
    protected HashMap<Integer, Product> materials;
    protected int inventory_size = 54;

    public PointsTraderVillager(String id, String display_name, int inventory_size) {
        this.id = id;
        this.display_name = display_name;
        this.inventory_size = inventory_size;
        this.materials = new HashMap<>();
    }

    public String getId() {
        return this.id;
    }

    public String getDisplayName() {
        return this.display_name;
    }

    public int getAmountRequired(int slot_position) {
        if (this.materials.containsKey(slot_position)) {
            return this.materials.get(slot_position).getRequestedAmount();
        }
        return -1;
    }

    public int getPrice(int slot_position) {
        if (this.materials.containsKey(slot_position)) {
            return this.materials.get(slot_position).getpointsGiven();        }
        return 0;
    }

    public ArrayList<Material> getAcceptedMaterials(int slot_position) {
        return this.materials.get(slot_position).getAcceptedMaterials();
    }

    public int getRecentSales(int slot_position) {
        return this.materials.get(slot_position).getRecentSales();
    }

    public HashMap<Material, Integer> getAllRecentSales() {
        HashMap<Material, Integer> result = new HashMap<>();
        for (Map.Entry<Integer, Product> pair : this.materials.entrySet()) {
            Product product = pair.getValue();
            result.put(product.getAcceptedMaterials().get(0), product.getRecentSales());
        }
        return result;
    }

    public HashMap<Material, Integer> getAllTotalSales() {
        HashMap<Material, Integer> result = new HashMap<>();
        for (Map.Entry<Integer, Product> pair : this.materials.entrySet()) {
            Product product = pair.getValue();
            result.put(product.getAcceptedMaterials().get(0), product.getAmountSold());
        }
        return result;
    }

    public void setRecentSales(String material_name, int recent_sales) {
        boolean isFound = false;
        Iterator<Map.Entry<Integer, Product>> iterator = this.materials.entrySet().iterator();
        while (iterator.hasNext() && !isFound) {
            Map.Entry<Integer, Product> pair = iterator.next();
            Product product = pair.getValue();
            if (product.getDisplayedName().equals(material_name)) {
                isFound = true;
                product.setRecentSales(recent_sales);
                this.materials.put(pair.getKey(), product);
            }
        }
    }

    public void setTotalSales(String material_name, int sales) {
        boolean isFound = false;
        Iterator<Map.Entry<Integer, Product>> iterator = this.materials.entrySet().iterator();
        while (iterator.hasNext() && !isFound) {
            Map.Entry<Integer, Product> pair = iterator.next();
            Product product = pair.getValue();
            if (product.getDisplayedName().equals(material_name)) {
                isFound = true;
                product.setAmountSold(sales);
                this.materials.put(pair.getKey(), product);
            }
        }
    }

    public void setSales(String material_name, int total, int last) {
        boolean isFound = false;
        Iterator<Map.Entry<Integer, Product>> iterator = this.materials.entrySet().iterator();
        while (iterator.hasNext() && !isFound) {
            Map.Entry<Integer, Product> pair = iterator.next();
            Product product = pair.getValue();
            if (product.getDisplayedName().equals(material_name)) {
                isFound = true;
                product.setAmountSold(total);
                product.setRecentSales(last);
                this.materials.put(pair.getKey(), product);
            }
        }
    }

    public void madeASales(int slot_position) {
        if (this.materials.containsKey(slot_position)) {
            Product product = this.materials.get(slot_position);
            product.madeASales();
            this.materials.put(slot_position, product);
        }
    }

    public Inventory createInventory() {
        Inventory inventory = Bukkit.createInventory(null, this.inventory_size, this.display_name);
        for (Map.Entry<Integer, Product> pair : this.materials.entrySet()) {
            Integer position = pair.getKey();
            Product product = pair.getValue();
            inventory.setItem(position, product.toItemStack());
        }
        return inventory;
    }

    public boolean isSameItem(ItemStack item, Product product) {
        if (item == null && product == null) {
            return true;
        }
        if ((item == null && product != null) || (item != null && product == null)) {
            return false;
        }
        if (item.getAmount() != product.getRequestedAmount()) {
            return false;
        }
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return false;
        }
        if (!meta.getDisplayName().equals(product.getDisplayedName())) {
            return false;
        }
        ArrayList<String> lore = (ArrayList<String>) meta.getLore();
        if (lore == null && !product.getLore().isEmpty()) {
            return false;
        }
        return product.isSameText(lore);
    }

    public boolean isMyInventory(Inventory inventory) {
        if (inventory == null) {
            return false;
        }
        ItemStack[] contents = inventory.getContents();
        boolean result = true;
        Iterator<Map.Entry<Integer, Product>> iterator = this.materials.entrySet().iterator();
        while (iterator.hasNext() && result) {
            Map.Entry<Integer, Product> pair = iterator.next();
            result = pair.getKey() < contents.length && this.isSameItem(contents[pair.getKey()], pair.getValue());
        }
        return result;
    }

    public static void create(World world, Location location, String name, Villager.Profession profession, String id) {
        Entity entity = world.spawnEntity(location, EntityType.VILLAGER);
        Villager pnj = (Villager) entity;

        // Set basic attributes
        pnj.setCustomName(name);
        pnj.setCustomNameVisible(true);
        pnj.setInvulnerable(true);
        pnj.setAI(false);
        pnj.setSilent(true);
        pnj.setProfession(profession); // Use given profession

        // Set persistent data (survives reload/restart)
        NamespacedKey keyTrader = new NamespacedKey(Main.getInstance(), "points_trader");
        NamespacedKey keyName = new NamespacedKey(Main.getInstance(), "trader_name");

        pnj.getPersistentDataContainer().set(keyTrader, PersistentDataType.INTEGER, 1); // Flag = true
        pnj.getPersistentDataContainer().set(keyName, PersistentDataType.STRING, id);   // Trader ID
        pnj.setMetadata("points_trader", new FixedMetadataValue(Main.getInstance(), true));
        pnj.setMetadata("trader_name", new FixedMetadataValue(Main.getInstance(), id));

        System.out.println("Spawned trader: " + name + " (" + id + ")");
    }

    public void create(World world, Location location, Villager.Profession profession) {
        PointsTraderVillager.create(world, location, this.display_name, profession, this.id);
    }

    public void refreshHisNeeds() {
        for (Map.Entry<Integer, Product> entry : this.materials.entrySet()) {
            entry.getValue().forgerSomeRecentSales();
        }
    }


}
