package fr.skyzoxs.main.SpinVillager;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ResetSpin implements CommandExecutor {

    private final SpinData dataManager;

    public ResetSpin(SpinData dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("spin.admin")) {
            sender.sendMessage("§cTu n'as pas la permission.");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("§cUtilisation: /resetspin <joueur>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage("§cJoueur introuvable.");
            return true;
        }

        UUID uuid = target.getUniqueId();
        // Supprimer la date stockée dans data.yml
        dataManager.getDataConfig().set("players." + uuid.toString(), null);
        dataManager.saveDataConfig();  // Sauvegarde du fichier data.yml

        sender.sendMessage("§aTimer de spin réinitialisé pour " + target.getName());
        return true;
    }
}
