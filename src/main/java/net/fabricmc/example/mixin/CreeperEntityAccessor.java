package net.fabricmc.example.mixin;
import net.minecraft.entity.mob.CreeperEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CreeperEntity.class)
public interface CreeperEntityAccessor {
    @Accessor
    int getFuseTime();

    @Accessor("fuseTime")
    public void setFuseTime(int fuseTime);
}

