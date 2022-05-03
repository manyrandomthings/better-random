package betterrandom.mixin;

import betterrandom.BetterRandom;
import net.minecraft.world.gen.GeneratorOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(GeneratorOptions.class)
public abstract class GeneratorOptionsMixin {
    @Redirect(method = {
        "getDefaultOptions",
        "fromProperties"
    }, at = @At(value = "NEW", target = "java/util/Random"))
    private static Random useBetterRandom() {
        return new BetterRandom();
    }
}
