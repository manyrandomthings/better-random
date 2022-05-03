package betterrandom.mixin;

import betterrandom.BetterRandom;
import net.minecraft.loot.context.LootContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(LootContext.Builder.class)
public abstract class LootContextBuilderMixin {
    @Redirect(method = "build", at = @At(value = "NEW", target = "java/util/Random"))
    private Random useBetterRandom() {
        return new BetterRandom();
    }

    @Redirect(method = {
        "random(J)Lnet/minecraft/loot/context/LootContext$Builder;",
        "random(JLjava/util/Random;)Lnet/minecraft/loot/context/LootContext$Builder;"
    }, at = @At(value = "NEW", target = "java/util/Random"))
    private Random useBetterRandomSeed(long seed) {
        return new BetterRandom(seed);
    }
}
