package com.simplespleef;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InventoryHandler {
    public static Map<UUID, ItemStack[]> playerInventory = new HashMap<>();

    private static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);

    }

    public static void saveInvetory(Player player) {
        playerInventory.put(player.getUniqueId(), player.getInventory().getContents());
        player.getInventory().clear();

    }

    public static void giveInventory(Player player) {
        player.getInventory().clear();
        player.getInventory().setContents(playerInventory.get(player.getUniqueId()));
        player.getInventory().remove(Items.spleefShovel());
        playerInventory.remove(player.getUniqueId(), player.getInventory().getContents());

    }

}
