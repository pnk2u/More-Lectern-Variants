package de.pnku.mlv;

import de.pnku.mlv.init.MlvBlockInit;
import de.pnku.mlv.init.MlvItemInit;
import de.pnku.mlv.poi.MlvPointOfInterestTypes;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;

public class MoreLecternVariants implements ModInitializer {
    public static final String MODID = "lolmlv";

    @Override
    public void onInitialize() {
        MlvBlockInit.registerBlocks();
        MlvItemInit.registerItems();
        MlvPointOfInterestTypes.init();
    }

    public static ResourceLocation asId(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}