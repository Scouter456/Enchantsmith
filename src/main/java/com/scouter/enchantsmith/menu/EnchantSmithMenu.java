package com.scouter.enchantsmith.menu;

import com.scouter.enchantsmith.utils.EnchantmentUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
Some import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.ClientSideMerchant;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EnchantSmithMenu extends AbstractContainerMenu {
    protected final ResultContainer resultSlots = new ResultContainer();
    private final DataSlot cost = DataSlot.standalone();
    private final DataSlot extraEnchantmentLevelCost = DataSlot.standalone();
    private final DataSlot extraExperienceLevelCost = DataSlot.standalone();
    private Map<Integer, Integer> emeraldCosts = new HashMap<>(){{
        put(3,2);
        put(7,3);
        put(15,4);
        put(31,5);
    }};
    private final Level level;
    private final DataSlot enchantmentId = DataSlot.standalone();
    private final ContainerLevelAccess access;
    private Map<Enchantment, Integer> enchantments = new HashMap<>();
    private ItemStack input = ItemStack.EMPTY;
    private ItemStack goldInput = ItemStack.EMPTY;
    private ItemStack emeraldInput = ItemStack.EMPTY;
    private final RandomSource random = RandomSource.create();
    private final DataSlot selectedEnchantmentIndex = DataSlot.standalone();
    private DataSlot enchantLevel = DataSlot.standalone();
    final Slot inputSlot;
    final Slot goldInputSlot;
    final Slot emeraldInputSlot;
    private Merchant trader;
    /** The inventory slot that stores the output of the crafting recipe. */
    final Slot resultSlot;
    Runnable slotUpdateListener = () -> {
    };
    public final Container container = new SimpleContainer(1) {
        /**
         * For tile entities, ensures the chunk containing the tile entity is saved to disk later - the game won't think
         * it hasn't changed and skip it.
         */
        public void setChanged() {
            super.setChanged();
            EnchantSmithMenu.this.slotsChanged(this);
            EnchantSmithMenu.this.slotUpdateListener.run();
        }
    };

    Runnable goldSlotUpdateListener = () -> {
    };
    public final Container goldContainer = new SimpleContainer(1) {
        /**
         * For tile entities, ensures the chunk containing the tile entity is saved to disk later - the game won't think
         * it hasn't changed and skip it.
         */
        public void setChanged() {
            super.setChanged();
            EnchantSmithMenu.this.goldSlotsChanged(this);
            EnchantSmithMenu.this.goldSlotUpdateListener.run();
        }
    };

    Runnable emeraldSlotUpdateListener = () -> {
    };
    public final Container emeraldContainer = new SimpleContainer(1) {
        /**
         * For tile entities, ensures the chunk containing the tile entity is saved to disk later - the game won't think
         * it hasn't changed and skip it.
         */
        public void setChanged() {
            super.setChanged();
            EnchantSmithMenu.this.emeraldSlotsChanged(this);
            EnchantSmithMenu.this.emeraldSlotUpdateListener.run();
        }
    };

    /** The inventory that stores the output of the crafting recipe. */
    final ResultContainer resultContainer = new ResultContainer();
    public EnchantSmithMenu(int id, Inventory inventory, FriendlyByteBuf friendlyByteBuf) {
        this(ESMenus.ENCHANTSMITH_MENU.get(), id, inventory, ContainerLevelAccess.NULL, new ClientSideMerchant(inventory.player));
    }

    public EnchantSmithMenu(int id, Inventory inventory, ContainerLevelAccess access, Merchant merchant) {
        this(ESMenus.ENCHANTSMITH_MENU.get(), id, inventory, access, merchant);
    }
    public EnchantSmithMenu(MenuType<?> menuType, int id, Inventory inventory, ContainerLevelAccess access, Merchant trader) {
        super(menuType, id);
        checkContainerSize(inventory, 4);
        this.access = access;
        this.level = inventory.player.level;



        addPlayerInventory(inventory);
        addPlayerHotbar(inventory);


        this.addDataSlot(this.cost);
        this.addDataSlot(this.extraEnchantmentLevelCost);
        this.addDataSlot(this.extraExperienceLevelCost);
        this.addDataSlot(this.enchantmentId);
        this.addDataSlot(this.enchantLevel);
        this.trader = trader;
        this.inputSlot = this.addSlot(new Slot(this.container, 0, 20, 33){
            @Override
            public boolean mayPlace(ItemStack pStack) {
                return (!pStack.is(Tags.Items.INGOTS) || !pStack.is(Tags.Items.GEMS)) && pStack.isEnchanted();
            }
        });
        this.goldInputSlot = this.addSlot(new Slot(this.goldContainer, 0, 135, 51){
            @Override
            public boolean mayPlace(ItemStack pStack) {
                return pStack.is(Items.GOLD_INGOT);
            }
        });
        this.emeraldInputSlot = this.addSlot(new Slot(this.emeraldContainer, 0, 154, 51){
            @Override
            public boolean mayPlace(ItemStack pStack) {
                return pStack.is(Items.EMERALD);
            }
        });
        this.resultSlot = this.addSlot(new Slot(this.resultContainer, 1, 143, 21) {
            /**
             * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
             */
            public boolean mayPlace(ItemStack p_40362_) {
                return false;
            }

            @Override
            public boolean mayPickup(Player pPlayer) {
                return EnchantSmithMenu.this.mayPickUp(pPlayer, this.hasItem());
            }

            public void onTake(Player p_150672_, ItemStack p_150673_) {
                p_150673_.onCraftedBy(p_150672_.level, p_150672_, p_150673_.getCount());
               // EnchantSmithMenu.this.resultContainer.awardUsedRecipes(p_150672_);
                ItemStack itemstack = EnchantSmithMenu.this.inputSlot.remove(1);
                if (!p_150672_.getAbilities().instabuild) {
                    p_150672_.giveExperienceLevels(-EnchantSmithMenu.this.extraExperienceLevelCost.get());
                    ItemStack itemstack2 = EnchantSmithMenu.this.goldInputSlot.remove(EnchantSmithMenu.this.cost.get());
                    ItemStack itemstack3 = EnchantSmithMenu.this.emeraldInputSlot.remove(EnchantSmithMenu.this.extraEnchantmentLevelCost.get());
                }
                EnchantSmithMenu.this.resetEmeraldValues();
                EnchantSmithMenu.this.enchantmentId.set(0);
                EnchantSmithMenu.this.cost.set(0);
                EnchantSmithMenu.this.resetValues();
                if (!itemstack.isEmpty()) {
                    EnchantSmithMenu.this.setupResultSlot(0);
                }


                EnchantSmithMenu.this.playTradeSound();
                super.onTake(p_150672_, p_150673_);
            }
        });


    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }


    //TODO add emerald costs and experience costs
    public boolean mayPickUp(Player player, boolean hasItem){
        return  player.getAbilities().instabuild || hasEmerald(player) && hasGold(player) && hasExperience(player);
    }

    private  boolean hasGold(Player player){
        return (this.goldInputSlot.hasItem() && this.goldInputSlot.getItem().getCount() >= this.cost.get()) && this.cost.get() > 0;
    }

    private  boolean hasEmerald(Player player){
        return (this.emeraldInputSlot.hasItem() && this.emeraldInputSlot.getItem().getCount() >= this.extraEnchantmentLevelCost.get()) && this.extraEnchantmentLevelCost.get() > 0;
    }

    private  boolean hasExperience(Player player){
        return (player.experienceLevel >= this.extraExperienceLevelCost.get()) && this.extraExperienceLevelCost.get() > 0;
    }
    public Map<Enchantment, Integer> getEnchantments() {
        return this.enchantments;
    }

    public int getNumEnchantments() {
        return this.enchantments.size();
    }
    public int getEnchantLevel() {
        return this.enchantLevel.get();
    }

    public int getExperienceCost() {
        return this.extraExperienceLevelCost.get();
    }
    private void setupEnchantsList(Container pContainer, ItemStack pStack) {
        this.enchantments.clear();
        this.resultSlot.set(ItemStack.EMPTY);
        if (!pStack.isEmpty()) {
            this.enchantments = EnchantmentHelper.getEnchantments(pStack);
        }

    }
    /**
     * Returns the index of the selected recipe.
     */
    public int getSelectedRecipeIndex() {
        return this.selectedEnchantmentIndex.get();
    }
    public boolean hasInputItem() {
        return !this.enchantments.isEmpty();
    }
    public int getGoldCost(){
        return this.cost.get();
    }

    public Slot getResultSlot(){
        return this.resultSlot;
    }

    public Slot getEmeraldResultSlot(){
        return this.emeraldInputSlot;
    }

    public void registerUpdateListener(Runnable pListener) {
        this.slotUpdateListener = pListener;
    }

    public void registerGoldUpdateListener(Runnable pListener) {
        this.goldSlotUpdateListener = pListener;
    }
    public Enchantment getRandomEnchant(){
        return Enchantment.byId(this.enchantmentId.get());
    }
    public void registerEmeraldUpdateListener(Runnable pListener) {
        this.emeraldSlotUpdateListener = pListener;
    }

    //TODO should be correct now, remove count from this shit
    void setupResultSlot(int count) {
        if (!this.enchantments.isEmpty() && this.isValidEnchantIndex(this.selectedEnchantmentIndex.get()) && count < 10 && !this.level.isClientSide) {

            Enchantment enchantment = this.enchantments.keySet().stream().toList().get(this.selectedEnchantmentIndex.get());

            ItemStack itemStack = this.input;
            ItemStack itemStack1 = ItemStack.EMPTY;
            ItemStack itemStack2 = Items.ENCHANTED_BOOK.getDefaultInstance();
            if(itemStack.is(Items.ENCHANTED_BOOK)) itemStack1 = Items.BOOK.getDefaultInstance();
            Map<Enchantment, Integer> enchants = new HashMap<>();
            enchants.putAll(getEnchantments());
            enchants.remove(enchantment);
            EnchantmentInstance chosenEnchant;

            if(itemStack1.is(Items.BOOK)) {
                 chosenEnchant = EnchantmentUtils.getRandomEnchantment(enchants, itemStack1, random);
            } else {
                 chosenEnchant = EnchantmentUtils.getRandomEnchantment(enchants, itemStack, random);
            }

            if(chosenEnchant == null)
                return;


            Enchantment selectedEnchant = chosenEnchant.enchantment;

            this.enchantmentId.set(EnchantmentUtils.getEnchantmentId(selectedEnchant));



            int emeralds = 1;
            if(this.emeraldInputSlot.getItem().getCount() > 1){
                emeralds = this.emeraldInputSlot.getItem().getCount();
            }
            this.enchantLevel.set(emeralds);
            int maxLevel = getRandomEnchant().getMaxLevel();
            if(this.enchantLevel.get() > maxLevel){
                this.enchantLevel.set(maxLevel);
            }
            this.extraEnchantmentLevelCost.set(this.enchantLevel.get());
            this.extraExperienceLevelCost.set(this.extraEnchantmentLevelCost.get() * 4);
            enchants.put(selectedEnchant, this.enchantLevel.get());

            if(itemStack1.is(Items.BOOK)){
                EnchantedBookItem.addEnchantment(itemStack2, chosenEnchant);
            } else {
                EnchantmentHelper.setEnchantments(enchants, itemStack);
            }

            if(itemStack1.is(Items.BOOK)) {
                Map<Enchantment, Integer> curEnchants = EnchantmentHelper.getEnchantments(itemStack2);
                if (curEnchants.size() != this.getEnchantments().size()) {
                    this.resultSlot.set(ItemStack.EMPTY);
                    setupResultSlot(count +=1);
                }
            } else {
                Map<Enchantment, Integer> curEnchants = EnchantmentHelper.getEnchantments(itemStack);
                if (curEnchants.size() != this.getEnchantments().size()) {
                    this.resultSlot.set(ItemStack.EMPTY);
                    setupResultSlot( count +=1);
                }
            }
            if(itemStack1.is(Items.BOOK)) {
                Map<Enchantment, Integer> enchantsStart = new HashMap<>();
                enchantsStart.putAll(getEnchantments());
                Map<Enchantment, Integer> enchantsFinal = new HashMap<>();
                enchantsFinal.putAll(EnchantmentHelper.getEnchantments(itemStack2));
                if(Objects.equals(enchantsStart, enchantsFinal)){
                    this.resultSlot.set(ItemStack.EMPTY);
                    setupResultSlot(count += 1);
                }
            } else {
                Map<Enchantment, Integer> enchantsStart = new HashMap<>();
                enchantsStart.putAll(getEnchantments());
                Map<Enchantment, Integer> enchantsFinal = new HashMap<>();
                enchantsFinal.putAll(EnchantmentHelper.getEnchantments(itemStack));
                if(Objects.equals(enchantsStart, enchantsFinal)){
                    this.resultSlot.set(ItemStack.EMPTY);
                    setupResultSlot(count += 1);
                }
            }
            int cost = EnchantmentUtils.getEnchantBaseGoldCost(getRandomEnchant());
            this.cost.set(cost);
            int currentGold = this.cost.get();
            int levelCosts = EnchantmentUtils.getExtraLevelCost(getRandomEnchant(), this.enchantLevel.get());

            this.cost.set(currentGold + levelCosts);


            if(itemStack1.is(Items.BOOK)) {
                this.resultSlot.set(itemStack2);
            } else {
                this.resultSlot.set(itemStack);
            }

        } else if(!this.level.isClientSide) {
            this.resultSlot.set(ItemStack.EMPTY);
        }

        this.broadcastChanges();
    }

    private boolean isValidEnchantIndex(int pRecipeIndex) {
        return pRecipeIndex >= 0 && pRecipeIndex < this.enchantments.size();
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    public void slotsChanged(Container pInventory) {
        super.slotsChanged(pInventory);
        ItemStack itemstack = this.inputSlot.getItem();
        if (!itemstack.is(this.input.getItem())) {
            this.input = itemstack.copy();
            this.setupEnchantsList(pInventory, itemstack);
        }
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    public void goldSlotsChanged(Container pInventory) {
        super.slotsChanged(pInventory);
        ItemStack itemstack = this.goldInputSlot.getItem();
        if (!itemstack.is(this.goldInput.getItem())) {
            this.goldInput = itemstack.copy();
            this.setupGoldCost(pInventory, itemstack);
        }
    }

    private void setupGoldCost(Container pContainer, ItemStack pStack) {
        if (!pStack.isEmpty()) {




        }
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    public void emeraldSlotsChanged(Container pInventory) {
        super.slotsChanged(pInventory);
        ItemStack itemstack = this.emeraldInputSlot.getItem();
        if (itemstack.is(Items.EMERALD) && !this.level.isClientSide) {
            this.emeraldInput = itemstack.copy();
            this.setupEmeraldCost(pInventory, itemstack);
        } else if(!this.level.isClientSide && !this.resultSlot.getItem().isEmpty()) {
            resetEmeraldValues();
            ItemStack itemStack = this.resultSlot.getItem();
            ItemStack itemStack2 = itemStack.copy();
            boolean flag = itemStack.is(Items.ENCHANTED_BOOK);


            if(!flag) {
                Map<Enchantment, Integer> emptyMap = new HashMap<>();
                EnchantmentHelper.setEnchantments(emptyMap, itemStack2);
                Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(itemStack);
                enchants.put(this.getRandomEnchant(), this.enchantLevel.get());
                EnchantmentHelper.setEnchantments(enchants, itemStack2);
                this.resultSlot.set(itemStack2);
            } else {
                itemStack2 = Items.ENCHANTED_BOOK.getDefaultInstance();
                Map<Enchantment, Integer> emptyMap = new HashMap<>();
                EnchantmentHelper.setEnchantments(emptyMap, itemStack2);
                EnchantmentInstance enchantmentInstance = new EnchantmentInstance(this.getRandomEnchant(), this.enchantLevel.get());
                EnchantedBookItem.addEnchantment(itemStack2, enchantmentInstance);
                this.resultSlot.set(itemStack2);
            }
        }
    }

    private void setupEmeraldCost(Container pContainer, ItemStack pStack) {

        if (!pStack.isEmpty() && !this.resultSlot.getItem().isEmpty() && !this.level.isClientSide) {
            resetEmeraldValues();
            this.enchantLevel.set(this.emeraldInputSlot.getItem().getCount());
            int maxLevel = getRandomEnchant().getMaxLevel();
            if(this.enchantLevel.get() > maxLevel){
                this.enchantLevel.set(maxLevel);
            }
            this.extraEnchantmentLevelCost.set(this.enchantLevel.get());
            int currentGold = EnchantmentUtils.getEnchantBaseGoldCost(getRandomEnchant());
            int levelCosts = EnchantmentUtils.getExtraLevelCost(getRandomEnchant(), this.enchantLevel.get());
            this.cost.set(currentGold + levelCosts);

            this.extraExperienceLevelCost.set(this.extraEnchantmentLevelCost.get() * 4);
            ItemStack itemStack = this.resultSlot.getItem();
            ItemStack itemStack2 = itemStack.copy();
            Map<Enchantment, Integer> emptyMap = new HashMap<>();
            EnchantmentHelper.setEnchantments(emptyMap,itemStack2);
            Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(itemStack);
            enchants.put(this.getRandomEnchant(), this.enchantLevel.get());
            EnchantmentHelper.setEnchantments(enchants,itemStack2);
            this.resultSlot.set(itemStack2);
        }
        this.broadcastChanges();
    }

    public void resetEmeraldValues(){
        this.enchantLevel.set(1);
        this.extraEnchantmentLevelCost.set(1);
        this.extraExperienceLevelCost.set(4);
        int levelCosts = EnchantmentUtils.getExtraLevelCost(getRandomEnchant(), this.enchantLevel.get());
        int cost = EnchantmentUtils.getEnchantBaseGoldCost(getRandomEnchant());
        this.cost.set(cost + levelCosts);

    }

    public boolean clickMenuButton(Player pPlayer, int pId) {
        if (this.isValidEnchantIndex(pId)) {
            this.selectedEnchantmentIndex.set(pId);
            resetValues();
        }

        return true;
    }

    public void resetValues(){
        if(!this.level.isClientSide) {
            this.cost.set(0);
            this.enchantLevel.set(0);
            this.extraEnchantmentLevelCost.set(0);
            this.extraExperienceLevelCost.set(0);
            this.setupResultSlot(0);
        }
    }
    //Todo make this correct
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 4;  // must be the number of slots you have!

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();


        // Check if the slot clicked is one of the vanilla container slots
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory

            if(index == 39){
                playTradeSound();
            }

            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }
    private void playTradeSound() {
        if (!this.trader.isClientSide()) {
            Entity entity = (Entity)this.trader;
            entity.getLevel().playLocalSound(entity.getX(), entity.getY(), entity.getZ(), this.trader.getNotifyTradeSound(), SoundSource.NEUTRAL, 1.0F, 1.0F, false);
        }
    }
    /**
     * Called when the container is closed.
     */
    public void removed(Player pPlayer) {
        super.removed(pPlayer);
        this.trader.setTradingPlayer((Player)null);
        this.resultContainer.removeItemNoUpdate(1);
        if (!this.trader.isClientSide()) {
            if (!pPlayer.isAlive() || pPlayer instanceof ServerPlayer && ((ServerPlayer)pPlayer).hasDisconnected()) {
                ItemStack itemstack = this.container.removeItemNoUpdate(0);
                if (!itemstack.isEmpty()) {
                    pPlayer.drop(itemstack, false);
                }

                itemstack = this.container.removeItemNoUpdate(0);
                if (!itemstack.isEmpty()) {
                    pPlayer.drop(itemstack, false);
                }

                ItemStack itemstack2 = this.emeraldContainer.removeItemNoUpdate(0);
                if (!itemstack2.isEmpty()) {
                    pPlayer.drop(itemstack2, false);
                }

                itemstack2 = this.emeraldContainer.removeItemNoUpdate(0);
                if (!itemstack2.isEmpty()) {
                    pPlayer.drop(itemstack2, false);
                }

                ItemStack itemstack3 = this.goldContainer.removeItemNoUpdate(0);
                if (!itemstack3.isEmpty()) {
                    pPlayer.drop(itemstack3, false);
                }

                itemstack3 = this.goldContainer.removeItemNoUpdate(0);
                if (!itemstack3.isEmpty()) {
                    pPlayer.drop(itemstack3, false);
                }


            } else if (pPlayer instanceof ServerPlayer) {
                pPlayer.getInventory().placeItemBackInInventory(this.container.removeItemNoUpdate(0));
                pPlayer.getInventory().placeItemBackInInventory(this.emeraldContainer.removeItemNoUpdate(0));
                pPlayer.getInventory().placeItemBackInInventory(this.goldContainer.removeItemNoUpdate(0));
            }

        }
    }
}
