package com.simplespleef;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnPointCommand implements CommandExecutor {
    private String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            try {
                if (args.length == 0 && Region.containsLocation(player.getLocation())) {
                    RegionListener.setSpawn(player);
                    player.sendMessage(color("&aSpawnpoint Created!"));
                } else if (args.length == 0 && !Region.containsLocation(player.getLocation())) {
                    player.sendMessage(color("&cERROR&f! &fSpawnpoints can only be placed inside a region! &7Please create one using /spleef-region create <region-name>&f!"));

                }
            } catch (NullPointerException ignored) {

            }
            if (args.length == 1 && args[0].equalsIgnoreCase("tp")) {
                RegionListener.getSpawn(player);

            }

        }
        return false;
    }
}
