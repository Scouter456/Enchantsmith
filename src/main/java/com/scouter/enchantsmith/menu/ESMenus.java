package com.scouter.enchantsmith.menu;

import com.mojang.logging.LogUtils;
import com.scouter.enchantsmith.EnchantSmith;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.Registry;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.slf4j.Logger;

public class ESMenus {
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final MenuType<EnchantSmithMenu> ENCHANTSMITH_MENU = new MenuType<>(EnchantSmithMenu::new);



    public static void MENUS()
    {
        LOGGER.info("Registering Menus for " + EnchantSmith.MODID);
    }

}
