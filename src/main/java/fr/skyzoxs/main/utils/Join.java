package fr.skyzoxs.main.utils;

import fr.skyzoxs.main.Grade.ShowGrade;
import fr.skyzoxs.main.Points.Points;
import fr.skyzoxs.main.Points.PointsScoreboard;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

    private final Points points;

    public Join(Points points) {
        this.points = points;
    }

    //Make a title when joining the game and setup his grade & scoreboard
    @EventHandler
    public void join(PlayerJoinEvent event) throws Exception {
        if (event == null) {
            return;
        }

        Player player = event.getPlayer();
        player.sendTitle("§6Bienvenue chez Greg", player.getName(), 20, 20, 20);
        PointsScoreboard.setScoreBoard(player, this.points);

        // Updates the player's list name.
        ShowGrade.setPlayerPointsGrade(player, this.points);
    }
}
