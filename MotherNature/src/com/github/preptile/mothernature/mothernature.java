package com.github.preptile.mothernature;

import java.lang.String;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import com.github.preptile.mothernature.PrepListener;


public final class mothernature extends JavaPlugin

{	
	
	protected String motd="";
	
	@Override
	public void onEnable()
	
	{
		loadConfig();
		
		String motd = getConfig().getString("strings.motd");
		PrepListener eventHandler = new PrepListener(motd);
		getServer().getPluginManager().registerEvents(eventHandler, this);
		getLogger().info("Plugin MotherNature Has Been Enabled");	
	}

	@Override
	public void onDisable()
	
	{
		getLogger().info("Plugin MotherNature Has Been Disabled");
	}
	
	protected void loadConfig() {

		getConfig().addDefault("strings.motd", "");

		getConfig().options().copyDefaults(true);

		saveConfig();

	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    
	{
		if(cmd.getName().equalsIgnoreCase("MN_motd")) // If the player typed /basic then do the following...
	        
    	{       
			if (sender instanceof Player)
			
			{
				sender.sendMessage("");
				sender.sendMessage(motd);
				sender.sendMessage("");
				
				return true;
			}
			
			else
			
			{
				sender.sendMessage("This command can only be run by an actual Player");
			}
			
			return true;
    	}
		
		return false;
		
	}
	
	
	
	

}

