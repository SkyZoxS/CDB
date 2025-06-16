package fr.skyzoxs.main.reward;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

public class RewardItem {

    private final String id;
    private final String name;
    private final Material material;
    private final List<String> lore;
    private final int chance;
    private final Map<String, Integer> enchantments;
    private final String skullOwner;

    public RewardItem(Map<String, Object> data) {
        this.id = (String) data.get("id");
        this.name = (String) data.get("name");
        this.material = Material.valueOf(((String) data.get("material")).toUpperCase());
        this.lore = (List<String>) data.getOrDefault("lore", new ArrayList<>());
        this.chance = (int) data.get("chance");

        // Enchantements
        this.enchantments = new HashMap<>();
        Object enchantsRaw = data.get("enchantments");
        if (enchantsRaw instanceof Map<?, ?> enchantsMap) {
            for (Map.Entry<?, ?> entry : enchantsMap.entrySet()) {
                String enchantName = entry.getKey().toString().toUpperCase();
                Object value = entry.getValue();
                if (value instanceof Number) {
                    enchantments.put(enchantName, ((Number) value).intValue());
                } else {
                    System.out.println("[Roulette] Niveau d'enchantement invalide pour : " + enchantName);
                }
            }
        }

        // Propriétaire de tête
        this.skullOwner = (String) data.getOrDefault("skullOwner", null);
    }

    public ItemStack toItemStack() {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if (meta == null) {
            System.out.println("[Roulette] Erreur : Impossible de récupérer l'ItemMeta pour " + id);
            return item;
        }

        meta.setDisplayName(name);
        meta.setLore(lore);

        // Appliquer les enchantements
        for (Map.Entry<String, Integer> enchant : enchantments.entrySet()) {
            Enchantment ench = Enchantment.getByName(enchant.getKey());
            if (ench != null) {
                meta.addEnchant(ench, enchant.getValue(), true);
            } else {
                System.out.println("[Roulette] Enchantement inconnu : " + enchant.getKey());
            }
        }

        // Si PLAYER_HEAD, appliquer le propriétaire
        if (material == Material.PLAYER_HEAD && meta instanceof SkullMeta skullMeta && skullOwner != null) {
            skullMeta.setOwner(skullOwner); // Fonctionne toujours sur la plupart des versions, bien que deprecated
            meta = skullMeta;
        }

        item.setItemMeta(meta);
        return item;
    }

    public int getChance() {
        return chance;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
