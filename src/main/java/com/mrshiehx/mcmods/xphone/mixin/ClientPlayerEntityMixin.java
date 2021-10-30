package com.mrshiehx.mcmods.xphone.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
    @Inject(at=@At("TAIL"),method = "useBook")
    void useBook(ItemStack book, Hand hand, CallbackInfo ci){
        /*if (book.isOf(Items.WRITABLE_BOOK)) {
            MinecraftClient.getInstance().openScreen(new XPhone1GUI(null));
        }*/
    }
}
