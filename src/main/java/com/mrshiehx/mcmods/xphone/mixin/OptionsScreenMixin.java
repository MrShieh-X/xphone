package com.mrshiehx.mcmods.xphone.mixin;

import com.mrshiehx.mcmods.xphone.screens.ChangeTitleScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OptionsScreen.class)
public class OptionsScreenMixin extends Screen {
    @Shadow @Final private GameOptions settings;

    protected OptionsScreenMixin(Text title) {
        super(title);
    }

    @Inject(at=@At("TAIL"),method = "init()V")
    protected void init(CallbackInfo ci) {
        addButton(new ButtonWidget(width / 2 - 155, this.height / 6 + 48 - 6-28, 150*2+10, 20, new TranslatableText("options.changeTitle"), (button) -> {
            if(client!=null)client.openScreen(new ChangeTitleScreen(this, this.settings));
        }));
    }
}
