package fr.skyzoxs.main.utils;

import fr.skyzoxs.main.Grade.ShowGrade;
import fr.skyzoxs.main.Points.GlobalContri;
import fr.skyzoxs.main.Points.PointsScoreboard;
import fr.skyzoxs.main.Team.TeamManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

    private final GlobalContri globalContri;
    private final TeamManager teamManager;

    public Join(GlobalContri globalContri, TeamManager teamManager) {
        this.globalContri = globalContri;
        this.teamManager = teamManager;
    }

    //Make a title when joining the game and setup his grade & scoreboard
    @EventHandler
    public void join(PlayerJoinEvent event){
        if (event == null) {
            return;
        }

        Player player = event.getPlayer();
        player.sendTitle("§6Bienvenue chez Greg", player.getName(), 20, 20, 20);
        PointsScoreboard.setScoreBoard(player, this.globalContri, teamManager);

        // Updates the player's list name.
        ShowGrade.setPlayerPointsGrade(player, this.globalContri);
    }
}
