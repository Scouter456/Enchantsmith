package com.scouter.enchantsmith.menu;

import com.scouter.enchantsmith.EnchantSmith;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ESMenus {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, EnchantSmith.MODID);

    public static final RegistryObject<MenuType<EnchantSmithMenu>> ENCHANTSMITH_MENU =
            registerMenuType((int pType, Inventory pContainerId, FriendlyByteBuf pPlayerInventory) -> new EnchantSmithMenu(pType, pContainerId, pPlayerInventory), "enchantsmith_menu");

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }
}
