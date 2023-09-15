package com.scouter.enchantsmith.banners;

import com.scouter.enchantsmith.EnchantSmith;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ESBanners {
    public static final DeferredRegister<BannerPattern> BANNER = DeferredRegister.create(Registry.BANNER_PATTERN_REGISTRY, EnchantSmith.MODID);
    public static final RegistryObject<BannerPattern> ENCHANTSMITH_BANNER = registerBannerPattern("enchantsmith_banner");
    public static final RegistryObject<BannerPattern> ENCHANTSMITH_BANNER_SPIRAL = registerBannerPattern("enchantsmith_banner_spiral");
    public static final RegistryObject<BannerPattern> ENCHANTSMITH_BANNER_BORDER = registerBannerPattern("enchantsmith_banner_border");
    public static final RegistryObject<BannerPattern> ENCHANTSMITH_BANNER_NOISE = registerBannerPattern("enchantsmith_banner_noise");
    public static RegistryObject<BannerPattern> registerBannerPattern(String name) {
        return BANNER.register(name, () -> new BannerPattern(name));
    }
}
