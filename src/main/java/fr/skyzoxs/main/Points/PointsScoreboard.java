package fr.skyzoxs.main.Points;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class PointsScoreboard {

    private static String prettyPrintNumber(int number) {
        return String.format("%,d", number).replace(',', ' ');
    }

    //Set scoreboard for everyone
    public static void setScoreBoard(Player player, Points points) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard(); // IMPORTANT

        Objective objective = board.registerNewObjective("points", "dummy", "§6§lCité Des Sables");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        // Ligne vide en haut
        objective.getScore("§0").setScore(10);

        // Points globaux
        int g_points = points.getGlobalPoints();
        Team globalTeam = board.registerNewTeam("globalPoints");
        globalTeam.addEntry("Points globaux : ");
        globalTeam.setSuffix(prettyPrintNumber(g_points));
        objective.getScore("Points globaux : ").setScore(9);

        // Ligne vide
        objective.getScore("§1").setScore(8);

        // Séparateur
        objective.getScore("--------------").setScore(7);

        // Ligne vide
        objective.getScore("§2").setScore(6);

        // Points perso
        int p_points = points.getPlayerPoints(String.valueOf(player.getUniqueId()));
        Team playerTeam = board.registerNewTeam("playerPoints");
        playerTeam.addEntry("Vos Points : ");
        playerTeam.setSuffix(prettyPrintNumber(p_points));
        objective.getScore("Vos Points : ").setScore(5);

        // Ligne vide
        objective.getScore("§3").setScore(4);

        player.setScoreboard(board);
    }

    public static void updateGlobalReputation(Player player, Points points) {
        Scoreboard board = player.getScoreboard();
        Team team = board.getTeam("globalPoints");
        if (team != null) {
            team.setSuffix(prettyPrintNumber(points.getGlobalPoints()));
        }
    }

    public static void updatePersonalReputation(Player player, Points points) {
        Scoreboard board = player.getScoreboard();
        Team team = board.getTeam("playerPoints");
        if (team != null) {
            team.setSuffix(prettyPrintNumber(points.getPlayerPoints(String.valueOf(player.getUniqueId()))));
        }
    }


}
