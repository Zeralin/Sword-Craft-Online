package com.zeralin.sao;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.PlayerInventory;

public class ItemMechanics implements Listener{

	public Main main;
	
	public ItemMechanics(Main plugin){
		main = plugin;
	}
	
	@EventHandler
	public void onDuraTake(EntityDamageByEntityEvent e){	
		if (e.getEntity() instanceof Player){
			Player player = (Player) e.getEntity();
			PlayerInventory inv = player.getInventory();
			
			if (inv.getHelmet() != null){
				inv.getHelmet().setDurability((short) 0);
				player.updateInventory();
			}
			if (inv.getChestplate() != null){
				inv.getChestplate().setDurability((short) 0);
				player.updateInventory();
			}
			if (inv.getLeggings() != null){
				inv.getLeggings().setDurability((short) 0);
				player.updateInventory();
			}
			if (inv.getBoots() != null){
				inv.getBoots().setDurability((short) 0);
				player.updateInventory();
			}
		}
	}
	
	@EventHandler
	public void onWeaponSwing(PlayerInteractEvent e){
		Player player = e.getPlayer();
		if (player.getItemInHand().getType() == Material.WOOD_AXE ||
			player.getItemInHand().getType() == Material.WOOD_SWORD ||
			player.getItemInHand().getType() == Material.STONE_AXE ||
			player.getItemInHand().getType() == Material.STONE_SWORD ||
			player.getItemInHand().getType() == Material.IRON_AXE ||
			player.getItemInHand().getType() == Material.IRON_SWORD ||
			player.getItemInHand().getType() == Material.DIAMOND_AXE ||
			player.getItemInHand().getType() == Material.DIAMOND_SWORD ||
			player.getItemInHand().getType() == Material.GOLD_AXE ||
			player.getItemInHand().getType() == Material.GOLD_SWORD){
			player.getItemInHand().setDurability((short) 0);
			if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK){
				player.updateInventory();
			}
		}
	}
	
	@EventHandler
	public void onItemCraft(CraftItemEvent e){
		e.setCancelled(true);
	}
}
