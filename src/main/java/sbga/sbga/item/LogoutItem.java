package sbga.sbga.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import sbga.sbga.sound.ModSounds;

import java.util.concurrent.CompletableFuture;

public class LogoutItem extends Item {

    public LogoutItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient && user instanceof ServerPlayerEntity player) {
            // Play error sound
            world.playSound(null, player.getX(), player.getY(), player.getZ(),
                    ModSounds.ERROR, SoundCategory.PLAYERS, 1.0f, 1.0f);

            // Schedule kick after 3 seconds (60 ticks)
            CompletableFuture.delayedExecutor(3, java.util.concurrent.TimeUnit.SECONDS).execute(() -> {
                if (player.isDisconnected()) {
                    return;
                }
                player.networkHandler.disconnect(Text.of("当前使用的账号没有正常登出，请过15分钟后再试"));
            });
        }

        return TypedActionResult.success(stack, world.isClient);
    }
}
