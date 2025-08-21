package de.pnku.mlv.block;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

import static de.pnku.mlv.MoreLecternVariants.withModId;

public class MoreLecternBlock extends LecternBlock {
    public final String lecternWoodType;

    public MoreLecternBlock(MapColor colour, String lecternWoodType) {
        super(Properties.ofFullCopy(Blocks.LECTERN).mapColor(colour).setId(ResourceKey.create(Registries.BLOCK, withModId(lecternWoodType + "_lectern"))));
        this.lecternWoodType = lecternWoodType;
    }

    public MoreLecternBlock(MapColor colour, SoundType soundType, String lecternWoodType) {
        super(Properties.ofFullCopy(Blocks.LECTERN).mapColor(colour).setId(ResourceKey.create(Registries.BLOCK, withModId(lecternWoodType + "_lectern"))).sound(soundType));
        this.lecternWoodType = lecternWoodType;
    }
}