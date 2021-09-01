package xyz.namutree0345.lml.mixins;

import me.wincho.leaf.Leaf;
import org.apache.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Leaf.class)
public class LeafMixin {

    @Final
    @Shadow
    private static Logger LOGGER;

    @Inject(method = "run", at = @At("HEAD"))
    public void run(CallbackInfo ci) {
        LOGGER.info("LML Initializing...");
    }

}
