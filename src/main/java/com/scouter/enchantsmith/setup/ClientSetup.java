package com.scouter.enchantsmith.setup;

import com.scouter.enchantsmith.menu.ESMenus;
import com.scouter.enchantsmith.screen.EnchantSmithScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;

public class ClientSetup implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MenuScreens.register(ESMenus.ENCHANTSMITH_MENU, EnchantSmithScreen::new);
    }




    public static void init(){

    }
}
