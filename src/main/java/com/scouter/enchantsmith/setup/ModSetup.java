package com.scouter.enchantsmith.setup;

import com.scouter.enchantsmith.EnchantSmith;
import com.scouter.enchantsmith.entity.villagerprofessions.VillagerProfessions;
import com.scouter.enchantsmith.items.ESItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = EnchantSmith.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSetup {

    public static void init(FMLCommonSetupEvent event){
        event.enqueueWork(() -> {
            VillagerProfessions.registerPOIs();
        });
    }

    @SubscribeEvent
    public static void addBannerToCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ESItems.ENCHANTSMITH_BANNER_PATTERN.get());
            event.accept(ESItems.ENCHANTSMITH_NOISE_BANNER_PATTERN.get());
            event.accept(ESItems.ENCHANTSMITH_BORDER_BANNER_PATTERN.get());
            event.accept(ESItems.ENCHANTSMITH_SPIRAL_BANNER_PATTERN.get());
        }
    }
    public static void setup(){
        IEventBus bus = MinecraftForge.EVENT_BUS;
    }
}
