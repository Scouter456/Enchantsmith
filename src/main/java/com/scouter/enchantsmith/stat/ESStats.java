package com.scouter.enchantsmith.stat;

import com.scouter.enchantsmith.EnchantSmith;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.minecraft.stats.Stats.CUSTOM;

public class ESStats {

    public static final Logger LOGGER = LoggerFactory.getLogger("enchantsmith");

    public static final ResourceLocation ENCHANTSMITH_USE_STAT = makeCustomStat("enchantsmith:enchantsmith_use", StatFormatter.DEFAULT);


    private static ResourceLocation makeCustomStat(String key, StatFormatter formatter) {
        ResourceLocation resourceLocation = new ResourceLocation(key);
        Registry.register(Registry.CUSTOM_STAT, key, resourceLocation);
        CUSTOM.get(resourceLocation, formatter);
        return resourceLocation;
    }
    public static void STAT(){
        LOGGER.info("Registering Stats for " + EnchantSmith.MODID);
    }
}
