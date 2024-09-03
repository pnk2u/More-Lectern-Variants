package de.pnku.mlv.init;

import de.pnku.mlv.MoreLecternVariants;
import de.pnku.mlv.block.MoreLecternBlock;
import de.pnku.mlv.block.MoreLecternBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.MapColor;

import java.util.ArrayList;
import java.util.List;

public class MlvBlockInit {
    public static final MoreLecternBlock SPRUCE_LECTERN = new MoreLecternBlock(MapColor.PODZOL, "spruce");
    public static final MoreLecternBlock BIRCH_LECTERN = new MoreLecternBlock(MapColor.SAND, "birch");
    public static final MoreLecternBlock JUNGLE_LECTERN = new MoreLecternBlock(MapColor.DIRT, "jungle");
    public static final MoreLecternBlock ACACIA_LECTERN = new MoreLecternBlock(MapColor.COLOR_ORANGE, "acacia");
    public static final MoreLecternBlock DARK_OAK_LECTERN = new MoreLecternBlock(MapColor.COLOR_BROWN, "dark_oak");
    public static final MoreLecternBlock MANGROVE_LECTERN = new MoreLecternBlock(MapColor.COLOR_RED, "mangrove");
    public static final MoreLecternBlock CHERRY_LECTERN = new MoreLecternBlock(MapColor.TERRACOTTA_WHITE, SoundType.CHERRY_WOOD, "cherry");
    public static final MoreLecternBlock BAMBOO_LECTERN = new MoreLecternBlock(MapColor.COLOR_YELLOW, SoundType.BAMBOO_WOOD, "bamboo");
    public static final MoreLecternBlock CRIMSON_LECTERN = new MoreLecternBlock(MapColor.CRIMSON_STEM, SoundType.NETHER_WOOD, "crimson");
    public static final MoreLecternBlock WARPED_LECTERN = new MoreLecternBlock(MapColor.WARPED_STEM, SoundType.NETHER_WOOD, "warped");

    public static BlockEntityType<MoreLecternBlockEntity> MORE_LECTERN_BLOCK_ENTITY;

    public static final List<Block> more_lecterns = new ArrayList<>();


    public static void registerBlocks() {
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

        MORE_LECTERN_BLOCK_ENTITY = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, MoreLecternVariants.asId("more_lectern_variant"), BlockEntityType.Builder.of(MoreLecternBlockEntity::new, MlvBlockInit.more_lecterns.toArray(Block[]::new)).build());
    }

    private static void registerBlock(MoreLecternBlock lectern) {
        Registry.register(BuiltInRegistries.BLOCK, MoreLecternVariants.asId(lectern.lecternWoodType + "_lectern"), lectern);
        more_lecterns.add(lectern);
    }
}