package com.scouter.enchantsmith.blocks;

import com.mojang.logging.LogUtils;
import com.scouter.enchantsmith.EnchantSmith;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod.EventBusSubscriber(modid = EnchantSmith.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ESBlocks {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, EnchantSmith.MODID);

    public static final RegistryObject<Block>  ENCHANTSMITH_CARPET = BLOCKS.register("enchantsmith_carpet", () -> new CarpetBlock(BlockBehaviour.Properties.of(Material.WOOL, MaterialColor.COLOR_RED).strength(0.2F).sound(SoundType.WOOL)));


}
