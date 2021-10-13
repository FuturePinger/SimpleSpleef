package com.simplespleef;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.ArrayList;
import java.util.List;

public class RegionListener implements Listener {
    public static List<Double> selPos1 = new ArrayList<>();
    public static List<Double> selPos2 = new ArrayList<>();
    public static List<Location> spawnPoint = new ArrayList<>();
    private Items items;


    private static String color(String string) {
        return (ChatColor.translateAlternateColorCodes('&', string));
    }

    public static void setSpawn(Player player) {

        if (!spawnPoint.isEmpty()) {
            spawnPoint.clear();
        }
        spawnPoint.add(player.getLocation());

    }

    public static void getSpawn(Player player) {
        if (!spawnPoint.isEmpty()) {
            player.teleport(spawnPoint.get(0));
        } else {
            player.sendMessage(color("&cERROR&f! &7There is no spawnpoint to tp to!"));
        }
    }

    @EventHandler
    public void regionSel(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (player.getInventory().getItemInMainHand().getItemMeta() != null
                && player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains(color("&d&lSpleef Selection Wand"))
                && e.getHand() == EquipmentSlot.HAND) {
            if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                player.sendMessage(color("&cYou have selected postion 1!"));

                selPos1.clear();
                selPos1.add((double) e.getClickedBlock().getLocation().getBlockX());
                selPos1.add((double) e.getClickedBlock().getLocation().getBlockY());
                selPos1.add((double) e.getClickedBlock().getLocation().getBlockZ());
            } else if (e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                player.sendMessage(color("&cYou have selected postion 2!"));

                selPos2.clear();
                selPos2.add((double) e.getClickedBlock().getLocation().getBlockX());
                selPos2.add((double) e.getClickedBlock().getLocation().getBlockY());
                selPos2.add((double) e.getClickedBlock().getLocation().getBlockZ());
            }
        }
    }

    @EventHandler
    public void regionBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains(color("&d&lSpleef Selection Wand"))) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (Spleef.getInstance().isRunning() && Spleef.joinedPlayers.containsKey(player)) {
            try {
                if (!Region.containsLocation(e.getTo())) {
                    player.sendMessage(color("&cI'm Sorry&f! &7You have been eliminated!"));
                    Spleef.joinedPlayers.remove(player);
                }
            } catch (NullPointerException ignored) {

            }
        }
    }
}
