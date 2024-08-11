package de.pnku.mlv.block;


import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

public class MoreLecternBlock extends LecternBlock {
    public final String lecternWoodType;

    public MoreLecternBlock(MapColor colour, String lecternWoodType) {
        super(Properties.copy(Blocks.LECTERN).mapColor(colour));
        this.lecternWoodType = lecternWoodType;
    }

    public MoreLecternBlock(MapColor colour, SoundType soundType, String lecternWoodType) {
        super(Properties.copy(Blocks.LECTERN).mapColor(colour).sound(soundType));
        this.lecternWoodType = lecternWoodType;
    }
}