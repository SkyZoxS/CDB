    package fr.skyzoxs.main.Grade;

    import fr.skyzoxs.main.Points.GlobalContri;
    import fr.skyzoxs.main.Team.TeamManager;
    import org.bukkit.ChatColor;
    import org.bukkit.entity.Player;
    import org.bukkit.event.EventHandler;
    import org.bukkit.event.Listener;
    import org.bukkit.event.player.AsyncPlayerChatEvent;

    public class GradeChat implements Listener {

        GlobalContri globalContri;
        TeamManager teamManager;

        public GradeChat(GlobalContri globalContri, TeamManager teamManager) {
            this.teamManager = teamManager;
            this.globalContri = globalContri;
        }

        @EventHandler
        public void changePlayerNameInChat(AsyncPlayerChatEvent event) {
            Player player = event.getPlayer();
            int playerReputation = this.globalContri.getPlayerPoints(String.valueOf(player.getUniqueId()));
            int gradeIndex = Grade.getGradeIndex(playerReputation);

            // Couleur temporaire bleue pour test
            String teamName = teamManager.getTeamOfPlayer(player.getUniqueId());
            ChatColor teamColorCode = ChatColor.WHITE; // Couleur par défaut

            if (teamName != null) {
                String colorName = teamManager.getTeamColor(teamName); // récupère "RED", "WHITE", etc.
                try {
                    teamColorCode = ChatColor.valueOf(colorName.toUpperCase()); // transforme en ChatColor
                    } catch (IllegalArgumentException e) {
                     // En cas de faute de frappe dans le nom de couleur dans le fichier YAML
                       teamColorCode = ChatColor.WHITE;
                }
            }

            String prettyName = ShowGrade.getPrettyPlayerName(player.getName(), gradeIndex);
            String finalName = prettyName.replace("§f" + player.getName(), teamColorCode + player.getName());

            event.setFormat("<" + finalName + "§f" + "> "  + event.getMessage());
        }

    }
