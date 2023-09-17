package com.scouter.enchantsmith.advancements;

import net.minecraft.advancements.CriteriaTriggers;

public class ESAdvancementTriggers {
    public static EnchantSmithTrigger ENCHANTSMITH_USE = new EnchantSmithTrigger();

    public static void init(){
        CriteriaTriggers.register(ENCHANTSMITH_USE);
    }

}
