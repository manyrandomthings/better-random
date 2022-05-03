package betterrandom.mixin;

import betterrandom.BetterRandom;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    @Redirect(method = {
        "<init>",
        "createWorlds"
    }, at = @At(value = "NEW", target = "java/util/Random"))
    private Random useBetterRandom() {
        return new BetterRandom();
    }
}
