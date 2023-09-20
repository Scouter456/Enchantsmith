package com.scouter.enchantsmith.structures;

import com.scouter.enchantsmith.EnchantSmith;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.StructureType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.scouter.enchantsmith.EnchantSmith.prefix;

public class ESStructures {
    public static final Logger LOGGER = LoggerFactory.getLogger("enchantsmith");

    public static StructureType<EnchantSmithCamp> ENCHANTSMITH_CAMP = () -> EnchantSmithCamp.CODEC;

    public static void STRUCTURES(){
        Registry.register(Registry.STRUCTURE_TYPES, prefix("enchantsmith_camp"),  ENCHANTSMITH_CAMP);
        LOGGER.info("Registering Structures for " + EnchantSmith.MODID);
    }
}
