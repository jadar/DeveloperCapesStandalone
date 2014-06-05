/**
 * DeveloperCapes by Jadar
 * License: MIT License (https://raw.github.com/jadar/DeveloperCapes/master/LICENSE)
 * version 3.0
 */
package com.jadarstudios.developercapes.standalone;

import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.jadarstudios.developercapes.DevCapes;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

@Mod(name="DeveloperCapes", modid="DevCapesStandalone", version="${version}")
public class DevCapesStandalone {

	@Instance
	public static DevCapesStandalone instance;
    public static Logger logger;

    public String capeConfigUrl;
    public String modId;

    @EventHandler
    public void preLoad(FMLPreInitializationEvent event) {
        this.logger = event.getModLog();
        this.modId = event.getModMetadata().modId;

        InputStream is = getClass().getResourceAsStream("/capeInfo.json");
        InputStreamReader reader = new InputStreamReader(is);
        try {
            Gson gson = new Gson();

            HashMap map = gson.fromJson(reader, HashMap.class);

            Object urlStringObject = map.get("capeConfigUrl");
            if (urlStringObject != null && (urlStringObject instanceof String)) {
                capeConfigUrl = Strings.nullToEmpty((String)urlStringObject);
            } else {
                throw new JsonParseException("Could not find key 'capeConfigUrl'");
            }
        } catch (JsonParseException e) {
            logger.error("Failed to parse capeInfo.json. This means you could have a corrupt mod jar.");
            e.printStackTrace();
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }

    }



	/**
	 * Sets up DeveloperCapes.
	 *
	 * @param event
	 */
	@EventHandler
	public void load(FMLInitializationEvent event) {
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
        {
            logger.info("Initializing DevCapes Standalone.");
            if (this.capeConfigUrl == null) return;
            DevCapes.getInstance().registerConfig(this.capeConfigUrl, this.modId);
        }
	}
	
}
