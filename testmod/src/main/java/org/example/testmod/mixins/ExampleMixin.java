package org.example.testmod.mixins;

import me.wincho.leaf.client.Leaf;
import me.wincho.leaf.client.main.Main;
import org.checkerframework.checker.units.qual.A;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.security.auth.callback.Callback;

@Mixin(Leaf.class)
public class ExampleMixin {
    @Inject(method = "run", at = @At("HEAD"))
    public void main(CallbackInfo info) {
        System.out.println("Example Mixin!!");
    }
}
