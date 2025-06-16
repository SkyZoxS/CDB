    package fr.skyzoxs.main.Grade;

    import fr.skyzoxs.main.Points.Points;
    import org.bukkit.entity.Player;
    import org.bukkit.event.EventHandler;
    import org.bukkit.event.Listener;
    import org.bukkit.event.player.AsyncPlayerChatEvent;

    public class GradeChat implements Listener {

        Points points;

        public GradeChat(Points points) {
            this.points = points;
        }

        @EventHandler
        public void changePlayerNameInChat(AsyncPlayerChatEvent event) {
            Player player = event.getPlayer();
            int player_reputation = this.points.getPlayerPoints(String.valueOf(player.getUniqueId()));
            int grade_index = Grade.getGradeIndex(player_reputation);
            String pretty_name = ShowGrade.getPrettyPlayerName(player.getName(), grade_index);

            event.setFormat(
                    String.format("<%s> %s",
                            pretty_name,
                            event.getMessage()
                    )
            );
        }
    }
