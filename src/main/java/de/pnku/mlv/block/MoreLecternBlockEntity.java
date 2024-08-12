package de.pnku.mlv.block;

import de.pnku.mlv.init.MlvBlockInit;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.Clearable;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.LecternMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.WrittenBookItem;
import net.minecraft.world.item.component.WritableBookContent;
import net.minecraft.world.item.component.WrittenBookContent;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.LecternBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MoreLecternBlockEntity extends BlockEntity implements Clearable, MenuProvider {
    public static final int DATA_PAGE = 0;
    public static final int NUM_DATA = 1;
    public static final int SLOT_BOOK = 0;
    public static final int NUM_SLOTS = 1;
    private final Container bookAccess = new Container() {
        public int getContainerSize() {
            return 1;
        }

        public boolean isEmpty() {
            return MoreLecternBlockEntity.this.book.isEmpty();
        }

        @Override
        public @NotNull ItemStack getItem(int slot) {
            return slot == 0 ? MoreLecternBlockEntity.this.book : ItemStack.EMPTY;
        }

        @Override
        public @NotNull ItemStack removeItem(int slot, int amount) {
            if (slot == 0) {
                ItemStack itemStack = MoreLecternBlockEntity.this.book.split(amount);
                if (MoreLecternBlockEntity.this.book.isEmpty()) {
                    MoreLecternBlockEntity.this.onBookItemRemove();
                }

                return itemStack;
            } else {
                return ItemStack.EMPTY;
            }
        }

        @Override
        public @NotNull ItemStack removeItemNoUpdate(int slot) {
            if (slot == 0) {
                ItemStack itemStack = MoreLecternBlockEntity.this.book;
                MoreLecternBlockEntity.this.book = ItemStack.EMPTY;
                MoreLecternBlockEntity.this.onBookItemRemove();
                return itemStack;
            } else {
                return ItemStack.EMPTY;
            }
        }

        @Override
        public void setItem(int slot, ItemStack stack) {
        }

        @Override
        public int getMaxStackSize() {
            return 1;
        }

        @Override
        public void setChanged() {
            MoreLecternBlockEntity.this.setChanged();
        }

        @Override
        public boolean stillValid(Player player) {
            return Container.stillValidBlockEntity(MoreLecternBlockEntity.this, player) && MoreLecternBlockEntity.this.hasBook();
        }

        @Override
        public boolean canPlaceItem(int slot, ItemStack stack) {
            return false;
        }

        @Override
        public void clearContent() {
        }
    };
        private final ContainerData dataAccess = new ContainerData() {
            public int get(int index) {
            return index == 0 ? MoreLecternBlockEntity.this.page : 0;
        }

        @Override
        public void set(int index, int value) {
            if (index == 0) {
                MoreLecternBlockEntity.this.setPage(value);
            }

        }
        @Override
        public int getCount() {
            return 1;
        }
    };
    ItemStack book;
    int page;
    private int pageCount;

    public MoreLecternBlockEntity(BlockPos pos, BlockState blockState) {
        super(MlvBlockInit.MORE_LECTERN_BLOCK_ENTITY, pos, blockState);
        this.book = ItemStack.EMPTY;
    }

    public ItemStack getBook() {
        return this.book;
    }

    public boolean hasBook() {
        return this.book.is(Items.WRITABLE_BOOK) || this.book.is(Items.WRITTEN_BOOK);
    }

    public void setBook(ItemStack stack) {
        this.setBook(stack, (Player)null);
    }

    void onBookItemRemove() {
        this.page = 0;
        this.pageCount = 0;
        MoreLecternBlock.resetBookState((Entity)null, this.getLevel(), this.getBlockPos(), this.getBlockState(), false);
    }

    public void setBook(ItemStack stack, @Nullable Player player) {
        this.book = this.resolveBook(stack, player);
        this.page = 0;
        this.pageCount = getPageCount(this.book);
        this.setChanged();
    }

    void setPage(int page) {
        int i = Mth.clamp(page, 0, this.pageCount - 1);
        if (i != this.page) {
            this.page = i;
            this.setChanged();
            MoreLecternBlock.signalPageChange(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }

    }

    public int getPage() {
        return this.page;
    }

    public int getRedstoneSignal() {
        float f = this.pageCount > 1 ? (float)this.getPage() / ((float)this.pageCount - 1.0F) : 1.0F;
        return Mth.floor(f * 14.0F) + (this.hasBook() ? 1 : 0);
    }

    private ItemStack resolveBook(ItemStack stack, @Nullable Player player) {
        if (this.level instanceof ServerLevel && stack.is(Items.WRITTEN_BOOK)) {
            WrittenBookItem.resolveBookComponents(stack, this.createCommandSourceStack(player), player);
        }

        return stack;
    }

    private CommandSourceStack createCommandSourceStack(@Nullable Player player) {
        String string;
        Object component;
        if (player == null) {
            string = "Lectern";
            component = Component.literal("Lectern");
        } else {
            string = player.getName().getString();
            component = player.getDisplayName();
        }

        Vec3 vec3 = Vec3.atCenterOf(this.worldPosition);
        return new CommandSourceStack(CommandSource.NULL, vec3, Vec2.ZERO, (ServerLevel)this.level, 2, string, (Component)component, this.level.getServer(), player);
    }

    public boolean onlyOpCanSetNbt() {
        return true;
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains("Book", 10)) {
            this.book = this.resolveBook((ItemStack)ItemStack.parse(registries, tag.getCompound("Book")).orElse(ItemStack.EMPTY), (Player)null);
        } else {
            this.book = ItemStack.EMPTY;
        }

        this.pageCount = getPageCount(this.book);
        this.page = Mth.clamp(tag.getInt("Page"), 0, this.pageCount - 1);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (!this.getBook().isEmpty()) {
            tag.put("Book", this.getBook().save(registries));
            tag.putInt("Page", this.page);
        }

    }
    @Override
    public void clearContent() {
        this.setBook(ItemStack.EMPTY);
    }

    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new LecternMenu(i, this.bookAccess, this.dataAccess);
    }

    protected MoreLecternBlock getBlock() {
        return (MoreLecternBlock) getBlockState().getBlock();
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("container." + getBlock().lecternWoodType + "_lectern");
    }

    private static int getPageCount(ItemStack stack) {
        WrittenBookContent writtenBookContent = (WrittenBookContent)stack.get(DataComponents.WRITTEN_BOOK_CONTENT);
        if (writtenBookContent != null) {
            return writtenBookContent.pages().size();
        } else {
            WritableBookContent writableBookContent = (WritableBookContent)stack.get(DataComponents.WRITABLE_BOOK_CONTENT);
            return writableBookContent != null ? writableBookContent.pages().size() : 0;
        }
    }
}