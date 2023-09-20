package com.scouter.enchantsmith.banners;

import com.scouter.enchantsmith.EnchantSmith;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BannerPattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.scouter.enchantsmith.EnchantSmith.prefix;

public class ESBanners {
    public static final Logger LOGGER = LoggerFactory.getLogger("enchantsmith");

    public static final ResourceKey<BannerPattern> ENCHANTSMITH_BANNER = registerBannerPattern("enchantsmith_banner");
    public static final ResourceKey<BannerPattern> ENCHANTSMITH_BANNER_SPIRAL = registerBannerPattern("enchantsmith_banner_spiral");
    public static final ResourceKey<BannerPattern> ENCHANTSMITH_BANNER_BORDER = registerBannerPattern("enchantsmith_banner_border");
    public static final ResourceKey<BannerPattern> ENCHANTSMITH_BANNER_NOISE = registerBannerPattern("enchantsmith_banner_noise");
    private static ResourceKey<BannerPattern> registerBannerPattern(String name) {
        Registry.register(Registry.BANNER_PATTERN, prefix(name), new BannerPattern(name));
        return ResourceKey.create(Registry.BANNER_PATTERN_REGISTRY, new ResourceLocation(name));
    }

    public static void BANNER(){
        LOGGER.info("Registering Banners for " + EnchantSmith.MODID);
    }
}
