package com.zeralin.sao;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import net.md_5.bungee.api.ChatColor;

public class FloorFunctions implements Listener{

	public Main main;
	
	public FloorFunctions(Main plugin){
		main = plugin;
	}
	
	public void goToSpawn(Player player){
		//Location floor = new Location(player.getWorld(), 1, 1, 1);
		//player.teleport(floor);
		player.sendMessage(ChatColor.GREEN + "Teleporting to the Town of Beginnings...");
	}
	
	public void goToFloor1(Player player){
		//Location floor = new Location(player.getWorld(), 1, 1, 1);
		//player.teleport(floor);
		player.sendMessage(ChatColor.GREEN + "Teleporting to Floor 1...");
	}
	
	public void goToFloor2(Player player){
		//Location floor = new Location(player.getWorld(), 1, 1, 1);
		//player.teleport(floor);
		player.sendMessage(ChatColor.GREEN + "Teleporting to Floor 2...");
	}
	
	public void goToFloor3(Player player){
		//Location floor = new Location(player.getWorld(), 1, 1, 1);
		//player.teleport(floor);
		player.sendMessage(ChatColor.GREEN + "Teleporting to Floor 3...");
	}
	
	public void goToFloor4(Player player){
		//Location floor = new Location(player.getWorld(), 1, 1, 1);
		//player.teleport(floor);
		player.sendMessage(ChatColor.GREEN + "Teleporting to Floor 4...");
	}
	
	public void goToFloor5(Player player){
		//Location floor = new Location(player.getWorld(), 1, 1, 1);
		//player.teleport(floor);
		player.sendMessage(ChatColor.GREEN + "Teleporting to Floor 5...");
	}
	
}
