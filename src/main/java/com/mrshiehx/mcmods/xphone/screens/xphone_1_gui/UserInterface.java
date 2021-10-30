package com.mrshiehx.mcmods.xphone.screens.xphone_1_gui;

import com.mrshiehx.mcmods.xphone.block.RemoteRedstoneReceiver;
import com.mrshiehx.mcmods.xphone.items.phones.xphone_1.XPhone1;
import com.mrshiehx.mcmods.xphone.modules.RegisterBlocks;
import com.mrshiehx.mcmods.xphone.screens.ChangeTitleScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public enum UserInterface {
    MAIN(null, null, new LinkedList<>()) {
        @Override
        public void init(MinecraftClient client, Screen currentScreen, Api api, Map<String, Object> intent) {
            //applications.clear();
            int i = 0;
            TexturedButtonWidget changeWindowTitle = new TexturedButtonWidget(api.calculateButtonGuiDirectionX(++i), api.calculateButtonGuiDirectionY(i), 20, 20, 123, 0, 20, XPhone1GUI.GUI_TEXTURE, 256, 256, (buttonWidget) -> {
                if (client != null) client.openScreen(new ChangeTitleScreen(currentScreen, client.options));
            });
            //changeWindowTitle.visible=true;
            changeWindowTitle.setMessage(new TranslatableText("application.change_window.title"));
            api.addButton(addApplications(changeWindowTitle));


            TexturedButtonWidget redstone = new TexturedButtonWidget(api.calculateButtonGuiDirectionX(++i), api.calculateButtonGuiDirectionY(i), 20, 20, 103, 0, 20, XPhone1GUI.GUI_TEXTURE, 256, 256, (buttonWidget) -> {
                api.changeUI(REDSTONE_CONTROLLER,null);
            });
            //redstone.visible=true;
            redstone.setMessage(new TranslatableText("application.redstone_controller.title"));
            api.addButton(addApplications(redstone));
        }


        private void setAllApplicationVisible(boolean visible) {
            for (ClickableWidget clickableWidget : applications) {
                if (clickableWidget != null) clickableWidget.visible = visible;
            }
        }
    },
    REDSTONE_CONTROLLER(new TranslatableText("application.redstone_controller.title"), MAIN, new LinkedList<>()) {
        final int DELETE_WIDTH =15;
        final int DELETE_HEIGHT =20;
        final int SPLIT=1;
        final int OPTION_WIDTH =59;
        final int OPTION_HEIGHT =20;
        final List<BlockPos>list=new LinkedList<>();
        @Override
        public void init(MinecraftClient client, Screen currentScreen, Api api, Map<String, Object> intent) {
            list.clear();
            ItemStack itemStack = api.getItemStack();
            NbtList blocks;
            if(intent!=null&&intent.size()>0){
                System.out.println("67");
                Object o=intent.get("blocks");
                if(o instanceof NbtList){
                    blocks=(NbtList) o;
                }else{
                    System.out.println("72");

                    blocks = itemStack.getOrCreateTag().getList(XPhone1.NBT_LIST_REMOTE_REDSTONE_RECEIVERS, 10/*10相当于存放jsonObject*/);
                }
            }else {
                System.out.println("76");
                blocks = itemStack.getOrCreateTag().getList(XPhone1.NBT_LIST_REMOTE_REDSTONE_RECEIVERS, 10/*10相当于存放jsonObject*/);
            }
            if (blocks.size() > 0) {
                for (int i = 0; i < blocks.size(); i++) {
                    NbtCompound block = blocks.getCompound(i);
                    if (block != null) {
                        if (block.contains("x") &&
                                block.contains("y") &&
                                block.contains("z")) {
                            list.add(new BlockPos(block.getInt("x"), block.getInt("y"), block.getInt("z")));
                        }
                    }
                }
                int size = list.size();
                if (size > 0) {
                    int deleteX = api.getGuiDirectionX() + XPhone1GUI.PHONE_WIDTH - 13 - DELETE_WIDTH;
                    int optionX = api.getGuiDirectionX() + 12;

                    for (int i = 0; i < size; i++) {
                        int j = i + 1;
                        BlockPos rrpos=list.get(i);
                        ButtonWidget option = new ButtonWidget(optionX, calculateDirectionY(api.getGuiDirectionY(), j), OPTION_WIDTH, OPTION_HEIGHT, new LiteralText(String.format("%d, %d, %d",rrpos.getX(),rrpos.getY(),rrpos.getZ())), new ButtonWidget.PressAction() {
                            @Override
                            public void onPress(ButtonWidget button) {
                                if (client.getServer().getOverworld().getBlockState(rrpos).getBlock() == RegisterBlocks.remoteRedstoneReceiver) {
                                    client.getServer().getOverworld().setBlockState(rrpos,
                                            client.getServer().getOverworld().getBlockState(rrpos).get(RemoteRedstoneReceiver.LIT)
                                                    ? client.getServer().getOverworld().getBlockState(rrpos).with(RemoteRedstoneReceiver.LIT, false)
                                                    : client.getServer().getOverworld().getBlockState(rrpos).with(RemoteRedstoneReceiver.LIT, true));
                                    //client.player.sendMessage(new TranslatableText("switch"), true);
                                }
                            }
                        });
                        api.addButton(addApplications(option));

                        TexturedButtonWidget delete = new TexturedButtonWidget(deleteX, calculateDirectionY(api.getGuiDirectionY(), j), DELETE_WIDTH, DELETE_HEIGHT, 173, 0, 20, XPhone1GUI.GUI_TEXTURE, 256, 256, (buttonWidget) -> {

                            NbtList blocks2 = itemStack.getOrCreateTag().getList(XPhone1.NBT_LIST_REMOTE_REDSTONE_RECEIVERS, 10/*10相当于存放jsonObject*/);
                            if (blocks2.size() > 0) {
                                for (int k = 0; k < blocks2.size(); k++) {
                                    NbtCompound block = blocks2.getCompound(k);
                                    if (block != null) {
                                        if (block.contains("x") &&
                                                block.contains("y") &&
                                                block.contains("z")) {
                                            if(block.getInt("x")==rrpos.getX()&&
                                                    block.getInt("y")==rrpos.getY()&&
                                                    block.getInt("z")==rrpos.getZ()){
                                                blocks2.remove(k);
                                                //Map<String,Object>intentFor=new HashMap<>();
                                                //intentFor.put("blocks",blocks2);
                                                api.changeUI(this,/*intentFor*/null);
                                                return;
                                            }
                                        }
                                    }
                                }
                            }

                        });
                        api.addButton(addApplications(delete));

                    }


                }
            }
        }

        private int calculateDirectionY(int baseY,int i) {
            baseY+=1;
            i=i-1;
            return baseY+(i* DELETE_HEIGHT)+(i*SPLIT);
        }
    };

    Text title;
    UserInterface parent;
    List<ClickableWidget> applications;

    UserInterface(Text title, UserInterface parent, List<ClickableWidget> elements) {
        this.title = title;
        this.parent = parent;
        this.applications = elements;
    }


    ClickableWidget addApplications(ClickableWidget clickableWidget) {
        applications.add(clickableWidget);
        return clickableWidget;
    }

    public abstract void init(MinecraftClient client, Screen currentScreen, Api api, Map<String, Object> intent);

}