package fr.skyzoxs.main.Trader;

import fr.skyzoxs.main.Main;
import fr.skyzoxs.main.Product.Product;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class PointsDataManager {

    private final File file;
    private final YamlConfiguration config;

    public PointsDataManager(Main plugin) {
        this.file = new File(plugin.getDataFolder(), "inflation.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public void saveTrader(PointsTraderVillager trader) {
        String path = "traders." + trader.getId();

        for (Map.Entry<Integer, Product> entry : trader.materials.entrySet()) {
            Product product = entry.getValue();
            String productPath = path + "." + product.getDisplayedName();
            config.set(productPath + ".recent_sales", product.getRecentSales());
            config.set(productPath + ".total_sales", product.getAmountSold());
        }

        saveFile();
    }

    public void loadTrader(PointsTraderVillager trader) {
        String path = "traders." + trader.getId();
        ConfigurationSection section = config.getConfigurationSection(path);
        if (section == null) return;

        for (String productName : section.getKeys(false)) {
            int recent = config.getInt(path + "." + productName + ".recent_sales");
            int total = config.getInt(path + "." + productName + ".total_sales");
            trader.setSales(productName, total, recent);
        }
    }

    public void saveAllTraders(Map<String, PointsTraderVillager> traders) {
        for (PointsTraderVillager trader : traders.values()) {
            saveTrader(trader);
        }
    }

    public void loadAllTraders(Map<String, PointsTraderVillager> traders) {
        for (PointsTraderVillager trader : traders.values()) {
            loadTrader(trader);
        }
    }

    private void saveFile() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
