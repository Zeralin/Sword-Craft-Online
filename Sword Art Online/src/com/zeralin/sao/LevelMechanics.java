package com.zeralin.sao;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class LevelMechanics implements Listener, CommandExecutor{

	public Main main;
	
	public LevelMechanics(Main plugin){
		main = plugin;
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e){
		main.healthMechanics.checkHealth(e.getPlayer());
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
			        		if (!(lvl < 1 || lvl > 25)){
				        		player.setLevel(lvl);
				        		if (lvl == 1){ player.setMaxHealth(100);		        				
				        		} else if (lvl == 2){ player.setMaxHealth(150);
				        		} else if (lvl == 3){ player.setMaxHealth(200);
				        		} else if (lvl == 4){ player.setMaxHealth(250);
				        		} else if (lvl == 5){ player.setMaxHealth(300);
				        		} else if (lvl == 6){ player.setMaxHealth(350);
				        		} else if (lvl == 7){ player.setMaxHealth(400);
				        		} else if (lvl == 8){ player.setMaxHealth(450);
				        		} else if (lvl == 9){ player.setMaxHealth(500);
				        		} else if (lvl == 10){ player.setMaxHealth(550);
				        		} else if (lvl == 11){ player.setMaxHealth(600);
				        		} else if (lvl == 12){ player.setMaxHealth(650);
				        		} else if (lvl == 13){ player.setMaxHealth(700);
				        		} else if (lvl == 14){ player.setMaxHealth(750);
				        		} else if (lvl == 15){ player.setMaxHealth(800);
				        		} else if (lvl == 16){ player.setMaxHealth(850);
				        		} else if (lvl == 17){ player.setMaxHealth(900);
				        		} else if (lvl == 18){ player.setMaxHealth(950);
				        		} else if (lvl == 19){ player.setMaxHealth(1000);
				        		} else if (lvl == 20){ player.setMaxHealth(1050);
				        		} else if (lvl == 21){ player.setMaxHealth(1100);
				        		} else if (lvl == 22){ player.setMaxHealth(1150);
				        		} else if (lvl == 23){ player.setMaxHealth(1200);
				        		} else if (lvl == 24){ player.setMaxHealth(1250);
				        		} else if (lvl == 25){ player.setMaxHealth(1300);
				        		}
				        		player.sendMessage(ChatColor.WHITE + "Your level has been set to " + 
				        		     ChatColor.GREEN + lvl + ChatColor.WHITE + "!");
			        		} else {
			        			cs.sendMessage(ChatColor.RED + "Invalid syntax. Use /setLevel <1-25>.");
			        		}
			        	} catch (Exception e){
			        		cs.sendMessage(ChatColor.RED + "Invalid syntax. Use /setLevel <1-25>.");
			        	}
			        } else {
			        	cs.sendMessage(ChatColor.RED + "Invalid syntax. Use /setLevel <1-25>.");
			        }
				} else {
					cs.sendMessage(ChatColor.RED + "Invalid permissions.");
				}
			}
		}
		return true;
	}
	
}
