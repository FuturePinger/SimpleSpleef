package com.simplespleef;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RegionCommand implements CommandExecutor {
    private final RegionHandler regionHandler;
    public Region region;
    public Region remove;

    public RegionCommand(RegionHandler regionHandler) {
        this.regionHandler = regionHandler;
    }

    private String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        /*
        args[]
                         0       1
        /spleef-region create <name>
        /spleef-region remove <name>
        /spleef-region list

        args.length
           0             1      2
        /spleef-region create <name>
        /spleef-region remove <name>
        /spleef-region list


         */


        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length < 1) {
                player.sendMessage(color("&cList Of Commands&f: "));
                player.sendMessage(color("- &7/spleef-region create <region-name>"));
                player.sendMessage(color("- &7/spleef-region remove <region-name>"));
                player.sendMessage(color("- &7/spleef-region list"));
            } else switch (args[0]) {
                case "create":
                    if (args.length == 2) {

                        if (regionHandler.savedRegions.containsKey(args[1])) {
                            player.sendMessage("you already have a region with that name please remove it first");
                        } else {
                            regionHandler.regionCreate(region, player, args[1]);
                        }

                    } else {
                        player.sendMessage(color("&cInvalid Usage&f: &7Try /spleef-region create <region-name>"));
                    }
                    break;
                case "remove":
                    if (args.length == 2 && regionHandler.savedRegions.containsKey(args[1])) {
                        regionHandler.regionRemove(args[1], player);
                    } else {
                        player.sendMessage(color("&cInvalid Usage&f: &7Try /spleef-region remove <region-name>"));
                    }
                    break;
                case "list":
                    if (args.length == 1) {
                        regionHandler.regionList(player);
                    } else {
                        player.sendMessage(color("&cInvalid Usage&f: &7Try /spleef-region list"));
                    }
                    break;
            }


        } else {
            System.out.println("Sorry! You must be a player to perform that command!");
        }


        return false;
    }
}
