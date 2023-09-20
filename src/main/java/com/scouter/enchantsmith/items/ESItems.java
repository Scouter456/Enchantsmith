package com.scouter.enchantsmith.items;


import com.scouter.enchantsmith.EnchantSmith;
import com.scouter.enchantsmith.blocks.ESBlocks;
import com.scouter.enchantsmith.setup.Registration;
import com.scouter.enchantsmith.utils.ESTags;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.world.item.BannerPatternItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.scouter.enchantsmith.EnchantSmith.prefix;


public class ESItems {
    public static final Logger LOGGER = LoggerFactory.getLogger("enchantsmith");

    

    //From Blocks

    public static final Item ENCHANTSMITH_CARPET = registerBlockItem(ESBlocks.ENCHANTSMITH_CARPET);
    //FISHINGROD

    public static final Item ENCHANTSMITH_BANNER_PATTERN = registerItem("enchantsmith_banner_pattern",new BannerPatternItem(ESTags.BannerPatterns.ENCHANTSMITH_BANNER, new FabricItemSettings().stacksTo(1).tab(CreativeModeTab.TAB_MISC)));
    public static final Item ENCHANTSMITH_SPIRAL_BANNER_PATTERN = registerItem("enchantsmith_spiral_banner_pattern",new BannerPatternItem(ESTags.BannerPatterns.ENCHANTSMITH_SPIRAL_BANNER, new FabricItemSettings().stacksTo(1).tab(CreativeModeTab.TAB_MISC)));
    public static final Item ENCHANTSMITH_BORDER_BANNER_PATTERN = registerItem("enchantsmith_border_banner_pattern",new BannerPatternItem(ESTags.BannerPatterns.ENCHANTSMITH_BORDER_BANNER, new FabricItemSettings().stacksTo(1).tab(CreativeModeTab.TAB_MISC)));
    public static final Item ENCHANTSMITH_NOISE_BANNER_PATTERN = registerItem("enchantsmith_noise_banner_pattern",new BannerPatternItem(ESTags.BannerPatterns.ENCHANTSMITH_NOISE_BANNER, new FabricItemSettings().stacksTo(1).tab(CreativeModeTab.TAB_MISC)));


    private static Item registerBlockItem(Block block){
        return Registry.register(Registry.ITEM, prefix(block.getDescriptionId().replace("block.enchantsmith.", "").toString()),
                new BlockItem(block, new FabricItemSettings().group(Registration.defaultBuilder)));
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, prefix(name), item);
    }


    public static void ITEMS(){
        LOGGER.info("Registering Items for " + EnchantSmith.MODID);
    }

}
