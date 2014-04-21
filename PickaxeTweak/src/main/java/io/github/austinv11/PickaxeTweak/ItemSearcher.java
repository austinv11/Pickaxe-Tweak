package io.github.austinv11.PickaxeTweak;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ItemSearcher {
	Material[] blacklist = {Material.DIAMOND_PICKAXE, Material.AIR};//TOOD update
	public int getUsableItem(Player player, int slot){
		int k = slot + 1;
		boolean r = true;
		for (int j = 0; j < blacklist.length; j++){
			if (player.getInventory().getItem(k) == null){
				r = false;
			}else if (player.getInventory().getItem(k).getType() == blacklist[j]){
				r = false;
			}
		}
		if (r == true){
			return k;
		}else{
			for (int i =  slot; i < 9; i++){
				if (player.getInventory().getItem(i).getType() == Material.TORCH){
					return i;
				}
			}
		}
		return 0;
	}
}