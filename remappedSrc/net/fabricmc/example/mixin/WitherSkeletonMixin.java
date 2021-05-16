package net.fabricmc.example.mixin;

import net.minecraft.entity.*;
import net.minecraft.entity.damage.*;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.world.*;

import org.apache.logging.log4j.core.net.ssl.StoreConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(WitherSkeletonEntity.class)
public abstract class WitherSkeletonMixin {

    @Inject(method = "dropEquipment", 
        at = @At("HEAD"))
    private void m_onDropEquipment(DamageSource source, int lootingMultiplier, boolean allowDrops, CallbackInfo info) {

        System.out.println("DEBUG: " + this.getClass().getName().toString());


        if ((Object)(this) instanceof WitherSkeletonEntity) {
//        if (this.getClass().getName().toString().toLowerCase().matches("^.*witherskeleton.*$")) {
            WitherSkeletonEntity e = (WitherSkeletonEntity)(Object)this;
//            e.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.DIAMOND_AXE));
            e.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
        }
    }
} // end class WitherSkeletonMixin
