package com.simplespleef;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Game extends BukkitRunnable {
    private final Spleef spleef;
    private final RegionHandler regionHandler;

    public Game(Spleef spleef, RegionHandler regionHandler) {
        this.spleef = spleef;
        this.regionHandler = regionHandler;
    }

    private static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);

    }

    public void giveReward(Player player) {

        player.getInventory().addItem(new ItemStack(Material.DIAMOND));
    }

    public void resetGameData() {
        Spleef.joinedPlayers.clear();
    }

    public void gameStart(Player player) {
        InventoryHandler.saveInvetory(player);
        player.getInventory().addItem(Items.spleefShovel());
        player.sendMessage(color("&cWARNING&f! &7The game is now starting"));
        RegionListener.getSpawn(player);

    }

    public void gameEnd(Player player) {
        InventoryHandler.giveInventory(player);
    }

    public void sendMessage(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(message);
        }
    }

    public void begin() {
        this.runTaskTimer(Spleef.getInstance(), 0, 200);
    }

    @Override
    public void run() {
        if (Spleef.joinedPlayers.size() < 2) {
            for (Player player : Spleef.joinedPlayers.keySet()) { // when game does not have enough players to start
                player.sendMessage(color("&f(" + Spleef.joinedPlayers.size() + "/" + Config.getRequiredPlayers() + ") &7needed to play!"));
            }

        } else {
            cancel();
        }
        if (RegionHandler.getInstance().savedRegions.isEmpty()) {
            System.out.println("[SimpleSpleef] Please create a region for the game the start!");
        } else {
            cancel();
        }
    }

}
