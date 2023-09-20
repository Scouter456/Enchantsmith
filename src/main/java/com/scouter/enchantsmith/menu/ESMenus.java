package com.scouter.enchantsmith.menu;

import com.mojang.logging.LogUtils;
import com.scouter.enchantsmith.EnchantSmith;
import net.minecraft.core.Registry;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.slf4j.Logger;

import static com.scouter.enchantsmith.EnchantSmith.prefix;

public class ESMenus {
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final MenuType<EnchantSmithMenu> ENCHANTSMITH_MENU = register("enchantsmith_menu", EnchantSmithMenu::new);

    private static <T extends AbstractContainerMenu> MenuType register(String key, MenuType.MenuSupplier<T> factory) {
        return Registry.register(Registry.MENU, prefix(key), new MenuType(factory));
    }


    public static void MENUS()
    {
        LOGGER.info("Registering Menus for " + EnchantSmith.MODID);
    }

}
