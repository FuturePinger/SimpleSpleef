package com.simplespleef;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SpleefListener implements Listener {
    private final Spleef spleef;
    List<UUID> snowballId = new ArrayList<>();
    private final Game game;
    public SpleefListener(Spleef spleef, Game game) {
        this.spleef = spleef;
        this.game = game;
    }

    private static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    @EventHandler
    public void spleefShoot(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        ItemStack mainHand = player.getInventory().getItemInMainHand();
        if (mainHand.getItemMeta() != null && mainHand.getItemMeta().getDisplayName().contains(color("&b&lSpleef Shovel"))) {
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                Snowball snowball = player.launchProjectile(Snowball.class, player.getLocation().getDirection());
                snowballId.add(snowball.getUniqueId());
            }
        }

    }

    @EventHandler
    public void snowBallLand(ProjectileHitEvent e) {
        Block hitBlock = e.getHitBlock();
        Entity snowBall = e.getEntity();

        if (snowballId.contains(snowBall.getUniqueId())) {
            if (hitBlock != null && hitBlock.getType().equals(Material.SNOW_BLOCK)) {
                hitBlock.setType(Material.AIR);
            }
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        Spleef.joinedPlayers.remove(player);
        game.resetGameData();
    }


}
