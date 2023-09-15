package com.scouter.enchantsmith.setup;


import com.scouter.enchantsmith.EnchantSmith;
import com.scouter.enchantsmith.menu.ESMenus;
import com.scouter.enchantsmith.screen.EnchantSmithScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = EnchantSmith.MODID, value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(FMLClientSetupEvent event){
        MenuScreens.register(ESMenus.ENCHANTSMITH_MENU.get(), EnchantSmithScreen::new);
    }
}

