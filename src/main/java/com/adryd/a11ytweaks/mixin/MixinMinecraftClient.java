
package com.adryd.a11ytweaks.mixin;

import com.adryd.a11ytweaks.A11yTweaksMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.util.Window;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecraftClient.class)
public abstract class MixinMinecraftClient {
    /* Scale Debug Pie */
    @Shadow
    @Final
    private Window window;
    @Redirect(method = "drawProfilerResults", at = @At(value = "INVOKE", target = "Lorg/joml/Matrix4f;setOrtho(FFFFFF)Lorg/joml/Matrix4f;"))
    private Matrix4f getScaledPosition(Matrix4f instance, float left, float right, float bottom, float top, float zNear, float zFar) {

        return instance.setOrtho(
                left,
                (float) this.getScaledWidth(window),
                (float) this.getScaledHeight(window),
                top,
                zNear,
                zFar
        );
    }

    @Redirect(method = "drawProfilerResults", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;getFramebufferWidth()I", ordinal = 1))
    private int getScaledWidth(Window instance) {
        if (A11yTweaksMod.CONFIG.getScaleDebugPie()) {
            return (int) (window.getFramebufferWidth() / (window.getScaleFactor() / 2));
        }
        return window.getFramebufferWidth();
    }

    @Redirect(method = "drawProfilerResults", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;getFramebufferHeight()I", ordinal = 1))
    private int getScaledHeight(Window instance) {
        if (A11yTweaksMod.CONFIG.getScaleDebugPie()) {
            return (int) (window.getFramebufferHeight() / (window.getScaleFactor() / 2));
        }
        return window.getFramebufferHeight();
    }
}
