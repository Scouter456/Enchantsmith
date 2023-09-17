package com.scouter.enchantsmith.utils;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.material.Fluid;

import static com.scouter.enchantsmith.EnchantSmith.prefix;

public class ESTags {


    public static class Fluids {

        public static final TagKey<Fluid> SHIMMER_FLUID = tag("shimmer_fluid");

        private static TagKey<Fluid> tag(String name){
            return FluidTags.create(prefix(name));

        }
        private static TagKey<Fluid> forgeTag(String name){
            return FluidTags.create(new ResourceLocation("forge", name));

        }
    }
    public static class Blocks {

        public static final TagKey<Block> SHIMMER_FLUID = tag("shimmer_fluid");
        public static final TagKey<Block> SHIMMER_TNT_BLACKLIST = tag("shimmer_tnt_blacklist");

        private static TagKey<Block> tag(String name){
            return BlockTags.create(prefix(name));

        }
        private static TagKey<Block> forgeTag(String name){
            return BlockTags.create(new ResourceLocation("forge", name));

        }
    }

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
        private static TagKey<Item> tag(String name){
            return ItemTags.create(prefix(name));

        }
        private static TagKey<Item> forgeTag(String name){
            return ItemTags.create(new ResourceLocation("forge", name));

        }
    }

    public static class EntityTypes {
        private static TagKey<EntityType<?>> tag(String name){
            return TagKey.create(Registry.ENTITY_TYPE_REGISTRY, prefix(name));

        }
        private static TagKey<EntityType<?>> forgeTag(String name){
            return TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation("forge", name));

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
}
