package com.scouter.enchantsmith.entity.villagerprofessions;

import com.google.common.collect.ImmutableSet;
import com.scouter.enchantsmith.EnchantSmith;
import com.scouter.enchantsmith.items.ESItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.scouter.enchantsmith.EnchantSmith.prefix;

public class VillagerProfessions {
    public static final Logger LOGGER = LoggerFactory.getLogger("enchantsmith");

    public static final PoiType ENCHANTSMITH_POI = registerPOI("enchantsmith_poi", Blocks.ENCHANTING_TABLE);

    public static final VillagerProfession ENCHANTSMITH = registerProfession("enchantsmith", ResourceKey.create(Registry.POINT_OF_INTEREST_TYPE_REGISTRY, prefix("enchantsmith_poi")));

    public static VillagerProfession registerProfession(String name, ResourceKey<PoiType> type) {
        return Registry.register(Registry.VILLAGER_PROFESSION, prefix(name),
                VillagerProfessionBuilder.create().id(prefix(name)).workstation(type)
                        .workSound(SoundEvents.VILLAGER_WORK_LIBRARIAN).build());
    }

    public static PoiType registerPOI(String name, Block block) {
        return PointOfInterestHelper.register(prefix( name),
                1, 1, ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates()));
    }

    public static void VILLAGERPROF(){
        LOGGER.info("Registering Villager Profession for " + EnchantSmith.MODID);
    }

    public static void registerTrades() {
        TradeOfferHelper.registerWanderingTraderOffers(1,
                factories -> {
                    factories.add(((entity, random) ->
                            new MerchantOffer(new ItemStack(Items.GOLD_INGOT, 1),
                                    new ItemStack(ESItems.ENCHANTSMITH_CARPET, 2),64
                                    ,12, 0.05F)));
                });
        TradeOfferHelper.registerWanderingTraderOffers(2,
                factories -> {
                    factories.add(((entity, random) ->
                            new MerchantOffer(
                                    new ItemStack(Items.EMERALD, 12),
                                    new ItemStack(ESItems.ENCHANTSMITH_BANNER_PATTERN, 1),
                                    2, 12, 0.15f)));

                    factories.add(((entity, random) ->
                            new MerchantOffer(
                                    new ItemStack(Items.EMERALD, 12),
                                    new ItemStack(ESItems.ENCHANTSMITH_SPIRAL_BANNER_PATTERN, 1),
                                    2, 12, 0.15f)));
                    factories.add(((entity, random) ->
                            new MerchantOffer(
                                    new ItemStack(Items.EMERALD, 12),
                                    new ItemStack(ESItems.ENCHANTSMITH_NOISE_BANNER_PATTERN, 1),
                                    2, 12, 0.15f)));factories.add(((entity, random) ->
                            new MerchantOffer(
                                    new ItemStack(Items.EMERALD, 12),
                                    new ItemStack(ESItems.ENCHANTSMITH_BORDER_BANNER_PATTERN, 1),
                                    2, 12, 0.15f)));
                });
    }
}
