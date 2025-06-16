package fr.skyzoxs.main.Trader;

import fr.skyzoxs.main.Product.Product;
import org.bukkit.Material;

public class WoodVillager extends PointsTraderVillager {

    public static final int CHEAP_LOG = 200;
    public static final int COMMON_LOG = 250;
    public static final int UNCOMMON_LOG = 300;
    public static final int LEAVES = 300;
    public static final int SAPLING = 100;

    public WoodVillager(String id, String name) {
        super(id, name, 27);

        // First line.
        this.materials.put(
                1,
                new Product(Material.OAK_LOG.name(), Material.OAK_LOG, WoodVillager.CHEAP_LOG, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                2,
                new Product(Material.SPRUCE_LOG.name(), Material.SPRUCE_LOG, WoodVillager.CHEAP_LOG, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                3,
                new Product(Material.JUNGLE_LOG.name(), Material.JUNGLE_LOG, WoodVillager.CHEAP_LOG, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                4,
                new Product(Material.ACACIA_LOG.name(), Material.ACACIA_LOG, WoodVillager.CHEAP_LOG, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                5,
                new Product(Material.DARK_OAK_LOG.name(), Material.DARK_OAK_LOG, WoodVillager.CHEAP_LOG, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                6,
                new Product(Material.BIRCH_LOG.name(), Material.BIRCH_LOG, WoodVillager.COMMON_LOG, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                7,
                new Product(Material.MANGROVE_LOG.name(), Material.MANGROVE_LOG, WoodVillager.UNCOMMON_LOG, PointsTraderVillager.STACK, 0, 0)
        );

        // Second line.
        this.materials.put(
                10,
                new Product(Material.CHERRY_LOG.name(), Material.CHERRY_LOG, WoodVillager.UNCOMMON_LOG, PointsTraderVillager.STACK, 0, 0)
        );
        Product sapling = new Product(Material.OAK_SAPLING.name(), Material.OAK_SAPLING, WoodVillager.SAPLING, PointsTraderVillager.STACK, 0, 0);
        sapling.addDescriptionLine("Tous les types de pousse sont acceptés.");
        this.materials.put(12, sapling);
        this.materials.put(
                14,
                new Product(Material.BAMBOO.name(), Material.BAMBOO, WoodVillager.SAPLING, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                16,
                new Product(Material.CHERRY_LEAVES.name(), Material.CHERRY_LEAVES, WoodVillager.UNCOMMON_LOG, PointsTraderVillager.STACK, 0, 0)
        );

        // Third line.
        this.materials.put(
                19,
                new Product(Material.OAK_LEAVES.name(), Material.OAK_LEAVES, WoodVillager.LEAVES, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                20,
                new Product(Material.SPRUCE_LEAVES.name(), Material.SPRUCE_LEAVES, WoodVillager.LEAVES, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                21,
                new Product(Material.JUNGLE_LEAVES.name(), Material.JUNGLE_LEAVES, WoodVillager.LEAVES, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                22,
                new Product(Material.ACACIA_LEAVES.name(), Material.ACACIA_LEAVES, WoodVillager.LEAVES, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                23,
                new Product(Material.DARK_OAK_LEAVES.name(), Material.DARK_OAK_LEAVES, WoodVillager.LEAVES, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                24,
                new Product(Material.BIRCH_LEAVES.name(), Material.BIRCH_LEAVES, WoodVillager.LEAVES, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                25,
                new Product(Material.MANGROVE_LEAVES.name(), Material.MANGROVE_LEAVES, WoodVillager.LEAVES, PointsTraderVillager.STACK, 0, 0)
        );
    }


}
