package com.mrshiehx.mcmods.xphone.screens.xphone_1_gui;

import com.mrshiehx.mcmods.xphone.XPhone;
import com.mrshiehx.mcmods.xphone.items.phones.xphone_1.XPhone1;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.WritableBookItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

import java.util.Map;
import java.util.Objects;

@Environment(EnvType.CLIENT)
public class XPhone1GUI extends Screen {
    public static final Identifier GUI_TEXTURE = new Identifier(XPhone.MODID,"textures/gui/xphone_1_gui.png");
    public static final int PHONE_WIDTH=100;
    public static final int PHONE_HEIGHT=144;
    public int INITIAL_BUTTON_DIRECTION_Y;
    public int INITIAL_BUTTON_DIRECTION_X;
    public static final int APPLICATION_WIDTH=20;
    public static final int APPLICATION_HEIGHT=20;
    //每个应用图标y轴的分隔
    public static final int SPLIT_Y=5;
    //每个应用图标x轴的分隔
    public static final int SPLIT_X=4;
    public static final String NBT_VOLUME="xphoneVolume";

    private int guiDirectionX, guiDirectionY;
    private int clGuiDirectionX, clGuiDirectionY;
    private UserInterface userInterface;
    private final Api api;
    private TexturedButtonWidget backButton;
    private Text uiTitle=new LiteralText("");
    private final ItemStack itemStack;
    private final XPhone1.XPhone1Type type;
    private int volume=100;

    public XPhone1GUI(@NotNull XPhone1.XPhone1Type type, ItemStack itemStack) {
        super(new TranslatableText("gui.xphone_1.title"));
        this.type=Objects.requireNonNull(type);
        this.itemStack=itemStack;
        //userInterface=UserInterface.MAIN;
        userInterface=UserInterface.MAIN;
        if(itemStack!=null) {
            NbtCompound nbt = itemStack.getOrCreateTag();
            if(nbt.contains(NBT_VOLUME)){
                int volume=nbt.getInt(NBT_VOLUME);
                if(volume>=0){
                    this.volume=volume;
                }else{
                    nbt.putInt(NBT_VOLUME,100);
                }
            }else{
                nbt.putInt(NBT_VOLUME,100);
            }
        }
        api=createApi();
    }

    private int getVolume() {
        int rVolume=volume>=0?volume:(volume=100);
        if(itemStack!=null){
            NbtCompound nbt = itemStack.getOrCreateTag();
            if(nbt.contains(NBT_VOLUME)){
                int volume=nbt.getInt(NBT_VOLUME);
                if(volume>=0){
                    this.volume=volume;
                    return volume;
                }else{
                    nbt.putInt(NBT_VOLUME,rVolume);
                }
            }else{
                nbt.putInt(NBT_VOLUME,rVolume);
                //return rVolume;
            }
        }
        return rVolume;
    }

    private void setVolume(int volume){
        if(volume>this.volume){
            if(this.volume!=100)this.volume=volume;
        }else if(volume<this.volume){
            if(this.volume!=0)this.volume=volume;
        }
        if(this.volume>100)this.volume=100;
        if(this.volume<0)this.volume=0;
        MinecraftClient client=MinecraftClient.getInstance();
        if(client!=null){
            PlayerEntity player=client.player;
            if(player!=null){
                player.sendMessage(new TranslatableText("message.xphone.set_volume", this.volume),true);
            }
            //.sendMess
        }
        if(itemStack!=null){
            NbtCompound nbt = itemStack.getOrCreateTag();
            nbt.putInt(NBT_VOLUME,this.volume);
        }
    }


    private void changeUI(UserInterface userInterface, Map<String,Object> intent){
        Objects.requireNonNull(userInterface);
        if(this.userInterface!=null){
            for(ClickableWidget clickableWidget:this.userInterface.applications){
                if(clickableWidget!=null)clickableWidget.visible=false;
            }
        }
        if(userInterface.parent!=null){
            //UserInterface parent=userInterface.parent;
            backButton.visible=true;
            guiDirectionY=clGuiDirectionY + 9 + 15;
            uiTitle=userInterface.title;

            //弄个高度为x的返回toolbar，guiDirectionY增加x，按返回按钮就changeUI(parent)
        }else{
            uiTitle=new LiteralText("");
            guiDirectionY=clGuiDirectionY;

            backButton.visible=false;
        }
        initInitialButtonDirection();
        this.userInterface=userInterface;
        userInterface.applications.clear();
        userInterface.init(client,this,api,intent);
    }

    void initInitialButtonDirection(){
        INITIAL_BUTTON_DIRECTION_X = guiDirectionX + 16;
        INITIAL_BUTTON_DIRECTION_Y = guiDirectionY + 13;
    }

    @Override
    protected void init() {
        super.init();
        //super.init(client,width,height);
        clGuiDirectionX=guiDirectionX = (this.width) - PHONE_WIDTH - (width/11);
        clGuiDirectionY=guiDirectionY = (this.height) - PHONE_HEIGHT;
        initInitialButtonDirection();

        //Power Button
        this.addButton(new TexturedButtonWidget(clGuiDirectionX + PHONE_WIDTH-3, clGuiDirectionY + 39, 3, 18, 100, 0, 18, GUI_TEXTURE, 256, 256, (buttonWidget) -> onClose()));

        //Up Button
        this.addButton(new TexturedButtonWidget(clGuiDirectionX, clGuiDirectionY + 17, 3, 18, 100, 0, 18, GUI_TEXTURE, 256, 256, (buttonWidget) -> {
            setVolume(volume+5);
            //this.client.openScreen(new DeathScreen(new TranslatableText("asloxuijlfhdop"),true));
        }));

        //Down Button
        this.addButton(new TexturedButtonWidget(clGuiDirectionX, clGuiDirectionY + 45, 3, 18, 100, 0, 18, GUI_TEXTURE, 256, 256, (buttonWidget) -> {
            setVolume(volume-5);
            //this.client.openScreen(new DeathScreen(new TranslatableText("asloxuijlfhdop"),true));
        }));

        //Back Button
        backButton=new TexturedButtonWidget(clGuiDirectionX+12, clGuiDirectionY + 9, 10, 15, 163, 0, 15, GUI_TEXTURE, 256, 256, (buttonWidget) -> {
            if(userInterface!=null&& userInterface.parent!=null)changeUI(userInterface.parent,null);
        });
        //backButton.visible=false;
        this.addButton(backButton);
        changeUI(userInterface,null);

        /*applications.clear();
        int i=0;
        TexturedButtonWidget changeWindowTitle=new TexturedButtonWidget(calculateButtonGuiDirectionX(++i), calculateButtonGuiDirectionY(i), 20, 20, 123, 0, 20, GUI_TEXTURE, 256, 256, (buttonWidget) -> {
            if(this.client!=null)this.client.openScreen(new ChangeTitleScreen(this, this.client.options));
        });
        changeWindowTitle.setMessage(new TranslatableText("application.change_window.title"));
        this.addButton(changeWindowTitle);
        this.addButton(addApplications(changeWindowTitle));
        TexturedButtonWidget redstone=new TexturedButtonWidget(calculateButtonGuiDirectionX(++i), calculateButtonGuiDirectionY(i), 20, 20, 103, 0, 20, GUI_TEXTURE, 256, 256, (buttonWidget) -> {
            //changeWindowTitle.visible=false;
            //this.client.openScreen(new ChangeTitleScreen(this, this.client.options));
            setAllApplicationVisible(false);
        });
        this.addButton(addApplications(redstone));*/



    }

    /*private ClickableWidget addApplications(ClickableWidget clickableWidget){
        applications.add(clickableWidget);
        return clickableWidget;
    }

    private void setAllApplicationVisible(boolean visible){
        for(ClickableWidget clickableWidget:applications){
            if(clickableWidget!=null)clickableWidget.visible=visible;
        }
    }*/

    /**
     * @param order starts from 1
     **/
    public int calculateButtonGuiDirectionX(int order){
        if(order<=0){
            throw new UnsupportedOperationException("the order starts from 1");
        }else {
            order=order+3;
            //int q=order-1;
            int w=order-2;
            int r=order-3;
            int t;
            if(r%3==0){
                t=3;
            } else if(w%3==0) {
                t = 2;
            } else{
                t=1;
            }
            return INITIAL_BUTTON_DIRECTION_X+((t-1)*APPLICATION_WIDTH)+((t-1)*SPLIT_X);
        }

    }

    /**
     * @param order starts from 1
     **/
    public int calculateButtonGuiDirectionY(int order) {
        if (order <= 0) {
            throw new UnsupportedOperationException("the order starts from 1");
        } else {
            int a = order / 3;
            int b = order % 3;
            //if (a == 0) return INITIAL_BUTTON_DIRECTION_Y;
            if (b == 0) {
                --a;
            }

            return INITIAL_BUTTON_DIRECTION_Y + (APPLICATION_HEIGHT * (a)) + (SPLIT_Y * a);
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        if(client!=null&&client.getTextureManager()!=null)this.client.getTextureManager().bindTexture(GUI_TEXTURE);
        this.drawTexture(matrices, clGuiDirectionX, clGuiDirectionY, 0, 0, PHONE_WIDTH, PHONE_HEIGHT);

        //this.fillGradient(matrices, 200, 100, 400, 200, 0x55000000, 0x55000000);
        textRenderer.draw(matrices,uiTitle,clGuiDirectionX+24,clGuiDirectionY+12.5f,16777215);
        //drawCenteredText(matrices, this.textRenderer, uiTitle, guiDirectionX + 10, guiDirectionY + 3, 16777215);
        //drawCenteredText(matrices, this.textRenderer, new TranslatableText("op6gui.button.redstone"), guiDirectionX + 5, guiDirectionY + 33, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }

    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_E || keyCode == GLFW.GLFW_KEY_ESCAPE) {
            if(userInterface==null||userInterface.parent==null) {
                this.onClose();
            }else{
                changeUI(userInterface.parent,null);
            }
        }
        return true;
    }

    public boolean isPauseScreen() {
        return false;
    }



    private Api createApi() {
        return new Api() {
            @Override
            public <T extends ClickableWidget> T addButton(T button) {
                return XPhone1GUI.this.addButton(button);
            }

            @Override
            public <T extends Element> T addChild(T child) {
                return XPhone1GUI.this.addChild(child);
            }

            @Override
            public int calculateButtonGuiDirectionX(int order) {
                return XPhone1GUI.this.calculateButtonGuiDirectionX(order);
            }

            @Override
            public int calculateButtonGuiDirectionY(int order) {
                return XPhone1GUI.this.calculateButtonGuiDirectionY(order);
            }

            @Override
            public int getGuiDirectionX() {
                return guiDirectionX;
            }

            @Override
            public int getGuiDirectionY() {
                return guiDirectionY;
            }

            @Override
            public int getCLGuiDirectionX() {
                return clGuiDirectionX;
            }

            @Override
            public int getCLGuiDirectionY() {
                return clGuiDirectionY;
            }

            @Override
            public void changeUI(UserInterface userInterface,Map<String,Object>intent){
                XPhone1GUI.this.changeUI(userInterface,intent);
            }

            @Override
            public int getVolume() {
                return XPhone1GUI.this.getVolume();
            }

            @Override
            public void setVolume(int volume) {
                XPhone1GUI.this.setVolume(volume);
            }

            @Override
            public ItemStack getItemStack() {
                return itemStack;
            }
        };
    }
}
