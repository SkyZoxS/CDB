package fr.skyzoxs.main.Trader;

import fr.skyzoxs.main.Product.Product;
import org.bukkit.Material;

public class NetherVillager extends PointsTraderVillager {

    public static final int COMMON_BLOCK = 200;
    public static final int UNCOMMON_BLOCK = 300;
    public static final int RARE_BLOCK = 400;

    public NetherVillager(String id, String name) {
        super(id, name, 54);

        // First line.
        this.materials.put(
                2,
                new Product(Material.BASALT.name(), Material.BASALT, NetherVillager.COMMON_BLOCK, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                4,
                new Product(Material.NETHER_BRICK.name(), Material.NETHER_BRICK, NetherVillager.COMMON_BLOCK, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                6,
                new Product(Material.BLACKSTONE.name(), Material.BLACKSTONE, NetherVillager.COMMON_BLOCK, PointsTraderVillager.STACK, 0, 0)
        );

        // Second line.
        this.materials.put(
                12,
                new Product(Material.SOUL_SAND.name(), Material.SOUL_SAND, NetherVillager.COMMON_BLOCK, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                14,
                new Product(Material.SOUL_SOIL.name(), Material.SOUL_SOIL, NetherVillager.COMMON_BLOCK, PointsTraderVillager.STACK, 0, 0)
        );

        // Third line.
        this.materials.put(
                22,
                new Product(Material.MAGMA_BLOCK.name(), Material.MAGMA_BLOCK, NetherVillager.RARE_BLOCK, PointsTraderVillager.STACK, 0, 0)
        );

        // Fourth line.
        this.materials.put(
                29,
                new Product(Material.CRIMSON_NYLIUM.name(), Material.CRIMSON_NYLIUM, NetherVillager.COMMON_BLOCK, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                30,
                new Product(Material.CRIMSON_STEM.name(), Material.CRIMSON_STEM, NetherVillager.COMMON_BLOCK, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                32,
                new Product(Material.WARPED_NYLIUM.name(), Material.WARPED_NYLIUM, NetherVillager.COMMON_BLOCK, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                33,
                new Product(Material.WARPED_STEM.name(), Material.WARPED_STEM, NetherVillager.COMMON_BLOCK, PointsTraderVillager.STACK, 0, 0)
        );

        // Fifth line.
        this.materials.put(
                40,
                new Product(Material.GLOWSTONE.name(), Material.GLOWSTONE, NetherVillager.UNCOMMON_BLOCK, PointsTraderVillager.STACK, 0, 0)
        );
    }
}
