package fr.skyzoxs.main.Points;

import java.util.HashMap;
import java.util.Set;

public class GlobalContri {
    private int globalPoints;
    private final HashMap<String, HashMap<String, Integer>> contributors;


    public GlobalContri() {
        this(0);
    }

    public GlobalContri(int globalPoints) {
        this.globalPoints = globalPoints;
        this.contributors = new HashMap<>();

    }

    //Getter for global points
    public int getGlobalPoints() {
        return globalPoints;
    }

    //Setter for global points
    public void setGlobalPoints(int globalPoints) {
        this.globalPoints = globalPoints;
    }

    //Getter for contributors
    public HashMap<String, HashMap<String, Integer>> getContributors() {
        return contributors;
    }

    //Setter for contributors
    public void setContributors(HashMap<String, HashMap<String, Integer>> contributors) {
        contributors.putAll(contributors);
    }

    //Getter for contributors names
    public Set<String> getContributorsName() {
        return this.contributors.keySet();
    }

    //Getter for player points
    public int getPlayerPoints(String playerName) {
        if (playerName.isEmpty()) {
            return 0;
        }
        if (this.contributors.containsKey(playerName)) {
            int total = 0;
            for (int points : this.contributors.get(playerName).values()) {
                total += points;
            }
            return total;
        }
        return 0;
    }

    //Getter for player points on a specific trader
    public int getPlayerPointsForOneTrader(String playerName, String traderName) {
        if (playerName.isEmpty() || traderName.isEmpty()) {
            return 0;
        }
        if (this.contributors.containsKey(playerName)) {
            HashMap<String, Integer> all_contributions = this.contributors.get(playerName);
            if (all_contributions.containsKey(traderName)) {
                return all_contributions.get(traderName);
            }
        }
        return 0;
    }

    //Getter for
    public Set<String> getTraderNameOfContributor(String playerName) {
        if (this.contributors.containsKey(playerName)) {
            HashMap<String, Integer> all_contributions = this.contributors.get(playerName);
            return all_contributions.keySet();
        }
        return null;
    }

    //Setter for
    public void setOneTradeOfAContributor(String playerName, String traderName, int initial_contribution) {
        if (playerName.isEmpty() || traderName.isEmpty()) {
            return;
        }

        if (this.contributors.containsKey(playerName)) {
            HashMap<String, Integer> all_contributions = this.contributors.get(playerName);
            if (!all_contributions.containsKey(traderName)) {
                all_contributions.put(traderName, initial_contribution);
                this.contributors.put(playerName, all_contributions);
            }
        }
        else {
            this.addContributor(playerName, traderName, initial_contribution);
        }
    }

    //Increase contribution of a player and global points
    public void increaseContribution(String playerName, String traderName, int contribution) {
        if (playerName.isEmpty() || traderName.isEmpty()) {
            return;
        }

        if (this.contributors.containsKey(playerName)) {
            HashMap<String, Integer> all_contributions = this.contributors.get(playerName);
            if (all_contributions.containsKey(traderName)) {
                all_contributions.put(traderName, all_contributions.get(traderName) + contribution);
                this.contributors.put(playerName, all_contributions);
            }
            else {
                this.setOneTradeOfAContributor(playerName, traderName, contribution);
            }
        }
        else {
            this.addContributor(playerName, traderName, contribution);
        }

        this.globalPoints += contribution;
    }

    //Add contributor
    public void addContributor(String playerName, String traderName, int initial_contribution) {
        if (playerName.isEmpty() || traderName.isEmpty()) {
            return;
        }

        if (!this.contributors.containsKey(playerName)) {
            HashMap<String, Integer> trader_contribution = new HashMap<>();
            trader_contribution.put(traderName, initial_contribution);
            this.contributors.put(playerName, trader_contribution);
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Les points globaux sont a  %d.\n",
                this.globalPoints
        );
    }
}
