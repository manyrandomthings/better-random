package betterrandom.mixin;

import betterrandom.BetterRandom;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(Explosion.class)
public class ExplosionMixin {
    @Redirect(method = "<init>(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/damage/DamageSource;Lnet/minecraft/world/explosion/ExplosionBehavior;DDDFZLnet/minecraft/world/explosion/Explosion$DestructionType;)V", at = @At(value = "NEW", target = "java/util/Random"))
    private Random useBetterRandom() {
        return new BetterRandom();
    }
}
