package fr.skyzoxs.main.Points;

public class PointsV {

    public static final int NUMBER_SALES_PER_LEVEL = 10;
    public static final int MAX_MODIFICATIONS = 5;
    public static final double PERCENT_REMOVED_BY_LEVEL = 10.0;

    private final int NUMBER_NEEDS_REFRESH = 10;
    private final int INITIAL_POINTS_GIVEN;
    private int points_given;
    private final int REQUESTED_AMOUNT;
    private int recent_sales;
    private int current_inflation_level;
    private int needs_before_next_level;
    private int current_sales_level;
    private double reduction_applied;


    public PointsV(int initial_points_given, int requested_amount, int recent_sales) throws IllegalArgumentException {
        if (initial_points_given <= 0) {
            throw new IllegalArgumentException("The given number of points must be strictly greater than 0.");
        }
        if (requested_amount <= 0) {
            throw new IllegalArgumentException("The number of items requested must be strictly greater than 0.");
        }
        if (recent_sales < 0) {
            throw new IllegalArgumentException("The number of recent sales must be greater or equal to 0.");
        }
        this.INITIAL_POINTS_GIVEN = initial_points_given;
        this.REQUESTED_AMOUNT = requested_amount;
        this.setRecentSales(recent_sales);
    }

    public PointsV(int initial_points_given, int requested_amount) {
        this(initial_points_given, requested_amount, 0);
    }

    public int getRequestedAmount() {
        return this.REQUESTED_AMOUNT;
    }

    public int getRecentSales() {
        return this.recent_sales;
    }

    public int getInitialPointsGiven() {
        return this.INITIAL_POINTS_GIVEN;
    }

    public int getModifiedPointsGiven() {
        return this.points_given;
    }

    public int getInflationLevel() {
        return this.current_inflation_level;
    }

    public int getItemsPerInflationLevel() {
        return this.REQUESTED_AMOUNT * PointsV.NUMBER_SALES_PER_LEVEL;
    }

    public int getItemsSalesInCurrentLevel() {
        return this.current_sales_level;
    }

    public int getItemsRequiredForNextLevel() {
        return this.needs_before_next_level;
    }

    public double getPercentModifier() {
        return this.reduction_applied;
    }

    public void setRecentSales(int recent_sales) {
        if (recent_sales < 0) {
            recent_sales = 0;
        }
        this.recent_sales = recent_sales;
        this.current_sales_level = this.recent_sales % this.getItemsPerInflationLevel();
        this.needs_before_next_level = this.getItemsPerInflationLevel() - this.current_sales_level;
        if (this.recent_sales != this.current_sales_level) {
            this.current_inflation_level = this.recent_sales / this.getItemsPerInflationLevel();
            if (this.current_inflation_level > PointsV.MAX_MODIFICATIONS) {
                this.current_inflation_level = PointsV.MAX_MODIFICATIONS;
            }
            if (this.current_inflation_level == PointsV.MAX_MODIFICATIONS) {
                this.needs_before_next_level = -1;
                this.current_sales_level = this.recent_sales - (PointsV.MAX_MODIFICATIONS * this.getItemsPerInflationLevel());
            }
            this.reduction_applied = this.current_inflation_level * PointsV.PERCENT_REMOVED_BY_LEVEL;
            int reputation_removed = (int) (this.INITIAL_POINTS_GIVEN * (this.reduction_applied / 100));
            this.points_given = this.INITIAL_POINTS_GIVEN - reputation_removed;
        }
        else {
            this.current_inflation_level = 0;
            this.reduction_applied = 0.0;
            this.points_given = this.INITIAL_POINTS_GIVEN;
        }
    }

    public boolean isMaxInflationLevel() {
        return this.current_inflation_level == PointsV.MAX_MODIFICATIONS;
    }

    public void madeASales() {
        this.setRecentSales(this.recent_sales + this.REQUESTED_AMOUNT);
    }

    public void forgerSomeRecentSales() {
        int recent_sales_forget = this.REQUESTED_AMOUNT * this.NUMBER_NEEDS_REFRESH;
        if (recent_sales_forget > this.recent_sales) {
            this.setRecentSales(0);
        }
        else {
            this.setRecentSales(this.recent_sales - recent_sales_forget);
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Point original: %d\nPoint modifiée: %d\nPalier d'inflation: %d\nPourcentage appliqué:%f\nItem vendus récemment: %d\nItems vendus durant le palier courrant: %d",
                this.INITIAL_POINTS_GIVEN,
                this.points_given,
                this.current_inflation_level,
                this.reduction_applied,
                this.recent_sales,
                this.current_sales_level
        );
    }
}