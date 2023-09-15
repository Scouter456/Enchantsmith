package com.scouter.enchantsmith.datagen;

import com.scouter.enchantsmith.EnchantSmith;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BiomeTagsProvider extends net.minecraft.data.tags.BiomeTagsProvider {
    public BiomeTagsProvider(DataGenerator pGenerator, ExistingFileHelper existingFileHelper) {
        super(pGenerator, EnchantSmith.MODID, existingFileHelper);
    }

}
