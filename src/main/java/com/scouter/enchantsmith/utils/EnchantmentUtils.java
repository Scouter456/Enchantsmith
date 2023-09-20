package com.scouter.enchantsmith.utils;

import net.minecraft.core.Registry;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class EnchantmentUtils {


    public static EnchantmentInstance getRandomEnchantment(Map<Enchantment, Integer> toFilter, ItemStack stack, RandomSource randomSource){
        Map<Enchantment, Integer> viableEnchants = new HashMap<>();

        boolean flag = stack.is(Items.BOOK);
        for(Enchantment enchantment : Registry.ENCHANTMENT) {
            if(enchantment.canEnchant(stack) && !toFilter.containsKey(enchantment) || (flag)) {
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
        int cost = 6;
        cost += getCostForRarity(enchantment);
        cost += enchantment.getMaxLevel();
        cost += getExtraLevelCost(enchantment, 1);
        if(enchantment.isTreasureOnly()){
            cost += 5;
        }
        if(enchantment.isDiscoverable()){
            cost -= 1;
        }
        if(enchantment.isCurse()){
            cost -= 2;
        }

        if(enchantment.isTradeable()) {
            cost -= 1;
        }

        return cost;
    }

    public static int getCostForRarity(Enchantment enchantment){
        switch (enchantment.getRarity()){
            case COMMON -> {
                return 2;
            }
            case UNCOMMON -> {
                return 4;
            }
            case RARE -> {
                return 6;
            }
            case VERY_RARE -> {
                return 8;
            }
            default -> {
                return 1;
            }
        }

    }
}
