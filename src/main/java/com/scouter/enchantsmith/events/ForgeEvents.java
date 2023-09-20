package com.scouter.enchantsmith.events;

import com.scouter.enchantsmith.EnchantSmith;
import com.scouter.enchantsmith.entity.villagerprofessions.VillagerProfessions;
import com.scouter.enchantsmith.items.ESItems;
import com.scouter.enchantsmith.menu.EnchantSmithMenu;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.OptionalInt;

@Mod.EventBusSubscriber(modid = EnchantSmith.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {

    @SubscribeEvent
    public static void openEnchantsmithScreen(PlayerInteractEvent.EntityInteract event) {
        if (event.getEntity().level().isClientSide) return;
        if (event.getTarget() instanceof Villager villager) {
            Player player = event.getEntity();
            InteractionHand hand = player.getUsedItemHand();
            if (villager.getVillagerData().getProfession() == VillagerProfessions.ENCHANTSMITH.get()) {
                ItemStack itemstack = player.getItemInHand(hand);
                if (itemstack.getItem() != Items.VILLAGER_SPAWN_EGG && villager.isAlive() && !villager.isTrading() && !villager.isSleeping() && !player.isSecondaryUseActive()) {
                    if (villager.isBaby()) {
                        villager.setUnhappy();
                    } else if (!event.getEntity().level().isClientSide) {
                        villager.setTradingPlayer(player);
                        OptionalInt optionalint = player.openMenu(new SimpleMenuProvider((p_45298_, p_45299_, p_45300_) -> {
                            return new EnchantSmithMenu(p_45298_, p_45299_, ContainerLevelAccess.create(player.level(), player.blockPosition()), villager);
                        }, villager.getDisplayName()));
                        playOpenScreenSound(villager);
                        player.awardStat(Stats.TALKED_TO_VILLAGER);
                    }

                }
            }

        }
    }


    @SubscribeEvent
    public static void addWanderingTraderTrades(WandererTradesEvent event){
        List<VillagerTrades.ItemListing> normalTrades = event.getGenericTrades();
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();

        normalTrades.add((trader, random) ->
            new MerchantOffer(new ItemStack(Items.GOLD_INGOT, 1),
                    new ItemStack(ESItems.ENCHANTSMITH_CARPET.get(), 2),64
                    ,12, 0.05F)
        );

        rareTrades.add((pTrader, pRandom) ->
                new MerchantOffer(
                new ItemStack(Items.EMERALD, 12),
                new ItemStack(ESItems.ENCHANTSMITH_BANNER_PATTERN.get(), 1),
                2, 12, 0.15f));
        rareTrades.add((pTrader, pRandom) ->
                new MerchantOffer(
                new ItemStack(Items.EMERALD, 4),
                new ItemStack(ESItems.ENCHANTSMITH_SPIRAL_BANNER_PATTERN.get(), 1),
                2, 12, 0.15f));
        rareTrades.add((pTrader, pRandom) ->
                new MerchantOffer(
                new ItemStack(Items.EMERALD, 4),
                new ItemStack(ESItems.ENCHANTSMITH_NOISE_BANNER_PATTERN.get(), 1),
                2, 12, 0.15f));
        rareTrades.add((pTrader, pRandom) ->
                new MerchantOffer(
                new ItemStack(Items.EMERALD, 4),
                new ItemStack(ESItems.ENCHANTSMITH_BORDER_BANNER_PATTERN.get(), 1),
                2, 12, 0.15f));
    }

    private static void playOpenScreenSound(Entity entity) {
        if (!entity.level().isClientSide()) {
            entity.level().playSound(null,entity.getX(), entity.getY(), entity.getZ(), SoundEvents.BOOK_PAGE_TURN, SoundSource.NEUTRAL, 1.0F, 1.0F);
        }
    }
}
