package com.scouter.enchantsmith.setup;

import com.scouter.enchantsmith.advancements.ESAdvancementTriggers;
import com.scouter.enchantsmith.banners.ESBanners;
import com.scouter.enchantsmith.blocks.ESBlocks;
import com.scouter.enchantsmith.entity.villagerprofessions.VillagerProfessions;
import com.scouter.enchantsmith.items.ESItems;
import com.scouter.enchantsmith.loot_table.ESLootPoolEntry;
import com.scouter.enchantsmith.menu.ESMenus;
import com.scouter.enchantsmith.stat.ESStats;
import com.scouter.enchantsmith.structures.ESStructures;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import static com.scouter.enchantsmith.EnchantSmith.prefix;

public class Registration {

    public static void init(){
        ESBlocks.BLOCKS();
        ESLootPoolEntry.LP();
        ESMenus.MENUS();
        ESItems.ITEMS();
        VillagerProfessions.VILLAGERPROF();
        VillagerProfessions.registerTrades();
        ESBanners.BANNER();
        ESStructures.STRUCTURES();
        ESStats.STAT();
        ESAdvancementTriggers.init();

    }

    public static final CreativeModeTab defaultBuilder = FabricItemGroupBuilder.build(prefix("enchantsmith"), () -> new ItemStack(ESItems.ENCHANTSMITH_CARPET));

}
