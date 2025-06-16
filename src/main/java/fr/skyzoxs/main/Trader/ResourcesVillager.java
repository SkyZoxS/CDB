package fr.skyzoxs.main.Trader;

import fr.skyzoxs.main.Product.Product;
import org.bukkit.Material;

public class ResourcesVillager extends PointsTraderVillager {

    public static final int PRICE_COPPER_INGOT = 300;
    public static final int PRICE_COPPER_ORE = PRICE_COPPER_INGOT * 8;
    public static final int PRICE_IRON_INGOT = 200;
    public static final int PRICE_IRON_ORE = PRICE_IRON_INGOT * (4 + 1);
    public static final int PRICE_EMERALD_INGOT = 100;
    public static final int PRICE_EMERALD_ORE = 2500;
    public static final int PRICE_GOLD_INGOT = 200;
    public static final int PRICE_GOLD_ORE = PRICE_GOLD_INGOT * (4 + 3);
    public static final int PRICE_REDSTONE_INGOT = 300;
    public static final int PRICE_REDSTONE_ORE = PRICE_REDSTONE_INGOT * 6;
    public static final int PRICE_LAPIS_INGOT = 100;
    public static final int PRICE_LAPIS_ORE = PRICE_LAPIS_INGOT * 15;
    public static final int PRICE_DIAMOND_INGOT = 750;
    public static final int PRICE_DIAMOND_ORE = PRICE_DIAMOND_INGOT * 4;
    public static final int PRICE_NETHERITE_INGOT = 5000;

    public ResourcesVillager(String id, String name) {
        super(id, name, 27);

        // First line.
        this.materials.put(
                1,
                new Product(Material.COPPER_INGOT.name(), Material.COPPER_INGOT, ResourcesVillager.PRICE_COPPER_INGOT, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                2,
                new Product(Material.IRON_INGOT.name(), Material.IRON_INGOT, ResourcesVillager.PRICE_IRON_INGOT, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                3,
                new Product(Material.GOLD_INGOT.name(), Material.GOLD_INGOT, ResourcesVillager.PRICE_GOLD_INGOT, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                4,
                new Product(Material.EMERALD.name(), Material.EMERALD, ResourcesVillager.PRICE_EMERALD_INGOT, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                5,
                new Product(Material.REDSTONE.name(), Material.REDSTONE, ResourcesVillager.PRICE_REDSTONE_INGOT, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                6,
                new Product(Material.LAPIS_LAZULI.name(), Material.LAPIS_LAZULI, ResourcesVillager.PRICE_LAPIS_INGOT, PointsTraderVillager.STACK, 0, 0)
        );

        this.materials.put(
                7,
                new Product(Material.DIAMOND.name(), Material.DIAMOND, ResourcesVillager.PRICE_DIAMOND_INGOT, PointsTraderVillager.TINY_STACK, 0, 0)
        );

        // Second line.
        this.materials.put(
                13,
                new Product(Material.NETHERITE_INGOT.name(), Material.NETHERITE_INGOT, ResourcesVillager.PRICE_NETHERITE_INGOT, PointsTraderVillager.TINY_STACK, 0, 0)
        );

        // Third line.
        Product copper_ore =new Product(Material.COPPER_ORE.name(), Material.COPPER_ORE, ResourcesVillager.PRICE_COPPER_ORE, PointsTraderVillager.STACK, 0, 0);
        copper_ore.addAnAcceptedMaterial(Material.DEEPSLATE_COPPER_ORE);
        copper_ore.addDescriptionLine("Les versions deepslate sont acceptées.");
        this.materials.put(19, copper_ore);

        Product iron_ore = new Product(Material.IRON_ORE.name(), Material.IRON_ORE, ResourcesVillager.PRICE_IRON_ORE, PointsTraderVillager.STACK, 0, 0);
        iron_ore.addAnAcceptedMaterial(Material.DEEPSLATE_IRON_ORE);
        iron_ore.addDescriptionLine("Les versions deepslate sont acceptées.");
        this.materials.put(20, iron_ore);

        Product gold_ore = new Product(Material.GOLD_ORE.name(), Material.GOLD_ORE, ResourcesVillager.PRICE_GOLD_ORE, PointsTraderVillager.STACK, 0, 0);
        gold_ore.addAnAcceptedMaterial(Material.DEEPSLATE_GOLD_ORE);
        gold_ore.addDescriptionLine("Les versions deepslate sont acceptées.");
        this.materials.put(21, gold_ore);

        Product emerald_ore = new Product(Material.EMERALD_ORE.name(), Material.EMERALD_ORE, ResourcesVillager.PRICE_EMERALD_ORE, PointsTraderVillager.TINY_STACK, 0, 0);
        emerald_ore.addAnAcceptedMaterial(Material.DEEPSLATE_EMERALD_ORE);
        emerald_ore.addDescriptionLine("Les versions deepslate sont acceptées.");
        this.materials.put(22, emerald_ore);

        Product redstone_ore = new Product(Material.REDSTONE_ORE.name(), Material.REDSTONE_ORE, ResourcesVillager.PRICE_REDSTONE_ORE, PointsTraderVillager.STACK, 0, 0);
        redstone_ore.addAnAcceptedMaterial(Material.DEEPSLATE_REDSTONE_ORE);
        redstone_ore.addDescriptionLine("Les versions deepslate sont acceptées.");
        this.materials.put(23, redstone_ore);

        Product lapis_ore = new Product(Material.LAPIS_ORE.name(), Material.LAPIS_ORE, ResourcesVillager.PRICE_LAPIS_ORE, PointsTraderVillager.STACK, 0, 0);
        lapis_ore.addAnAcceptedMaterial(Material.DEEPSLATE_LAPIS_ORE);
        lapis_ore.addDescriptionLine("Les versions deepslate sont acceptées.");
        this.materials.put(24, lapis_ore);

        Product diamond_ore =new Product(Material.DIAMOND_ORE.name(), Material.DIAMOND_ORE, ResourcesVillager.PRICE_DIAMOND_ORE, PointsTraderVillager.TINY_STACK, 0, 0);
        diamond_ore.addAnAcceptedMaterial(Material.DEEPSLATE_DIAMOND_ORE);
        diamond_ore.addDescriptionLine("Les versions deepslate sont acceptées.");
        this.materials.put(25, diamond_ore);
    }
}
