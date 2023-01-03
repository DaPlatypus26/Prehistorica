package net.perry.prehistorica.register;

import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.screen.ScreenHandlerType;
import net.perry.prehistorica.screen.analyzer.AnalyzerScreen;
import net.perry.prehistorica.screen.analyzer.AnalyzerScreenHandler;
import net.perry.prehistorica.screen.dna_implementer.DnaImplementerScreen;
import net.perry.prehistorica.screen.dna_implementer.DnaImplementerScreenHandler;
import net.perry.prehistorica.screen.incubator.IncubatorScreen;
import net.perry.prehistorica.screen.incubator.IncubatorScreenHandler;

public class ModScreenHandlers {
    public static ScreenHandlerType<AnalyzerScreenHandler> ANALYZER_SCREEN_HANDLER;
    public static ScreenHandlerType<DnaImplementerScreenHandler> DNA_IMPLEMENTER_SCREEN_HANDLER;
    public static ScreenHandlerType<IncubatorScreenHandler> INCUBATOR_SCREEN_HANDLER;

    public static void registerScreenHandlers() {
        ANALYZER_SCREEN_HANDLER = new ScreenHandlerType<>(AnalyzerScreenHandler::new);
        DNA_IMPLEMENTER_SCREEN_HANDLER = new ScreenHandlerType<>(DnaImplementerScreenHandler::new);
        INCUBATOR_SCREEN_HANDLER = new ScreenHandlerType<>(IncubatorScreenHandler::new);
    }

    public static void registerHandledScreens() {
        HandledScreens.register(ANALYZER_SCREEN_HANDLER, AnalyzerScreen::new);
        HandledScreens.register(DNA_IMPLEMENTER_SCREEN_HANDLER, DnaImplementerScreen::new);
        HandledScreens.register(INCUBATOR_SCREEN_HANDLER, IncubatorScreen::new);
    }
}
