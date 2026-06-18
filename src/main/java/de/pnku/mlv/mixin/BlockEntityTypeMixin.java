package de.pnku.mlv.mixin;

import de.pnku.mlv.block.MoreLecternBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin {
    @Inject(method = "isValid", at = @At("HEAD"), cancellable = true)
    private void isValid(BlockState blockState, CallbackInfoReturnable<Boolean> cir) {
        if (BuiltInRegistries.BLOCK_ENTITY_TYPE.get(Identifier.withDefaultNamespace("lectern")).get().value().equals(this) && blockState.getBlock() instanceof MoreLecternBlock) {
            cir.setReturnValue(true);
        }
    }
}