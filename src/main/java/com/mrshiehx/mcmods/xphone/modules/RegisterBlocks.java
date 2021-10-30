package com.mrshiehx.mcmods.xphone.modules;

import com.mrshiehx.mcmods.xphone.XPhone;
import com.mrshiehx.mcmods.xphone.block.RemoteRedstoneReceiver;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import javax.xml.xpath.XPath;

public class RegisterBlocks {
    public static final Block remoteRedstoneReceiver=new RemoteRedstoneReceiver(FabricBlockSettings.of(Material.STONE).strength(5).requiresTool()./*透明*/nonOpaque());
    public static void registerBlocks(){
        Registry.register(Registry.BLOCK, new Identifier(XPhone.MODID, "remote_redstone_receiver"), remoteRedstoneReceiver);
        Registry.register(Registry.ITEM, new Identifier(XPhone.MODID, "remote_redstone_receiver"), new BlockItem(remoteRedstoneReceiver, new Item.Settings().group(RegisterItems.ITEM_GROUP)));
    }
}
