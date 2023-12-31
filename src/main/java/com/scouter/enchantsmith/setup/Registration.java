package com.scouter.enchantsmith.setup;

import com.mojang.logging.LogUtils;
import com.scouter.enchantsmith.advancements.ESAdvancementTriggers;
import com.scouter.enchantsmith.banners.ESBanners;
import com.scouter.enchantsmith.blocks.ESBlocks;
import com.scouter.enchantsmith.config.EnchantsmithConfig;
import com.scouter.enchantsmith.entity.villagerprofessions.VillagerProfessions;
import com.scouter.enchantsmith.items.ESItems;
import com.scouter.enchantsmith.loot_table.ESLootPoolEntry;
import com.scouter.enchantsmith.menu.ESMenus;
import com.scouter.enchantsmith.stat.ESStats;
import com.scouter.enchantsmith.structures.ESStructures;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


public class Registration {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static void init(){
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, EnchantsmithConfig.CONFIG_BUILDER);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ESBlocks.BLOCKS.register(bus);
        ESLootPoolEntry.LP_ENTRY.register(bus);
        ESMenus.MENUS.register(bus);
        ESItems.ITEMS.register(bus);
        VillagerProfessions.register(bus);
        ESBanners.BANNER.register(bus);
        ESStructures.STRUCTURES.register(bus);
        ESStats.STAT.register(bus);
        ESAdvancementTriggers.init();
    }

    public static final Item.Properties defaultBuilder() {
        return new Item.Properties().tab(ESItems.creativeTab);
    }
}
