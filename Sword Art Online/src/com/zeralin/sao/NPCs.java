package com.zeralin.sao;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class NPCs implements Listener{

    public Main main;
    
    public NPCs(Main plugin){
    	main = plugin;
    }
	
    @EventHandler
    public void onNPCTalk(PlayerInteractEntityEvent e){
    	if (e.getRightClicked() instanceof HumanEntity && e.getPlayer() instanceof Player){
    		HumanEntity npc = (HumanEntity) e.getRightClicked();
    		if (npc.getName().equalsIgnoreCase("Floor Teleporter")){
    		Player player = e.getPlayer();
    		Inventory inv = Bukkit.getServer().createInventory(null, 9, "Floor Teleporter");
    			
    		ItemStack town = new ItemStack(Material.ENDER_PEARL);
    		ItemMeta townMeta = town.getItemMeta();
    		townMeta.setDisplayName(ChatColor.GREEN + "Town of Beginnings");
    		townMeta.setLore(Arrays.asList(ChatColor.WHITE + "The starting town."));
    		town.setItemMeta(townMeta);
    		
    		ItemStack floor1 = new ItemStack(Material.ENDER_PEARL);
    		ItemMeta floor1Meta = floor1.getItemMeta();
    		floor1Meta.setDisplayName(ChatColor.GREEN + "Floor 1");
    		floor1Meta.setLore(Arrays.asList(ChatColor.WHITE + "Recommended level: 1 - 5"));
    		floor1.setItemMeta(floor1Meta);
    		
    		ItemStack floor2 = new ItemStack(Material.ENDER_PEARL);
    		ItemMeta floor2Meta = floor1.getItemMeta();
    		floor2Meta.setDisplayName(ChatColor.GREEN + "Floor 2");
    		floor2Meta.setLore(Arrays.asList(ChatColor.WHITE + "Recommended level: 5 - 10"));
    		floor2.setItemMeta(floor2Meta);
    		
    		ItemStack floor3 = new ItemStack(Material.ENDER_PEARL);
    		ItemMeta floor3Meta = floor1.getItemMeta();
    		floor3Meta.setDisplayName(ChatColor.GREEN + "Floor 3");
    		floor3Meta.setLore(Arrays.asList(ChatColor.WHITE + "Recommended level: 10 - 15"));
    		floor3.setItemMeta(floor3Meta);
    		
    		ItemStack floor4 = new ItemStack(Material.ENDER_PEARL);
    		ItemMeta floor4Meta = floor1.getItemMeta();
    		floor4Meta.setDisplayName(ChatColor.GREEN + "Floor 4");
    		floor4Meta.setLore(Arrays.asList(ChatColor.WHITE + "Recommended level: 15 - 20"));
    		floor4.setItemMeta(floor4Meta);
    		
    		ItemStack floor5 = new ItemStack(Material.ENDER_PEARL);
    		ItemMeta floor5Meta = floor1.getItemMeta();
    		floor5Meta.setDisplayName(ChatColor.GREEN + "Floor 5");
    		floor5Meta.setLore(Arrays.asList(ChatColor.WHITE + "Recommended level: 20 - 25"));
    		floor5.setItemMeta(floor5Meta);
    		
    			inv.setItem(0, town);
    			inv.setItem(1, floor1);
    			inv.setItem(2, floor2);
    			inv.setItem(3, floor3);
    			inv.setItem(4, floor4);
    			inv.setItem(5, floor5);
    			
    			player.openInventory(inv);
    		}
         }
      }
   }