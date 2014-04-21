package io.github.austinv11.PickaxeTweak;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ItemSearcher {
	Material[] blacklist = {Material.WOOD_PICKAXE, Material.IRON_PICKAXE, Material.GOLD_PICKAXE, Material.DIAMOND_PICKAXE, Material.AIR};//TOOD update
	public int getUsableItem(Player player, int slot){
		int k = slot + 1;
		boolean r = true;
		for (int j = 0; j < blacklist.length; j++){
			if (player.getInventory().getItem(k) == null){
				r = false;
			}else if (player.getInventory().getItem(k).getType() == blacklist[j] || player.getInventory().getItem(k).getType().isBlock() == false){
				r = false;
			}
		}
		if (r == true){
			return k;
		}else{
			return 0;
		}
	}
}