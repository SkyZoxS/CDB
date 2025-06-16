    package fr.skyzoxs.main.Grade;

    import fr.skyzoxs.main.Points.GlobalContri;
    import org.bukkit.entity.Player;
    import org.bukkit.event.EventHandler;
    import org.bukkit.event.Listener;
    import org.bukkit.event.player.AsyncPlayerChatEvent;

    public class GradeChat implements Listener {

        GlobalContri globalContri;

        public GradeChat(GlobalContri globalContri) {
            this.globalContri = globalContri;
        }

        @EventHandler
        public void changePlayerNameInChat(AsyncPlayerChatEvent event) {
            Player player = event.getPlayer();
            int player_reputation = this.globalContri.getPlayerPoints(String.valueOf(player.getUniqueId()));
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
