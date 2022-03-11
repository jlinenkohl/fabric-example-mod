package net.fabricmc.example.mixin;

import net.fabricmc.example.FarsightedMobs;
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

@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin {
/*
    public CreeperEntityMixin(EntityType<? extends CreeperEntity> entityType, World world, CallbackInfo info) {
    //    super(entityType, world);

    }

    @Inject(method = "<init>(Lnet/minecraft/entity/EntityType;Lnet/minecraft/world/World;)V", 
        at = @At("TAIL"))
    private void m_onInit(EntityType<? extends CreeperEntity> entityType, World world, CallbackInfo info) {

        System.out.println("DEBUG: " + this.getClass().getName().toString());
        if ((Object)(this) instanceof CreeperEntity) {
            System.out.println("DEBUG: CreeperEntity found.  " + this.getClass().getName().toString());
        }
    }
*/

    @Inject(method = "initGoals()V", 
    at = @At("TAIL"))
    private void m_onInit(CallbackInfo info) {
/*
        if ((Object)(this) instanceof CreeperEntity) {
            MobEntityMixin e = (MobEntityMixin)(Object)(this);
            // leverage method from MobEntityMixin to override goals
            e.creeperSetGoals();
        }
*/
        if ((Object)(this) instanceof CreeperEntity) {
            Entity e = (Entity)(Object)(this);
            e = FarsightedMobs.upgradeEntity(e);
            System.out.println("DEBUG: initGoals() hook executed.");
        }

    }

} // end class CreeperEntityMixin
