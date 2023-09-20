package com.scouter.enchantsmith.loot_table;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.mojang.logging.LogUtils;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntries;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.fml.ModList;
import org.slf4j.Logger;

import java.util.function.Consumer;

public class OptionalModItemLootTable extends LootPoolSingletonContainer {
    public static final Logger LOGGER = LogUtils.getLogger();
    final Item item;

    OptionalModItemLootTable(Item pItem, int pWeight, int pQuality, LootItemCondition[] pConditions, LootItemFunction[] pFunctions) {
        super(pWeight, pQuality, pConditions, pFunctions);
        this.item = pItem;
    }

    public LootPoolEntryType getType() {
        return LootPoolEntries.ITEM;
    }

    /**
     * Generate the loot stacks of this entry.
     * Contrary to the method name this method does not always generate one stack, it can also generate zero or multiple
     * stacks.
     */
    public void createItemStack(Consumer<ItemStack> pStackConsumer, LootContext pLootContext) {
        pStackConsumer.accept(new ItemStack(this.item));
    }

    public static LootPoolSingletonContainer.Builder<?> lootTableItem(ItemLike pItem) {
        return simpleBuilder((p_79583_, p_79584_, p_79585_, p_79586_) -> {
            return new OptionalModItemLootTable(pItem.asItem(), p_79583_, p_79584_, p_79585_, p_79586_);
        });
    }

    public static class Serializer extends LootPoolSingletonContainer.Serializer<OptionalModItemLootTable> {
        public void serializeCustom(JsonObject pObject, OptionalModItemLootTable pContext, JsonSerializationContext pConditions) {
            super.serializeCustom(pObject, pContext, pConditions);
            ResourceLocation resourcelocation = BuiltInRegistries.ITEM.getKey(pContext.item);
            String modid = pObject.get("modid").getAsString();
            if(modid != null && ModList.get().isLoaded(modid) && pContext.item != Items.AIR){
                if (resourcelocation == null) {
                    throw new IllegalArgumentException("Can't serialize unknown item " + pContext.item);
                } else {
                    pObject.addProperty("name", resourcelocation.toString());
                }
            }
        }

        protected OptionalModItemLootTable deserialize(JsonObject pObject, JsonDeserializationContext pContext, int pWeight, int pQuality, LootItemCondition[] pConditions, LootItemFunction[] pFunctions) {
            String modid = pObject.get("modid").getAsString();
            if(modid != null && ModList.get().isLoaded(modid)) {
                Item item = GsonHelper.getAsItem(pObject, "name");
                return new OptionalModItemLootTable(item, pWeight, pQuality, pConditions, pFunctions);
            }
            return new OptionalModItemLootTable(Items.AIR, pWeight, pQuality, pConditions, pFunctions);
        }
    }
}
