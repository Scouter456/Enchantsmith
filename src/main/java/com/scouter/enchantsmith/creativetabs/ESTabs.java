package com.scouter.enchantsmith.creativetabs;

import com.scouter.enchantsmith.EnchantSmith;
import com.scouter.enchantsmith.items.ESItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ESTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EnchantSmith.MODID);

    private static final CreativeModeTab ENCHANTSMITH = new CreativeModeTab.Builder(CreativeModeTab.Row.TOP, 9)
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .title(Component.translatable("itemGroup.enchantsmith"))
            .icon(() -> new ItemStack(ESItems.ENCHANTSMITH_CARPET.get()))
            .displayItems((d, entries) ->{
                entries.accept(ESItems.ENCHANTSMITH_CARPET.get());

            })
            .build();


    public static final RegistryObject<CreativeModeTab> ENCHANTSMITH_TAB = TABS.register("enchantsmith", () -> ENCHANTSMITH);
}
