package com.scouter.enchantsmith.utils;

import com.google.common.collect.ImmutableList;
import com.scouter.enchantsmith.config.EnchantsmithConfig;
import net.minecraft.core.Registry;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EnchantmentUtils {


    public static EnchantmentInstance getRandomEnchantment(Map<Enchantment, Integer> toFilter, ItemStack stack, RandomSource randomSource){
        Map<Enchantment, Integer> viableEnchants = new HashMap<>();
        List<Enchantment> enchantmentList = Registry.ENCHANTMENT.getTag(ESTags.Enchantments.ENCHANTSMITH_ENCHANTMENT_BLACKLIST).stream().flatMap(s -> s.stream().map(e -> e.value())).toList();

        boolean flag = stack.is(Items.BOOK);
        for(Enchantment enchantment : Registry.ENCHANTMENT) {
            if((enchantment.canEnchant(stack) && !toFilter.containsKey(enchantment) || (flag)) && !enchantmentList.contains(enchantment)) {
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
        int cost = EnchantsmithConfig.ENCHANT_BASE_COST;
        cost += getCostForRarity(enchantment);
        cost += enchantment.getMaxLevel();
        cost += getExtraLevelCost(enchantment, 1);
        if(enchantment.isTreasureOnly()){
            cost += EnchantsmithConfig.TREASURE_ONLY_COST;
        }
        if(enchantment.isDiscoverable()){
            cost += EnchantsmithConfig.IS_DISCOVERABLE_COST;
        }
        if(enchantment.isCurse()){
            cost += EnchantsmithConfig.IS_CURSE_COST;
        }

        if(enchantment.isTradeable()) {
            cost += EnchantsmithConfig.IS_TRADEABLE_COST;
        }

        return cost;
    }

    public static int getCostForRarity(Enchantment enchantment){
        switch (enchantment.getRarity()){
            case COMMON -> {
                return EnchantsmithConfig.ENCHANT_COMMON_COST;
            }
            case UNCOMMON -> {
                return EnchantsmithConfig.ENCHANT_UNCOMMON_COST;
            }
            case RARE -> {
                return EnchantsmithConfig.ENCHANT_RARE_COST;
            }
            case VERY_RARE -> {
                return EnchantsmithConfig.ENCHANT_VERY_RARE_COST;
            }
            default -> {
                return 1;
            }
        }

    }
}
