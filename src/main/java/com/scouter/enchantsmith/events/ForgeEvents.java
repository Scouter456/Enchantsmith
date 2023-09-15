package com.scouter.enchantsmith.events;

import com.scouter.enchantsmith.EnchantSmith;
import com.scouter.enchantsmith.entity.villagerprofessions.VillagerProfessions;
import com.scouter.enchantsmith.menu.EnchantSmithMenu;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.OptionalInt;

@Mod.EventBusSubscriber(modid = EnchantSmith.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {

    @SubscribeEvent
    public static void openEnchantsmithScreen(PlayerInteractEvent.EntityInteract event) {
        if (event.getEntity().getLevel().isClientSide) return;
        if(event.getTarget() instanceof Villager villager){
            Player player = event.getEntity();
            InteractionHand hand = player.getUsedItemHand();
            if(villager.getVillagerData().getProfession() == VillagerProfessions.ENCHANTSMITH.get()){
                ItemStack itemstack = player.getItemInHand(hand);
                if (itemstack.getItem() != Items.VILLAGER_SPAWN_EGG && villager.isAlive() && !villager.isTrading() && !villager.isSleeping() && !player.isSecondaryUseActive()) {
                    if (villager.isBaby()) {
                        villager.setUnhappy();
                        return;
                    } else {
                        boolean flag = villager.getOffers().isEmpty();
                        if (hand == InteractionHand.MAIN_HAND) {
                            if (flag && !event.getEntity().getLevel().isClientSide) {
                                villager.setUnhappy();
                            }

                            player.awardStat(Stats.TALKED_TO_VILLAGER);
                        }

                        if (!flag) {
                            return;
                        } else {
                            if (!event.getEntity().getLevel().isClientSide) {
                                villager.setTradingPlayer(player);
                                OptionalInt optionalint = player.openMenu(new SimpleMenuProvider((p_45298_, p_45299_, p_45300_) -> {
                                    return new EnchantSmithMenu(p_45298_, p_45299_, ContainerLevelAccess.create(player.level, player.blockPosition()), villager);
                                }, villager.getDisplayName()));
                            }

                            return;
                        }
                    }
                }
            }

        }
    }

}
