package io.github.austinv11.PickaxeTweak;

import net.gravitydevelopment.updater.Updater;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class PickaxeTweak extends JavaPlugin implements Listener{
	String CURRENT_VERSION = "1.0.0"; //TODO REMEMBER TO UPDATE
	String CURRENT_GAME_VERSION = "CB 1.7.2-R0.3"; //TODO REMEMBER TO UPDATE
	int id = 78650; //TODO
	FileConfiguration config = getConfig();
	ItemSearcher search;
	LocationCalculator calc;
	@Override
    public void onEnable() {
		configInit(false);
		if (config.getBoolean("Options.setToDefault") == true){
			configInit(true);
		}
		if (config.getBoolean("Options.autoUpdater") == true){
			Updater updater = new Updater(this, id, this.getFile(), Updater.UpdateType.NO_DOWNLOAD, false);
			if (updater.getLatestGameVersion() == CURRENT_GAME_VERSION){
				Updater updaterAuto = new Updater(this, id, this.getFile(), Updater.UpdateType.DEFAULT, true);
			}
		}
		getServer().getPluginManager().registerEvents(this, this);
		search = new ItemSearcher();
		calc = new LocationCalculator();
		getLogger().info("Woot! Modded features in Vanilla!");
	}
	public void configInit(boolean revert){
		if (revert == false){
			config.addDefault("Options.autoUpdater", true);
			config.addDefault("Options.setToDefault", false);
			config.options().copyDefaults(true);
			saveConfig();
		}else{
			config.set("Options.autoUpdater", true);
			config.set("Options.setToDefault", false);
			saveConfig();
		}
	}
	@Override
    public void onDisable() {
		getLogger().info("It's fine, I won't take it personally :'(");
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockInteract(PlayerInteractEvent event){
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().isLiquid() == false && event.getClickedBlock().getType() != Material.AIR){
			if (event.getPlayer().getItemInHand().getType() == Material.WOOD_PICKAXE || event.getPlayer().getItemInHand().getType() == Material.STONE_PICKAXE || event.getPlayer().getItemInHand().getType() == Material.IRON_PICKAXE || event.getPlayer().getItemInHand().getType() == Material.GOLD_PICKAXE || event.getPlayer().getItemInHand().getType() == Material.DIAMOND_PICKAXE){
				int slot = event.getPlayer().getInventory().getHeldItemSlot();
				int usableSlot = search.getUsableItem(event.getPlayer(), slot);
				Material item = event.getPlayer().getInventory().getItem(usableSlot).getType();
				Location loc = event.getClickedBlock().getLocation().clone();
				Location placement = calc.getLoc(event.getBlockFace(), loc);
				if (placement != null && usableSlot != 0 && item != Material.AIR && item != null){
					placement.getBlock().setType(item);
					int itemNum = event.getPlayer().getInventory().getItem(usableSlot).getAmount();
					if (itemNum == 1){
						ItemStack clear = new ItemStack (Material.AIR);
						event.getPlayer().getInventory().setItem(usableSlot, clear);
						event.getPlayer().updateInventory();//FIXME
					}else{
						event.getPlayer().getInventory().getItem(usableSlot).setAmount(itemNum - 1);
						event.getPlayer().updateInventory(); //FIXME
					}
				}
			}
		}
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void onLogin(PlayerJoinEvent event){
		event.getPlayer().sendMessage("This server has the PickaxeTweak plugin installed, to learn how to use it, use the /pt-about command");
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("pt-about")) {
			sender.sendMessage("With the PickaxeTweak plugin installed, when you right click with a pickaxe in hand, you will use the item to the right of the pickaxe, just like in the Tinkers' Construct mod");
			return true;
		}
		return false;
	}
}
