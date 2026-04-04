package sbga.sbga;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sbga.sbga.item.ModItems;
import sbga.sbga.sound.ModSounds;

public class sbga implements ModInitializer {
    public static final String MOD_ID = "sbga";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModSounds.initialize();
        ModItems.initialize();
    }
}
