package com.mrshiehx.mcmods.xphone.modules;

import com.mrshiehx.mcmods.xphone.XPhone;
import com.mrshiehx.mcmods.xphone.items.phones.xphone_1.XPhone1Complete;
import com.mrshiehx.mcmods.xphone.items.phones.xphone_1.XPhone1Default;
import com.mrshiehx.mcmods.xphone.items.phones.xphone_1.XPhone1Horn;
import com.mrshiehx.mcmods.xphone.items.phones.xphone_1.XPhone1WlanConnector;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.mrshiehx.mcmods.xphone.XPhone.*;

public class RegisterItems {
    public static final Item battery = new Item(new FabricItemSettings().group(RegisterItems.ITEM_GROUP));
    public static final Item cpu = new Item(new FabricItemSettings().group(RegisterItems.ITEM_GROUP));
    public static final Item gpu = new Item(new FabricItemSettings().group(RegisterItems.ITEM_GROUP));
    public static final Item horn = new Item(new FabricItemSettings().group(RegisterItems.ITEM_GROUP));
    public static final Item mainboard = new Item(new FabricItemSettings().group(RegisterItems.ITEM_GROUP));
    public static final Item wrapped_mainboard_for_xphone_1 = new Item(new FabricItemSettings().group(RegisterItems.ITEM_GROUP).maxCount(1));
    public static final Item ram_512k = new Item(new FabricItemSettings().group(RegisterItems.ITEM_GROUP).maxCount(1));
    public static final Item rom_8gb = new Item(new FabricItemSettings().group(RegisterItems.ITEM_GROUP).maxCount(1));
    public static final Item screen = new Item(new FabricItemSettings().group(RegisterItems.ITEM_GROUP));
    public static final Item shell = new Item(new FabricItemSettings().group(RegisterItems.ITEM_GROUP));
    public static final Item wlan_connector = new Item(new FabricItemSettings().group(RegisterItems.ITEM_GROUP));
    public static final XPhone1Complete xphone_1_horn_wc = new XPhone1Complete();
    public static final XPhone1Default xphone_1 = new XPhone1Default();
    public static final XPhone1WlanConnector xphone_1_wc = new XPhone1WlanConnector();
    public static final XPhone1Horn xphone_1_horn = new XPhone1Horn();
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(
            new Identifier(XPhone.MODID,"item_group_xphone"))
            .icon(()->new ItemStack(xphone_1_horn_wc))
            .appendItems(stacks->{
                stacks.add(new ItemStack(battery));
                stacks.add(new ItemStack(shell));
                stacks.add(new ItemStack(screen));
                stacks.add(new ItemStack(cpu));
                stacks.add(new ItemStack(gpu));
                stacks.add(new ItemStack(mainboard));
                stacks.add(new ItemStack(ram_512k));
                stacks.add(new ItemStack(rom_8gb));
                stacks.add(new ItemStack(wrapped_mainboard_for_xphone_1));
                stacks.add(new ItemStack(horn));
                stacks.add(new ItemStack(wlan_connector));
                stacks.add(new ItemStack(xphone_1));
                stacks.add(new ItemStack(xphone_1_horn_wc));
                stacks.add(new ItemStack(xphone_1_horn));
                stacks.add(new ItemStack(xphone_1_wc));

                stacks.add(new ItemStack(RegisterBlocks.remoteRedstoneReceiver));
                //stacks.add(new ItemStack(battery));
            }).build();
    public static void registerItems(){
        Registry.register(Registry.ITEM, new Identifier(MODID, "battery"), battery);
        Registry.register(Registry.ITEM, new Identifier(MODID, "cpu"), cpu);
        Registry.register(Registry.ITEM, new Identifier(MODID, "gpu"), gpu);
        Registry.register(Registry.ITEM, new Identifier(MODID, "horn"), horn);
        Registry.register(Registry.ITEM, new Identifier(MODID, "mainboard"), mainboard);
        Registry.register(Registry.ITEM, new Identifier(MODID, "wrapped_mainboard_for_xphone_1"), wrapped_mainboard_for_xphone_1);
        Registry.register(Registry.ITEM, new Identifier(MODID, "ram_512k"), ram_512k);
        Registry.register(Registry.ITEM, new Identifier(MODID, "rom_8gb"), rom_8gb);
        Registry.register(Registry.ITEM, new Identifier(MODID, "screen"), screen);
        Registry.register(Registry.ITEM, new Identifier(MODID, "shell"), shell);
        Registry.register(Registry.ITEM, new Identifier(MODID, "wlan_connector"), wlan_connector);
        Registry.register(Registry.ITEM, new Identifier(MODID, "xphone_1_horn_wc"), xphone_1_horn_wc);
        Registry.register(Registry.ITEM, new Identifier(MODID, "xphone_1"), xphone_1);
        Registry.register(Registry.ITEM, new Identifier(MODID, "xphone_1_wc"), xphone_1_wc);
        Registry.register(Registry.ITEM, new Identifier(MODID, "xphone_1_horn"), xphone_1_horn);
    }
}
