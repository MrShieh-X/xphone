package com.mrshiehx.mcmods.xphone.mixin;

import com.mrshiehx.mcmods.xphone.modules.TitleManager;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Window.class)
public class WindowMixin {
    @Inject(at=@At("TAIL"),method = "setTitle(Ljava/lang/String;)V")
    public void setTitle(String title, CallbackInfo ci){
        TitleManager.windowTitle=title;
    }
}
