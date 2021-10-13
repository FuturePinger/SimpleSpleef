package com.simplespleef;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RegionTabCompleter implements TabCompleter {
    private final RegionHandler regionHandler;

    public RegionTabCompleter(RegionHandler regionHandler) {
        this.regionHandler = regionHandler;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> arguments = new ArrayList<>();
        if (args.length == 1) {
            arguments.add("create");
            arguments.add("list");
            arguments.add("remove");
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("remove")) {
            for (Map.Entry<String, Region> pair : regionHandler.savedRegions.entrySet()) {
                arguments.add(pair.getKey());
            }

        }
        return arguments;
    }
}
