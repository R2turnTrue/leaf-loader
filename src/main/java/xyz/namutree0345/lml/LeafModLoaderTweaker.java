package xyz.namutree0345.lml;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import java.io.File;
import java.util.List;

public class LeafModLoaderTweaker implements ITweaker {
    @Override
    public void acceptOptions(List<String> list, File file, File file1, String s) {

    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader launchClassLoader) {
        MixinBootstrap.init();

        MixinEnvironment environment = MixinEnvironment.getDefaultEnvironment();
        Mixins.addConfiguration("mixins.leaf.json");

        environment.setSide(MixinEnvironment.Side.CLIENT);
    }

    @Override
    public String getLaunchTarget() {
        return "me.wincho.leaf.main.Main";
    }

    @Override
    public String[] getLaunchArguments() {
        return new String[0];
    }
}
