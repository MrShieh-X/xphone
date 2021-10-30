package com.mrshiehx.mcmods.xphone.mixin;

import com.mrshiehx.mcmods.xphone.XPhone;
import com.mrshiehx.mcmods.xphone.modules.TitleManager;
import com.mrshiehx.mcmods.xphone.utils.Utils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
	/*@Inject(at = @At("HEAD"), method = "init()V")
	private void init(CallbackInfo info) {
		ExampleMod.LOGGER.info("This line is printed by an example mod mixin!");
	}*/

	@Inject(at=@At("RETURN"),method = "getWindowTitle()Ljava/lang/String;" , cancellable = true)
	private void getWindowTitle(CallbackInfoReturnable<String>cir){
		cir.setReturnValue(!Utils.isEmpty(TitleManager.customTitle)?TitleManager.customTitle:(cir.getReturnValue()));
	}

	@Inject(at=@At("TAIL"),method = "openScreen(Lnet/minecraft/client/gui/screen/Screen;)V")
	public void openScreen(Screen screen, CallbackInfo ci) {
		if(screen!=null&&screen.getClass()!=null)XPhone.LOGGER.info("openScreen: "+screen.getClass().getName());
	}
}
