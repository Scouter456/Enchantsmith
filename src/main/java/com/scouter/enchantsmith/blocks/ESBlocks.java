package com.scouter.enchantsmith.blocks;

import com.mojang.logging.LogUtils;
import com.scouter.enchantsmith.EnchantSmith;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import org.slf4j.Logger;

import static com.scouter.enchantsmith.EnchantSmith.prefix;

public class ESBlocks {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final Block  ENCHANTSMITH_CARPET = registerBlock("enchantsmith_carpet", new CarpetBlock(FabricBlockSettings.of(Material.WOOL, MaterialColor.COLOR_RED).strength(0.2F).sound(SoundType.WOOL)));

    private static Block registerBlock(String name, Block block){
        return Registry.register(Registry.BLOCK, prefix(name), block);
    }
    public static void BLOCKS(){
        LOGGER.info("Registering Blocks for " + EnchantSmith.MODID);
    }
}
