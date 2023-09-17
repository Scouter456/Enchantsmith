package com.scouter.enchantsmith.setup;

import com.mojang.logging.LogUtils;
import com.scouter.enchantsmith.banners.ESBanners;
import com.scouter.enchantsmith.blocks.ESBlocks;
import com.scouter.enchantsmith.entity.villagerprofessions.VillagerProfessions;
import com.scouter.enchantsmith.items.ESItems;
import com.scouter.enchantsmith.menu.ESMenus;
import com.scouter.enchantsmith.sounds.ESSounds;
import com.scouter.enchantsmith.structures.ESStructures;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


public class Registration {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static void init(){

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ESBlocks.BLOCKS.register(bus);
        ESMenus.MENUS.register(bus);
        ESItems.ITEMS.register(bus);
        VillagerProfessions.register(bus);
        ESBanners.BANNER.register(bus);
        ESStructures.STRUCTURES.register(bus);
        ESSounds.SOUNDS.register(bus);
    }

    public static final Item.Properties defaultBuilder() {
        return new Item.Properties().fireResistant().tab(ESItems.creativeTab);
    }
}
