package com.scouter.enchantsmith.setup;

import com.scouter.enchantsmith.EnchantSmith;
import com.scouter.enchantsmith.entity.villagerprofessions.VillagerProfessions;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = EnchantSmith.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSetup {

    public static void init(FMLCommonSetupEvent event){
        event.enqueueWork(() -> {
            VillagerProfessions.registerPOIs();
        });
    }


    public static void setup(){
        IEventBus bus = MinecraftForge.EVENT_BUS;
    }
}
