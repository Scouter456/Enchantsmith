package com.scouter.enchantsmith.structures;

import com.scouter.enchantsmith.EnchantSmith;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ESStructures {
    public static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registries.STRUCTURE_TYPE, EnchantSmith.MODID);
    public static final RegistryObject<StructureType<EnchantSmithCamp>> ENCHANTSMITH_CAMP = STRUCTURES.register("enchantsmith_camp", () -> () -> EnchantSmithCamp.CODEC);
}
