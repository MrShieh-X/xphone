package com.mrshiehx.mcmods.xphone.items.phones.xphone_1;

import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

import java.util.List;

public class XPhone1Default extends XPhone1{
    @Override
    protected void appendPhoneTip(List<Text> list) {
        list.add(new TranslatableText("itemTooltip.xphone1.default").formatted(Formatting.GRAY));
    }
    public XPhone1Default() {
        super(XPhone1Type.DEFAULT);
    }
}
