package com.pnku.mlv.block;

import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;

public class MoreLecternBlock extends LecternBlock {
    public final String lecternType;

    public MoreLecternBlock(MapColor colour, String lecternType) {
        super(Settings.copy(Blocks.LECTERN).mapColor(colour));
        this.lecternType = lecternType;
    }

    public MoreLecternBlock(MapColor colour, BlockSoundGroup sound, String lecternType) {
        super(Settings.copy(Blocks.LECTERN).mapColor(colour).sounds(sound));
        this.lecternType = lecternType;
    }
}