package com.scouter.enchantsmith.config;

import com.mojang.datafixers.util.Pair;
import com.scouter.enchantsmith.EnchantSmith;

public class EnchantsmithConfig {
    public static SimpleConfig CONFIG;
    private static EnchantsmithConfigProvider configs;
    public static int ENCHANT_BASE_COST;
    public static int ENCHANT_COMMON_COST;
    public static int ENCHANT_UNCOMMON_COST;
    public static int ENCHANT_RARE_COST;
    public static int ENCHANT_VERY_RARE_COST;
    public static int TREASURE_ONLY_COST;
    public static int IS_DISCOVERABLE_COST;
    public static int IS_CURSE_COST;
    public static int IS_TRADEABLE_COST;
    public static int EMERALD_COST_MULTP;
    public static int EXPERIENCE_COST_MULTP;

    public static void registerConfigs() {
        configs = new EnchantsmithConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(EnchantSmith.MODID + "config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("enchant_base_cost", 6), "Base cost for enchants");
        configs.addKeyValuePair(new Pair<>("enchant_common_cost", 2), "Cost for common enchants");
        configs.addKeyValuePair(new Pair<>("enchant_uncommon_cost", 4), "Cost for uncommon enchants");
        configs.addKeyValuePair(new Pair<>("enchant_rare_cost", 6), "Cost for rare enchants");
        configs.addKeyValuePair(new Pair<>("enchant_very_rare_cost", 8), "Cost for very rare enchants");
        configs.addKeyValuePair(new Pair<>("treasure_only_cost", 5), "Extra cost for treasure only enchants");
        configs.addKeyValuePair(new Pair<>("is_discoverable_cost", -1), "Cost for discoverable enchants");
        configs.addKeyValuePair(new Pair<>("is_curse_cost", -2), "Cost for curse enchants");
        configs.addKeyValuePair(new Pair<>("is_tradeable_cost", -1), "Cost for tradeable enchants");
        configs.addKeyValuePair(new Pair<>("emerald_cost_multp", 1), "Multiplier for emerald cost");
        configs.addKeyValuePair(new Pair<>("experience_cost_multp", 4), "Multiplier for experience cost");
    }

    private static void assignConfigs() {

        ENCHANT_BASE_COST = CONFIG.getOrDefault("enchant_base_cost", 6);
        ENCHANT_COMMON_COST = CONFIG.getOrDefault("enchant_common_cost", 2);
        ENCHANT_UNCOMMON_COST = CONFIG.getOrDefault("enchant_uncommon_cost", 4);
        ENCHANT_RARE_COST = CONFIG.getOrDefault("enchant_rare_cost", 6);
        ENCHANT_VERY_RARE_COST = CONFIG.getOrDefault("enchant_very_rare_cost", 8);
        TREASURE_ONLY_COST = CONFIG.getOrDefault("treasure_only_cost", 5);
        IS_DISCOVERABLE_COST = CONFIG.getOrDefault("is_discoverable_cost", -1);
        IS_CURSE_COST = CONFIG.getOrDefault("is_curse_cost", -2);
        IS_TRADEABLE_COST = CONFIG.getOrDefault("is_tradeable_cost", -1);
        EMERALD_COST_MULTP = CONFIG.getOrDefault("emerald_cost_multp", 1);
        EXPERIENCE_COST_MULTP = CONFIG.getOrDefault("experience_cost_multp", 4);

        System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }
}
