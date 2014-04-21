package io.github.austinv11.PickaxeTweak;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;

public class LocationCalculator {
	public Location getLoc(BlockFace bf, Location loc){
		if (bf == BlockFace.DOWN){
			Location newLoc = new Location(loc.getWorld(), loc.getX(), loc.getY() - 1, loc.getZ());
			if (newLoc.getBlock().getType() == Material.AIR){
				return newLoc;
			}else{
				return null;
			}
		}else if (bf == BlockFace.EAST){
			Location newLoc = new Location(loc.getWorld(), loc.getX() + 1, loc.getY(), loc.getZ());
			if (newLoc.getBlock().getType() == Material.AIR){
				return newLoc;
			}else{
				return null;
			}
		}else if (bf == BlockFace.NORTH){
			Location newLoc = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ() - 1);
			if (newLoc.getBlock().getType() == Material.AIR){
				return newLoc;
			}else{
				return null;
			}
		}else if (bf == BlockFace.SOUTH){
			Location newLoc = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ() + 1);
			if (newLoc.getBlock().getType() == Material.AIR){
				return newLoc;
			}else{
				return null;
			}
		}else if (bf == BlockFace.UP){
			Location newLoc = new Location(loc.getWorld(), loc.getX(), loc.getY() + 1, loc.getZ());
			if (newLoc.getBlock().getType() == Material.AIR){
				return newLoc;
			}else{
				return null;
			}
		}else if (bf == BlockFace.WEST){
			Location newLoc = new Location(loc.getWorld(), loc.getX() - 1, loc.getY(), loc.getZ());
			if (newLoc.getBlock().getType() == Material.AIR){
				return newLoc;
			}else{
				return null;
			}
		}else{
			Bukkit.getServer().getLogger().info("ERROR: UNKNOWN BLOCKFACE");
			return null;
		}
	}
}
