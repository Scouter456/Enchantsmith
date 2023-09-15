package com.scouter.enchantsmith;

import com.mojang.logging.LogUtils;
import com.scouter.enchantsmith.setup.ClientSetup;
import com.scouter.enchantsmith.setup.ModSetup;
import com.scouter.enchantsmith.setup.Registration;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.slf4j.Logger;

import java.util.Locale;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EnchantSmith.MODID)
public class EnchantSmith
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "enchantsmith";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public EnchantSmith()
    {
        Registration.init();
        ModSetup.setup();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        modbus.addListener(ModSetup::init);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modbus.addListener(ClientSetup::init));
        if (FMLEnvironment.dist == Dist.CLIENT) {
            //    MinecraftForge.EVENT_BUS.register(ClientEvents.class);
            // static method with no client-only classes in method signature
        }
    }

    public static ResourceLocation prefix(String name) {
        return new ResourceLocation(MODID, name.toLowerCase(Locale.ROOT));
    }
}
