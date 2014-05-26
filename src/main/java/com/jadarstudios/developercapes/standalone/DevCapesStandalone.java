/**
 * DeveloperCapes by Jadar
 * License: MIT License (https://raw.github.com/jadar/DeveloperCapes/master/LICENSE)
 * version 3.0
 */
package com.jadarstudios.developercapes.standalone;

import com.jadarstudios.developercapes.DevCapes;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.relauncher.Side;

import java.io.InputStream;

@Mod(name="DeveloperCapes", modid="devcapesstandalone", version="${version}")
public class DevCapesStandalone {

	@Instance
	public static DevCapesStandalone instance;

	/**
	 * Sets up DeveloperCapes.
	 *
	 * @param event
	 */
	@EventHandler
	public void load(FMLInitializationEvent event) {
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
        {
            DevCapes.logger.info("Initializing DevCapes Standalone.");
            InputStream stream = this.getClass().getResourceAsStream("/capeInfo.json");
            if (stream == null) return;
            DevCapes.getInstance().registerConfig(stream, "devcapesstandalone");
        }
	}
	
}
