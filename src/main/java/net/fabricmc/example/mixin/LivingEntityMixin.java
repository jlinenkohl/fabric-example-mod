package net.fabricmc.example.mixin;

import net.minecraft.entity.*;
import net.minecraft.entity.damage.*;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.world.*;
import net.minecraft.world.explosion.*;
import net.minecraft.util.math.Vec3d;


import org.apache.logging.log4j.core.net.ssl.StoreConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

// global hook for LivingEntity instances; setHealth() is called in their constructor
    @Inject(method = "setHealth", 
    at = @At("HEAD"))
    private void m_onSetHealth(CallbackInfo info) {
//        System.out.println("DEBUG - LivingEntityMixin - m_onSetHealth: " + this.getClass().getName().toString());
    }

// per burger, might use
//FabricLoader.getInstance().getMappingResolver().mapClassName()

// damage - fiery chickens
    @Inject(method = "damage", 
        at = @At("HEAD"))
    private void m_onDamage(DamageSource source, float amount, CallbackInfoReturnable info) {
//        System.out.println("DEBUG - LivingEntityMixin - m_onDamage: " + this.getClass().getName().toString());
        if ((Object)(this) instanceof ChickenEntity) {
//        if (this.getClass().getName().toString().toLowerCase().matches("^.*passive.chicken.*$")) {
//            System.out.println("DEBUG - LivingEntityMixin - m_onDamage: " + this.getClass().getName().toString());
            if (source.name.matches("player")) {
                Entity e = (Entity)(Object)this;
                e.setOnFireFor(5);

            }
        }
    } // end m_onDamage()


// onDeath - exploding pigs
    @Inject(method = "onDeath", 
        at = @At("HEAD"))
    private void m_onDeath(DamageSource source, CallbackInfo info) {
 //       System.out.println("DEBUG - LivingEntityMixin - m_onDeath: " + this.getClass().getName().toString());
        if ((Object)this instanceof PigEntity) {
//        if (this.getClass().getName().toString().toLowerCase().matches("^.*passive.pig.*$")) {
//            System.out.println("DEBUG - LivingEntityMixin - m_onDeath: " + this.getClass().getName().toString());
            Entity e = (Entity)(Object)this;
            Explosion.DestructionType destructionType = e.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING) ? Explosion.DestructionType.DESTROY : Explosion.DestructionType.NONE;
            e.world.createExplosion(e, e.getX(), e.getY(), e.getZ(), 3.0F, true, destructionType);
        }
    } // end onDeath()

    @Inject(method = "travel", 
        at = @At("HEAD"))
    private void m_onTravel(Vec3d movementInput, CallbackInfo info) {
        if ((Object)(this) instanceof CreeperEntity) {
            CreeperEntity e = (CreeperEntity)(Object)this;
//            System.out.println("DEBUG: CreeperEntity current movementSpeed: " + e.getMovementSpeed());
//            System.out.println("DEBUG: CreeperEntity setMovementSpeed()");
            e.setMovementSpeed(e.getMovementSpeed() * 2.0F);
//            Vec3d m_Movement = e.getVelocity();
//            e.updateVelocity(e.getMovementSpeed(), m_Movement);
//            System.out.println("DEBUG: CreeperEntity new movementSpeed: " + e.getMovementSpeed());
        }
    }

} // end class EntityMixin
