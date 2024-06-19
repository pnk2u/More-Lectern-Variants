package com.pnku.mlv.init;

import com.pnku.mlv.MoreLecternVariants;
import com.pnku.mlv.block.MoreLecternBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;

import java.util.ArrayList;
import java.util.List;

public class MlvBlockInit {
    public static final MoreLecternBlock OAK_LECTERN = new MoreLecternBlock(MapColor.OAK_TAN, "oak");
    public static final MoreLecternBlock SPRUCE_LECTERN = new MoreLecternBlock(MapColor.SPRUCE_BROWN, "spruce");
    public static final MoreLecternBlock BIRCH_LECTERN = new MoreLecternBlock(MapColor.PALE_YELLOW, "birch");
    public static final MoreLecternBlock JUNGLE_LECTERN = new MoreLecternBlock(MapColor.DIRT_BROWN, "jungle");
    public static final MoreLecternBlock ACACIA_LECTERN = new MoreLecternBlock(MapColor.ORANGE, "acacia");
    public static final MoreLecternBlock DARK_OAK_LECTERN = new MoreLecternBlock(MapColor.BROWN, "dark_oak");
    public static final MoreLecternBlock MANGROVE_LECTERN = new MoreLecternBlock(MapColor.RED, "mangrove");
    public static final MoreLecternBlock CHERRY_LECTERN = new MoreLecternBlock(MapColor.TERRACOTTA_WHITE, BlockSoundGroup.CHERRY_WOOD, "cherry");
    public static final MoreLecternBlock BAMBOO_LECTERN = new MoreLecternBlock(MapColor.YELLOW, BlockSoundGroup.BAMBOO_WOOD, "bamboo");
    public static final MoreLecternBlock CRIMSON_LECTERN = new MoreLecternBlock(MapColor.DULL_PINK, BlockSoundGroup.NETHER_WOOD, "crimson");
    public static final MoreLecternBlock WARPED_LECTERN = new MoreLecternBlock(MapColor.DARK_AQUA, BlockSoundGroup.NETHER_WOOD, "warped");

    public static final List<Block> more_lecterns = new ArrayList<>();


    public static void registerBlocks() {
        registerBlock(OAK_LECTERN);
        registerBlock(SPRUCE_LECTERN);
        registerBlock(BIRCH_LECTERN);
        registerBlock(JUNGLE_LECTERN);
        registerBlock(ACACIA_LECTERN);
        registerBlock(DARK_OAK_LECTERN);
        registerBlock(MANGROVE_LECTERN);
        registerBlock(CHERRY_LECTERN);
        registerBlock(BAMBOO_LECTERN);
        registerBlock(CRIMSON_LECTERN);
        registerBlock(WARPED_LECTERN);

    }

    private static void registerBlock(MoreLecternBlock lectern) {
        Registry.register(Registries.BLOCK, MoreLecternVariants.asId(lectern.lecternType + "_lectern"), lectern);
        more_lecterns.add(lectern);
    }
}