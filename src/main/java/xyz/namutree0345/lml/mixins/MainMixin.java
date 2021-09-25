package xyz.namutree0345.lml.mixins;

import me.wincho.leaf.Leaf;
import me.wincho.leaf.main.Main;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.namutree0345.lml.LoafLoader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

@Mixin(Main.class)
public class MainMixin {

    @Inject(method = "main", at = @At("HEAD"))
    private static void main(CallbackInfo ci) {
        LoafLoader.logger.info("LML Initializing...");
        LoafLoader loader = LoafLoader.getInstance();
        try {
            loader.loadMods(new File("mods/"));
        } catch (MalformedURLException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            LoafLoader.logger.info("Could not load mods!");
            e.printStackTrace();
        }
    }

}
