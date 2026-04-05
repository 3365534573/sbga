package sbga.sbga.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
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

import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class MaimaiItem extends Item {
    private static final Random RANDOM = new Random();

    public MaimaiItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack maimaiStack = user.getStackInHand(hand);
        ItemStack coinStack = user.getOffHandStack();

        // Check if player has coin in offhand
        if (coinStack.isEmpty() || coinStack.getItem() != ModItems.COIN) {
            return TypedActionResult.pass(maimaiStack);
        }

        if (!world.isClient && user instanceof ServerPlayerEntity player) {
            // Deduct one coin
            coinStack.decrement(1);

            // 70% success, 30% fail
            if (RANDOM.nextFloat() < 0.7f) {
                // Success
                world.playSound(null, player.getX(), player.getY(), player.getZ(),
                        ModSounds.SUCCESS, SoundCategory.PLAYERS, 1.0f, 1.0f);

                // Give Strength V for 600 seconds (12000 ticks)
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 12000, 4, false, true));

                // Give logout item
                player.giveItemStack(new ItemStack(ModItems.LOGOUT));
            } else {
                // Fail
                world.playSound(null, player.getX(), player.getY(), player.getZ(),
                        ModSounds.ERROR, SoundCategory.PLAYERS, 1.0f, 1.0f);

                // Kick after 3 seconds
                CompletableFuture.delayedExecutor(3, java.util.concurrent.TimeUnit.SECONDS).execute(() -> {
                    if (player.isDisconnected()) {
                        return;
                    }
                    player.networkHandler.disconnect(Text.of("数据保存失败，无法反映本次游戏成绩"));
                });
            }
        }

        return TypedActionResult.success(maimaiStack, world.isClient);
    }
}
