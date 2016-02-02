package com.zeralin.sao;

import java.util.Arrays;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class BossMechanics implements Listener{

	public Main main;
	
	public BossMechanics(Main plugin){
		main = plugin;
	}

	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent e){
		    if (e.getEntity() instanceof Skeleton){
			Skeleton mob = (Skeleton) e.getEntity();
			if (mob.getSkeletonType() == SkeletonType.WITHER){
				if (mob.getCustomName().equalsIgnoreCase("Illfang")){
				mob.setCustomName(ChatColor.LIGHT_PURPLE + "Illfang the Kobold Lord");
				Random random = new Random();
				int hp = random.nextInt(200) + 601;
				mob.setMaxHealth(hp);
				mob.setHealth(10);
				mob.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET));
				mob.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
				mob.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
				mob.getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS));
				mob.getEquipment().setItemInHand(new ItemStack(Material.STONE_SWORD));
			  }
			}
		}
	}
	
	@EventHandler
	public void onDeath(EntityDeathEvent e){
		if (e.getEntity() instanceof Skeleton){
			Skeleton mob = (Skeleton) e.getEntity();
			if (mob.getSkeletonType() == SkeletonType.WITHER){
				if (mob.getCustomName().equalsIgnoreCase(ChatColor.LIGHT_PURPLE + "Illfang the Kobold Lord")){
					Random random = new Random();
					int drop = random.nextInt(2) + 1;
					if (drop == 1){
						ItemStack cape = new ItemStack(Material.LEATHER_CHESTPLATE);
						ItemMeta capeMeta = cape.getItemMeta();
						int hp = random.nextInt(100) + 176;
						capeMeta.setDisplayName(ChatColor.AQUA + "Coat of Midnight");
						capeMeta.setLore(Arrays.asList(ChatColor.GREEN + "Health: " + hp, 
								                       ChatColor.GREEN + "// other stats being coded",
								                       ChatColor.LIGHT_PURPLE + "Rare Item"));
						cape.setItemMeta(capeMeta);
						
						e.getDrops().add(cape);
					} else {
						
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e){
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Skeleton){
			 Skeleton mob = (Skeleton) e.getDamager();
			if (mob.getSkeletonType() == SkeletonType.WITHER){
			if (mob.getCustomName().equalsIgnoreCase(ChatColor.LIGHT_PURPLE + "Illfang The Kobold Lord")){
				Random random = new Random();
				int dmg = random.nextInt(120) + 51;
				e.setDamage(dmg);
			  }
			}
		}
	}
	
}
