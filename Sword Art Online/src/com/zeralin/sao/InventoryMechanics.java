package com.zeralin.sao;

import java.util.Arrays;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import net.md_5.bungee.api.ChatColor;

public class InventoryMechanics implements Listener{
	
	public Main main;
	
	public InventoryMechanics(Main plugin){
	     main = plugin;
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e){
		ItemMeta itemMeta = e.getItemDrop().getItemStack().getItemMeta();
		
		if (itemMeta.hasDisplayName() && 
				itemMeta.getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Character Menu")){
			e.setCancelled(true);
		}	
		
		if (itemMeta.hasDisplayName() && 
				itemMeta.getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Gold Storage")){
			e.setCancelled(true);
		}	
	}
	
	@EventHandler
	public void onInvOpen(InventoryOpenEvent e){
		Player player = (Player) e.getPlayer();
		player.updateInventory();
		player.saveData();
	}
	
	public void openMenu(Player player){
	      Inventory inv = Bukkit.createInventory(null, 9, "Character Menu");
	      
	      ItemStack info = new ItemStack(Material.BOOK);
	      ItemMeta infoMeta = info.getItemMeta();
	      infoMeta.setDisplayName(ChatColor.GREEN + "Player Information");
	      infoMeta.setLore(Arrays.asList(ChatColor.WHITE + "Shows your HP and other stats!"));
	      info.setItemMeta(infoMeta);
	      
	      ItemStack friends = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
	      SkullMeta friendsMeta = (SkullMeta) friends.getItemMeta();
	      friendsMeta.setOwner(player.getName());
	      friendsMeta.setDisplayName(ChatColor.GREEN + "Friends");
	      friendsMeta.setLore(Arrays.asList(ChatColor.WHITE + "Check if your friends are online!"));
	      friends.setItemMeta(friendsMeta);
	      
	      ItemStack options = new ItemStack(Material.COMPASS);
	      ItemMeta optionsMeta = options.getItemMeta();
	      optionsMeta.setDisplayName(ChatColor.GREEN + "Options");
	      optionsMeta.setLore(Arrays.asList(ChatColor.WHITE + "Ask for help, or logout through this menu!"));
	      options.setItemMeta(optionsMeta);
	      
	      inv.setItem(2, info);
	      inv.setItem(4, friends);
	      inv.setItem(6, options);
	      player.openInventory(inv);
	}
	
	@EventHandler
	public void onMenuClick(InventoryClickEvent e){
		
		if (e.getInventory().getTitle().equalsIgnoreCase("Character Menu")){
			e.setCancelled(true);
		}
		
		if (e.getInventory().getTitle().equalsIgnoreCase("Floor Teleporter")){
			e.setCancelled(true);
		}
		
		if (e.getInventory().getTitle().equalsIgnoreCase("Player Stats")){
			e.setCancelled(true);
		}
		
		if (e.getInventory().getTitle().equalsIgnoreCase("Options")){
			e.setCancelled(true);
		}
		
		if (e.getInventory().getTitle().equalsIgnoreCase("Gold Storage")){
			e.setCancelled(true);
		  }
	}
	
	@EventHandler
	public void onBack(InventoryClickEvent e){
		if (e.getInventory().getTitle().equalsIgnoreCase("Player Stats")){
			Player player = (Player) e.getWhoClicked();
			if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR){
			  if (e.getCurrentItem().getType() == Material.ARROW){
				openMenu(player);
			}
		  }
		}
		
		if (e.getInventory().getTitle().equalsIgnoreCase("Options")){
			Player player = (Player) e.getWhoClicked();
			if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR){
			   if (e.getCurrentItem().getType() == Material.ARROW){
				openMenu(player);
			  }
			}
			if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR){
			   if (e.getCurrentItem().getType() == Material.DIAMOND){
				player.kickPlayer(ChatColor.GREEN + "Safely logged out.");
			  }
			}
		}
	}
	
	@EventHandler
	public void onStorageOpen(PlayerInteractEvent e){
		Player player = e.getPlayer();
		if (e.getItem() != null && e.getItem().getType() != Material.AIR){
			if (e.getItem().getType() == Material.INK_SACK){
				Inventory inv = Bukkit.createInventory(null, 9, "Gold Storage");
				
				ItemStack storage = new ItemStack(Material.DOUBLE_PLANT);
				ItemMeta storageMeta = storage.getItemMeta();
				storageMeta.setDisplayName(ChatColor.YELLOW + "Gold");
				storageMeta.setLore(Arrays.asList(ChatColor.GREEN + "Gold Amount: 0"));
				storage.setItemMeta(storageMeta);
				
				inv.setItem(4, storage);
				player.openInventory(inv);
			}
		}
	}
	
	@EventHandler
	public void onMenuOpen(InventoryClickEvent e){
		if (e.getInventory().getTitle().equalsIgnoreCase("Character Menu")){
			Player player = (Player) e.getWhoClicked();
			if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR){
			  if (e.getCurrentItem().getItemMeta().hasDisplayName() && 
					e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Player Information")){
			      Inventory inv = Bukkit.getServer().createInventory(null, 9, "Player Stats");
			      
			      ItemStack stats = new ItemStack(Material.BOOK);
			      ItemMeta statsMeta = stats.getItemMeta();
			      statsMeta.setDisplayName(ChatColor.GREEN + "Player Stats");
			      statsMeta.setLore(Arrays.asList(ChatColor.WHITE + "Health: " + 
			           (int) player.getHealth() + " / " + (int) player.getMaxHealth(), 
			                 ChatColor.WHITE + "Health Regen: " + (int) player.getMaxHealth() / 20));
			      stats.setItemMeta(statsMeta);
			      
			      ItemStack back = new ItemStack(Material.ARROW);
			      ItemMeta backMeta = back.getItemMeta();
			      backMeta.setDisplayName(ChatColor.GREEN + "Back");
			      backMeta.setLore(Arrays.asList(ChatColor.WHITE + "Go back to the main menu."));
			      back.setItemMeta(backMeta);
			      
			      inv.setItem(4, stats);
			      inv.setItem(8, back);
			      player.openInventory(inv);
			  }
			  if (e.getCurrentItem().getItemMeta().hasDisplayName() &&
					  e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Friends")){
				 player.sendMessage(ChatColor.RED + "Coming soon!"); 
			  }
			  if (e.getCurrentItem().getItemMeta().hasDisplayName() &&
					  e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Options")){
				  Inventory inv = Bukkit.createInventory(null, 9, "Options");
				  
				  ItemStack log = new ItemStack(Material.DIAMOND);
				  ItemMeta logMeta = log.getItemMeta();
				  logMeta.setDisplayName(ChatColor.GREEN + "Logout");
				  logMeta.setLore(Arrays.asList(ChatColor.WHITE + "It logs you out safely."));
				  log.setItemMeta(logMeta);
				  
				  ItemStack back = new ItemStack(Material.ARROW);
			      ItemMeta backMeta = back.getItemMeta();
			      backMeta.setDisplayName(ChatColor.GREEN + "Back");
			      backMeta.setLore(Arrays.asList(ChatColor.WHITE + "Go back to the main menu."));
			      back.setItemMeta(backMeta);
			      
			      player.openInventory(inv);
				  inv.setItem(4, log);
				  inv.setItem(8, back);
			  }
			}
		}
	}
	
	@EventHandler
	public void onTeleporterOpen(InventoryClickEvent e){
		  if (e.getInventory().getTitle().equalsIgnoreCase("Floor Teleporter")){
			  Player player = (Player) e.getWhoClicked();
			  
			  if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR){
		    if (e.getCurrentItem().getItemMeta().hasDisplayName() && 
		    	 e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Town of Beginnings")){
			   main.floorFunctions.goToSpawn(player);
			} else if (e.getCurrentItem().getItemMeta().hasDisplayName() && 
		    	 e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Floor 1")){
		    	main.floorFunctions.goToFloor1(player);
	    	} else if (e.getCurrentItem().getItemMeta().hasDisplayName() && 
		    	 e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Floor 2")){
		       main.floorFunctions.goToFloor2(player);
	    	} else if (e.getCurrentItem().getItemMeta().hasDisplayName() && 
		    	 e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Floor 3")){
		        main.floorFunctions.goToFloor3(player);
	    	} else if (e.getCurrentItem().getItemMeta().hasDisplayName() && 
		    	 e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Floor 4")){
	    		main.floorFunctions.goToFloor4(player);
	    	} else if (e.getCurrentItem().getItemMeta().hasDisplayName() && 
		    	 e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Floor 5")){
	    		main.floorFunctions.goToFloor5(player);
	    	     }
			  }
			   } 
			  }

	
	@EventHandler
	public void onMenuClick(PlayerInteractEvent e){
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
		 if (e.getItem() != null){
		if (e.getItem().getType() == Material.BOOK) {
		ItemMeta itemMeta = e.getItem().getItemMeta();
		if (itemMeta.hasDisplayName() && 
				itemMeta.getDisplayName().equals(ChatColor.GREEN + "Character Menu")){
				openMenu(e.getPlayer());		
			}
		  }
		 }
		}
	 }

	public void getSpawnKit(Player player){
		ItemStack wep = new ItemStack(Material.WOOD_SWORD);
		ItemMeta wepMeta = wep.getItemMeta();
		Random random = new Random();
		int min = random.nextInt(2) + 4;;
		int max = random.nextInt(3) + 5;
		wepMeta.setDisplayName(ChatColor.WHITE + "Starting Sword");
		wepMeta.setLore(Arrays.asList(ChatColor.GREEN + "Damage: " + min + " - " + max));
		wep.setItemMeta(wepMeta);
		
		ItemStack money = new ItemStack(Material.INK_SACK);
		ItemMeta moneyMeta = money.getItemMeta();
		moneyMeta.setDisplayName(ChatColor.GREEN + "Gold Storage");
		moneyMeta.setLore(Arrays.asList(ChatColor.WHITE + "Where all your gold is stored."));
		money.setItemMeta(moneyMeta);
		
		ItemStack info = new ItemStack(Material.BOOK);
		ItemMeta infoMeta = info.getItemMeta();
		infoMeta.setDisplayName(ChatColor.GREEN + "Character Menu");
		infoMeta.setLore(Arrays.asList(ChatColor.WHITE + "Use this menu to look at stats, friends and other options!"));
		info.setItemMeta(infoMeta);
		
	    player.getInventory().setItem(0, wep);
		player.getInventory().setItem(7, money);
		player.getInventory().setItem(8, info);
	}
}