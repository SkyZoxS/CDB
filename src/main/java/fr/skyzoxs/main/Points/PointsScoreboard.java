package fr.skyzoxs.main.Points;

import fr.skyzoxs.main.Team.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.Objects;

public class PointsScoreboard {

    private static boolean reveal = false;
    private static String prettyPrintNumber(int number) {
        return String.format("%,d", number).replace(',', ' ');
    }

    //Set scoreboard for everyone
    public static void setScoreBoard(Player player, GlobalContri globalContri, TeamManager teamManager) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard(); // IMPORTANT

        Objective objective = board.registerNewObjective("points", "dummy", "§6§lCité Des Sables");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        // Ligne vide en haut
        objective.getScore("§0").setScore(10);

        if(!reveal) {
            // Points globaux
            Team globalTeam = board.registerNewTeam("globalPoints");
            globalTeam.addEntry("§bPoints globaux : §f§kAaaaaa");
            objective.getScore("§bPoints globaux : §f§kAaaaaa").setScore(9);

            // Ligne vide
            objective.getScore("§1").setScore(8);

            // Séparateur
            objective.getScore("--------------").setScore(7);

            // Ligne vide
            objective.getScore("§2").setScore(6);


            // Points équipe
            String teamName = teamManager.getTeamOfPlayer(player.getUniqueId());
            if (teamName != null) {
                int teamPoints = teamManager.getTeamPoints(teamName, globalContri);
                String teamOfPlayer = teamManager.getTeamOfPlayer(player.getUniqueId());
                Team teamScoreTeam = board.registerNewTeam("teamPoints");
                teamScoreTeam.addEntry(ChatColor.valueOf(teamManager.getTeamColor(teamOfPlayer)) + teamOfPlayer + " : ");
                teamScoreTeam.setSuffix(prettyPrintNumber(teamPoints));
                objective.getScore(ChatColor.valueOf(teamManager.getTeamColor(teamOfPlayer)) + teamOfPlayer + " : ").setScore(5);
            }

            // Ligne vide
            objective.getScore("§1").setScore(4);

            // Séparateur
            objective.getScore("--------------").setScore(3);

            // Ligne vide
            objective.getScore("§2").setScore(2);


            // Points perso
            int p_points = globalContri.getPlayerPoints(String.valueOf(player.getUniqueId()));
            Team playerTeam = board.registerNewTeam("playerPoints");
            playerTeam.addEntry("§6Vos Points : ");
            playerTeam.setSuffix(prettyPrintNumber(p_points));
            objective.getScore("§6Vos Points : ").setScore(1);

            // Ligne vide
            objective.getScore("§3").setScore(0);

            player.setScoreboard(board);
        }
        else {
            int scoreIndex = 15;

            // Titre
            objective.getScore("§0").setScore(scoreIndex--);

            // Points globaux
            int g_points = globalContri.getGlobalPoints();
            Team globalTeam = board.registerNewTeam("globalPoints");
            globalTeam.addEntry("§bPoints globaux : ");
            globalTeam.setSuffix(prettyPrintNumber(g_points));
            objective.getScore("§bPoints globaux : ").setScore(scoreIndex--);

            // Ligne vide
            objective.getScore("§3").setScore(scoreIndex--);

            // Séparateur
            objective.getScore("--------------").setScore(scoreIndex--);

            // Ligne vide
            objective.getScore("§3").setScore(scoreIndex--);

            // Affichage des points de chaque équipe
            for (String teamName : teamManager.getAllTeamNames()) {
                int teamPoints = teamManager.getTeamPoints(teamName, globalContri);
                String colorCode = ChatColor.valueOf(teamManager.getTeamColor(teamName)).toString();

                String entry = colorCode + teamName + " : ";
                Team team = board.registerNewTeam("team_" + teamName); // unique name
                team.addEntry(entry);
                team.setSuffix(prettyPrintNumber(teamPoints));
                objective.getScore(entry).setScore(scoreIndex--);

                // Ligne vide
                objective.getScore("§3").setScore(scoreIndex--);
            }

            // Ligne vide
            objective.getScore("§1").setScore(scoreIndex--);

            // Séparateur
            objective.getScore("--------------").setScore(scoreIndex--);

            // Ligne vide
            objective.getScore("§2").setScore(scoreIndex--);

            // Points perso
            int p_points = globalContri.getPlayerPoints(String.valueOf(player.getUniqueId()));
            Team playerTeam = board.registerNewTeam("playerPoints");
            playerTeam.addEntry("§6Vos Points : ");
            playerTeam.setSuffix(prettyPrintNumber(p_points));
            objective.getScore("§6Vos Points : ").setScore(1);

            // Ligne vide
            objective.getScore("§3").setScore(0);

            player.setScoreboard(board);
        }
    }

    public static void updateGlobalPoints(Player player, GlobalContri globalContri) {
        int g_points = 0; //globalContri.getGlobalPoints();
        Scoreboard board = player.getScoreboard();
        board.getTeam("globalPoints").setSuffix(
                PointsScoreboard.prettyPrintNumber(g_points)
        );
    }

    public static void updatePersonalPoints(Player player, GlobalContri globalContri) {
        int p_reputation = globalContri.getPlayerPoints(String.valueOf(player.getUniqueId()));
        Scoreboard board = player.getScoreboard();
        board.getTeam("playerPoints").setSuffix(
                PointsScoreboard.prettyPrintNumber(p_reputation)
        );
    }

    public static void reveal(String bool){
        if(Objects.equals(bool, "true")) reveal = true;
        else reveal = false;

    }

}
