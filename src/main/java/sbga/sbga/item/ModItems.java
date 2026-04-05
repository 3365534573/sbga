package sbga.sbga.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import sbga.sbga.sbga;
import sbga.sbga.sound.ModSounds;

public class ModItems {
    private ModItems() {
    }

    public static final MusicDiscItem MUSIC_DISC_XTERFUSION = register(
            new MusicDiscItem(15, ModSounds.XTERFUSION, new Item.Settings().maxCount(1).rarity(Rarity.RARE), 170),
            "music_disc_xterfusion"
    );

    public static final MusicDiscItem MUSIC_DISC_QZKAGO = register(
            new MusicDiscItem(14, ModSounds.QZKAGO, new Item.Settings().maxCount(1).rarity(Rarity.RARE), 124),
            "music_disc_qzkago"
    );

    private static final FoodComponent FALSE_MILK_FOOD = new FoodComponent.Builder()
            .alwaysEdible()
            .build();

    public static final Item FALSE_MILK = register(
            new FalseMilkItem(new Item.Settings().maxCount(16).food(FALSE_MILK_FOOD)),
            "false_milk"
    );

    public static final Item COIN = register(
            new Item(new Item.Settings().maxCount(64)),
            "coin"
    );

    public static final Item LOGOUT = register(
            new LogoutItem(new Item.Settings().maxCount(1)),
            "logout"
    );

    public static final Item MAIMAI = register(
            new MaimaiItem(new Item.Settings().maxCount(1)),
            "maimai"
    );

    private static <T extends Item> T register(T item, String id) {
        Identifier itemID = new Identifier(sbga.MOD_ID, id);
        return Registry.register(Registries.ITEM, itemID, item);
    }

    public static void initialize() {
        sbga.LOGGER.info("Registering " + sbga.MOD_ID + " Items");

        // Add music discs to Tools and Utilities tab
        ItemGroupEvents.modifyEntriesEvent(net.minecraft.item.ItemGroups.TOOLS).register(content -> {
            content.add(MUSIC_DISC_XTERFUSION);
            content.add(MUSIC_DISC_QZKAGO);
        });

        // Add false_milk to Food and Drink tab
        ItemGroupEvents.modifyEntriesEvent(net.minecraft.item.ItemGroups.FOOD_AND_DRINK).register(content -> {
            content.add(FALSE_MILK);
        });

        // Add coin to Ingredients tab
        ItemGroupEvents.modifyEntriesEvent(net.minecraft.item.ItemGroups.INGREDIENTS).register(content -> {
            content.add(COIN);
        });

        // Add logout to Tools tab
        ItemGroupEvents.modifyEntriesEvent(net.minecraft.item.ItemGroups.TOOLS).register(content -> {
            content.add(LOGOUT);
        });

        // Add maimai to Tools tab
        ItemGroupEvents.modifyEntriesEvent(net.minecraft.item.ItemGroups.TOOLS).register(content -> {
            content.add(MAIMAI);
        });
    }
}
