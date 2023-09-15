package com.scouter.enchantsmith.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.scouter.enchantsmith.EnchantSmith;
import com.scouter.enchantsmith.menu.EnchantSmithMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.EnchantmentNames;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EnchantSmithScreen extends AbstractContainerScreen<EnchantSmithMenu> {
    private static final ResourceLocation BG_LOCATION = new ResourceLocation(EnchantSmith.MODID, "textures/gui/enchantsmith_new.png");
    private static final ResourceLocation BG_SLOT_LOCATION = new ResourceLocation(EnchantSmith.MODID, "textures/gui/enchantsmith_buy_slot.png");
    private static final ResourceLocation XP_OVERLAY_LOCATION = new ResourceLocation(EnchantSmith.MODID, "textures/gui/xp_overlay.png");
    private float scrollOffs;
    /**
     * Is {@code true} if the player clicked on the scroll wheel in the GUI.
     */
    private boolean scrolling;
    /**
     * The index of the first recipe to display.
     * The number of recipes displayed at any time is 12 (4 recipes per row, and 3 rows). If the player scrolled down one
     * row, this value would be 4 (representing the index of the first slot on the second row).
     */
    private int startIndex;
    private boolean displayEnchantments;

    public EnchantSmithScreen(EnchantSmithMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        pMenu.registerUpdateListener(this::containerChanged);
        pMenu.registerGoldUpdateListener(this::containerChanged);
        pMenu.registerEmeraldUpdateListener(this::containerChanged);
    }

    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pPoseStack, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pX, int pY) {
        this.renderBackground(pPoseStack);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, BG_LOCATION);

        int i = this.leftPos;
        int j = this.topPos;
        this.blit(pPoseStack, i, j, 0, 0, this.imageWidth, this.imageHeight);
        int k = (int) (41.0F * this.scrollOffs);
        this.blit(pPoseStack, i + 119, j + 15 + k, 176 + (this.isScrollBarActive() ? 0 : 12), 0, 12, 15);

        int l = this.leftPos + 52;
        int i1 = this.topPos + 14;
        int j1 = this.startIndex + 3;
        this.renderButtons(pPoseStack, pX, pY, l, i1, j1);
        this.renderEnchantments(l + 2, i1, j1);

        this.renderStack(Items.GOLD_INGOT.getDefaultInstance(), this.menu.getGoldCost(), this.menu.goldContainer,this.leftPos, this.topPos,135, 51);
        this.renderStack(Items.EMERALD.getDefaultInstance(), this.menu.getEnchantLevel(), this.menu.emeraldContainer,this.leftPos, this.topPos,154, 51);
        this.renderSlotOverlay(pPoseStack, this.menu.getGoldCost(), this.menu.goldContainer, this.leftPos, this.topPos, 134, 50);
        this.renderSlotOverlay(pPoseStack,  this.menu.getEnchantLevel(), this.menu.emeraldContainer, this.leftPos, this.topPos, 153, 50);
        this.renderXpOrbOverlay(pPoseStack, i,j);
    }

    private void renderXpOrbOverlay(PoseStack poseStack ,int leftPos, int rightPos){
        if(this.menu.getExperienceCost() > 0 && this.menu.getResultSlot().hasItem()){
            int drawPosLeft = leftPos + 147;
            int drawPosTop = rightPos + 69;
            poseStack.pushPose();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, BG_LOCATION);
            this.blit(poseStack,drawPosLeft, drawPosTop , 177, 69, 14, 14);
            FormattedText text = FormattedText.of(String.valueOf(this.menu.getExperienceCost()));
            this.font.drawWordWrap(text, drawPosLeft + 8, drawPosTop + 6, 20, 0xffffff);
            poseStack.popPose();
        }
    }

    private void renderEnchantments(int pLeft, int pTop, int pRecipeIndexOffsetMax) {
        Map<Enchantment, Integer> list = this.menu.getEnchantments();

        for (int i = this.startIndex; i < pRecipeIndexOffsetMax && i < this.menu.getNumEnchantments(); ++i) {
            int j = i - this.startIndex;
            int k = pLeft + j % 1 * 16;
            int l = j / 1;
            int i1 = pTop + l * 18 + 2;
            Enchantment enchantment = list.keySet().stream().toList().get(i);
            FormattedText text = FormattedText.of(Component.translatable(enchantment.getDescriptionId()).getString());
            this.font.drawWordWrap(text, k, i1, 60, 8453920);
            //this.minecraft.getItemRenderer().renderAndDecorateItem(list.get(i).getResultItem(), k, i1);
        }

    }

    protected void renderTooltip(PoseStack pPoseStack, int pX, int pY) {
        if (this.menu.getCarried().isEmpty() && this.hoveredSlot != null && this.hoveredSlot.hasItem() && this.hoveredSlot != this.menu.getResultSlot()) {
            this.renderTooltip(pPoseStack, this.hoveredSlot.getItem(), pX, pY);
        } else if(this.menu.getCarried().isEmpty() && this.hoveredSlot != null && this.hoveredSlot == this.menu.getEmeraldResultSlot()) {
            this.renderTooltip(pPoseStack, Component.translatable("enchantsmith.emerald_levels"), pX, pY);
        }

        if (this.menu.getCarried().isEmpty() && this.hoveredSlot != null && this.hoveredSlot.hasItem() && this.hoveredSlot == this.menu.getResultSlot()) {
            List<Component> components = this.menu.getResultSlot().getItem().getTooltipLines(null, TooltipFlag.Default.NORMAL);
            List<Component> toShow = new ArrayList<>();
            //Map<Enchantment, Integer> enchants = this.menu.getEnchantments();
            //Map<Enchantment, Integer> changedEnchants = EnchantmentHelper.getEnchantments(this.menu.getResultSlot().getItem());

            //for (Enchantment enchantment1 : enchants.keySet()) {
            //    if (changedEnchants.containsKey(enchantment1)) {
            //        changedEnchants.remove(enchantment1);
            //    }
            //}

            //if (!changedEnchants.isEmpty()) {
                Enchantment finalEnchant = this.menu.getRandomEnchant();
                for (Component component : components) {
                    if (component.toString().contains("enchantment")) {
                        if (finalEnchant != null && component.toString().contains(finalEnchant.getDescriptionId())) {
                            int finalLevel = this.menu.getEnchantLevel();
                            String random = EnchantmentNames.getInstance().getRandomName(this.font, 80).getString();
                            MutableComponent component1 = Component.literal(random + " " + finalLevel);
                            toShow.add(component1);

                        } else {
                            toShow.add(component);
                        }
                    } else {
                        toShow.add(component);
                    }

              //  }
            }
            this.renderComponentTooltip(pPoseStack, toShow, pX, pY);
        }


        if (this.displayEnchantments) {
            int i = this.leftPos + 52;
            int j = this.topPos + 14;
            int k = this.startIndex + 12;
            List<Enchantment> list = this.menu.getEnchantments().keySet().stream().toList();

            for (int l = this.startIndex; l < k && l < this.menu.getNumEnchantments(); ++l) {
                int i1 = l - this.startIndex;
                int j1 = i + i1 % 1 * 16;
                int k1 = j + i1 / 1 * 18 + 2;
                if (pX >= j1 && pX < j1 + 64 && pY >= k1 && pY < k1 + 18) {
                    this.renderTooltip(pPoseStack, Component.translatable(list.get(l).getDescriptionId()), pX, pY);
                }
            }
        }

    }

    private void renderStack(ItemStack stack, int costs, Container container, int pLeft, int pTop, int offSetLeft, int offSetTop) {
        if (costs > 0 && container.isEmpty() && !this.menu.getResultSlot().getItem().isEmpty()) {
            int slotTopLeft = pLeft + offSetLeft;
            int slotTopTop = pTop + offSetTop;
            ItemStack itemStack = stack;
            itemStack.setCount(costs);
            this.minecraft.getItemRenderer().renderAndDecorateFakeItem(itemStack, slotTopLeft, slotTopTop);
            this.minecraft.getItemRenderer().renderGuiItemDecorations(this.font, itemStack, slotTopLeft, slotTopTop);
        }

    }

    private void renderSlotOverlay(PoseStack poseStack,int costs, Container container , int pLeft, int pTop, int offSetLeft, int offSetTop) {
        if (costs > 0 && container.isEmpty() && !this.menu.getResultSlot().getItem().isEmpty()) {
            int slotTopLeft = pLeft + offSetLeft;
            int slotTopTop = pTop + offSetTop;
            poseStack.pushPose();
            poseStack.translate(0, 0, 200);
            RenderSystem.enableBlend();

            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.5F);
            RenderSystem.setShaderTexture(0, BG_SLOT_LOCATION);
            this.blit(poseStack, slotTopLeft, slotTopTop, 18, 0, 18, 18, 18, 18);
            poseStack.popPose();
        }
    }

    private void renderButtons(PoseStack pPoseStack, int pMouseX, int pMouseY, int pX, int pY, int pLastVisibleElementIndex) {
        for (int i = this.startIndex; i < pLastVisibleElementIndex && i < this.menu.getNumEnchantments(); ++i) {
            int j = i - this.startIndex;
            int k = pX + j % 1 * 16;
            int l = j / 1;
            int i1 = pY + l * 18 + 2;
            int j1 = this.imageHeight;
            if (i == this.menu.getSelectedRecipeIndex()) {
                j1 += 18;
            } else if (pMouseX >= k && pMouseY >= i1 && pMouseX < k + 64 && pMouseY < i1 + 18) {
                j1 += 36;
            }

            this.blit(pPoseStack, k, i1 - 1, 0, j1, 64, 18);
        }

    }

    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        this.scrolling = false;
        if (this.displayEnchantments) {
            int i = this.leftPos + 52;
            int j = this.topPos + 14;
            int k = this.startIndex + 3;

            for (int l = this.startIndex; l < k; ++l) {
                int i1 = l - this.startIndex;
                double d0 = pMouseX - (double) (i + i1 % 1 * 64);
                double d1 = pMouseY - (double) (j + i1 / 1 * 18);
                if (d0 >= 0.0D && d1 >= 0.0D && d0 < 64.0D && d1 < 18.0D && this.menu.clickMenuButton(this.minecraft.player, l)) {
                    Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
                    this.minecraft.gameMode.handleInventoryButtonClick((this.menu).containerId, l);
                    return true;
                }
            }

            i = this.leftPos + 119;
            j = this.topPos + 9;
            if (pMouseX >= (double) i && pMouseX < (double) (i + 12) && pMouseY >= (double) j && pMouseY < (double) (j + 54)) {
                this.scrolling = true;
            }
        }

        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }


    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        if (this.scrolling && this.isScrollBarActive()) {
            int i = this.topPos + 14;
            int j = i + 54;
            this.scrollOffs = ((float) pMouseY - (float) i - 7.5F) / ((float) (j - i) - 15.0F);
            this.scrollOffs = Mth.clamp(this.scrollOffs, 0.0F, 1.0F);
            this.startIndex = (int) ((double) (this.scrollOffs * (float) this.getOffscreenRows()) + 0.5D) * 3;
            return true;
        } else {
            return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
        }
    }

    public boolean mouseScrolled(double pMouseX, double pMouseY, double pDelta) {
        if (this.isScrollBarActive()) {
            int i = this.getOffscreenRows();
            float f = (float) pDelta / (float) i;
            this.scrollOffs = Mth.clamp(this.scrollOffs - f, 0.0F, 1.0F);
            this.startIndex = (int) ((double) (this.scrollOffs * (float) i) + 0.5D) * 4;
        }

        return true;
    }

    private boolean isScrollBarActive() {
        return this.displayEnchantments && this.menu.getNumEnchantments() > 3;
    }

    protected int getOffscreenRows() {
        return Mth.ceil((this.menu.getNumEnchantments()) / 3F);
    }

    private void containerChanged() {
        this.displayEnchantments = this.menu.hasInputItem();
        if (!this.displayEnchantments) {
            this.scrollOffs = 0.0F;
            this.startIndex = 0;
        }
    }
}
