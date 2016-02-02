package com.zeralin.sao;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.connorlinfoot.bountifulapi.BountifulAPI;

import net.md_5.bungee.api.ChatColor;

public class HealthMechanics implements Listener, CommandExecutor{

	public Main main;
	
	public HealthMechanics(Main plugin){
		main = plugin;
	}
	
	public int getHPFromLore(ItemStack item, String value){
		int health = 0;
		List<String> lore = item.getItemMeta().getLore();
		if (lore != null && lore.get(0).contains(value)){
			try {
				String hp = (String) lore.get(0).split(": ")[1];
				hp = ChatColor.stripColor(hp);
				health = Integer.parseInt(hp.trim());
			} catch (Exception e) {}
		}
		return health;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e){
		Player player = (Player) e.getWhoClicked();
		Bukkit.getScheduler().runTaskLater(main.getPlugin(), new Runnable(){
			@Override
			public void run() {
					checkHealth(player);
			}
		}, 1L);
	}
	
	public void checkHealth(Player player){
			PlayerInventory inv = player.getInventory();
			int lvl = player.getLevel();
			int HP = 0;
			if (lvl == 1){ HP = 100;		        				
    		} else if (lvl == 2){ HP = 150;
    		} else if (lvl == 3){ HP = 200;
    		} else if (lvl == 4){ HP = 250;
    		} else if (lvl == 5){ HP = 300;
    		} else if (lvl == 6){ HP = 350;
    		} else if (lvl == 7){ HP = 400;
    		} else if (lvl == 8){ HP = 450;
    		} else if (lvl == 9){ HP = 500;
    		} else if (lvl == 10){ HP = 550;
    		} else if (lvl == 11){ HP = 600;
    		} else if (lvl == 12){ HP = 650;
    		} else if (lvl == 13){ HP = 700;
    		} else if (lvl == 14){ HP = 750;
    		} else if (lvl == 15){ HP = 800;
    		} else if (lvl == 16){ HP = 850;
    		} else if (lvl == 17){ HP = 900;
    		} else if (lvl == 18){ HP = 950;
    		} else if (lvl == 19){ HP = 1000;
    		} else if (lvl == 20){ HP = 1050;
    		} else if (lvl == 21){ HP = 1100;
    		} else if (lvl == 22){ HP = 1150;
    		} else if (lvl == 23){ HP = 1200;
    		} else if (lvl == 24){ HP = 1250;
    		} else if (lvl == 25){ HP = 1300; }
			int health = 0;
			
			if (inv.getHelmet() != null && inv.getHelmet().getItemMeta().hasLore()){
				health = getHPFromLore(inv.getHelmet(), "Health");
				HP += health;
			}
			if (inv.getChestplate() != null && inv.getChestplate().getItemMeta().hasLore()){
				health = getHPFromLore(inv.getChestplate(), "Health");
				HP += health;
			}
			if (inv.getLeggings() != null && inv.getLeggings().getItemMeta().hasLore()){
				health = getHPFromLore(inv.getLeggings(), "Health");
				HP += health;
			}
			if (inv.getBoots() != null && inv.getBoots().getItemMeta().hasLore()){
				health = getHPFromLore(inv.getBoots(), "Health");
				HP += health;
			}
			
				 player.setMaxHealth(HP);	
	 }
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (cs instanceof Player){
			if (cmd.getName().equalsIgnoreCase("setHealth")){
				if (cs.isOp()){
					Player player = (Player) cs;
			        if (args.length == 1){
			        	try {
			        		int hp = Integer.parseInt(args[0]);
			        		if (!(hp < 1)){
				        		player.setMaxHealth(hp);
				        		player.setHealth(hp);
				        		player.sendMessage(ChatColor.WHITE + "Your health has been set to " + 
				        		     ChatColor.GREEN + hp + ChatColor.WHITE + "!");
			        		} else {
			        			cs.sendMessage(ChatColor.RED + "Invalid syntax. Use /setHealth <number>.");
			        		}
			        	} catch (Exception e){
			        		cs.sendMessage(ChatColor.RED + "Invalid syntax. Use /setHealth <number>.");
			        	}
			        } else {
			        	cs.sendMessage(ChatColor.RED + "Invalid syntax. Use /setHealth <number>.");
			        }
				} else {
					cs.sendMessage(ChatColor.RED + "Invalid permissions.");
			}
		}
	  }
		return true;
	}
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
    	 Bukkit.getScheduler().scheduleSyncRepeatingTask(main.getPlugin(), new Runnable(){
 			@Override
 			public void run() {
 				BountifulAPI.sendActionBar(e.getPlayer(), 
 				 ChatColor.WHITE + "HP " + (int) e.getPlayer().getHealth() + "/" + (int) e.getPlayer().getMaxHealth());
 			}
 	    }, 1L, 1L);
    }
	
	
	@EventHandler
	public void onRegen(EntityRegainHealthEvent e){
		if (e.getEntity() instanceof Player){
			Player player = (Player) e.getEntity();
				int HPs = (int) player.getMaxHealth() / 20;
			    e.setAmount(HPs);
			    player.sendMessage(ChatColor.GREEN + "+" + HPs + " HP");
			}
	}
	
}
