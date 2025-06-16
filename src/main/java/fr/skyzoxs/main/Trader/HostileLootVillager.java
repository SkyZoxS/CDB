package fr.skyzoxs.main.Trader;

import fr.skyzoxs.main.Product.Product;
import org.bukkit.Material;

public class HostileLootVillager extends PointsTraderVillager {

    public final static int CHEAP_LOOT = 100;
    public final static int ALMOST_COMMON_LOOT = 150;
    public final static int COMMON_LOOT = 200;
    public final static int SUPER_RARE_LOOT = 1000;

    public HostileLootVillager(String id, String name) {
        super(id, name, 54);

        // First line.
        this.materials.put(
                1,
                new Product(Material.ARROW.name(), Material.ARROW, HostileLootVillager.CHEAP_LOOT, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                3,
                new Product(Material.ROTTEN_FLESH.name(), Material.ROTTEN_FLESH, HostileLootVillager.CHEAP_LOOT, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                4,
                new Product(Material.GUNPOWDER.name(), Material.GUNPOWDER, HostileLootVillager.CHEAP_LOOT, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                5,
                new Product(Material.SPIDER_EYE.name(), Material.SPIDER_EYE, HostileLootVillager.CHEAP_LOOT, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                7,
                new Product(Material.BONE.name(), Material.BONE, HostileLootVillager.CHEAP_LOOT, PointsTraderVillager.STACK, 0, 0)
        );

        // Second line.
        this.materials.put(
                11,
                new Product(Material.SLIME_BALL.name(), Material.SLIME_BALL, HostileLootVillager.CHEAP_LOOT, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                13,
                new Product(Material.ENDER_PEARL.name(), Material.ENDER_PEARL, HostileLootVillager.ALMOST_COMMON_LOOT, PointsTraderVillager.TINY_STACK, 0, 0)
        );
        this.materials.put(
                15,
                new Product(Material.PHANTOM_MEMBRANE.name(), Material.PHANTOM_MEMBRANE, HostileLootVillager.COMMON_LOOT, PointsTraderVillager.TINY_STACK, 0, 0)
        );

        // Third line.
        this.materials.put(
                21,
                new Product(Material.MAGMA_CREAM.name(), Material.MAGMA_CREAM, HostileLootVillager.CHEAP_LOOT, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                23,
                new Product(Material.BLAZE_ROD.name(), Material.BLAZE_ROD, HostileLootVillager.ALMOST_COMMON_LOOT, PointsTraderVillager.STACK, 0, 0)
        );

        // Fourth line.
        this.materials.put(
                31,
                new Product(Material.TOTEM_OF_UNDYING.name(), Material.TOTEM_OF_UNDYING, HostileLootVillager.COMMON_LOOT, PointsTraderVillager.UNIQUE, 0, 0)
        );

        // Fifth line
        this.materials.put(
                40,
                new Product(Material.GHAST_TEAR.name(), Material.GHAST_TEAR, HostileLootVillager.COMMON_LOOT, PointsTraderVillager.UNIQUE, 0, 0)
        );

        // Sixth line.
        this.materials.put(
                49,
                new Product(Material.NETHER_STAR.name(), Material.NETHER_STAR, HostileLootVillager.SUPER_RARE_LOOT, PointsTraderVillager.UNIQUE, 0, 0)
        );
    }
}
