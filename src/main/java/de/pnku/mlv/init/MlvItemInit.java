package de.pnku.mlv.init;

import de.pnku.mlv.MoreLecternVariants;
import de.pnku.mlv.block.MoreLecternBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import static de.pnku.mlv.init.MlvBlockInit.*;

public class MlvItemInit {
    public static final BlockItem SPRUCE_LECTERN_I = itemFromBlock(SPRUCE_LECTERN);
    public static final BlockItem BIRCH_LECTERN_I = itemFromBlock(BIRCH_LECTERN);
    public static final BlockItem JUNGLE_LECTERN_I = itemFromBlock(JUNGLE_LECTERN);
    public static final BlockItem ACACIA_LECTERN_I = itemFromBlock(ACACIA_LECTERN);
    public static final BlockItem DARK_OAK_LECTERN_I = itemFromBlock(DARK_OAK_LECTERN);
    public static final BlockItem PALE_OAK_LECTERN_I = itemFromBlock(PALE_OAK_LECTERN);
    public static final BlockItem MANGROVE_LECTERN_I = itemFromBlock(MANGROVE_LECTERN);
    public static final BlockItem CHERRY_LECTERN_I = itemFromBlock(CHERRY_LECTERN);
    public static final BlockItem BAMBOO_LECTERN_I = itemFromBlock(BAMBOO_LECTERN);
    public static final BlockItem CRIMSON_LECTERN_I = itemFromBlock(CRIMSON_LECTERN);
    public static final BlockItem WARPED_LECTERN_I = itemFromBlock(WARPED_LECTERN);

    public static BlockItem itemFromBlock(MoreLecternBlock moreLecternBlock) {
        return new BlockItem(moreLecternBlock, setProperties(moreLecternBlock));
    }

    public static Item.Properties setProperties(MoreLecternBlock moreLecternBlock) {
        return new Item.Properties()
                .setId(ResourceKey.create(Registries.ITEM,BuiltInRegistries.BLOCK.getKey(moreLecternBlock))).useBlockDescriptionPrefix();
    }

    public static void registerItems() {
        registerItem(SPRUCE_LECTERN_I, Items.LECTERN);
        registerItem(BIRCH_LECTERN_I, SPRUCE_LECTERN_I);
        registerItem(JUNGLE_LECTERN_I, BIRCH_LECTERN_I);
        registerItem(ACACIA_LECTERN_I, JUNGLE_LECTERN_I);
        registerItem(DARK_OAK_LECTERN_I, ACACIA_LECTERN_I);
        registerItem(PALE_OAK_LECTERN_I, DARK_OAK_LECTERN_I);
        registerItem(MANGROVE_LECTERN_I, PALE_OAK_LECTERN_I);
        registerItem(CHERRY_LECTERN_I, MANGROVE_LECTERN_I);
        registerItem(BAMBOO_LECTERN_I, CHERRY_LECTERN_I);
        registerItem(CRIMSON_LECTERN_I, BAMBOO_LECTERN_I);
        registerItem(WARPED_LECTERN_I, CRIMSON_LECTERN_I);
    }

    private static void registerItem(BlockItem lectern, Item lecternAfter) {
        Registry.register(BuiltInRegistries.ITEM, MoreLecternVariants.asId(((MoreLecternBlock) lectern.getBlock()).lecternWoodType + "_lectern"), lectern);

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> entries.addAfter(lecternAfter, lectern));
    }
}