package xyz.namutree0345.lml.mixins;

import me.wincho.leaf.Leaf;
import me.wincho.leaf.graphics.screen.TitleScreen;
import me.wincho.leaf.graphics.ui.*;
import me.wincho.leaf.utils.Vector2i;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

@Mixin(me.wincho.leaf.graphics.screen.TitleScreen.class)
public class MainMenuMixin {

    @Inject(method = "init", at = @At(value = "INVOKE", target = "Lme/wincho/leaf/graphics/ui/UIPanel;addComponent(Lme/wincho/leaf/graphics/ui/UIComponent)V", ordinal = 0))
    public void initInject(CallbackInfoReturnable<UIPanel> cir) {
        System.out.println("Injected");
    }

}

