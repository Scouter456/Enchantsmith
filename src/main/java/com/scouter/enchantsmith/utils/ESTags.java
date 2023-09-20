package com.scouter.enchantsmith.utils;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.material.Fluid;

import static com.scouter.enchantsmith.EnchantSmith.prefix;

public class ESTags {

    public static class BannerPatterns {

        public static final TagKey<BannerPattern> ENCHANTSMITH_BANNER = tag("enchantsmith_banner_pattern");
        public static final TagKey<BannerPattern> ENCHANTSMITH_SPIRAL_BANNER = tag("enchantsmith_spiral_banner_pattern");
        public static final TagKey<BannerPattern> ENCHANTSMITH_BORDER_BANNER = tag("enchantsmith_border_banner_pattern");
        public static final TagKey<BannerPattern> ENCHANTSMITH_NOISE_BANNER = tag("enchantsmith_noise_banner_pattern");
        private static TagKey<BannerPattern> tag(String name){
            return TagKey.create(Registry.BANNER_PATTERN_REGISTRY, prefix(name));

        }
    }

    public static class Items {
        public static final TagKey<Item> ENCHANTSMITH_ITEM_BLACKLIST = tag("enchantsmith_item_blacklist");


        private static TagKey<Item> tag(String name){
            return TagKey.create(Registry.ITEM_REGISTRY,prefix(name));

        }

    }

    public static class Biomes {

        public static final TagKey<Biome> ENCHANTSMITH_CAMP_BIOMES = tag("enchantsmith_camp_biomes");


        private static TagKey<Biome> tag(String name){
            return TagKey.create(Registry.BIOME_REGISTRY, prefix(name));

        }
        private static TagKey<Biome> forgeTag(String name){
            return TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("forge", name));

        }
    }

    public static class Enchantments {

        public static final TagKey<Enchantment> ENCHANTSMITH_ENCHANTMENT_BLACKLIST = tag("enchantsmith_enchantment_blacklist");


        private static TagKey<Enchantment> tag(String name){
            return TagKey.create(Registry.ENCHANTMENT_REGISTRY, prefix(name));

        }
    }
}
