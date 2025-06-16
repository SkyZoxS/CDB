package fr.skyzoxs.main.reward;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

//Represent a reward
public class RewardItem {

    private final String id;
    private final String name;
    private final Material material;
    private final List<String> lore;
    private final int chance;

    //Builder for RewardItem with id, name, material, chance and description
    public RewardItem(String id, String name, Material material, List<String> lore, int chance) {
        this.id = id;
        this.name = name;
        this.material = material;
        this.lore = lore;
        this.chance = chance;
    }

    public ItemStack toItemStack() {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if(meta == null) return null;
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public int getChance() {
        return chance;
    }

}
