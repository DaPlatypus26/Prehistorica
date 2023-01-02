package net.perry.prehistorica.screen;

import net.minecraft.screen.ScreenHandlerType;
import net.perry.prehistorica.screen.analyzer.AnalyzerScreenHandler;
import net.perry.prehistorica.screen.incubator.IncubatorScreenHandler;

public class ModScreenHandlers {
    public static ScreenHandlerType<AnalyzerScreenHandler> ANALYZER_SCREEN_HANDLER;
    public static ScreenHandlerType<IncubatorScreenHandler> INCUBATOR_SCREEN_HANDLER;

    public static void registerScreenHandlers() {
        ANALYZER_SCREEN_HANDLER = new ScreenHandlerType<>(AnalyzerScreenHandler::new);
        INCUBATOR_SCREEN_HANDLER = new ScreenHandlerType<>(IncubatorScreenHandler::new);
    }
}
