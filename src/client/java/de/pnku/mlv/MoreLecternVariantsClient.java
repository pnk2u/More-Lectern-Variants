package de.pnku.mlv;

import de.pnku.mlv.client.render.MoreLecternRenderer;
import net.fabricmc.api.ClientModInitializer;

public class MoreLecternVariantsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MoreLecternRenderer.registerMoreLecternRenderer();
    }
}
