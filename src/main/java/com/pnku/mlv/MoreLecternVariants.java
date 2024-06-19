package com.pnku.mlv;

import com.pnku.mlv.init.MlvBlockInit;
import com.pnku.mlv.init.MlvItemInit;
import com.pnku.mlv.poi.MlvPointOfInterestTypes;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class MoreLecternVariants implements ModInitializer {
    public static final String MODID = "lolmlv";

    @Override
    public void onInitialize() {
        MlvBlockInit.registerBlocks();
        MlvItemInit.registerItems();
        MlvPointOfInterestTypes.init();
    }

    public static Identifier asId(String path) {
        return Identifier.of(MODID, path);
    }
}