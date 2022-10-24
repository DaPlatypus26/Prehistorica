package net.perry.prehistorica.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.perry.prehistorica.client.renderer.BlockRenderer;
import net.perry.prehistorica.client.renderer.EntityRenderer;
import net.perry.prehistorica.screen.ModScreenHandlers;
import net.perry.prehistorica.screen.analyzer.AnalyzerScreen;

@Environment(EnvType.CLIENT)
public class PrehistoricaClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderer.blockRenderer();
        EntityRenderer.entityRenderer();

        HandledScreens.register(ModScreenHandlers.ANALYZER_SCREEN_HANDLER, AnalyzerScreen::new);
    }
}
