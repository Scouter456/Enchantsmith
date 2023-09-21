package com.scouter.enchantsmith.menu;

import com.mojang.logging.LogUtils;
import com.scouter.enchantsmith.EnchantSmith;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.slf4j.Logger;

import static com.scouter.enchantsmith.EnchantSmith.prefix;

public class ESMenus {
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final MenuType<EnchantSmithMenu> ENCHANTSMITH_MENU = register("enchantsmith_menu",EnchantSmithMenu::new);


    private static <T extends AbstractContainerMenu> MenuType register(String key, MenuType.MenuSupplier<T> factory) {
        return Registry.register(BuiltInRegistries.MENU, prefix(key), new MenuType(factory, FeatureFlagSet.of()));
    }
    public static void MENUS()
    {
        LOGGER.info("Registering Menus for " + EnchantSmith.MODID);
    }

}
