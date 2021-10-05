package xyz.r2turntrue.lml.mixins;

import me.wincho.leaf.client.gui.screen.Screen;
import me.wincho.leaf.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
    @Inject(method = "init", at = @At("HEAD"))
    public void init(CallbackInfo ci) {
    }
}
