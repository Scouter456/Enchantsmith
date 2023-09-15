package com.scouter.enchantsmith.datagen;

import com.scouter.enchantsmith.EnchantSmith;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTagsGenerator extends BlockTagsProvider {
    public BlockTagsGenerator(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, EnchantSmith.MODID, helper);
    }

    @Override
    protected void addTags(){

    }


    @Override
    public String getName() { return "Enchantsmith Tags";}
}
