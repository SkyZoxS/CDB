package fr.skyzoxs.main.SpinVillager;

import fr.skyzoxs.main.SpinVillager.reward.RewardItem;
import fr.skyzoxs.main.SpinVillager.reward.RewardManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class SpinGUI {
    private final Plugin plugin;
    private final RewardManager rewardManager;
    private final Random random = new Random();



    public SpinGUI(Plugin plugin, RewardManager rewardManager) {
        this.plugin = plugin;
        this.rewardManager = rewardManager;
    }

    //Open gui
    public void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9, ChatColor.DARK_PURPLE + "Roulette");

        // vitres sauf centre
        for (int i = 0; i < 9; i++) {
            if (i != 4) {
                inv.setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
            }
        }

        player.openInventory(inv);

        List<RewardItem> rewards = rewardManager.getRewards();
        if (rewards.isEmpty()) {
            player.sendMessage("Pas de récompenses configurées !");
            return;
        }

        new BukkitRunnable() {
            int ticks = 0;
            int index = 0;
            final int maxTicks =  20 + random.nextInt(20);

            @Override
            public void run() {
                if (!player.isOnline() || !player.getOpenInventory().getTopInventory().equals(inv)) {
                    cancel();
                    return;
                }

                RewardItem current = rewards.get(index % rewards.size());
                inv.setItem(4, current.toItemStack());
                player.updateInventory();

                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);

                ticks++;
                index++;

                if (ticks >= maxTicks) {
                    cancel();

                    RewardItem reward = rewards.get((index - 1) % rewards.size());

                    player.getInventory().addItem(reward.toItemStack());
                    player.sendMessage("§aTu as gagné §6" + reward.toItemStack().getItemMeta().getDisplayName());
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                }
            }
        }.runTaskTimer(plugin, 0L, 3L);
    }
}

