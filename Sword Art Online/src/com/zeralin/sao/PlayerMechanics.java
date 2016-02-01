package com.zeralin.sao;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.connorlinfoot.titleapi.TitleAPI;

import net.md_5.bungee.api.ChatColor;

public class PlayerMechanics implements Listener{

	public Main main;
	
	public PlayerMechanics(Main plugin){
		main = plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		
		TitleAPI.sendTitle(e.getPlayer(), 20, 60, 20, ChatColor.GREEN + 
				"Welcome to Sword Craft Online!", ChatColor.AQUA + "Link Start!");
		TitleAPI.sendTabTitle(e.getPlayer(), ChatColor.AQUA + "Welcome to Sword Craft Online!"
				        , ChatColor.GREEN + "Please report any bugs to the GMs.");
		
		if (e.getPlayer().hasPlayedBefore()){
			e.getPlayer().setHealthScale(20D);
		    e.getPlayer().setHealthScaled(true);
		} else {
			e.getPlayer().setMaxHealth(100D);
			e.getPlayer().setHealth(100D);
			e.getPlayer().setHealthScale(20D);
			e.getPlayer().setLevel(1);
		    e.getPlayer().setHealthScaled(true);
		    main.inventoryMechanics.getSpawnKit(e.getPlayer());
		}
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e){
		e.getPlayer().setMaxHealth(100D);
		e.getPlayer().setHealth(100D);
		e.getPlayer().setHealthScale(20D);
	    e.getPlayer().setHealthScaled(true);
	    
	    main.inventoryMechanics.getSpawnKit(e.getPlayer());
	}
	
	@EventHandler
	public void onFoodLoss(FoodLevelChangeEvent e){
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e){
		if (e.getPlayer().getGameMode() == GameMode.SURVIVAL){
			e.setCancelled(true);
			if (e.getPlayer().getItemInHand() != null){
				e.getPlayer().getItemInHand().setDurability((short) 0);
			}
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e){
		if (e.getPlayer().getGameMode() == GameMode.SURVIVAL){
			e.setCancelled(true);
		}
	}
}
