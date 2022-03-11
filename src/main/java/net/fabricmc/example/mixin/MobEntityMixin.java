package net.fabricmc.example.mixin;

import net.minecraft.command.argument.Vec3ArgumentType;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.*;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.*;

import org.apache.logging.log4j.core.net.ssl.StoreConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin {

@Shadow
public GoalSelector goalSelector;

    public MobEntityMixin(EntityType<? extends CreeperEntity> entityType, World world, CallbackInfo info) {
    //    super(entityType, world);

    }

    @Inject(method = "<init>(Lnet/minecraft/entity/EntityType;Lnet/minecraft/world/World;)V", 
        at = @At("TAIL"))
    private void m_onInit(EntityType<? extends MobEntity> entityType, World world, CallbackInfo info) {
        if ((Object)(this) instanceof CreeperEntity) {
//            System.out.println("DEBUG: CreeperEntity found.  " + this.getClass().getName().toString());
// hack
//            goalSelector.remove(new LookAtEntityGoal((CreeperEntity)(Object)(this), PlayerEntity.class, 8.0F));
//            goalSelector.add(5, new LookAtEntityGoal((CreeperEntity)(Object)(this), PlayerEntity.class, 26.0F));

        }
    }

    @Inject(method = "initGoals()V", 
    at = @At("TAIL"))
    private void m_onInit(CallbackInfo info) {
    //    .goalSelector.remove(new LookAtEntityGoal((CreeperEntity)(Object)(this), PlayerEntity.class, 8.0F));
    //    this.goalSelector.add(5, new LookAtEntityGoal((CreeperEntity)(Object)(this), PlayerEntity.class, 16.0F));
        if ((Object)(this) instanceof CreeperEntity) {
//            MobEntity e = (MobEntity)(Object)(this);
            System.out.println("DEBUG: CreeperEntity initGoals() rewrite hook found.");
        }
    }

    public void creeperSetGoals(){
        System.out.println("DEBUG: creeperSetGoals() - Overriding creeper goals.");
/*
        goalSelector.remove(new LookAtEntityGoal((CreeperEntity)(Object)(this), PlayerEntity.class, 8.0F));
        goalSelector.add(1, new LookAtEntityGoal((CreeperEntity)(Object)(this), PlayerEntity.class, 60.0F));
        goalSelector.remove(new FollowTargetGoal((CreeperEntity)(Object)(this), PlayerEntity.class, true));
        goalSelector.add(1, new FollowTargetGoal((CreeperEntity)(Object)(this), PlayerEntity.class, false));
        goalSelector.add(100, new LookAtEntityGoal((CreeperEntity)(Object)(this), PlayerEntity.class, 60.0F));
        goalSelector.add(100, new FollowTargetGoal((CreeperEntity)(Object)(this), PlayerEntity.class, false));
*/
    }
} // end class MobEntityMixin

