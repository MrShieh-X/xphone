package com.mrshiehx.mcmods.xphone.items.phones.xphone_1;

import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

import java.util.List;

public class XPhone1Horn extends XPhone1{
    @Override
    protected void appendPhoneTip(List<Text> list) {
        list.add(new TranslatableText("itemTooltip.xphone1.no_wlan_connector").formatted(Formatting.GRAY));
    }

    public XPhone1Horn() {
        super(XPhone1Type.HORN);
    }
}
