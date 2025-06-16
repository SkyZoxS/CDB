package fr.skyzoxs.main.Trader;

import fr.skyzoxs.main.Product.Product;
import org.bukkit.Material;

public class EndVillager extends PointsTraderVillager {

    public static final int COMMON_ITEMS = 200;
    public static final int ALMOST_UNCOMMON_ITEMS = 250;
    public static final int UNCOMMON_ITEMS = 300;
    public static final int SUPER_RARE_ITEMS = 500;
    public static final int PRICE_CRYING_OBSIDIAN = 500;

    public EndVillager(String id, String name) {
        super(id, name, 54);

        // First line.
        this.materials.put(
                11,
                new Product(Material.END_STONE_BRICKS.name(), Material.END_STONE_BRICKS, EndVillager.COMMON_ITEMS, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                13,
                new Product(Material.OBSIDIAN.name(), Material.OBSIDIAN, EndVillager.UNCOMMON_ITEMS, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                15,
                new Product(Material.PURPUR_BLOCK.name(), Material.PURPUR_BLOCK, EndVillager.COMMON_ITEMS, PointsTraderVillager.STACK, 0, 0)
        );

        // Second line.
        this.materials.put(
                21,
                new Product(Material.END_CRYSTAL.name(), Material.END_CRYSTAL, EndVillager.SUPER_RARE_ITEMS, PointsTraderVillager.UNIQUE, 0, 0)
        );
        this.materials.put(
                23,
                new Product(Material.DRAGON_BREATH.name(), Material.DRAGON_BREATH, EndVillager.SUPER_RARE_ITEMS, EndVillager.TINY_STACK, 0, 0)
        );

        // Third line.
        this.materials.put(
                30,
                new Product(Material.CHORUS_FRUIT.name(), Material.CHORUS_FRUIT, EndVillager.ALMOST_UNCOMMON_ITEMS, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                32,
                new Product(Material.ENDER_EYE.name(), Material.ENDER_EYE, EndVillager.COMMON_ITEMS, PointsTraderVillager.STACK, 0, 0)
        );

        // Fourth line.
        this.materials.put(
                39,
                new Product(Material.END_ROD.name(), Material.END_ROD, EndVillager.COMMON_ITEMS, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                41,
                new Product(Material.CRYING_OBSIDIAN.name(), Material.CRYING_OBSIDIAN, EndVillager.PRICE_CRYING_OBSIDIAN, PointsTraderVillager.UNIQUE, 0, 0)
        );
    }
}
