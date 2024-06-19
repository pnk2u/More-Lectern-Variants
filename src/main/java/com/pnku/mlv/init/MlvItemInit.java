package com.pnku.mlv.init;

import com.pnku.mlv.MoreLecternVariants;
import com.pnku.mlv.block.MoreLecternBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class MlvItemInit {
    public static final BlockItem OAK_LECTERN_I = new BlockItem(MlvBlockInit.OAK_LECTERN, new Item.Settings());
    public static final BlockItem SPRUCE_LECTERN_I = new BlockItem(MlvBlockInit.SPRUCE_LECTERN, new Item.Settings());
    public static final BlockItem BIRCH_LECTERN_I = new BlockItem(MlvBlockInit.BIRCH_LECTERN, new Item.Settings());
    public static final BlockItem JUNGLE_LECTERN_I = new BlockItem(MlvBlockInit.JUNGLE_LECTERN, new Item.Settings());
    public static final BlockItem ACACIA_LECTERN_I = new BlockItem(MlvBlockInit.ACACIA_LECTERN, new Item.Settings());
    public static final BlockItem DARK_OAK_LECTERN_I = new BlockItem(MlvBlockInit.DARK_OAK_LECTERN, new Item.Settings());
    public static final BlockItem MANGROVE_LECTERN_I = new BlockItem(MlvBlockInit.MANGROVE_LECTERN, new Item.Settings());
    public static final BlockItem CHERRY_LECTERN_I = new BlockItem(MlvBlockInit.CHERRY_LECTERN, new Item.Settings());
    public static final BlockItem BAMBOO_LECTERN_I = new BlockItem(MlvBlockInit.BAMBOO_LECTERN, new Item.Settings());
    public static final BlockItem CRIMSON_LECTERN_I = new BlockItem(MlvBlockInit.CRIMSON_LECTERN, new Item.Settings());
    public static final BlockItem WARPED_LECTERN_I = new BlockItem(MlvBlockInit.WARPED_LECTERN, new Item.Settings());


    public static void registerItems() {
        registerItem(OAK_LECTERN_I, Items.LECTERN);
        registerItem(SPRUCE_LECTERN_I, OAK_LECTERN_I);
        registerItem(BIRCH_LECTERN_I, SPRUCE_LECTERN_I);
        registerItem(JUNGLE_LECTERN_I, BIRCH_LECTERN_I);
        registerItem(ACACIA_LECTERN_I, JUNGLE_LECTERN_I);
        registerItem(DARK_OAK_LECTERN_I, ACACIA_LECTERN_I);
        registerItem(MANGROVE_LECTERN_I, DARK_OAK_LECTERN_I);
        registerItem(CHERRY_LECTERN_I, MANGROVE_LECTERN_I);
        registerItem(BAMBOO_LECTERN_I, CHERRY_LECTERN_I);
        registerItem(CRIMSON_LECTERN_I, BAMBOO_LECTERN_I);
        registerItem(WARPED_LECTERN_I, CRIMSON_LECTERN_I);
    }

    private static void registerItem(BlockItem lectern, Item lecternAfter) {
        Registry.register(Registries.ITEM, MoreLecternVariants.asId(((MoreLecternBlock) lectern.getBlock()).lecternType + "_lectern"), lectern);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> entries.addAfter(lecternAfter, lectern));
    }
}