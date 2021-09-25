package xyz.r2turntrue.lml.mixins;

import me.wincho.leaf.client.graphics.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TitleScreen.class)
public class MainMenuMixin {

//    @Inject(method = "init", at = @At(value = "INVOKE", target = "Lme/wincho/leaf/client/graphics/ui/UIPanel;addComponent(Lme/wincho/leaf/client/graphics/ui/UIComponent)V", ordinal = 0))
//    public void initInject(CallbackInfoReturnable<UIPanel> cir) {
//        System.out.println("Injected");
//    }

}

