package betterrandom.mixin;

import betterrandom.BetterRandom;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.server.network.ServerLoginNetworkHandler;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

// todo(?): split these up to different mixins
@Mixin({
	DispenserBlockEntity.class,
	Sensor.class,
	ServerLoginNetworkHandler.class,
	ItemScatterer.class,
	MathHelper.class
})
public abstract class StaticRandomMixins {
	@Redirect(method = "<clinit>", at = @At(value = "NEW", target = "java/util/Random"))
	private static Random useBetterRandom() {
		return new BetterRandom();
	}
}
