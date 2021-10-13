package com.simplespleef;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Items {
    private static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static ItemStack spleefShovel() {
        List<String> lore = new ArrayList<>();
        lore.add(color("&7Main this bad boi could cause some "));
        lore.add(color("&7damage!"));
        lore.add("");
        lore.add(color("&8(&7Right&8-&7Click&8)"));

        ItemStack spleefShovel = new ItemStack(Material.DIAMOND_SHOVEL);
        ItemMeta spleefShovelMeta = spleefShovel.getItemMeta();
        spleefShovelMeta.setLore(lore);
        spleefShovelMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        spleefShovelMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
        spleefShovelMeta.setDisplayName(color("&b&lSpleef Shovel"));
        spleefShovel.setItemMeta(spleefShovelMeta);

        return spleefShovel;
    }

    public static ItemStack selWand() {
        List<String> lore = new ArrayList<>();
        lore.add(color("&7Used to Select a Region!"));
        lore.add("");
        lore.add(color("&8(&7Right&8-&7Click&8) to sel Postion 1"));
        lore.add(color("&8(&7Left&8-&7Click&8) to sel Postion 2"));

        ItemStack selWand = new ItemStack(Material.STICK);
        ItemMeta selWandMeta = selWand.getItemMeta();
        selWandMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        selWandMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
        selWandMeta.setLore(lore);
        selWandMeta.setDisplayName(color("&d&lSpleef Selection Wand"));
        selWand.setItemMeta(selWandMeta);

        return selWand;
    }

}
