package fr.skyzoxs.main.Trader;

import fr.skyzoxs.main.Product.Product;
import org.bukkit.Material;

public class GardenVillager extends PointsTraderVillager {

    public static final int CHEAP_FLOWER_PRICE = 100;
    public static final int COMMON_VEGETABLE_PRICE = 200;
    public static final int UNCOMMON_VEGETABLE_PRICE = 300;
    public static final int RARE_VEGETABLE_PRICE = 400;

    public GardenVillager(String id, String name) {
        super(id, name, 54);

        // First line.
        this.materials.put(
                1,
                new Product(Material.DANDELION.name(), Material.DANDELION, GardenVillager.CHEAP_FLOWER_PRICE, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                2,
                new Product(Material.POPPY.name(), Material.POPPY, GardenVillager.CHEAP_FLOWER_PRICE, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                6,
                new Product(Material.BROWN_MUSHROOM.name(), Material.BROWN_MUSHROOM, GardenVillager.COMMON_VEGETABLE_PRICE, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                7,
                new Product(Material.RED_MUSHROOM.name(), Material.RED_MUSHROOM, GardenVillager.COMMON_VEGETABLE_PRICE, PointsTraderVillager.STACK, 0, 0)
        );

        // Second line.
        this.materials.put(
                11,
                new Product(Material.RED_TULIP.name(), Material.RED_TULIP, GardenVillager.UNCOMMON_VEGETABLE_PRICE, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                12,
                new Product(Material.ORANGE_TULIP.name(), Material.ORANGE_TULIP, GardenVillager.UNCOMMON_VEGETABLE_PRICE, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                13,
                new Product(Material.WHITE_TULIP.name(), Material.WHITE_TULIP, GardenVillager.UNCOMMON_VEGETABLE_PRICE, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                14,
                new Product(Material.PINK_TULIP.name(), Material.PINK_TULIP, GardenVillager.UNCOMMON_VEGETABLE_PRICE, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                15,
                new Product(Material.PINK_TULIP.name(), Material.PINK_TULIP, GardenVillager.UNCOMMON_VEGETABLE_PRICE, PointsTraderVillager.STACK, 0, 0)
        );

        // Third line.
        this.materials.put(
                19,
                new Product(Material.OXEYE_DAISY.name(), Material.OXEYE_DAISY, GardenVillager.UNCOMMON_VEGETABLE_PRICE, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                20,
                new Product(Material.BLUE_ORCHID.name(), Material.BLUE_ORCHID, GardenVillager.UNCOMMON_VEGETABLE_PRICE, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                21,
                new Product(Material.ALLIUM.name(), Material.ALLIUM, GardenVillager.UNCOMMON_VEGETABLE_PRICE, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                23,
                new Product(Material.LILY_OF_THE_VALLEY.name(), Material.LILY_OF_THE_VALLEY, GardenVillager.UNCOMMON_VEGETABLE_PRICE, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                24,
                new Product(Material.CORNFLOWER.name(), Material.CORNFLOWER, GardenVillager.UNCOMMON_VEGETABLE_PRICE, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                25,
                new Product(Material.AZURE_BLUET.name(), Material.AZURE_BLUET, GardenVillager.UNCOMMON_VEGETABLE_PRICE, PointsTraderVillager.STACK, 0, 0)
        );

        // Fourth line.
        this.materials.put(
                30,
                new Product(Material.MOSS_BLOCK.name(), Material.MOSS_BLOCK, GardenVillager.COMMON_VEGETABLE_PRICE, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                31,
                new Product(Material.SUGAR_CANE.name(), Material.SUGAR_CANE, GardenVillager.COMMON_VEGETABLE_PRICE, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                32,
                new Product(Material.CACTUS.name(), Material.CACTUS, GardenVillager.COMMON_VEGETABLE_PRICE, PointsTraderVillager.STACK, 0, 0)
        );

        // Fifth line.
        this.materials.put(
                40,
                new Product(Material.WITHER_ROSE.name(), Material.WITHER_ROSE, GardenVillager.UNCOMMON_VEGETABLE_PRICE, PointsTraderVillager.TINY_STACK, 0, 0)
        );

        // Sixth line.
        this.materials.put(
                48,
                new Product(Material.SPORE_BLOSSOM.name(), Material.SPORE_BLOSSOM, GardenVillager.RARE_VEGETABLE_PRICE, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                50,
                new Product(Material.FLOWERING_AZALEA.name(), Material.FLOWERING_AZALEA, GardenVillager.RARE_VEGETABLE_PRICE, PointsTraderVillager.TINY_STACK, 0, 0)
        );
    }
}
