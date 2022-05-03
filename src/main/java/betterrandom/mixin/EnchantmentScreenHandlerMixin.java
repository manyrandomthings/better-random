package betterrandom.mixin;

import betterrandom.BetterRandom;
import betterrandom.PlayerEntityRealEnchantSeedInterface;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Random;

@Mixin(EnchantmentScreenHandler.class)
public abstract class EnchantmentScreenHandlerMixin {
    @Shadow @Final private Random random;
    @Unique
    private long betterRandomRealEnchantSeed;

    @Redirect(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At(value = "NEW", target = "java/util/Random"))
    private Random useBetterRandom() {
        return new BetterRandom();
    }

    @Inject(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At("TAIL"))
    private void getRealPlayerRandom(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context, CallbackInfo ci) {
        this.betterRandomRealEnchantSeed = ((PlayerEntityRealEnchantSeedInterface) playerInventory.player).getBetterRandomRealEnchantSeed();
    }

    @Inject(method = "generateEnchantments", at = @At(value = "INVOKE", target = "Ljava/util/Random;setSeed(J)V", shift = At.Shift.AFTER))
    private void setRealEnchantSeed1(ItemStack stack, int slot, int level, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {
        this.random.setSeed(this.betterRandomRealEnchantSeed + slot);
    }

    @Dynamic
    @Inject(method = "method_17411(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V", at = @At(value = "INVOKE", target = "Ljava/util/Random;setSeed(J)V", shift = At.Shift.AFTER))
    private void setRealEnchantSeed2(ItemStack itemStack, World world, BlockPos pos, CallbackInfo ci) {
        this.random.setSeed(this.betterRandomRealEnchantSeed);
    }

    @Dynamic
    @Inject(method = "method_17410(Lnet/minecraft/item/ItemStack;ILnet/minecraft/entity/player/PlayerEntity;ILnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/Property;set(I)V", shift = At.Shift.AFTER))
    private void resetRealEnchantSeed(ItemStack itemStack, int i, PlayerEntity playerEntity, int j, ItemStack itemStack2, World world, BlockPos pos, CallbackInfo ci) {
        this.betterRandomRealEnchantSeed = ((PlayerEntityRealEnchantSeedInterface) playerEntity).getBetterRandomRealEnchantSeed();
    }
}
