package com.scouter.enchantsmith.datagen;

import com.scouter.enchantsmith.EnchantSmith;
import com.scouter.enchantsmith.utils.ESTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemTagsGenerator extends ItemTagsProvider {
    public ItemTagsGenerator(DataGenerator generator, BlockTagsProvider provider, ExistingFileHelper helper) {
        super(generator, provider, EnchantSmith.MODID, helper);
    }

    @Override
    protected void addTags(){
        this.tag(ESTags.Items.ENCHANTSMITH_ITEM_BLACKLIST).add(Items.DIAMOND_HELMET);
    }


    @Override
    public String getName() { return "Enchantsmith Item Tags";}
}
