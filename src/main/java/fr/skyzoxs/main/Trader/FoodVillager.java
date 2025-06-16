package fr.skyzoxs.main.Trader;

import fr.skyzoxs.main.Product.Product;
import org.bukkit.Material;


public class FoodVillager extends PointsTraderVillager {

    public static final int CHEAP_FOOD = 100;
    public static final int ALMOST_COMMON_FOOD = 150;
    public static final int COMMON_FOOD = 200;
    public static final int SUPER_RARE_FOOD = 500;
    public static final int PRICE_APPLE = 300;
    public static final int PRICE_GOLDEN_CARROT = 100 + (int) (ResourcesVillager.PRICE_GOLD_INGOT * 0.89) + 50;
    public static final int PRICE_GOLDEN_APPLE = (FoodVillager.PRICE_APPLE / 4) + (ResourcesVillager.PRICE_GOLD_INGOT * 2) + 50;
    public static final int PRICE_ENCHANTED_GOLDEN_APPLE = 5000;

    public FoodVillager(String id, String name) {
        super(id, name, 54);

        // First line.
        this.materials.put(
                1,
                new Product(Material.COOKED_CHICKEN.name(), Material.COOKED_CHICKEN, FoodVillager.COMMON_FOOD, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                2,
                new Product(Material.COOKED_MUTTON.name(), Material.COOKED_MUTTON, FoodVillager.COMMON_FOOD, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                4,
                new Product(Material.COOKED_SALMON.name(), Material.COOKED_SALMON, FoodVillager.COMMON_FOOD, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                6,
                new Product(Material.COOKED_PORKCHOP.name(), Material.COOKED_PORKCHOP, FoodVillager.COMMON_FOOD, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                7,
                new Product(Material.COOKED_RABBIT.name(), Material.COOKED_RABBIT, FoodVillager.SUPER_RARE_FOOD, PointsTraderVillager.STACK, 0, 0)
        );

        // Second line.
        this.materials.put(
                11,
                new Product(Material.MUSHROOM_STEW.name(), Material.MUSHROOM_STEW, FoodVillager.CHEAP_FOOD, PointsTraderVillager.UNIQUE, 0, 0)
        );
        this.materials.put(
                13,
                new Product(Material.RABBIT_STEW.name(), Material.RABBIT_STEW, FoodVillager.COMMON_FOOD, PointsTraderVillager.UNIQUE, 0, 0)
        );
        this.materials.put(
                15,
                new Product(Material.BEETROOT_SOUP.name(), Material.BEETROOT_SOUP, FoodVillager.ALMOST_COMMON_FOOD, PointsTraderVillager.UNIQUE, 0, 0)
        );

        // Third line.
        this.materials.put(
                21,
                new Product(Material.GOLDEN_APPLE.name(), Material.GOLDEN_APPLE, FoodVillager.PRICE_GOLDEN_APPLE, PointsTraderVillager.TINY_STACK, 0, 0)
        );
        this.materials.put(
                23,
                new Product(Material.GOLDEN_CARROT.name(), Material.GOLDEN_CARROT, FoodVillager.PRICE_GOLDEN_CARROT, PointsTraderVillager.STACK, 0, 0)
        );

        // Fourth line.
        this.materials.put(
                31,
                new Product(Material.ENCHANTED_GOLDEN_APPLE.name(), Material.ENCHANTED_GOLDEN_APPLE, FoodVillager.PRICE_ENCHANTED_GOLDEN_APPLE, PointsTraderVillager.UNIQUE, 0, 0)
        );

        // Fifth line.
        Product cookie = new Product(Material.COOKIE.name(), Material.COOKIE, FoodVillager.CHEAP_FOOD, PointsTraderVillager.STACK, 0, 0);
        cookie.addDescriptionLine("Les joueurs diabétiques ne peuvent pas échanger cet item.");
        this.materials.put(39, cookie);

        Product cake = new Product(Material.CAKE.name(), Material.CAKE, FoodVillager.CHEAP_FOOD, PointsTraderVillager.UNIQUE, 0, 0);
        cake.addDescriptionLine("Les joueurs diabétiques ne peuvent pas échanger cet item.");
        this.materials.put(41, cake);

        // Sixth line.
        this.materials.put(
                47,
                new Product(Material.PUMPKIN_PIE.name(), Material.PUMPKIN_PIE, FoodVillager.COMMON_FOOD, PointsTraderVillager.TINY_STACK, 0, 0)
        );
        this.materials.put(
                48,
                new Product(Material.APPLE.name(), Material.APPLE, FoodVillager.PRICE_APPLE, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                49,
                new Product(Material.CARROT.name(), Material.CARROT, 1, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                50,
                new Product(Material.BAKED_POTATO.name(), Material.BAKED_POTATO, FoodVillager.ALMOST_COMMON_FOOD, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                51,
                new Product(Material.MELON_SLICE.name(), Material.MELON_SLICE, FoodVillager.CHEAP_FOOD, PointsTraderVillager.STACK, 0, 0)
        );
    }

}
