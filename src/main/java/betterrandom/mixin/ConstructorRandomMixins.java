package betterrandom.mixin;

import betterrandom.BetterRandom;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.collection.WeightedList;
import net.minecraft.village.raid.Raid;
import net.minecraft.world.MobSpawnerLogic;
import net.minecraft.world.WanderingTraderManager;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

// todo(?): split these up to different mixins
@Mixin({
	Entity.class,
	WeightedList.class,
	Raid.class,
	MobSpawnerLogic.class,
	WanderingTraderManager.class,
	World.class
})
public abstract class ConstructorRandomMixins {
	@Redirect(method = "<init>", at = @At(value = "NEW", target = "java/util/Random"))
	private Random useBetterRandom() {
		return new BetterRandom();
	}
}
