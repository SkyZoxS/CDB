package fr.skyzoxs.main.Points;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DescriptionItemPoints {

    private static final int ID_DESCRIPTION_PRICE = 0;
    private static final int ID_DESCRIPTION_INFLATION = 1;
    private final String item_name;
    private final int requested_amount;
    private final int max_sales_per_lvl;
    private final ArrayList<String> text;

    public DescriptionItemPoints(String item_name, int requested_amount, int points_given, int current_sales, int max_sales_per_lvl) {
        this.item_name = item_name;
        this.requested_amount = requested_amount;
        this.max_sales_per_lvl = max_sales_per_lvl;

        this.text = new ArrayList<>();
        this.text.add(
                String.format(
                        "§6%d de %s pour %d de point.",
                        this.requested_amount,
                        this.item_name,
                        points_given
                )
        );
        this.text.add(
                String.format(
                        "§6Aucune inflation (%d/%d).",
                        current_sales,
                        this.max_sales_per_lvl
                )
        );
    }

    public ArrayList<String> getText() {
        return this.text;
    }

    public void addLine(String line) {
        this.text.add(line);
    }

    public void addLineColorsAccepted() {
        this.text.add("Toutes les couleurs sont acceptés.");
    }

    @Contract(pure = true)
    private static @NotNull String getFormatPointPrice() {
        return "§6%d de %s pour %d de point.";
    }

    private static String getRegexPointPrice(int fixed_amount, String product_name) {
        return String.format(
                "§6%d de %s pour (0|[1-9][0-9]*) de point.",
                fixed_amount,
                product_name
        );
    }

    @Contract(pure = true)
    private static @NotNull String getFormatNoInflation() {
        return "§6Aucune inflation (%d/%d).";
    }

    @Contract(pure = true)
    private static @NotNull String getRegexNoInflation() {
        return "§6Aucune inflation \\((0|[1-9][0-9]*)/(0|[1-9][0-9]*)\\).";
    }

    @Contract(pure = true)
    private static @NotNull String getFormatReduction() {
        return "§6Prix réduit de %.1f%% (%d/%d).";
    }

    @Contract(pure = true)
    private static @NotNull String getFormatAugmentation() {
        return "§6Prix augmenté de %.1f%% (%d/%d).";
    }

    @Contract(pure = true)
    private static @NotNull String getRegexPercentModification() {
        return "§6Prix (réduit|augmenté) de -?[1-9][0-9]*[.,][0-9]+% \\((0|[1-9][0-9]*)/(0|[1-9][0-9]*)\\).";
    }

    @Contract(pure = true)
    private static @NotNull String getFormatMaxInflation() {
        return "§6Inflation maximum %.1f%% (%d de surplus).";
    }

    @Contract(pure = true)
    private static @NotNull String getRegexMaxInflation() {
        return "§6Inflation maximum -?[1-9][0-9]*[.,][0-9]+% \\((0|[1-9][0-9]*) de surplus\\).";
    }

    public void updateLinePointsGiven(int reputation_given) {
        if (reputation_given <= 0) {
            throw new IllegalArgumentException("The given points cannot be less than or equal to 0.");
        }
        this.text.set(DescriptionItemPoints.ID_DESCRIPTION_PRICE,
                String.format(
                        DescriptionItemPoints.getFormatPointPrice(),
                        this.requested_amount,
                        this.item_name,
                        reputation_given
                )
        );
    }

    public void updateLineInflation(int current_sales) {
        if (current_sales > this.max_sales_per_lvl) {
            throw new IllegalArgumentException("The current number of sales cannot be greater than the maximum number of sales.");
        }
        this.text.set(DescriptionItemPoints.ID_DESCRIPTION_INFLATION,
                String.format(
                        DescriptionItemPoints.getFormatNoInflation(),
                        current_sales,
                        this.max_sales_per_lvl
                )
        );
    }

    public void updateLineInflation(double percent_modifier, int current_sales) {
        if (current_sales > this.max_sales_per_lvl) {
            throw new IllegalArgumentException("The current number of sales cannot be greater than the maximum number of sales.");
        }
        if (percent_modifier == 0.0) {
            this.text.set(DescriptionItemPoints.ID_DESCRIPTION_INFLATION,
                    String.format(
                            DescriptionItemPoints.getFormatNoInflation(),
                            current_sales,
                            this.max_sales_per_lvl
                    )
            );
        } else if (percent_modifier < 0) {
            this.text.set(DescriptionItemPoints.ID_DESCRIPTION_INFLATION,
                    String.format(
                            DescriptionItemPoints.getFormatReduction(),
                            percent_modifier,
                            current_sales,
                            this.max_sales_per_lvl
                    )
            );
        }
        else {
            this.text.set(DescriptionItemPoints.ID_DESCRIPTION_INFLATION,
                    String.format(
                            DescriptionItemPoints.getFormatAugmentation(),
                            percent_modifier,
                            current_sales,
                            this.max_sales_per_lvl
                    )
            );
        }
    }

    public void updateLineInflationMax(double percent_modifier, int current_sales) {
        if (percent_modifier == 0.0) {
            throw new IllegalArgumentException("The modification percentage cannot be equal to 0.");
        }
        this.text.set(DescriptionItemPoints.ID_DESCRIPTION_INFLATION,
                String.format(
                        DescriptionItemPoints.getFormatMaxInflation(),
                        percent_modifier,
                        current_sales
                )
        );
    }

    public boolean isSameLinePoints(String other) {
        Pattern reput_pattern = Pattern.compile(
                DescriptionItemPoints.getRegexPointPrice(this.requested_amount, this.item_name),
                Pattern.CASE_INSENSITIVE
        );
        Matcher self_matcher = reput_pattern.matcher(this.text.get(DescriptionItemPoints.ID_DESCRIPTION_PRICE));
        Matcher other_matcher = reput_pattern.matcher(other);
        return self_matcher.find() && other_matcher.find();
    }

    public boolean isSameLineInflation(String other) {
        Pattern inter_pattern = Pattern.compile(
                //"^§6(Prix (réduit|augmenté) de -?[1-9][0-9]*[.,][0-9]+%( \\((0|[1-9][0-9]*)/(0|[1-9][0-9]*)\\))?|Aucune inflation \\((0|[1-9][0-9]*)/(0|[1-9][0-9]*)\\)|Inflation maximum -?[1-9][0-9]*[.,][0-9]+%)\\.$",
                String.format(
                        "(%s|%s|%s)",
                        getRegexNoInflation(),
                        getRegexPercentModification(),
                        getRegexMaxInflation()
                ),
                Pattern.CASE_INSENSITIVE
        );
        Matcher self_matcher = inter_pattern.matcher(this.text.get(DescriptionItemPoints.ID_DESCRIPTION_INFLATION));
        Matcher other_matcher = inter_pattern.matcher(other);
        return self_matcher.find() && other_matcher.find();
    }

    public boolean isSameText(ArrayList<String> other) {
        if (this.text.size() != other.size() || other.size() < 2) {
            return false;
        }
        return isSameLinePoints(other.get(0)) && isSameLineInflation(other.get(1));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        DescriptionItemPoints other = (DescriptionItemPoints) obj;
        return (this.isSameLinePoints(other.getText().get(DescriptionItemPoints.ID_DESCRIPTION_PRICE))
                &&
                this.isSameLineInflation(other.getText().get(DescriptionItemPoints.ID_DESCRIPTION_INFLATION)));
    }
}
