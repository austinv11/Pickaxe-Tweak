package io.github.austinv11.PickaxeTweak;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ItemSearcher {
	Material[] blacklist = {Material.DIAMOND_PICKAXE};//TOOD update
	public int getUsableItem(Player player, int slot){
		int k = slot + 1;
		for (int j = 0; j < blacklist.length; j++){
			if (player.getInventory().getItem(k).getType() != blacklist[j]){
				return k;
			}
		}
		for (int i =  slot; i < 9; i++){
			if (player.getInventory().getItem(i).getType() == Material.TORCH){
				return i;
			}
		}
		return 0;
	}
}