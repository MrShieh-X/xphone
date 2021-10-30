package com.mrshiehx.mcmods.xphone.items.phones.xphone_1;

import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

import java.util.List;

public class XPhone1WlanConnector extends XPhone1{
    @Override
    protected void appendPhoneTip(List<Text> list) {
        list.add(new TranslatableText("itemTooltip.xphone1.no_horn").formatted(Formatting.GRAY));
    }


    public XPhone1WlanConnector() {
        super(XPhone1Type.WLAN_CONNECTOR);
    }
}
