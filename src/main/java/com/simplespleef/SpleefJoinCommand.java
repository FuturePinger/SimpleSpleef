package com.simplespleef;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpleefJoinCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                Spleef.joinedPlayers.put(player, true);
                player.sendMessage("You have joined the game!");
            } else if (args.length == 1 && args[0].equalsIgnoreCase("leave")) {
                Spleef.joinedPlayers.remove(player);
                player.sendMessage("You have left the game!");
            }
        }
        return false;
    }
}
