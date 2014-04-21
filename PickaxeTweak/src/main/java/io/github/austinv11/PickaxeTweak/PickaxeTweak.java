package io.github.austinv11.PickaxeTweak;

import net.gravitydevelopment.updater.Updater;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class PickaxeTweak extends JavaPlugin implements Listener{
	String CURRENT_VERSION = "1.0.0"; //TODO REMEMBER TO UPDATE
	String CURRENT_GAME_VERSION = "CB 1.7.2-R0.3"; //TODO REMEMBER TO UPDATE
	int id; //TODO
	FileConfiguration config = getConfig();
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
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("basic")) {
			return true;
		}
		return false;
	}
}
