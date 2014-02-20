package me.zeroenergymc;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

	public Main plugin;

	public PlayerListener(Main instance){
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent e){
		Player player = e.getPlayer();
		e.setJoinMessage(ChatColor.GOLD + player.getName() + ChatColor.GREEN + " logged in!");
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerQuit(PlayerQuitEvent e){
		Player player = e.getPlayer();
		e.setQuitMessage(ChatColor.GOLD + player.getName() + ChatColor.RED + " logged out!");
	}
}