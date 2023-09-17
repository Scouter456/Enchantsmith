package com.scouter.enchantsmith.sounds;

import com.scouter.enchantsmith.EnchantSmith;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ESSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, EnchantSmith.MODID);
    public static final RegistryObject<SoundEvent> OPEN_BOOK_1 = createSoundEvent("open_book_1");
    public static final RegistryObject<SoundEvent> OPEN_BOOK_2 = createSoundEvent("open_book_2");
    public static final RegistryObject<SoundEvent> OPEN_BOOK_3 = createSoundEvent("open_book_3");
    public static final RegistryObject<SoundEvent> OPEN_BOOK_4 = createSoundEvent("open_book_4");
    public static final RegistryObject<SoundEvent> OPEN_BOOK_5 = createSoundEvent("open_book_5");
    public static final RegistryObject<SoundEvent> OPEN_BOOK_6 = createSoundEvent("open_book_6");
    private static RegistryObject<SoundEvent> createSoundEvent(final String soundName) {
        return SOUNDS.register(soundName, () -> new SoundEvent(new ResourceLocation(EnchantSmith.MODID, soundName)));
    }
}
