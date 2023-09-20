package com.scouter.enchantsmith.config;

import com.scouter.enchantsmith.EnchantSmith;
import net.minecraftforge.common.ForgeConfigSpec;

public class EnchantsmithConfig {



    public static final ForgeConfigSpec CONFIG_BUILDER;

    static {
        ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
        setupConfig(configBuilder);
        CONFIG_BUILDER = configBuilder.build();
    }
    public static ForgeConfigSpec.ConfigValue<Integer> ENCHANT_BASE_COST;
    public static ForgeConfigSpec.ConfigValue<Integer> ENCHANT_COMMON_COST;
    public static ForgeConfigSpec.ConfigValue<Integer> ENCHANT_UNCOMMON_COST;
    public static ForgeConfigSpec.ConfigValue<Integer> ENCHANT_RARE_COST;
    public static ForgeConfigSpec.ConfigValue<Integer> ENCHANT_VERY_RARE_COST;
    public static ForgeConfigSpec.ConfigValue<Integer> TREASURE_ONLY_COST;
    public static ForgeConfigSpec.ConfigValue<Integer> IS_DISCOVERABLE_COST;
    public static ForgeConfigSpec.ConfigValue<Integer> IS_CURSE_COST;
    public static ForgeConfigSpec.ConfigValue<Integer> IS_TRADEABLE_COST;
    public static ForgeConfigSpec.ConfigValue<Integer> EMERALD_COST_MULTP;
    public static ForgeConfigSpec.ConfigValue<Integer> EXPERIENCE_COST_MULTP;


    private static void setupConfig(ForgeConfigSpec.Builder builder) {
        builder.comment(EnchantSmith.MODID + " Config");

        builder.comment("Config for the Enchantsmith");
        ENCHANT_BASE_COST = builder.comment("Base cost for enchants").define("enchant_base_cost", 6);
        ENCHANT_COMMON_COST = builder.comment("Cost for common enchants").define("enchant_common_cost", 2);
        ENCHANT_UNCOMMON_COST = builder.comment("Cost for uncommon enchants").define("enchant_uncommon_cost", 4);
        ENCHANT_RARE_COST = builder.comment("Cost for rare enchants").define("enchant_rare_cost", 6);
        ENCHANT_VERY_RARE_COST = builder.comment("Cost for very rare enchants").define("enchant_very_rare_cost", 8);
        TREASURE_ONLY_COST = builder.comment("Extra cost for treasure only enchants").define("treasure_only_cost", 5);
        IS_DISCOVERABLE_COST = builder.comment("Cost for discoverable enchants").define("is_discoverable_cost", -1);
        IS_CURSE_COST = builder.comment("Cost for curse enchants").define("is_curse_cost", -2);
        IS_TRADEABLE_COST = builder.comment("Cost for tradeable enchants").define("is_tradeable_cost", -1);
        EMERALD_COST_MULTP = builder.comment("Multiplier for emerald cost").define("emerald_cost_multp", 1);
        EXPERIENCE_COST_MULTP = builder.comment("Multiplier for experience cost").define("experience_cost_multp", 4);

    }
}
