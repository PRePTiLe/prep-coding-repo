package com.github.preptile.mothernature;

import java.lang.String;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class PrepListener implements Listener

{
	
	protected String motd="";

	public PrepListener(String motd) {

		this.motd=motd;

	}
	
	@EventHandler(priority=EventPriority.MONITOR)
	public void onPlayerJoinEvent(PlayerJoinEvent e)

	{
		e.getPlayer().sendMessage("");
		e.getPlayer().sendMessage(motd);
		e.getPlayer().sendMessage("");	
	}
	
}
