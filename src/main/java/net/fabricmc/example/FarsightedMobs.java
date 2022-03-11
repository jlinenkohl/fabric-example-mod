package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import net.fabricmc.example.mixin.FollowTargetGoalAccessor;
import net.fabricmc.example.mixin.GoalSelectorAccessor;
import net.fabricmc.example.mixin.MobEntityAccessor;

import java.util.HashMap;

public class FarsightedMobs implements ModInitializer {

//    public static ModConfig CONFIG = new ModConfig();

    @Override
    public void onInitialize() {
/*
        AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
*/
    }

    public static Entity upgradeEntity(Entity e) {
        if (e instanceof LivingEntity) {
            Identifier type = EntityType.getId(e.getType());
            LivingEntity living = (LivingEntity)e;
//            if (FarsightedMobs.CONFIG.followRanges.containsKey(type.toString())) {
//                int range = FarsightedMobs.CONFIG.followRanges.get(type.toString());
                int range = 64;
                System.out.println("DEBUG: upgradeEntity() - range before: " + living.getAttributeInstance(EntityAttributes.GENERIC_FOLLOW_RANGE).getBaseValue());
                living.getAttributeInstance(EntityAttributes.GENERIC_FOLLOW_RANGE).setBaseValue(range);
                System.out.println("DEBUG: upgradeEntity() - range after: " + living.getAttributeInstance(EntityAttributes.GENERIC_FOLLOW_RANGE).getBaseValue());
                FixFollowRange(living);
                return living;
//            }
        }
        return e;
    }

    public static void FixFollowRange(LivingEntity livingEntity) {
        if (livingEntity instanceof MobEntity) {
            ((GoalSelectorAccessor)((MobEntityAccessor) livingEntity).getTargetSelector()).getGoals().forEach(prioritizedGoal -> {
                Goal goal = prioritizedGoal.getGoal();
                if (goal instanceof FollowTargetGoalAccessor) {
                    FollowTargetGoalAccessor followTargetGoal = (FollowTargetGoalAccessor)  goal;
                    followTargetGoal.setTargetPredicate(followTargetGoal.getTargetPredicate()
                            .setBaseMaxDistance(livingEntity.getAttributeValue(EntityAttributes.GENERIC_FOLLOW_RANGE)));
                }
            });
        }
    }
}
