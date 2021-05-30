package net.fabricmc.example.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

import net.minecraft.entity.projectile.*;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.world.explosion.*;

import org.apache.logging.log4j.core.net.ssl.StoreConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.*;


@Mixin(SnowballEntity.class)
public abstract class SnowballEntityMixin {
    @Inject(method = "onEntityHit",
        at = @At("HEAD"))
    private void m_onEntityHit(EntityHitResult entityHitResult, CallbackInfo info) {
        Entity e = entityHitResult.getEntity();
    //        System.out.println("DEBUG: m_onEntityHit() called.");
    //        entity.damage(DamageSource.thrownProjectile(this, this.getOwner()), (float)i);
        Explosion.DestructionType destructionType = Explosion.DestructionType.DESTROY;
        e.world.createExplosion(e, e.getX(), e.getY(), e.getZ(), 5.0F, destructionType);
        // cast to get the actual source
        float damage = 35.0F;
        SnowballEntity m_this = ((SnowballEntity)(Object)this);
        e.damage(DamageSource.thrownProjectile(m_this, m_this.getOwner()), damage);
    }
}


/*

# Just tucking in an example for a very generic constructor Mixin injection here.

@Mixin(SomeJavaFile.class)
public class MixinSomeJavaFile {

    // signal that we want to inject into a method
    @Inject(
        method = "<init>",  // the jvm bytecode signature for the constructor
        at = @At("HEAD")  // signal that this void should be run at the method HEAD, meaning the first opcode
    )
    public void constructorHead(
        // you will need to put any parameters the constructor accepts here, they will be the actual passed values
        // it also needs to accept a special argument that mixin passes to this injection method
        CallbackInfo ci
    ) {
        System.out.println("An instance of SomeJavaFile has been created!");
    }

}

*/