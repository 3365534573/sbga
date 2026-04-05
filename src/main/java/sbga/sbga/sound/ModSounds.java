package sbga.sbga.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import sbga.sbga.sbga;

public class ModSounds {
    private ModSounds() {
    }

    public static final SoundEvent XTERFUSION = registerSound("xterfusion");
    public static final SoundEvent QZKAGO = registerSound("qzkago");
    public static final SoundEvent ERROR = registerSound("error");
    public static final SoundEvent SUCCESS = registerSound("success");

    private static SoundEvent registerSound(String id) {
        Identifier identifier = new Identifier(sbga.MOD_ID, id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    public static void initialize() {
        sbga.LOGGER.info("Registering " + sbga.MOD_ID + " Sounds");
    }
}
