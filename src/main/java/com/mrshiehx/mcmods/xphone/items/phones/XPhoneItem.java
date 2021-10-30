package com.mrshiehx.mcmods.xphone.items.phones;

import com.mrshiehx.mcmods.xphone.modules.RegisterItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class XPhoneItem extends Item {
    public XPhoneItem() {
        this(RegisterItems.ITEM_GROUP,1);
    }
    public XPhoneItem(int maxCount) {
        this(RegisterItems.ITEM_GROUP,maxCount);
    }
    public XPhoneItem(ItemGroup itemGroup) {
        this(itemGroup,1);
    }
    public XPhoneItem(ItemGroup itemGroup, int maxCount) {
        super(new FabricItemSettings().group(itemGroup).maxCount(maxCount));
    }
}
