package com.mrshiehx.mcmods.xphone.screens.xphone_1_gui;

import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.item.ItemStack;

import java.util.Map;

public interface Api{
    <T extends ClickableWidget> T addButton(T button);
    <T extends Element> T addChild(T child);
    int calculateButtonGuiDirectionX(int order);
    int calculateButtonGuiDirectionY(int order);
    int getGuiDirectionX();
    int getGuiDirectionY();
    int getCLGuiDirectionX();
    int getCLGuiDirectionY();
    void changeUI(UserInterface userInterface, Map<String,Object> intent);
    int getVolume();
    void setVolume(int volume);
    ItemStack getItemStack();
}