package de.pnku.mlv.init;

import de.pnku.mlv.MoreLecternVariants;
import de.pnku.mlv.block.MoreLecternBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class MlvItemInit {
    public static final BlockItem SPRUCE_LECTERN_I = new BlockItem(MlvBlockInit.SPRUCE_LECTERN, new Item.Properties());
    public static final BlockItem BIRCH_LECTERN_I = new BlockItem(MlvBlockInit.BIRCH_LECTERN, new Item.Properties());
    public static final BlockItem JUNGLE_LECTERN_I = new BlockItem(MlvBlockInit.JUNGLE_LECTERN, new Item.Properties());
    public static final BlockItem ACACIA_LECTERN_I = new BlockItem(MlvBlockInit.ACACIA_LECTERN, new Item.Properties());
    public static final BlockItem DARK_OAK_LECTERN_I = new BlockItem(MlvBlockInit.DARK_OAK_LECTERN, new Item.Properties());
    public static final BlockItem MANGROVE_LECTERN_I = new BlockItem(MlvBlockInit.MANGROVE_LECTERN, new Item.Properties());
    public static final BlockItem CHERRY_LECTERN_I = new BlockItem(MlvBlockInit.CHERRY_LECTERN, new Item.Properties());
    public static final BlockItem BAMBOO_LECTERN_I = new BlockItem(MlvBlockInit.BAMBOO_LECTERN, new Item.Properties());
    public static final BlockItem CRIMSON_LECTERN_I = new BlockItem(MlvBlockInit.CRIMSON_LECTERN, new Item.Properties());
    public static final BlockItem WARPED_LECTERN_I = new BlockItem(MlvBlockInit.WARPED_LECTERN, new Item.Properties());


    public static void registerItems() {
        registerItem(SPRUCE_LECTERN_I, Items.LECTERN);
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
        Registry.register(BuiltInRegistries.ITEM, MoreLecternVariants.asId(((MoreLecternBlock) lectern.getBlock()).lecternWoodType + "_lectern"), lectern);

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> entries.addAfter(lecternAfter, lectern));
    }
}