package com.scouter.enchantsmith.utils;

import com.scouter.enchantsmith.config.EnchantsmithConfig;
import net.minecraft.core.Registry;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EnchantmentUtils {


    public static EnchantmentInstance getRandomEnchantment(Map<Enchantment, Integer> toFilter, ItemStack stack, RandomSource randomSource){
        Map<Enchantment, Integer> viableEnchants = new HashMap<>();
        List<Enchantment> enchantmentList = ForgeRegistries.ENCHANTMENTS.tags().getTag(ESTags.Enchantments.ENCHANTSMITH_ENCHANTMENT_BLACKLIST).stream().toList();

        boolean flag = stack.is(Items.BOOK);
        for(Enchantment enchantment : Registry.ENCHANTMENT) {
            if((enchantment.canEnchant(stack) && !toFilter.containsKey(enchantment) || (flag && enchantment.isAllowedOnBooks())) && !enchantmentList.contains(enchantment)) {
                viableEnchants.put(enchantment, 1);
            }
        }

        if(!viableEnchants.isEmpty()) {
            Enchantment randomEnchant = viableEnchants.keySet().stream().collect(Collectors.toList()).get(randomSource.nextInt(viableEnchants.size()));
            return  new EnchantmentInstance(randomEnchant, 1);
        }
        return null;
    }

    public static int getEnchantmentId(Enchantment enchantment){
        return Registry.ENCHANTMENT.getId(enchantment);
    }

    public static Enchantment getEnchant(int id){
        return Enchantment.byId(id);
    }

    public static int getExtraLevelCost(Enchantment enchantment, int level){
        if(level > enchantment.getMaxLevel()) level = enchantment.getMaxLevel();
        return (int) (getCostForRarity(enchantment) * level * 0.5F);
    }

    public static int getEnchantBaseGoldCost(Enchantment enchantment){
        int cost = EnchantsmithConfig.ENCHANT_BASE_COST.get();
        cost += getCostForRarity(enchantment);
        cost += enchantment.getMaxLevel();
        cost += getExtraLevelCost(enchantment, 1);
        if(enchantment.isTreasureOnly()){
            cost += EnchantsmithConfig.TREASURE_ONLY_COST.get();
        }
        if(enchantment.isDiscoverable()){
            cost += EnchantsmithConfig.IS_DISCOVERABLE_COST.get();
        }
        if(enchantment.isCurse()){
            cost += EnchantsmithConfig.IS_CURSE_COST.get();
        }

        if(enchantment.isTradeable()) {
            cost += EnchantsmithConfig.IS_TRADEABLE_COST.get();
        }

        return cost;
    }

    public static int getCostForRarity(Enchantment enchantment){
        switch (enchantment.getRarity()){
            case COMMON -> {
                return EnchantsmithConfig.ENCHANT_COMMON_COST.get();
            }
            case UNCOMMON -> {
                return EnchantsmithConfig.ENCHANT_UNCOMMON_COST.get();
            }
            case RARE -> {
                return EnchantsmithConfig.ENCHANT_RARE_COST.get();
            }
            case VERY_RARE -> {
                return EnchantsmithConfig.ENCHANT_VERY_RARE_COST.get();
            }
            default -> {
                return 1;
            }
        }

    }
}
