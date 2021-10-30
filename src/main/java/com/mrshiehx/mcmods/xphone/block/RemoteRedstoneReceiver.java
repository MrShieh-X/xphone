package com.mrshiehx.mcmods.xphone.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RemoteRedstoneReceiver extends Block {

    public static final BooleanProperty LIT = Properties.LIT;

    public RemoteRedstoneReceiver(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(LIT, false));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(new TranslatableText("itemTooltip.remote_redstone_receiver_1").formatted(Formatting.GOLD));
        tooltip.add(new TranslatableText("itemTooltip.remote_redstone_receiver_2").formatted(Formatting.GOLD));
        tooltip.add(new TranslatableText("itemTooltip.remote_redstone_receiver_3").formatted(Formatting.GOLD));
        tooltip.add(new TranslatableText("itemTooltip.remote_redstone_receiver_4").formatted(Formatting.GOLD));
        tooltip.add(new TranslatableText("itemTooltip.remote_redstone_receiver_5").formatted(Formatting.GOLD));
        tooltip.add(new TranslatableText("itemTooltip.remote_redstone_receiver_6").formatted(Formatting.GOLD));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    //红石线连接
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    //环境光遮蔽
    @Environment(EnvType.CLIENT)
    public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        return 1.0F;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        world.setBlockState(pos,state.get(LIT)?state.with(LIT,false):state.with(LIT,true));
        return ActionResult.SUCCESS;
    }

    //信号强度
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return state.get(LIT)?15:0;
    }
}
