package fr.skyzoxs.main.SpinVillager;

import fr.skyzoxs.main.SpinVillager.reward.RewardManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.time.LocalDate;
import java.util.UUID;

public class Spin {

    private final SpinData dataManager;
    private final SpinGUI spinGUI;

    public Spin(SpinData dataManager, RewardManager rewardManager, Plugin plugin) {
        this.dataManager = dataManager;
        this.spinGUI = new SpinGUI(plugin, rewardManager);
    }

    //Boolean to know if player can spin the wheel
    public boolean canSpin(Player player) {
        String today = LocalDate.now().toString();
        String lastDay = dataManager.getDataConfig().getString("players." + player.getUniqueId());
        return !today.equals(lastDay);
    }

    //Spin the wheel
    public void spin(Player player) {
        UUID uuid = player.getUniqueId();
        String today = LocalDate.now().toString();

        if (!canSpin(player)) {
            player.sendMessage("§cTu as déjà utilisé la roulette aujourd'hui !");
            return;
        }

        dataManager.getDataConfig().set("players." + uuid, today);
        dataManager.saveDataConfig();

        spinGUI.open(player);
    }
}
