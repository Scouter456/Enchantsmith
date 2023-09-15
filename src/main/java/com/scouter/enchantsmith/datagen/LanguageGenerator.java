package com.scouter.enchantsmith.datagen;

import com.mojang.logging.LogUtils;
import com.scouter.enchantsmith.EnchantSmith;
import com.scouter.enchantsmith.blocks.ESBlocks;
import com.scouter.enchantsmith.items.ESItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.common.data.LanguageProvider;
import org.slf4j.Logger;

import java.util.function.Supplier;

public class LanguageGenerator extends LanguageProvider {
    public LanguageGenerator(DataGenerator gen){
        super(gen, EnchantSmith.MODID, "en_us");
    }
    private static final Logger LOGGER = LogUtils.getLogger();
    @Override
    protected void addTranslations(){


        addItem(() -> ESBlocks.ENCHANTSMITH_CARPET.get().asItem(), "Enchantsmith Carpet");

        add("enchantsmith.emerald_levels", "Pay Emeralds for extra Levels!");
        add("entity.minecraft.villager.enchantsmith.enchantsmith", "Enchantsmith");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner.white", "White Enchantsmith Banner");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner.orange", "Orange Enchantsmith Banner");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner.magenta", "Magenta Enchantsmith Banner");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner.light_blue", "Light Blue Enchantsmith Banner");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner.yellow", "Yellow Enchantsmith Banner");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner.lime", "Lime Enchantsmith Banner");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner.pink", "Pink Enchantsmith Banner");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner.gray", "Gray Enchantsmith Banner");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner.light_gray", "Light Gray Enchantsmith Banner");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner.cyan", "Cyan Enchantsmith Banner");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner.purple", "Purple Enchantsmith Banner");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner.blue", "Blue Enchantsmith Banner");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner.brown", "Brown Enchantsmith Banner");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner.green", "Green Enchantsmith Banner");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner.red", "Red Enchantsmith Banner");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner.black", "Black Enchantsmith Banner");

        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_spiral.white", "White Enchantsmith Banner Spiral");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_spiral.orange", "Orange Enchantsmith Banner Spiral");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_spiral.magenta", "Magenta Enchantsmith Banner Spiral");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_spiral.light_blue", "Light Blue Enchantsmith Banner Spiral");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_spiral.yellow", "Yellow Enchantsmith Banner Spiral");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_spiral.lime", "Lime Enchantsmith Banner Spiral");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_spiral.pink", "Pink Enchantsmith Banner Spiral");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_spiral.gray", "Gray Enchantsmith Banner Spiral");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_spiral.light_gray", "Light Gray Enchantsmith Banner Spiral");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_spiral.cyan", "Cyan Enchantsmith Banner Spiral");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_spiral.purple", "Purple Enchantsmith Banner Spiral");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_spiral.blue", "Blue Enchantsmith Banner Spiral");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_spiral.brown", "Brown Enchantsmith Banner Spiral");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_spiral.green", "Green Enchantsmith Banner Spiral");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_spiral.red", "Red Enchantsmith Banner Spiral");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_spiral.black", "Black Enchantsmith Banner Spiral");

        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_border.white", "White Enchantsmith Banner Border");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_border.orange", "Orange Enchantsmith Banner Border");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_border.magenta", "Magenta Enchantsmith Banner Border");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_border.light_blue", "Light Blue Enchantsmith Banner Border");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_border.yellow", "Yellow Enchantsmith Banner Border");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_border.lime", "Lime Enchantsmith Banner Border");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_border.pink", "Pink Enchantsmith Banner Border");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_border.gray", "Gray Enchantsmith Banner Border");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_border.light_gray", "Light Gray Enchantsmith Banner Border");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_border.cyan", "Cyan Enchantsmith Banner Border");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_border.purple", "Purple Enchantsmith Banner Border");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_border.blue", "Blue Enchantsmith Banner Border");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_border.brown", "Brown Enchantsmith Banner Border");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_border.green", "Green Enchantsmith Banner Border");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_border.red", "Red Enchantsmith Banner Border");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_border.black", "Black Enchantsmith Banner Border");

        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_noise.white", "White Enchantsmith Banner Noise");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_noise.orange", "Orange Enchantsmith Banner Noise");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_noise.magenta", "Magenta Enchantsmith Banner Noise");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_noise.light_blue", "Light Blue Enchantsmith Banner Noise");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_noise.yellow", "Yellow Enchantsmith Banner Noise");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_noise.lime", "Lime Enchantsmith Banner Noise");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_noise.pink", "Pink Enchantsmith Banner Noise");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_noise.gray", "Gray Enchantsmith Banner Noise");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_noise.light_gray", "Light Gray Enchantsmith Banner Noise");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_noise.cyan", "Cyan Enchantsmith Banner Noise");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_noise.purple", "Purple Enchantsmith Banner Noise");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_noise.blue", "Blue Enchantsmith Banner Noise");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_noise.brown", "Brown Enchantsmith Banner Noise");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_noise.green", "Green Enchantsmith Banner Noise");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_noise.red", "Red Enchantsmith Banner Noise");
        add("block.minecraft.banner.enchantsmith.enchantsmith_banner_noise.black", "Black Enchantsmith Banner Noise");


        add("item.enchantsmith.banner.enchantsmith_banner_pattern", "Banner Pattern");
        add("item.enchantsmith.banner.enchantsmith_noise_pattern", "Banner Pattern");
        add("item.enchantsmith.banner.enchantsmith_spiral_pattern", "Banner Pattern");
        add("item.enchantsmith.banner.enchantsmith_border_pattern", "Banner Pattern");
        add("item.enchantsmith.banner.enchantsmith_banner_pattern.desc", "Enchantsmith Banner");
        add("item.enchantsmith.banner.enchantsmith_noise_pattern.desc", "Enchantsmith Banner Noise");
        add("item.enchantsmith.banner.enchantsmith_spiral_pattern.desc", "Enchantsmith Banner Spiral");
        add("item.enchantsmith.banner.enchantsmith_border_pattern.desc", "Enchantsmith Banner Border");

        addTabName(ESItems.creativeTab, "Enchantsmith");

    }

    @Override
    public String getName() {
        return "Enchantsmith: en_us";
    }

    public void addTabName(CreativeModeTab key, String name){
        add(key.getDisplayName().getString(), name);
    }

    public void add(CreativeModeTab key, String name) {
        add(key.getDisplayName().getString(), name);
    }

    public void addPotion(Supplier<? extends Potion> key, String name, String regName) {
        add(key.get(), name, regName);
    }

    public void add(Potion key, String name, String regName) {
        add("item.minecraft.potion.effect." + regName, name);
        add("item.minecraft.splash_potion.effect." + regName, "Splash " + name);
        add("item.minecraft.lingering_potion.effect." + regName, "Lingering " + name);
    }
}
