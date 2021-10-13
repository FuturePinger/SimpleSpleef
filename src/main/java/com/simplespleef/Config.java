package com.simplespleef;

public class Config {
    private static Spleef spleef;

    public Config(Spleef spleef) {
        Config.spleef = spleef;

        spleef.getConfig().options().copyDefaults();
        spleef.saveDefaultConfig();
    }

    public static int getRequiredPlayers() {
        return spleef.getConfig().getInt("required-players");
    }
}
