package com.scouter.enchantsmith.setup;

import com.scouter.enchantsmith.advancements.ESAdvancementTriggers;
import com.scouter.enchantsmith.banners.ESBanners;
import com.scouter.enchantsmith.blocks.ESBlocks;
import com.scouter.enchantsmith.creativetabs.ESTabs;
import com.scouter.enchantsmith.entity.villagerprofessions.VillagerProfessions;
import com.scouter.enchantsmith.items.ESItems;
import com.scouter.enchantsmith.loot_table.ESLootPoolEntry;
import com.scouter.enchantsmith.menu.ESMenus;
import com.scouter.enchantsmith.stat.ESStats;
import com.scouter.enchantsmith.structures.ESStructures;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;

public class Registration {

    public static void init(){
        ESBlocks.BLOCKS();
        ESLootPoolEntry.LP();
        ESMenus.MENUS();
        ESItems.ITEMS();
        ESTabs.TABS();
        VillagerProfessions.VILLAGERPROF();
        VillagerProfessions.registerTrades();
        ESBanners.BANNER();
        ESStructures.STRUCTURES();
        ESStats.STAT();
        ESAdvancementTriggers.init();
        addBannerPatterns();
    }

    public static  void addBannerPatterns(){
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(l -> {
            l.accept(ESItems.ENCHANTSMITH_BANNER_PATTERN);
            l.accept(ESItems.ENCHANTSMITH_BORDER_BANNER_PATTERN);
            l.accept(ESItems.ENCHANTSMITH_NOISE_BANNER_PATTERN);
            l.accept(ESItems.ENCHANTSMITH_SPIRAL_BANNER_PATTERN);
        });
    }
}
