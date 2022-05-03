package betterrandom.mixin;

import betterrandom.BetterRandom;
import net.minecraft.server.command.SpreadPlayersCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(SpreadPlayersCommand.class)
public abstract class SpreadPlayersCommandMixin {
    @Redirect(method = "execute", at = @At(value = "NEW", target = "java/util/Random"))
    private static Random useBetterRandom() {
        return new BetterRandom();
    }
}
