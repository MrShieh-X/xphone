package com.mrshiehx.mcmods.xphone.items.phones.xphone_1;

import com.mrshiehx.mcmods.xphone.items.phones.XPhoneItem;
import com.mrshiehx.mcmods.xphone.modules.RegisterBlocks;
import com.mrshiehx.mcmods.xphone.modules.RegisterItems;
import com.mrshiehx.mcmods.xphone.screens.xphone_1_gui.XPhone1GUI;
import com.mrshiehx.mcmods.xphone.utils.Utils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public abstract class XPhone1 extends XPhoneItem {
    public static final String NBT_LIST_REMOTE_REDSTONE_RECEIVERS="remoteRedstoneReceivers";
    private final XPhone1Type type;

    public XPhone1(XPhone1Type type) {
        this(RegisterItems.ITEM_GROUP,1,type);
    }
    public XPhone1(int maxCount, XPhone1Type type) {
        this(RegisterItems.ITEM_GROUP,maxCount,type);
    }
    public XPhone1(ItemGroup itemGroup, XPhone1Type type) {
        this(itemGroup,1,type);
    }
    public XPhone1(ItemGroup itemGroup, int maxCount, XPhone1Type type) {
        super(itemGroup,maxCount);
        this.type=type;
    }

    protected abstract void appendPhoneTip(List<Text>list);
    //public abstract XPhone1Type getType();

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        //XPhone.LOGGER.log(Level.INFO,"nbt1: "+stack.getOrCreateNbt().getString("bbbbb"));
        //stack.getOrCreateNbt().putString("bbbbb", ("bbbbb"));
        //XPhone.LOGGER.log(Level.INFO,"nbt2: "+stack.getOrCreateNbt().getString("bbbbb"));
        tooltip.add(new TranslatableText("componentModel.855cpu").formatted(Formatting.GRAY));
        tooltip.add(new TranslatableText("componentModel.8855gpu").formatted(Formatting.GRAY));
        tooltip.add(new TranslatableText("componentModel.512k_ram").formatted(Formatting.GRAY));
        tooltip.add(new TranslatableText("componentModel.8gb_rom").formatted(Formatting.GRAY));
        appendPhoneTip(tooltip);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        if (!world.isClient) {
            MinecraftClient.getInstance().openScreen(new XPhone1GUI(type,itemStack)/*new BookEditScreen(user,itemStack,hand)*/);
        }
        //MinecraftClient.getInstance().openScreen(new BookEditScreen(user, itemStack, hand)new XPhone1GUI(getType()));
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos pos=context.getBlockPos();
        if(context.getWorld().getBlockState(pos).getBlock() == RegisterBlocks.remoteRedstoneReceiver){
            ItemStack itemStack=context.getStack();
            if(itemStack==null){
                Utils.showMessage(context,new TranslatableText("message.xphone.bind_remote_redstone_receiver_unable"));
                return ActionResult.FAIL;
            }
            NbtList blocks=itemStack.getOrCreateTag().getList(NBT_LIST_REMOTE_REDSTONE_RECEIVERS,10/*10相当于存放jsonObject*/);

            int j=0;
            for(int i=0;i<blocks.size();i++){
                NbtCompound block=blocks.getCompound(i);
                if(block!=null){
                    if(block.contains("x")&&
                            block.contains("y")&&
                            block.contains("z")){
                        j++;
                        if(
                                block.getInt("x")==pos.getX()&&
                                        block.getInt("y")==pos.getY()&&
                                        block.getInt("z")==pos.getZ()){
                            Utils.showMessage(context,new TranslatableText("message.xphone.bind_remote_redstone_receiver_duplicate_bind"));
                            return ActionResult.FAIL;
                        }
                    }
                }
            }
            if(j>=5){
                Utils.showMessage(context,new TranslatableText("message.xphone.bind_remote_redstone_receiver_too_much"));
                return ActionResult.FAIL;
            }
            NbtCompound block=new NbtCompound();
            block.putInt("x",pos.getX());
            block.putInt("y",pos.getY());
            block.putInt("z",pos.getZ());
            blocks.add(block);
            itemStack.putSubTag(NBT_LIST_REMOTE_REDSTONE_RECEIVERS,blocks);
            Utils.showMessage(context,new TranslatableText("message.xphone.bind_remote_redstone_receiver_successfully"));
            return ActionResult.SUCCESS;
        }
        return super.useOnBlock(context);
    }

    /*@Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        if (!world.isClient) {
            MinecraftClient.getInstance().openScreen(new XPhone1GUI(new TranslatableText("aaasaaa")));
        }
        return TypedActionResult.consume(itemStack);
    }*/

    public static enum XPhone1Type{
        DEFAULT,HORN,WLAN_CONNECTOR,COMPLETE
    }


}
