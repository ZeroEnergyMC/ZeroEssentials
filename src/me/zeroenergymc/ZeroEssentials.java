package me.zeroenergymc;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;




public class ZeroEssentials extends JavaPlugin implements Listener {
	
	private PlayerListener PlayerListener = new PlayerListener(this);
	
	Logger consoleLogger = Bukkit.getLogger();
	String pl = "ZeroEssentials";
	String ver = "1.0";
	
	
	public void onEnable() {
		consoleLogger.info(pl + " enabled version: " + ver);
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.PlayerListener, this);
	}
	
	public void onDisable() {
		consoleLogger.severe(pl + " has been disabled!");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		
		if (cmd.getName().equalsIgnoreCase("vote")){
			player.sendMessage(ChatColor.GREEN + "You can vote for the server for rewards! Click the link below.");
			player.sendMessage(ChatColor.AQUA + "http://is.gd/ALeA6j");
		}
		
		if (cmd.getName().equalsIgnoreCase("hd")){
			
			if (args.length == 0){
					player.sendMessage(ChatColor.AQUA + "Help");
					player.sendMessage(ChatColor.GOLD + "===========================");
					player.sendMessage(ChatColor.AQUA +"Commands:");
					player.sendMessage(ChatColor.GREEN + "/hd spawn - Spawn's a players head!");
					player.sendMessage(ChatColor.GREEN + "/hd wear - Wear's a players head!");
					
			} else if (args.length >= 0){
			
			if (args[0].equals("spawn")) {
				
			if (args.length == 1){
				player.sendMessage(ChatColor.GREEN + "You have received your own head!");
				ItemStack im = new ItemStack(Material.SKULL_ITEM);
				SkullMeta sm = (SkullMeta) im.getItemMeta();
				sm.setOwner(sender.getName());
				im.setItemMeta(sm);
				im.setDurability((short)3);
				player.getInventory().addItem(im);
			}
			else

				player.sendMessage(ChatColor.GREEN + "You have received " + args[1] + "'s head!");
				ItemStack im = new ItemStack(Material.SKULL_ITEM);
				SkullMeta sm = (SkullMeta) im.getItemMeta();
				sm.setOwner(args[1]);
				im.setItemMeta(sm);
				im.setDurability((short)3);
				player.getInventory().addItem(im);
			}
			
			else if(args[0].equals("wear")) {
				if (args.length == 1){
					player.sendMessage(ChatColor.RED + "You cannot wear your own head!");
				}
				if (args.length == 2){
					player.sendMessage(ChatColor.GREEN + "You're now wearing " + args[1] + "'s head!");
					ItemStack im = new ItemStack(Material.SKULL_ITEM);
					SkullMeta sm = (SkullMeta) im.getItemMeta();
					sm.setOwner(args[1]);
					im.setItemMeta(sm);
					im.setDurability((short)3);
					player.getInventory().setHelmet(im);
				}
			}
			} 


			}
		
		if (cmd.getName().equalsIgnoreCase("heal"))
		{
			player.setHealth(20D);
			player.sendMessage(ChatColor.GREEN + "You have been healed!");
		}
		return false;
	}
}



