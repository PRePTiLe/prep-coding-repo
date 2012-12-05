package com.github.preptile.mothernature;

import java.lang.String;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import com.github.preptile.mothernature.PrepListener;


public final class GreenThumbs extends JavaPlugin

{	
	
	
	
	@Override
	public void onEnable()
	
	{
		// loadConfig();
		
	
		new PrepListener(this);
		
		getLogger().info("Plugin GreenThumbs By PReP Has Been Enabled");
		
	}

	@Override
	public void onDisable()
	
	{
		getLogger().info("Plugin GreenThumbs Has Been Disabled");
	}
	
	protected void loadConfig() {

		getConfig().addDefault("strings.motd", "");

		getConfig().options().copyDefaults(true);

		saveConfig();

	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    
	{
		if(cmd.getName().equalsIgnoreCase("gt_motd")) // If the player typed /basic then do the following...
	        
    	{       
			if (sender instanceof Player)
			
			{
				sender.sendMessage("");
				sender.sendMessage("GreenThumbs Plugin By PReP");
				sender.sendMessage("Golden Hoe Does Magic Stuff");
				sender.sendMessage("And More To Come");
				
				return true;
			}
			
			else
			
			{
				sender.sendMessage("This command should only be run by an actual Player");
			}
			
			return true;
    	}
		
		return false;
		
	}
	
	
	
	
}

