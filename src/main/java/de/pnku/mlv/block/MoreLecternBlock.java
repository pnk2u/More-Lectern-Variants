package de.pnku.mlv.block;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.LecternBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MoreLecternBlock extends LecternBlock {
    public final String lecternWoodType;

    public MoreLecternBlock(MapColor colour, String lecternWoodType) {
        super(Properties.ofFullCopy(Blocks.LECTERN).mapColor(colour));
        this.lecternWoodType = lecternWoodType;
    }

    public MoreLecternBlock(MapColor colour, SoundType soundType, String lecternWoodType) {
        super(Properties.ofFullCopy(Blocks.LECTERN).mapColor(colour).sound(soundType));
        this.lecternWoodType = lecternWoodType;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MoreLecternBlockEntity(pos, state);
    }

    public static boolean tryPlaceBook(@Nullable LivingEntity entity, Level level, BlockPos pos, BlockState state, ItemStack stack) {
        if (!(Boolean)state.getValue(HAS_BOOK)) {
            if (!level.isClientSide) {
                placeBook(entity, level, pos, state, stack);
            }

            return true;
        } else {
            return false;
        }
    }

    private static void placeBook(@Nullable LivingEntity entity, Level level, BlockPos pos, BlockState state, ItemStack stack) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof MoreLecternBlockEntity moreLecternBlockEntity) {
            moreLecternBlockEntity.setBook(stack.consumeAndReturn(1, entity));
            resetBookState(entity, level, pos, state, true);
            level.playSound((Player)null, pos, SoundEvents.BOOK_PUT, SoundSource.BLOCKS, 1.0F, 1.0F);
        }

    }

    public static void resetBookState(@Nullable Entity entity, Level level, BlockPos pos, BlockState state, boolean hasBook) {
        BlockState blockState = (BlockState)((BlockState)state.setValue(POWERED, false)).setValue(HAS_BOOK, hasBook);
        level.setBlock(pos, blockState, 3);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(entity, blockState));
        updateBelow(level, pos, state);
    }

    private void popBook(BlockState state, Level level, BlockPos pos) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof MoreLecternBlockEntity moreLecternBlockEntity) {
            Direction direction = (Direction)state.getValue(FACING);
            ItemStack itemStack = moreLecternBlockEntity.getBook().copy();
            float f = 0.25F * (float)direction.getStepX();
            float g = 0.25F * (float)direction.getStepZ();
            ItemEntity itemEntity = new ItemEntity(level, (double)pos.getX() + 0.5 + (double)f, (double)(pos.getY() + 1), (double)pos.getZ() + 0.5 + (double)g, itemStack);
            itemEntity.setDefaultPickUpDelay();
            level.addFreshEntity(itemEntity);
            moreLecternBlockEntity.clearContent();
        }

    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        if ((Boolean)state.getValue(HAS_BOOK)) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof MoreLecternBlockEntity) {
                return ((MoreLecternBlockEntity)blockEntity).getRedstoneSignal();
            }
        }

        return 0;
    }

    private void openScreen(Level level, BlockPos pos, Player player) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof MoreLecternBlockEntity) {
            player.openMenu((MoreLecternBlockEntity)blockEntity);
            player.awardStat(Stats.INTERACT_WITH_LECTERN);
        }

    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if ((Boolean)state.getValue(HAS_BOOK)) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        } else if (stack.is(ItemTags.LECTERN_BOOKS)) {
            return tryPlaceBook(player, level, pos, state, stack) ? ItemInteractionResult.sidedSuccess(level.isClientSide) : ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION;
        } else {
            return stack.isEmpty() && hand == InteractionHand.MAIN_HAND ? ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION : ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if ((Boolean)state.getValue(HAS_BOOK)) {
            if (!level.isClientSide) {
                this.openScreen(level, pos, player);
            }

            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            return InteractionResult.CONSUME;
        }
    }
}