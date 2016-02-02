package com.zeralin.sao;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.connorlinfoot.bountifulapi.BountifulAPI;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener{

	public Plugin plugin;
	public FloorFunctions floorFunctions;
	public InventoryMechanics inventoryMechanics;
	public CurrencyMechanics currencyMechanics;
	public HealthMechanics healthMechanics;
	
	@Override
	public void onEnable(){
		plugin = this;
		setupListeners();
		setupCommands();
	    setupClassReference();
	    
	    Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
 			@Override
 			public void run() {
 				for (Player player : Bukkit.getOnlinePlayers()){
 				BountifulAPI.sendActionBar(player, 
 				 ChatColor.WHITE + "HP " + (int) player.getHealth() + "/" + (int) player.getMaxHealth());
 			   }
 			}
 	    }, 1L, 1L);
	  }

	@Override
	public void onDisable(){
		plugin = null;
	}
	
	public void setupListeners(){
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(this, this);
		pm.registerEvents(new PlayerMechanics(this), this);
		pm.registerEvents(new ItemMechanics(this), this);
		pm.registerEvents(new FloorFunctions(this), this);
		pm.registerEvents(new CombatMechanics(this), this);
		pm.registerEvents(new InventoryMechanics(this), this);
		pm.registerEvents(new TeleportMechanics(this), this);
		pm.registerEvents(new MobMechanics(this), this);
		pm.registerEvents(new NPCs(this), this);
		pm.registerEvents(new HealthMechanics(this), this);
		pm.registerEvents(new CurrencyMechanics(this), this);
		pm.registerEvents(new LevelMechanics(this), this);
		pm.registerEvents(new BossMechanics(this), this);
	}
	
	public void setupCommands(){
		getCommand("setHealth").setExecutor(new HealthMechanics(this));
		getCommand("setLevel").setExecutor(new LevelMechanics(this));
	}
	
	public void setupClassReference(){
		floorFunctions = new FloorFunctions(this);
		inventoryMechanics = new InventoryMechanics(this);
        currencyMechanics = new CurrencyMechanics(this);
        healthMechanics = new HealthMechanics(this);
	}
	
	public Plugin getPlugin(){
		return plugin;
	}
	
	@EventHandler
	public void onServerPing(ServerListPingEvent e){
		e.setMotd(ChatColor.AQUA + "Welcome to Sword Craft Online!");
	}
}
