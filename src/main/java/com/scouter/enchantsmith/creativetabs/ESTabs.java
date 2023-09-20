package com.scouter.enchantsmith.creativetabs;

import com.scouter.enchantsmith.EnchantSmith;
import com.scouter.enchantsmith.items.ESItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.scouter.enchantsmith.EnchantSmith.prefix;

public class ESTabs {
    public static final Logger LOGGER = LoggerFactory.getLogger("enchantsmith");

    private static final CreativeModeTab ENCHANTSMITH  = FabricItemGroup
            .builder()
            .title(Component.translatable("itemGroup.enchantsmith"))
            .displayItems((enabledFeatures, entries) -> {
                entries.accept(ESItems.ENCHANTSMITH_CARPET);

            })
            .icon(ESItems.ENCHANTSMITH_CARPET::getDefaultInstance)
            .build();


    public static final CreativeModeTab ENCHANTSMITH_TAB = creativeModeTab("enchantsmith", ENCHANTSMITH);

    private static CreativeModeTab creativeModeTab(String name, CreativeModeTab item) {
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, prefix(name), item);
    }


    public static void TABS(){
        LOGGER.info("Registering tabs for " + EnchantSmith.MODID);
    }
}
