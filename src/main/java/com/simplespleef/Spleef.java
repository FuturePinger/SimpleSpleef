package com.simplespleef;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class Spleef extends JavaPlugin {


    public static Map<Player, Boolean> joinedPlayers = new HashMap<>();
    private static Spleef instance;
    int seconds;
    private Region region;
    private RegionHandler regionHandler;
    private Game game;
    private Spleef spleef;
    private boolean running = false;

    public static Spleef getInstance() {
        return instance;
    }

    private String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);

    }

    @Override
    public void onEnable() {
        // Plugin startup logic

        game = new Game(getInstance(), regionHandler);
        regionHandler = new RegionHandler();
        new Config(this);
        new RegionHandler();


        Spleef.instance = this;
        game.begin();

        Bukkit.getPluginCommand("spleef-region").setExecutor(new RegionCommand(regionHandler));
        Bukkit.getPluginCommand("spleef-region").setTabCompleter(new RegionTabCompleter(regionHandler));
        //Handles all region commands
        Bukkit.getPluginCommand("spleef-wand").setExecutor(new WandCommand());
        //Gives player selection wand
        Bukkit.getPluginCommand("spleef-spawnpoint").setExecutor(new SpawnPointCommand());
        Bukkit.getPluginCommand("spleef-spawnpoint").setTabCompleter(new SpawnPointTabCompleter());
        //Handles creating and tping to spawnpoint
        Bukkit.getPluginCommand("spleef-join").setExecutor(new SpleefJoinCommand());
        Bukkit.getPluginManager().registerEvents(new SpleefListener(getInstance(), game), this);
        //Handles the game such as the Shovel that shoots snowballs and what the snowballs do when they land and when a player leaves the game
        Bukkit.getPluginManager().registerEvents(new RegionListener(), this);

        Bukkit.getScheduler().runTaskTimer(this, new Runnable() {


            @Override
            public void run() {


                if (!isRunning()) { // game is not running reset any data
                    seconds = 35;
                    //working

                }
                if (seconds == 35 && joinedPlayers.size() >= Config.getRequiredPlayers() && !regionHandler.savedRegions.isEmpty()) {// game just started
                    for (Player player : joinedPlayers.keySet()) {
                        game.gameStart(player);
                        //working

                    }
                    //working
                    game.cancel();
                    game = new Game(getInstance(), regionHandler);
                    setRunning(true);

                }
                if (joinedPlayers.size() == 1 && isRunning()) { // if there is only one player alive
                    for (Player player : joinedPlayers.keySet()) {
                        game.giveReward(player);
                        game.gameEnd(player);
                        for (Map.Entry<Player, Boolean> players : joinedPlayers.entrySet()) {
                            game.sendMessage(color("&cATTENTION&f! &7Everyone but one person has been eliminated. Congrats to &a" + players.getKey().getDisplayName()) + color("&7!"));
                        }

                    }
                    game.resetGameData();
                    setRunning(false);
                } else if (seconds == 0 && joinedPlayers.size() <= 2) {// if the round is over but there are more than 1 people alive.
                    for (Player player : joinedPlayers.keySet()) {
                        game.gameEnd(player);
                        //working
                    }
                    game.sendMessage(color("&cATTENTION&F! &7I regret to inform you. None of you recieved a reward since there was two survivors!"));
                    game.resetGameData();
                    setRunning(false);
                }
                if (isRunning() && seconds <= 30 && !(seconds % 5 != 0 && seconds > 5)) { // display when there are 30, 25, 20, 15, 10, 5, 4, 3, 2 and 1 seconds left
                    if (seconds == 0) return;
                    game.sendMessage(color("&eYou have " + seconds + " seconds remaining!"));
                    //working
                }
                seconds--;
                //working


            }
        }, 20, 20);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public boolean isRunning() { // Determines if the game is running
        return running;
    } //Allows us to check if the game is running

    public void setRunning(boolean running) { // boolean to stop or start game
        this.running = running;
    } //Allows us to change the state of the game
}
