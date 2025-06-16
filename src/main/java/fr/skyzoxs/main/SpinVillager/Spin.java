package fr.skyzoxs.main.SpinVillager;

import fr.skyzoxs.main.reward.RewardManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.time.LocalDate;
import java.util.UUID;

public class Spin {

    private final FileConfiguration config;
    private final SpinGUI spinGUI;

    public Spin(FileConfiguration config, RewardManager rewardManager, Plugin plugin) {
        this.config = config;
        this.spinGUI = new SpinGUI(plugin, rewardManager);
    }

    public boolean canSpin(Player player) {
        String today = LocalDate.now().toString();
        String lastDay = config.getString("players." + player.getUniqueId());
        return !today.equals(lastDay);
    }

    public void spin(Player player) {
        UUID uuid = player.getUniqueId();
        String today = LocalDate.now().toString();

        if (!canSpin(player)) {
            player.sendMessage("§cTu as déjà utilisé la roulette aujourd'hui !");
            return;
        }

        config.set("players." + uuid, today);
        spinGUI.open(player);

    }
}
