package com.mrshiehx.mcmods.xphone.modules;

import com.mrshiehx.mcmods.xphone.utils.Utils;
import net.minecraft.client.MinecraftClient;

public class TitleManager {
    public static String customTitle;
    public static String windowTitle;

    public static void setTitle(String title){
        customTitle = Utils.isEmpty(title)?null:title;
        MinecraftClient.getInstance().updateWindowTitle();
    }
}
