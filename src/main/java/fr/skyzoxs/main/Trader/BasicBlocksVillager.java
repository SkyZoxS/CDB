package fr.skyzoxs.main.Trader;

import fr.skyzoxs.main.Product.Product;
import org.bukkit.Material;

public class BasicBlocksVillager extends PointsTraderVillager {

    public static final int DUPLICABLE = 100;
    public static final int NON_SILK_TOUCH = 300;
    public static final int SILK_TOUCH = 500;

    public BasicBlocksVillager(String id, String name) {
        super(id, name, 54);

        // First line.
        this.materials.put(
                1,
                new Product(Material.STONE.name(), Material.STONE, BasicBlocksVillager.DUPLICABLE, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                2,
                new Product(Material.TUFF.name(), Material.TUFF, BasicBlocksVillager.NON_SILK_TOUCH, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                3,
                new Product(Material.DEEPSLATE.name(), Material.DEEPSLATE, BasicBlocksVillager.SILK_TOUCH, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                5,
                new Product(Material.GRANITE.name(), Material.GRANITE, BasicBlocksVillager.NON_SILK_TOUCH, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                6,
                new Product(Material.DIORITE.name(), Material.DIORITE, BasicBlocksVillager.NON_SILK_TOUCH, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                7,
                new Product(Material.ANDESITE.name(), Material.ANDESITE, BasicBlocksVillager.NON_SILK_TOUCH, PointsTraderVillager.STACK, 0, 0)
        );

        // Second line.
        this.materials.put(
                12,
                new Product(Material.GRASS_BLOCK.name(), Material.GRASS_BLOCK, BasicBlocksVillager.SILK_TOUCH, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                13,
                new Product(Material.MYCELIUM.name(), Material.MYCELIUM, BasicBlocksVillager.SILK_TOUCH, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                14,
                new Product(Material.PODZOL.name(), Material.PODZOL, BasicBlocksVillager.SILK_TOUCH, PointsTraderVillager.STACK, 0, 0)
        );

        // Third line
        this.materials.put(
                19,
                new Product(Material.SAND.name(), Material.SAND,  BasicBlocksVillager.DUPLICABLE, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                20,
                new Product(Material.SANDSTONE.name(), Material.SANDSTONE,  BasicBlocksVillager.DUPLICABLE, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                22,
                new Product(Material.GRAVEL.name(), Material.GRAVEL,  BasicBlocksVillager.DUPLICABLE, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                24,
                new Product(Material.RED_SAND.name(), Material.RED_SAND,  BasicBlocksVillager.DUPLICABLE, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                25,
                new Product(Material.RED_SANDSTONE.name(), Material.RED_SANDSTONE,  BasicBlocksVillager.DUPLICABLE, PointsTraderVillager.STACK, 0, 0)
        );

        // Fourth line.
        this.materials.put(
                30,
                new Product(Material.CLAY.name(), Material.CLAY, BasicBlocksVillager.NON_SILK_TOUCH, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                32,
                new Product(Material.MUD.name(), Material.MUD, BasicBlocksVillager.NON_SILK_TOUCH, PointsTraderVillager.STACK, 0, 0)
        );

        // Fifth line.
        this.materials.put(
                38,
                new Product(Material.SNOW_BLOCK.name(), Material.SNOW_BLOCK,  BasicBlocksVillager.DUPLICABLE, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                39,
                new Product(Material.PACKED_ICE.name(), Material.PACKED_ICE, BasicBlocksVillager.SILK_TOUCH, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                41,
                new Product(Material.PRISMARINE.name(), Material.PRISMARINE, BasicBlocksVillager.DUPLICABLE, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                42,
                new Product(Material.AMETHYST_BLOCK.name(), Material.AMETHYST_BLOCK, BasicBlocksVillager.NON_SILK_TOUCH, PointsTraderVillager.STACK, 0, 0)
        );

        // Sixth line.
        this.materials.put(
                49,
                new Product(Material.CALCITE.name(), Material.CALCITE, BasicBlocksVillager.NON_SILK_TOUCH, PointsTraderVillager.STACK, 0, 0)
        );
    }


}