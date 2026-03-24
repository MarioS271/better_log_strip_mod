package net.marios271;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;

public class StripHandler {
    private static BlockPos lastPos = null;
    private static long lastTime = 0;
    private static final long WINDOW_MS = 500;

    public static void register() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (!world.isClientSide())
                return InteractionResult.PASS;

            ItemStack held = player.getItemInHand(hand);
            BlockPos pos = hitResult.getBlockPos();
            BlockState state = world.getBlockState(pos);

            if (!(held.getItem() instanceof AxeItem))
                return InteractionResult.PASS;

            if (!AxeItem.STRIPPABLES.containsKey(state.getBlock()))
                return InteractionResult.PASS;

            long now = System.currentTimeMillis();

            if (pos.equals(lastPos) && (now - lastTime) < WINDOW_MS) {
                player.displayClientMessage(Component.empty(), true);

                lastPos = null;
                return InteractionResult.PASS;
            } else {
                player.displayClientMessage(Component.translatable("message.better_log_strip.click_again"), true);

                lastPos = pos;
                lastTime = now;
                return InteractionResult.FAIL;
            }
        });
    }
}
