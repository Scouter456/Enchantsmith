package com.scouter.enchantsmith.datagen;

import com.scouter.enchantsmith.EnchantSmith;
import com.scouter.enchantsmith.utils.ESTags;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.data.ExistingFileHelper;

public class EnchantmentTagsProvider extends TagsProvider<Enchantment> {
    public EnchantmentTagsProvider(DataGenerator pGenerator, @org.jetbrains.annotations.Nullable net.minecraftforge.common.data.ExistingFileHelper existingFileHelper) {
        super(pGenerator, Registry.ENCHANTMENT, EnchantSmith.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(ESTags.Enchantments.ENCHANTSMITH_ENCHANTMENT_BLACKLIST).add(Enchantments.BANE_OF_ARTHROPODS)

        ;
    }
}
