package fr.skyzoxs.main.Team;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class TeamCommands implements CommandExecutor {

    private final TeamManager teamManager;

    public TeamCommands(JavaPlugin plugin, TeamManager teamManager) {
        this.teamManager = teamManager;
        plugin.getCommand("teamcreate").setExecutor(this);
        plugin.getCommand("teamdelete").setExecutor(this);
        plugin.getCommand("teamlist").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;

        if (command.getName().equalsIgnoreCase("teamcreate")) {
            return handleCreateTeam(sender, args);
        }

        if (command.getName().equalsIgnoreCase("teamdelete")) {
            return handleDeleteTeam(sender, args);
        }

        if (command.getName().equalsIgnoreCase("teamlist")) {
            return handleTeamList(sender);
        }

        return false;
    }

    private boolean handleTeamList(CommandSender sender) {
        Map<String, List<UUID>> teams = teamManager.getAllTeams();

        if (teams.isEmpty()) {
            sender.sendMessage(ChatColor.YELLOW + "Aucune équipe n'a été créée.");
            return true;
        }

        sender.sendMessage(ChatColor.GOLD + "Liste des équipes :");
        for (String teamName : teams.keySet()) {
            String colorName = teamManager.getTeamColor(teamName);
            ChatColor color;

            try {
                color = ChatColor.valueOf(colorName.toUpperCase());
            } catch (IllegalArgumentException e) {
                color = ChatColor.WHITE;
            }

            int size = teams.get(teamName).size();
            sender.sendMessage(color + "- " + teamName + ChatColor.GRAY + " (" + size + " joueur" + (size > 1 ? "s" : "") + ")");
        }

        return true;
    }

    private boolean handleCreateTeam(CommandSender sender, String[] args) {
        if (args.length < 3) {
            sender.sendMessage(ChatColor.RED + "Usage: /createteam <NomEquipe> <Couleur> <Joueur1> <Joueur2> ...");
            return true;
        }

        String teamName = args[0];
        String color = args[1].toUpperCase();

        ChatColor chatColor;
        try {
            chatColor = ChatColor.valueOf(color);
        } catch (IllegalArgumentException e) {
            sender.sendMessage(ChatColor.RED + "Couleur invalide. Ex: RED, BLUE, GREEN, etc.");
            return true;
        }

        List<UUID> members = new ArrayList<>();
        for (int i = 2; i < args.length; i++) {
            Player target = Bukkit.getPlayerExact(args[i]);
            if (target != null) {
                members.add(target.getUniqueId());
            } else {
                sender.sendMessage(ChatColor.YELLOW + args[i] + " est hors ligne ou introuvable.");
            }
        }

        if (members.isEmpty()) {
            sender.sendMessage(ChatColor.RED + "Aucun joueur valide spécifié !");
            return true;
        }

        teamManager.createTeam(teamName, color, members);
        sender.sendMessage(chatColor + "Équipe '" + teamName + "' créée avec succès avec " + members.size() + " joueurs !");
        return true;
    }

    private boolean handleDeleteTeam(CommandSender sender, String[] args) {
        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /deleteteam <NomEquipe>");
            return true;
        }

        String teamName = args[0];
        if (teamManager.deleteTeam(teamName)) {
            sender.sendMessage(ChatColor.GREEN + "Équipe '" + teamName + "' supprimée avec succès !");
        } else {
            sender.sendMessage(ChatColor.RED + "Équipe '" + teamName + "' introuvable !");
        }

        return true;
    }
}
