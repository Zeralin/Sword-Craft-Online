package com.zeralin.sao;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import net.md_5.bungee.api.ChatColor;

public class LevelMechanics implements Listener, CommandExecutor{

	public Main main;
	
	public HashMap<String, Integer> xp = new HashMap<String, Integer>();	
	
	public LevelMechanics(Main plugin){
		main = plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		if (e.getPlayer().hasPlayedBefore()){
			
		} else {
			xp.put(e.getPlayer().getName(), 0);
		}
	}
	
	@EventHandler
	public void onDeath(EntityDeathEvent e){
		if (!(e.getEntity() instanceof Player)){
			e.getDrops().clear();
			e.setDroppedExp(0);
		}
		
		if (e.getEntity() instanceof Wolf && e.getEntity().getKiller() instanceof Player){
			Player player = e.getEntity().getKiller();
			Wolf wolf = (Wolf) e.getEntity();
			if (wolf.getCustomName().equalsIgnoreCase(ChatColor.WHITE + "Dire Wolf")){
				Random random = new Random();
				int exp = random.nextInt(20) + 31;
				player.sendMessage(ChatColor.YELLOW + "+" + xp  + "/ exp for level");
				xp.put(player.getName(), exp);
			}
		}
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (cs instanceof Player){
			if (cmd.getName().equalsIgnoreCase("setLevel")){
				if (cs.isOp()){
					Player player = (Player) cs;
			        if (args.length == 1){
			        	try {
			        		int lvl = Integer.parseInt(args[0]);
			        		if (!(lvl < 1 || lvl > 100)){
				        		player.setLevel(lvl);
				        		player.sendMessage(ChatColor.WHITE + "Your level has been set to " + 
				        		     ChatColor.GREEN + lvl + ChatColor.WHITE + "!");
			        		} else {
			        			cs.sendMessage(ChatColor.RED + "Invalid syntax. Use /setLevel <number>.");
			        		}
			        	} catch (Exception e){
			        		cs.sendMessage(ChatColor.RED + "Invalid syntax. Use /setLevel <number>.");
			        	}
			        } else {
			        	cs.sendMessage(ChatColor.RED + "Invalid syntax. Use /setLevel <number>.");
			        }
				} else {
					cs.sendMessage(ChatColor.RED + "Invalid permissions.");
				}
			}
		}
		return true;
	}

}
