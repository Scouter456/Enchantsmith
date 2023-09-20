package com.scouter.enchantsmith.loot_table;

import com.scouter.enchantsmith.EnchantSmith;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ESLootPoolEntry {
    public static final Logger LOGGER = LoggerFactory.getLogger("enchantsmith");

    public static final LootPoolEntryType OPTIONAL_ITEM = register("enchantsmith:optional_item", new OptionalModItemLootTable.Serializer());

    private static LootPoolEntryType register(String name, Serializer<? extends LootPoolEntryContainer> serializer) {
        return Registry.register(Registry.LOOT_POOL_ENTRY_TYPE, new ResourceLocation(name), new LootPoolEntryType(serializer));
    }

    public static void LP(){
        LOGGER.info("Registering Loot Entries for " + EnchantSmith.MODID);
    }
}
