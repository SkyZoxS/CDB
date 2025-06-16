package fr.skyzoxs.main.Trader;

import fr.skyzoxs.main.Product.Product;
import org.bukkit.Material;


public class DecoratorVillager extends PointsTraderVillager {

    private static final int SMALL_PRICE = 300;
    private static final int MIDDLE_PRICE = 350;
    private static final int GOOD_PRICE = 400;
    private static final int BEST_PRICE = 600;

    public DecoratorVillager(String id, String name) {
        super(id, name, 54);

        // 2e ligne.
        this.materials.put(
                11, new Product(Material.SEA_LANTERN.name(), Material.SEA_LANTERN, DecoratorVillager.BEST_PRICE, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                12, new Product(Material.OCHRE_FROGLIGHT.name(), Material.OCHRE_FROGLIGHT, DecoratorVillager.GOOD_PRICE, PointsTraderVillager.STACK, 0,0)
        );
        this.materials.put(
                14, new Product(Material.PEARLESCENT_FROGLIGHT.name(), Material.PEARLESCENT_FROGLIGHT, DecoratorVillager.GOOD_PRICE, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                15, new Product(Material.VERDANT_FROGLIGHT.name(), Material.VERDANT_FROGLIGHT, DecoratorVillager.GOOD_PRICE, PointsTraderVillager.STACK, 0, 0)
        );

        // 3e ligne.
        Product candle = new Product(Material.WHITE_CANDLE.name(), Material.WHITE_CANDLE, DecoratorVillager.GOOD_PRICE, PointsTraderVillager.STACK, 0, 0);
        candle.addAnAcceptedMaterial(Material.CANDLE);
        candle.addAnAcceptedMaterial(Material.LIGHT_GRAY_CANDLE);
        candle.addAnAcceptedMaterial(Material.GRAY_CANDLE);
        candle.addAnAcceptedMaterial(Material.BLACK_CANDLE);
        candle.addAnAcceptedMaterial(Material.BROWN_CANDLE);
        candle.addAnAcceptedMaterial(Material.RED_CANDLE);
        candle.addAnAcceptedMaterial(Material.ORANGE_CANDLE);
        candle.addAnAcceptedMaterial(Material.YELLOW_CANDLE);
        candle.addAnAcceptedMaterial(Material.LIME_CANDLE);
        candle.addAnAcceptedMaterial(Material.GREEN_CANDLE);
        candle.addAnAcceptedMaterial(Material.CYAN_CANDLE);
        candle.addAnAcceptedMaterial(Material.LIGHT_BLUE_CANDLE);
        candle.addAnAcceptedMaterial(Material.BLUE_CANDLE);
        candle.addAnAcceptedMaterial(Material.PURPLE_CANDLE);
        candle.addAnAcceptedMaterial(Material.MAGENTA_CANDLE);
        candle.addAnAcceptedMaterial(Material.PINK_CANDLE);
        candle.addDescriptionAllColorsAreAccepted();
        this.materials.put(22, candle);

        // 4e ligne.
        this.materials.put(
                30, new Product(Material.LANTERN.name(), Material.LANTERN, DecoratorVillager.MIDDLE_PRICE, PointsTraderVillager.STACK, 0, 0)
        );
        this.materials.put(
                32, new Product(Material.SOUL_LANTERN.name(), Material.SOUL_LANTERN, DecoratorVillager.MIDDLE_PRICE, PointsTraderVillager.STACK, 0, 0)
        );

        // 5e ligne.
        Product glass_pane = new Product(
                Material.GLASS_PANE.name(), Material.GLASS_PANE,
                DecoratorVillager.SMALL_PRICE, PointsTraderVillager.STACK, 0, 0
        );
        glass_pane.addAnAcceptedMaterial(Material.WHITE_STAINED_GLASS_PANE);
        glass_pane.addAnAcceptedMaterial(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        glass_pane.addAnAcceptedMaterial(Material.GRAY_STAINED_GLASS_PANE);
        glass_pane.addAnAcceptedMaterial(Material.BLACK_STAINED_GLASS_PANE);
        glass_pane.addAnAcceptedMaterial(Material.BROWN_STAINED_GLASS_PANE);
        glass_pane.addAnAcceptedMaterial(Material.RED_STAINED_GLASS_PANE);
        glass_pane.addAnAcceptedMaterial(Material.ORANGE_STAINED_GLASS_PANE);
        glass_pane.addAnAcceptedMaterial(Material.YELLOW_STAINED_GLASS_PANE);
        glass_pane.addAnAcceptedMaterial(Material.LIME_STAINED_GLASS_PANE);
        glass_pane.addAnAcceptedMaterial(Material.GREEN_STAINED_GLASS_PANE);
        glass_pane.addAnAcceptedMaterial(Material.CYAN_STAINED_GLASS_PANE);
        glass_pane.addAnAcceptedMaterial(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
        glass_pane.addAnAcceptedMaterial(Material.BLUE_STAINED_GLASS_PANE);
        glass_pane.addAnAcceptedMaterial(Material.PURPLE_STAINED_GLASS_PANE);
        glass_pane.addAnAcceptedMaterial(Material.MAGENTA_STAINED_GLASS_PANE);
        glass_pane.addAnAcceptedMaterial(Material.PINK_STAINED_GLASS_PANE);
        glass_pane.addDescriptionAllColorsAreAccepted();
        this.materials.put(38, glass_pane);

        Product glass = new Product(
                Material.GLASS.name(), Material.GLASS,
                DecoratorVillager.SMALL_PRICE, PointsTraderVillager.STACK, 0, 0
        );
        glass.addAnAcceptedMaterial(Material.WHITE_STAINED_GLASS);
        glass.addAnAcceptedMaterial(Material.LIGHT_GRAY_STAINED_GLASS);
        glass.addAnAcceptedMaterial(Material.GRAY_STAINED_GLASS);
        glass.addAnAcceptedMaterial(Material.BLACK_STAINED_GLASS);
        glass.addAnAcceptedMaterial(Material.BROWN_STAINED_GLASS);
        glass.addAnAcceptedMaterial(Material.RED_STAINED_GLASS);
        glass.addAnAcceptedMaterial(Material.ORANGE_STAINED_GLASS);
        glass.addAnAcceptedMaterial(Material.YELLOW_STAINED_GLASS);
        glass.addAnAcceptedMaterial(Material.LIME_STAINED_GLASS);
        glass.addAnAcceptedMaterial(Material.GREEN_STAINED_GLASS);
        glass.addAnAcceptedMaterial(Material.CYAN_STAINED_GLASS);
        glass.addAnAcceptedMaterial(Material.LIGHT_BLUE_STAINED_GLASS);
        glass.addAnAcceptedMaterial(Material.BLUE_STAINED_GLASS);
        glass.addAnAcceptedMaterial(Material.PURPLE_STAINED_GLASS);
        glass.addAnAcceptedMaterial(Material.MAGENTA_STAINED_GLASS);
        glass.addAnAcceptedMaterial(Material.PINK_STAINED_GLASS);
        glass.addDescriptionAllColorsAreAccepted();
        this.materials.put(39, glass);

        Product concrete = new Product(
                Material.WHITE_CONCRETE.name(), Material.WHITE_CONCRETE,
                DecoratorVillager.SMALL_PRICE, PointsTraderVillager.STACK, 0, 0
        );
        concrete.addAnAcceptedMaterial(Material.LIGHT_GRAY_CONCRETE);
        concrete.addAnAcceptedMaterial(Material.GRAY_CONCRETE);
        concrete.addAnAcceptedMaterial(Material.BLACK_CONCRETE);
        concrete.addAnAcceptedMaterial(Material.BROWN_CONCRETE);
        concrete.addAnAcceptedMaterial(Material.RED_CONCRETE);
        concrete.addAnAcceptedMaterial(Material.ORANGE_CONCRETE);
        concrete.addAnAcceptedMaterial(Material.YELLOW_CONCRETE);
        concrete.addAnAcceptedMaterial(Material.LIME_CONCRETE);
        concrete.addAnAcceptedMaterial(Material.GREEN_CONCRETE);
        concrete.addAnAcceptedMaterial(Material.CYAN_CONCRETE);
        concrete.addAnAcceptedMaterial(Material.LIGHT_BLUE_CONCRETE);
        concrete.addAnAcceptedMaterial(Material.BLUE_CONCRETE);
        concrete.addAnAcceptedMaterial(Material.PURPLE_CONCRETE);
        concrete.addAnAcceptedMaterial(Material.MAGENTA_CONCRETE);
        concrete.addAnAcceptedMaterial(Material.PINK_CONCRETE);
        concrete.addDescriptionAllColorsAreAccepted();
        this.materials.put(41, concrete);

        Product terracotta = new Product(
                Material.WHITE_TERRACOTTA.name(), Material.WHITE_TERRACOTTA,
                DecoratorVillager.GOOD_PRICE, PointsTraderVillager.STACK, 0, 0
        );
        terracotta.addAnAcceptedMaterial(Material.LIGHT_GRAY_TERRACOTTA);
        terracotta.addAnAcceptedMaterial(Material.GRAY_TERRACOTTA);
        terracotta.addAnAcceptedMaterial(Material.BLACK_TERRACOTTA);
        terracotta.addAnAcceptedMaterial(Material.BROWN_TERRACOTTA);
        terracotta.addAnAcceptedMaterial(Material.RED_TERRACOTTA);
        terracotta.addAnAcceptedMaterial(Material.ORANGE_TERRACOTTA);
        terracotta.addAnAcceptedMaterial(Material.YELLOW_TERRACOTTA);
        terracotta.addAnAcceptedMaterial(Material.LIME_TERRACOTTA);
        terracotta.addAnAcceptedMaterial(Material.GREEN_TERRACOTTA);
        terracotta.addAnAcceptedMaterial(Material.CYAN_TERRACOTTA);
        terracotta.addAnAcceptedMaterial(Material.LIGHT_BLUE_TERRACOTTA);
        terracotta.addAnAcceptedMaterial(Material.BLUE_TERRACOTTA);
        terracotta.addAnAcceptedMaterial(Material.PURPLE_TERRACOTTA);
        terracotta.addAnAcceptedMaterial(Material.MAGENTA_TERRACOTTA);
        terracotta.addAnAcceptedMaterial(Material.PINK_TERRACOTTA);
        terracotta.addDescriptionAllColorsAreAccepted();
        this.materials.put(42, terracotta);
    }
}