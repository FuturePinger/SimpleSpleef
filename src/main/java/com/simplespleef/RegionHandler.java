package com.simplespleef;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class RegionHandler {
    private static RegionHandler instance;
    public LinkedHashMap<String, Region> savedRegions = new LinkedHashMap<>();

    public RegionHandler() {
        RegionHandler.instance = this;
    }

    public static RegionHandler getInstance() {
        return instance;
    }

    private String color(String string) {
        return (ChatColor.translateAlternateColorCodes('&', string));
    }

    public void regionCreate(Region region, Player player, String id) {
        if (!RegionListener.selPos1.isEmpty() || !RegionListener.selPos2.isEmpty()) {
            region = new Region(id,
                    new Location(player.getWorld(), RegionListener.selPos1.get(0), RegionListener.selPos1.get(1), RegionListener.selPos1.get(2)),
                    new Location(player.getWorld(), RegionListener.selPos2.get(0), RegionListener.selPos2.get(1), RegionListener.selPos2.get(2)));
            savedRegions.put(id, region);
            player.sendMessage(color("&fYou have created the region &c" + id + "&f!"));
        } else {
            player.sendMessage(color("&7&lHEYY! &cYou must select two positons first! &7Use: /spleef-wand"));
        }
    }

    public void regionRemove(String id, Player player) {
        savedRegions.remove(id);
        player.sendMessage(color("&fYou have removed the region &c" + id + "&f!"));

    }

    public void regionList(Player player) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= savedRegions.keySet().size(); i++) {
            for (Map.Entry<String, Region> pair : savedRegions.entrySet()) {
                sb.append(color("&7")).append(i++).append(color(". &6")).append(pair.getKey()).append("\n");
            }

        }
        if (savedRegions.isEmpty()) {
            player.sendMessage(color("&cPlease create a region to view the list!"));
        } else {
            player.sendMessage(color("&fList of current regions: "));
            player.sendMessage(sb.toString());
        }

    }

}
