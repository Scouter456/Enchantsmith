package com.scouter.enchantsmith.items;


import com.scouter.enchantsmith.EnchantSmith;
import com.scouter.enchantsmith.blocks.ESBlocks;
import com.scouter.enchantsmith.setup.Registration;
import com.scouter.enchantsmith.utils.ESTags;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ESItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EnchantSmith.MODID);

    //From Blocks

    public static final RegistryObject<Item> ENCHANTSMITH_CARPET = fromBlock(ESBlocks.ENCHANTSMITH_CARPET);
    //FISHINGROD

    public static final RegistryObject<Item> ENCHANTSMITH_BANNER_PATTERN = ITEMS.register("enchantsmith_banner_pattern",() -> new BannerPatternItem(ESTags.BannerPatterns.ENCHANTSMITH_BANNER, (new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> ENCHANTSMITH_SPIRAL_BANNER_PATTERN = ITEMS.register("enchantsmith_spiral_banner_pattern",() -> new BannerPatternItem(ESTags.BannerPatterns.ENCHANTSMITH_SPIRAL_BANNER, (new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> ENCHANTSMITH_BORDER_BANNER_PATTERN = ITEMS.register("enchantsmith_border_banner_pattern",() -> new BannerPatternItem(ESTags.BannerPatterns.ENCHANTSMITH_BORDER_BANNER, (new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> ENCHANTSMITH_NOISE_BANNER_PATTERN = ITEMS.register("enchantsmith_noise_banner_pattern",() -> new BannerPatternItem(ESTags.BannerPatterns.ENCHANTSMITH_NOISE_BANNER, (new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_MISC)));



    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), Registration.defaultBuilder()));
    }

    public static <B extends Block> RegistryObject<Item> fromBlockFireRes(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), Registration.defaultBuilder()));
    }


    public static CreativeModeTab creativeTab = new CreativeModeTab("enchantsmith") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ENCHANTSMITH_CARPET.get());
        }
    };

}
