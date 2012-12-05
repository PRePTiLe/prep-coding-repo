package com.github.preptile.mothernature;


import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import org.bukkit.inventory.PlayerInventory;

public class PrepListener implements Listener

{

	
	
	public PrepListener(GreenThumbs plugin) 
	
	{
		
        // Register Listener
       
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
        
	}

	// ----------------------------------------------------------------------------- //
	
    @EventHandler(priority=EventPriority.MONITOR)
    public void onPlayerMagic(PlayerInteractEvent e) 
    
    {
    	
        Player player = e.getPlayer();
        PlayerInventory pi = player.getInventory();
        
        // --------------------------------------------------------------------------//
        
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) // Catch If Right Clicked On Block
        
        {   
        	
        	if(pi.getItemInHand().getTypeId() == 294) // Check If Gold_Hoe (item 294) was used in hand
        
        	{
        		
        		if(e.getClickedBlock().getType() == Material.SAPLING) // Check If The Block Clicked consisted of a Sapling
                	
            	{
            		    
        			Block TheBlock = e.getClickedBlock();
        			
        		    byte MN_TreeType = 0;
        			TreeType MN_TreeGrowType;                      // These Sets Up Variables For Later
        			MN_TreeGrowType = TreeType.BIG_TREE;
        		    
        			Random RandomGen = new Random();
    				int RandomNumber = RandomGen.nextInt(12);  // These Randomizes a value used for tree base-lenght
    				RandomNumber = RandomNumber + 1;
        		

    				// ------ TreeTypeCheckIfs are here ----- //
    				
        			if (TheBlock.getData() == 0)
        			
        			{
        				
        				MN_TreeType = 0;
        				MN_TreeGrowType = TreeType.BIG_TREE;

        			}
        		
        			// ------------------------------- //
        			
        			if (TheBlock.getData() == 1)
        			
        			{
        				
        				MN_TreeType = 1;
        				MN_TreeGrowType = TreeType.REDWOOD;
        				
        			}
        			
        			// ------------------------------- //
        			
        			if (TheBlock.getData() == 2)
            			
        			{
        				
        				MN_TreeType = 2;
        				MN_TreeGrowType = TreeType.BIRCH;
        				
        			}
        			
        			// ------------------------------- //
        			
        			if (TheBlock.getData() == 3)
        			
        			{
	
        				MN_TreeType = 3;
        				MN_TreeGrowType = TreeType.SMALL_JUNGLE;
    
        			}
        			
        			// ------------------------------- //
        			
        			if (TheBlock.getData() == 4)
            			
        			{
        				
        				MN_TreeType = 0;
        				MN_TreeGrowType = TreeType.BIG_TREE;

        			}
        			
        			// ------------------------------------------------------------------------- //
        			
        			
        			
        			 // Set Up First Block-Replacement //
    				
    				TheBlock.setType(Material.LOG);
    				TheBlock.setData((byte) MN_TreeType);
    				
    				//Run Trough The Number Of Blocks Upward, Making Them WoodLogs
    				
    				for (int Runs = 1; Runs <= RandomNumber; ++Runs)
    				
    				{ 
    					
    					Block TheBlockNow = TheBlock.getRelative(BlockFace.UP,Runs);
        				
    					
    					// Check If Blocks above is anything other than AIR, If So, Revert Previous To Air and End Iteration
    					
    					if(TheBlockNow.getType() != Material.AIR)
    						
    					{

    						for (int RunsBack = 1; RunsBack <= Runs - 1; ++RunsBack)
    		    				
    	    				{ 
    							
    							TheBlockNow = TheBlock.getRelative(BlockFace.UP,RunsBack);
    							TheBlockNow.setType(Material.AIR);
    							
    	    				}
    						
    						Location BlockPlace = TheBlockNow.getLocation();	
    						World TheWorld = player.getWorld();
    						TheWorld.playSound(BlockPlace, Sound.WOOD_CLICK, 10, -3);
    						break;
    						
    					}
    					
    					/// Check And Revert Done ^

        				TheBlockNow.setType(Material.LOG);
        				TheBlockNow.setData((byte) MN_TreeType);            				            				       
        				
        				//When Done with height, switch top to dirt+sapling, make tree grow and switch back dirt to log
        				
        				if (Runs == RandomNumber)
        			   
        				{ 
        					
        					TheBlockNow = TheBlock.getRelative(BlockFace.UP,Runs);
        					TheBlockNow.setType(Material.DIRT);
        					
        					TheBlockNow = TheBlockNow.getRelative(BlockFace.UP,1);
        					
        					// Check Again If Blocks above is anything other than AIR, If So, Revert Previous To Air and End Iteration
        					
        					if(TheBlockNow.getType() != Material.AIR)
        						
        					{

        						for (int RunsBack = 1; RunsBack <= Runs; ++RunsBack)
        		    				
        	    				{ 
        							
        							TheBlockNow = TheBlock.getRelative(BlockFace.UP,RunsBack);
        							TheBlockNow.setType(Material.AIR);
        						
        	    				}
        						
        						Location BlockPlace = TheBlockNow.getLocation();	
        						World TheWorld = player.getWorld();
        						TheWorld.playSound(BlockPlace, Sound.WOOD_CLICK, 10, -4);
        						break;
        						
        					}
        					
        					//// Check And Revert Done ^
        					
        					TheBlockNow.setType(Material.SAPLING);
        
        					
        					Location BlockPlace = TheBlockNow.getLocation();					
        					
        					TheBlockNow.setTypeId(0);
        					
        					TheBlockNow.getWorld().generateTree(BlockPlace, MN_TreeGrowType);
        					
        					
        					TheBlockNow = TheBlock.getRelative(BlockFace.UP,Runs);
        					TheBlockNow.setType(Material.LOG);
        					TheBlockNow.setData((byte) MN_TreeType);
        					
        					
        					World TheWorld = player.getWorld();
        					TheWorld.playSound(BlockPlace, Sound.FIZZ, 10, -2);
        					// short TheItem = pi.getItemInHand().getDurability();
        				  
        					//player.getItemInHand().getDurability();
        					
        					player.getItemInHand().setDurability((short) +1);
        					
    					
        				}
        				
    				}
        			
                } // --------- Sapling Control End --------
        		
        	}
        	
        }
               
    }
	
}
