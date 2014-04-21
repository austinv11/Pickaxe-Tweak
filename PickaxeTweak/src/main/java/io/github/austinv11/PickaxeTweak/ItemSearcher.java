package io.github.austinv11.PickaxeTweak;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ItemSearcher {
	Material[] blacklist = {Material.DIAMOND_PICKAXE};//TOOD update
	public int getUsableItem(Player player, int slot){
		for (int i = slot; i < 9; i++){
			for (int j = 0; j < blacklist.length; j++){
				if (player.getInventory().getItem(i).getType() != blacklist[j]){
					return i;
				}
			}
		}
		return 0;
	}
}
