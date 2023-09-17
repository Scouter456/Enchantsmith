package com.scouter.enchantsmith.stat;

import com.scouter.enchantsmith.EnchantSmith;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.scouter.enchantsmith.EnchantSmith.prefix;

public class ESStats {
    public static final DeferredRegister<ResourceLocation> STAT = DeferredRegister.create(Registry.CUSTOM_STAT_REGISTRY, EnchantSmith.MODID);
    public static final RegistryObject<ResourceLocation> ENCHANTSMITH_USE_STAT = STAT.register("enchantsmith_use", () -> prefix("enchantsmith_use"));
}
