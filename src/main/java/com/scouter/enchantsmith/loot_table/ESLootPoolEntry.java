package com.scouter.enchantsmith.loot_table;

import com.scouter.enchantsmith.EnchantSmith;
import net.minecraft.core.Registry;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ESLootPoolEntry {
    public static final DeferredRegister<LootPoolEntryType> LP_ENTRY = DeferredRegister.create(Registry.LOOT_ENTRY_REGISTRY, EnchantSmith.MODID);

    public static final RegistryObject<LootPoolEntryType> OPTIONAL_ITEM = LP_ENTRY.register("optional_item", () ->new LootPoolEntryType(new OptionalModItemLootTable.Serializer()));
}
