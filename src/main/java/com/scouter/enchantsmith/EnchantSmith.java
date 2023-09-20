package com.scouter.enchantsmith;

import com.scouter.enchantsmith.setup.ClientSetup;
import com.scouter.enchantsmith.setup.Registration;
import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public class EnchantSmith implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("enchantsmith");
	public static final String MODID = "enchantsmith";

	@Override
	public void onInitialize() {
		Registration.init();
		ClientSetup.init();
	}

	public static ResourceLocation prefix(String name) {
		return new ResourceLocation(MODID, name.toLowerCase(Locale.ROOT));
	}
}