package com.zeralin.sao;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Creature;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.md_5.bungee.api.ChatColor;

public class CombatMechanics implements Listener{

	public List<String> tag = new ArrayList<String>();
	
	public Main main;
	
	public CombatMechanics(Main plugin){
		main = plugin;
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e){
		e.setDeathMessage(null);
	}
	
	@EventHandler
	public void onOutgoingDamage(EntityDamageByEntityEvent e){
		if (e.getDamager() instanceof Player){
			Player player = (Player) e.getDamager();
			Creature mob = (Creature) e.getEntity();
			
			int dmg = (int) e.getDamage();
			int hp = (int) mob.getHealth() - dmg;
			
			if (hp > 0){
			player.sendMessage(ChatColor.RED + "" + dmg + " -> " + mob.getCustomName() + 
					ChatColor.WHITE + " [" + ChatColor.GREEN + hp + ChatColor.WHITE + "]");
			} else {
				player.sendMessage(ChatColor.RED + "" + dmg + " -> " + mob.getCustomName() + 
						ChatColor.WHITE + " [" + ChatColor.GREEN + "0" + ChatColor.WHITE + "]");
			}
		}
	}
	
}
