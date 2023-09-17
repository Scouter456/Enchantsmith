package com.scouter.enchantsmith.advancements;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class EnchantSmithTrigger extends SimpleCriterionTrigger<EnchantSmithTrigger.TriggerInstance> {
    static final ResourceLocation ID = new ResourceLocation("enchantsmith_enchant");

    public ResourceLocation getId() {
        return ID;
    }

    public TriggerInstance createInstance(JsonObject pJson, EntityPredicate.Composite pEntityPredicate, DeserializationContext pConditionsParser) {
        EntityPredicate.Composite entitypredicate$composite = EntityPredicate.Composite.fromJson(pJson, "villager", pConditionsParser);
        ItemPredicate itempredicate = ItemPredicate.fromJson(pJson.get("item"));
        return new TriggerInstance(pEntityPredicate, entitypredicate$composite, itempredicate);
    }

    public void trigger(ServerPlayer pPlayer) {
        this.trigger(pPlayer, (p_70970_) -> {
            return p_70970_.matches();
        });
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        private final EntityPredicate.Composite villager;
        private final ItemPredicate item;

        public TriggerInstance(EntityPredicate.Composite pPlayer, EntityPredicate.Composite pVillager, ItemPredicate pItem) {
            super(EnchantSmithTrigger.ID, pPlayer);
            this.villager = pVillager;
            this.item = pItem;
        }

        public static TriggerInstance tradedWithVillager() {
            return new TriggerInstance(EntityPredicate.Composite.ANY, EntityPredicate.Composite.ANY, ItemPredicate.ANY);
        }

        public static TriggerInstance tradedWithVillager(EntityPredicate.Builder pVillager) {
            return new TriggerInstance(EntityPredicate.Composite.wrap(pVillager.build()), EntityPredicate.Composite.ANY, ItemPredicate.ANY);
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
