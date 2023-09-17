package com.scouter.enchantsmith.datagen;

import com.scouter.enchantsmith.EnchantSmith;
import com.scouter.enchantsmith.utils.ESTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BiomeTagsProvider extends net.minecraft.data.tags.BiomeTagsProvider {
    public BiomeTagsProvider(DataGenerator pGenerator, ExistingFileHelper existingFileHelper) {
        super(pGenerator, EnchantSmith.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(ESTags.Biomes.ENCHANTSMITH_CAMP_BIOMES)
                .add(Biomes.PLAINS)
                .add(Biomes.MEADOW)
                .add(Biomes.SNOWY_PLAINS)
                .add(Biomes.TAIGA)
                .add(Biomes.OLD_GROWTH_SPRUCE_TAIGA)
                .add(Biomes.OLD_GROWTH_PINE_TAIGA)
                .add(Biomes.WINDSWEPT_FOREST)
                .add(Biomes.WINDSWEPT_HILLS)
                .add(Biomes.SUNFLOWER_PLAINS)
                .add(Biomes.FOREST)
                .add(Biomes.FLOWER_FOREST)
                .add(Biomes.DARK_FOREST)
                .add(Biomes.BIRCH_FOREST)
        ;
    }
}
