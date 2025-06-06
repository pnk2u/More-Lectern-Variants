package de.pnku.mlv;

import de.pnku.mlv.init.MlvBlockInit;
import de.pnku.mlv.init.MlvItemInit;
import de.pnku.mlv.poi.MlvPointOfInterestTypes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class MoreLecternVariants implements ModInitializer {
    public static final String MODID = "lolmlv";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    @Override
    public void onInitialize() {
        MlvBlockInit.registerBlocks();
        MlvItemInit.registerItems();
        MlvPointOfInterestTypes.init();
        if (FabricLoader.getInstance().isModLoaded("quad")) {
            ResourceManagerHelper.registerBuiltinResourcePack(
                    withModId("mlecternv-quad-compat"),
                    FabricLoader.getInstance().getModContainer(MODID).orElseThrow(),
                    ResourcePackActivationType.ALWAYS_ENABLED
            );
        }
    }

    public static ResourceLocation withModId(String path) {
        return new ResourceLocation(MODID, path);
    }
}