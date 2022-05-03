package betterrandom.mixin;

import betterrandom.BetterRandom;
import betterrandom.PlayerEntityRealEnchantSeedInterface;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {
    @Redirect(method = "moveToSpawn", at = @At(value = "NEW", target = "java/util/Random"))
    private Random useBetterRandom() {
        return new BetterRandom();
    }

    @Inject(method = "copyFrom", at = @At("TAIL"))
    private void copyRealEnchantSeed(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo ci) {
        ((PlayerEntityRealEnchantSeedInterface) this).setBetterRandomRealEnchantSeed(((PlayerEntityRealEnchantSeedInterface) oldPlayer).getBetterRandomRealEnchantSeed());
    }
}
