package vg.civcraft.mc.namelayer.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import vg.civcraft.mc.namelayer.group.Group;

public class PlayerListener implements Listener{

	private static Map<UUID, List<Group>> notifications = new HashMap<UUID, List<Group>>();
	
	@EventHandler(priority=EventPriority.LOWEST)
	public void playerJoinEvent(PlayerJoinEvent event){
		Player p = event.getPlayer();
		UUID uuid = p.getUniqueId();
		if (!notifications.containsKey(uuid))
			return;
		String x = "You have been invited to the following groups while you were away: ";
		for (Group g: notifications.get(uuid)){
			x += g.getName() + ", ";
		}
		x = x.substring(0, x.length()- 2);
		x += ".";
		p.sendMessage(ChatColor.YELLOW + x);
	}
	
	public static void addNotification(UUID u, Group g){
		if (!notifications.containsKey(u))
			notifications.put(u, new ArrayList<Group>());
		notifications.get(u).add(g);
	}
	
	public static void removeNotification(UUID u, Group g){
		if (!notifications.containsKey(u))
			notifications.put(u, new ArrayList<Group>());
		notifications.get(u).remove(g);
	}
}