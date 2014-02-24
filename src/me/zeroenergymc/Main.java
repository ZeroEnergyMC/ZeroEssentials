package me.zeroenergymc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.*;
import org.bukkit.event.player.*;
import org.bukkit.permissions.*;
import org.bukkit.plugin.java.JavaPlugin;
import static java.lang.System.out;



public class Main extends JavaPlugin implements Listener {

	private PlayerListener PlayerListener = new PlayerListener(this);

	Logger consoleLogger = Bukkit.getLogger();
	MyConfigManager manager;
	MyConfig playersConfig;
	String pl = "ZeroEssentials";
	String ver = "1.0";
	HashMap<String, MyConfig> playerConfig = new HashMap<String, MyConfig>();

	public void onEnable() {
		consoleLogger.info(pl + " enabled version: " + ver);
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.PlayerListener, this);
		manager = new MyConfigManager(this);
		try
		{
			playersConfig = manager.getNewConfig("Players.yml", new String[] {"ZeroEssentials", "by ZeroEnergyMC", "1.0 - Alpha"});
			consoleLogger.info("Players.yml config created!");
		}
		catch(InternalError exception)
		{
			consoleLogger.info("Error creating Players.yml!");
		}
	}

	public void onDisable() {
		consoleLogger.severe(pl + " has been disabled!");
	}


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("vote")){
			player.sendMessage(ChatColor.GREEN + "You can vote for the server for rewards! Click the link below.");
			player.sendMessage(ChatColor.AQUA + "http://is.gd/J2BK9S");
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
			player.setFoodLevel(20);
			player.sendMessage(ChatColor.GREEN + "You have been healed!");
		}

		if(cmd.getName().equalsIgnoreCase("warps")){
			
			Inventory powerinv = Bukkit.createInventory(null, 9, ChatColor.BLUE + "Warps");
			((Player) sender).openInventory(powerinv);
			
			ItemStack eye = new ItemStack(Material.EYE_OF_ENDER);
			ItemMeta warp1 = eye.getItemMeta();
			warp1.setDisplayName(ChatColor.AQUA + "The End");   
			List<String> lore = new ArrayList<String>();
			lore.add("Teleports you to the End!");
			warp1.setLore(lore);
			eye.setItemMeta(warp1);
			powerinv.addItem(eye);
		}
		
		if (cmd.getName().equalsIgnoreCase("help")) {
			if(sender instanceof Player){
		 		ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
				BookMeta bm = (BookMeta)book.getItemMeta();
				bm.addPage(ChatColor.GREEN + " == Legacy Miners ==  \n\n" + ChatColor.RED + "Welcome to the server! \n" + ChatColor.GOLD + "Owner: ZeroEnergyMC\nAdmin(s): qasw287\nModerator(s): End51" + ChatColor.BLUE + "\n\nMake sure you vote daily to receive rewards.\n\nBe active and friendly to achieve staff.");
				bm.setAuthor(ChatColor.RED + "Legacy King");
				bm.setTitle(ChatColor.GREEN + "== Legacy Miners ==");
				book.setItemMeta(bm);
				player.getInventory().addItem(book);
				consoleLogger.info(player.getName() + " ran the /help command.");
			}
		
	}
		return false;
	}
}



