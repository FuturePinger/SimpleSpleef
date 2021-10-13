package com.simplespleef;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WandCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.getInventory().contains(Items.selWand())) {
                player.getInventory().addItem(Items.selWand());
            }

        } else {
            System.out.println("Sorry! You must be a player to perform that command!");
        }
        return false;
    }
}
