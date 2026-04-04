package sbga.sbga.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.UseAction;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.World;
import sbga.sbga.sound.ModSounds;

public class FalseMilkItem extends Item {

    public FalseMilkItem(Settings settings) {
        super(settings);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        // Call parent to handle consumption
        ItemStack result = super.finishUsing(stack, world, user);

        if (!world.isClient && user instanceof ServerPlayerEntity player) {
            // Play qzkago sound
            world.playSound(null, player.getX(), player.getY(), player.getZ(),
                    ModSounds.QZKAGO, SoundCategory.PLAYERS, 1.0f, 1.0f);

            // Give night vision for 600 seconds (12000 ticks)
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 12000, 0, false, true));

            // Give strength III for 600 seconds
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 12000, 2, false, true));
        }

        return result;
    }
}
