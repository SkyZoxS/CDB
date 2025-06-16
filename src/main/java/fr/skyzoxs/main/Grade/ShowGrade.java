    package fr.skyzoxs.main.Grade;

    import fr.skyzoxs.main.Points.Points;
    import org.bukkit.entity.Player;

    public class ShowGrade {

        //Color for grades
        private static String getColorGrade(int index) throws IllegalArgumentException {
            String color = "";
            switch (index) {
                case 0:
                case 1: {
                    color = "§e";
                    break;
                }
                case 2:
                case 3:
                case 4: {
                    color = "§a";
                    break;
                }
                case 5:
                case 6: {
                    color = "§c";
                    break;
                }
                case 7:
                case 8:
                case 9: {
                    color = "§b";
                    break;
                }
                case 10:
                case 11:
                case 12: {
                    color = "§2";
                    break;
                }
                case 13:
                case 14:
                case 15: {
                    color = "§4";
                    break;
                }
                case 16:
                case 17:
                case 18: {
                    color = "§1";
                    break;
                }
                case 19: {
                    color = "§l§6";
                    break;
                }
                case 20: {
                    color = "§o§0";
                    break;
                }
                case 21: {
                    color = "§n§o§0";
                    break;
                }
                case 22: {
                    color = "§o§d";
                    break;
                }
                case 23: {
                    color = "§o§5";
                    break;
                }
                default:
                    throw new IllegalArgumentException("Color cannot be recovered, grade does not exist");
            }
            return color;
        }


        public static String getPrettyPlayerName(String player_name, int grade_index) {
            String grade_name = Grade.gradeName[grade_index];
            String color = ShowGrade.getColorGrade(grade_index);
            return String.format("%s[%s] §f%s",
                    color,
                    grade_name,
                    player_name
            );
        }

        //Setter for playerpoints
        public static void setPlayerPointsGrade(Player player, Points points) {
            int Points = points.getPlayerPoints(String.valueOf(player.getUniqueId()));
            int grade_index = Grade.getGradeIndex(Points);
            player.setPlayerListName(
                    ShowGrade.getPrettyPlayerName(player.getName(), grade_index)
            );
        }
    }

