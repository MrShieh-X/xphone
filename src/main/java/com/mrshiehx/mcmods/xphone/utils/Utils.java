package com.mrshiehx.mcmods.xphone.utils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class Utils {
    public static boolean isEmpty(CharSequence charSequence){
        return charSequence==null||charSequence.length()<=0;
    }

    public static void showMessage(ItemUsageContext context, Text text){
        if(context!=null){
            PlayerEntity player=context.getPlayer();
            if(player!=null)player.sendMessage(text, true);
        }
    }
}
