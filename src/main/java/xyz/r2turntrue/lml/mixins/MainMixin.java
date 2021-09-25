package xyz.r2turntrue.lml.mixins;

import me.wincho.leaf.client.main.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.r2turntrue.lml.LoafLoader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

@Mixin(Main.class)
public class MainMixin {

    @Inject(method = "main", at = @At("HEAD"))
    private static void main(CallbackInfo ci) {
        LoafLoader.logger.info("LML Initializing...");
        LoafLoader loader = LoafLoader.getInstance();
        try {
            loader.loadMods(Paths.get("mods"));
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | IOException | ClassNotFoundException | URISyntaxException e) {
            LoafLoader.logger.error("Could not load mods!");
            e.printStackTrace();
        }
    }

}
