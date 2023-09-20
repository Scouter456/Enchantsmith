package com.scouter.enchantsmith.mixin;

import com.scouter.enchantsmith.entity.villagerprofessions.VillagerProfessions;
import com.scouter.enchantsmith.menu.EnchantSmithMenu;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.OptionalInt;

@Mixin(Villager.class)
public abstract class VillagerMixin {

    @Inject(method = "mobInteract", at = @At("HEAD"))
    public void enchantsmith$mobInteract(Player pPlayer, InteractionHand pHand, CallbackInfoReturnable callbackInfoReturnable) {
        Villager villager = (Villager) (Object) this;

        Player player = pPlayer;
        InteractionHand hand = player.getUsedItemHand();
        if (villager.getVillagerData().getProfession() == VillagerProfessions.ENCHANTSMITH) {
            ItemStack itemstack = player.getItemInHand(hand);
            if (itemstack.getItem() != Items.VILLAGER_SPAWN_EGG && villager.isAlive() && !villager.isTrading() && !villager.isSleeping() && !player.isSecondaryUseActive()) {
                if (villager.isBaby()) {
                    villager.setUnhappy();
                } else if (!villager.level().isClientSide) {
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

    private static void playOpenScreenSound(Entity entity) {
        if (!entity.level().isClientSide()) {
            entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.BOOK_PAGE_TURN, SoundSource.NEUTRAL, 1.0F, 1.0F);
        }
    }
}
