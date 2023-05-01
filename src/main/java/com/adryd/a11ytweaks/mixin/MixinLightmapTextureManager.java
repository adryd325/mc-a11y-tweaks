package com.adryd.a11ytweaks.mixin;

import com.adryd.a11ytweaks.A11yTweaksMod;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightmapTextureManager.class)
public abstract class MixinLightmapTextureManager {
    @Accessor("flickerIntensity")
    abstract void setFlickerIntensity(float value);

    @Inject(method = "update", at = @At("HEAD"))
    private void disableTorchFlicker(float delta, CallbackInfo ci) {
        if (A11yTweaksMod.CONFIG.getDisableTorchFlicker()) {
            setFlickerIntensity(0);
        }
    }

    @Redirect(method = "update", at=@At(value="INVOKE", target = "Lnet/minecraft/client/render/GameRenderer;getNightVisionStrength(Lnet/minecraft/entity/LivingEntity;F)F"))
    private float forceNightVisionDuration(LivingEntity entity, float f) {
        if (A11yTweaksMod.CONFIG.getDisableNightVisionFlashing()) {
            return 1.0F;
        }

        return GameRenderer.getNightVisionStrength(entity, f);
    }
}
