package betterrandom.mixin;

import betterrandom.PlayerEntityRealEnchantSeedInterface;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements PlayerEntityRealEnchantSeedInterface {
    private static final String ENCHANT_SEED_NBT_KEY = "betterrandom:realEnchantSeed";

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow protected int enchantmentTableSeed;

    @Unique
    private long betterRandomRealEnchantSeed;

    @Override
    public long getBetterRandomRealEnchantSeed() {
        return this.betterRandomRealEnchantSeed;
    }

    @Override
    public void setBetterRandomRealEnchantSeed(long seed) {
        this.betterRandomRealEnchantSeed = seed;
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readRealEnchantSeed(NbtCompound nbt, CallbackInfo ci) {
        this.betterRandomRealEnchantSeed = nbt.getLong(ENCHANT_SEED_NBT_KEY);
        if(this.betterRandomRealEnchantSeed == 0L) {
            this.betterRandomRealEnchantSeed = this.enchantmentTableSeed;
        }
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeRealEnchantSeed(NbtCompound nbt, CallbackInfo ci) {
        nbt.putLong(ENCHANT_SEED_NBT_KEY, this.betterRandomRealEnchantSeed);
    }

    @Inject(method = "applyEnchantmentCosts", at = @At("TAIL"))
    private void randomizeRealEnchantSeed(ItemStack enchantedItem, int experienceLevels, CallbackInfo ci) {
        this.betterRandomRealEnchantSeed = this.random.nextLong();
    }
}
