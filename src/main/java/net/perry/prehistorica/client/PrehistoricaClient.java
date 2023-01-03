package net.perry.prehistorica.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.perry.prehistorica.client.renderer.BlockRenderer;
import net.perry.prehistorica.client.renderer.EntityRenderer;
import net.perry.prehistorica.register.ModScreenHandlers;
import net.perry.prehistorica.screen.analyzer.AnalyzerScreen;
import net.perry.prehistorica.screen.incubator.IncubatorScreen;

@Environment(EnvType.CLIENT)
public class PrehistoricaClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderer.blockRenderer();
        EntityRenderer.entityRenderer();
        ModScreenHandlers.registerHandledScreens();
    }
}
