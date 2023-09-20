package com.scouter.enchantsmith.advancements;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;

import static com.scouter.enchantsmith.EnchantSmith.prefix;

public class EnchantSmithTrigger extends SimpleCriterionTrigger<EnchantSmithTrigger.TriggerInstance> {
    static final ResourceLocation ID = prefix("enchantsmith_enchant");

    public ResourceLocation getId() {
        return ID;
    }

    public EnchantSmithTrigger.TriggerInstance createInstance(JsonObject p_286654_, ContextAwarePredicate p_286835_, DeserializationContext p_286772_) {
        ContextAwarePredicate contextawarepredicate = EntityPredicate.fromJson(p_286654_, "villager", p_286772_);
        ItemPredicate itempredicate = ItemPredicate.fromJson(p_286654_.get("item"));
        return new EnchantSmithTrigger.TriggerInstance(p_286835_, contextawarepredicate, itempredicate);
    }

    public void trigger(ServerPlayer pPlayer) {
        this.trigger(pPlayer, (p_70970_) -> {
            return p_70970_.matches();
        });
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        private final ContextAwarePredicate villager;
        private final ItemPredicate item;

        public TriggerInstance(ContextAwarePredicate p_286523_, ContextAwarePredicate p_286395_, ItemPredicate p_286263_) {
            super(EnchantSmithTrigger.ID, p_286523_);
            this.villager = p_286395_;
            this.item = p_286263_;
        }

        public static EnchantSmithTrigger.TriggerInstance tradedWithVillager() {
            return new EnchantSmithTrigger.TriggerInstance(ContextAwarePredicate.ANY, ContextAwarePredicate.ANY, ItemPredicate.ANY);
        }

        public static EnchantSmithTrigger.TriggerInstance tradedWithVillager(EntityPredicate.Builder pVillager) {
            return new EnchantSmithTrigger.TriggerInstance(EntityPredicate.wrap(pVillager.build()), ContextAwarePredicate.ANY, ItemPredicate.ANY);
        }

        public boolean matches() {
            return true;
        }

        public JsonObject serializeToJson(SerializationContext pConditions) {
            JsonObject jsonobject = super.serializeToJson(pConditions);
            jsonobject.add("item", this.item.serializeToJson());
            jsonobject.add("villager", this.villager.toJson(pConditions));
            return jsonobject;
        }
    }
}
