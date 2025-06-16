package fr.skyzoxs.main.Product;

import fr.skyzoxs.main.Points.DescriptionItemPoints;
import fr.skyzoxs.main.Points.PointsV;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;


public class Product {

    private final String displayed_name;
    private final ArrayList<Material> accepted_materials;
    private final PointsV points;
    private int amount_sold;
    private final DescriptionItemPoints description;

    public Product(String displayed_name, Material material, int points_given, int requested_amount, int recent_sell, int amount_sold) {
        this.displayed_name = displayed_name;
        this.accepted_materials = new ArrayList<>();
        this.accepted_materials.add(material);
        this.points = new PointsV(points_given, requested_amount, recent_sell);
        this.amount_sold = amount_sold;
        this.description = new DescriptionItemPoints(
                this.displayed_name,
                this.points.getRequestedAmount(),
                this.points.getInitialPointsGiven(),
                this.points.getItemsSalesInCurrentLevel(),
                this.points.getItemsPerInflationLevel()
        );
    }

    public Product(String displayed_name, Material material, int points_given, int requested_amount, int recent_sell, int amount_sold, ArrayList<String> description) {
        this(displayed_name, material, points_given, requested_amount, recent_sell, amount_sold);
        for (String string : description) {
            this.description.addLine(string);
        }
    }

    public Product(String displayed_name, Material material, int points_given, int requested_amount, int recent_sell) {
        this(displayed_name, material, points_given, requested_amount, recent_sell, 0);
    }

    public Product(String displayed_name, Material material, int points_given, int requested_amount) {
        this(displayed_name, material, points_given, requested_amount, 0, 0);
    }

    public String getDisplayedName() {
        return this.displayed_name;
    }

    public ArrayList<Material> getAcceptedMaterials() {
        return this.accepted_materials;
    }

    public int getAmountSold() {
        return this.amount_sold;
    }

    public void setAmountSold(int amount) {
        this.amount_sold = amount;
    }

    public int getRecentSales() {
        return this.points.getRecentSales();
    }

    public DescriptionItemPoints getDescription() {
        return this.description;
    }

    public ArrayList<String> getLore() {
        return this.description.getText();
    }

    public void addAnAcceptedMaterial(Material material) {
        this.accepted_materials.add(material);
    }

    public void updateDescription() {
        this.description.updateLinePointsGiven(this.getpointsGiven());
        if (this.points.isMaxInflationLevel()) {
            this.description.updateLineInflationMax(
                    this.points.getPercentModifier(),
                    this.points.getRecentSales()
            );
        }
        else {
            this.description.updateLineInflation(
                    this.points.getPercentModifier(),
                    this.points.getItemsSalesInCurrentLevel()
            );
        }
    }

    public int getpointsGiven() {
        return this.points.getModifiedPointsGiven();
    }

    public int getRequestedAmount() {
        return this.points.getRequestedAmount();
    }

    public void setRecentSales(int recent_sales) {
        this.points.setRecentSales(recent_sales);
        this.updateDescription();
    }

    public void addDescriptionLine(String line) {
        this.description.addLine(line);
    }

    public void addDescriptionAllColorsAreAccepted() {
        this.description.addLineColorsAccepted();
    }

    public void madeASales() {
        this.amount_sold += this.points.getRequestedAmount();
        this.points.madeASales();
        this.updateDescription();
    }

    public void forgerSomeRecentSales() {
        this.points.forgerSomeRecentSales();
        this.updateDescription();
    }

    public boolean isSameText(ArrayList<String> other) {
        return this.description.isSameText(other);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Product other = (Product) obj;
        return (
                this.displayed_name.equals(other.getDisplayedName())
                && this.accepted_materials.equals(other.getAcceptedMaterials())
                && this.description.equals(other.getDescription())
        );
    }

    public ItemStack toItemStack() {
        ItemStack item = new ItemStack(this.getAcceptedMaterials().get(0));
        item.setAmount(this.getRequestedAmount());
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(this.getDisplayedName());
        meta.setLore(this.getLore());
        item.setItemMeta(meta);
        return item;
    }
}
