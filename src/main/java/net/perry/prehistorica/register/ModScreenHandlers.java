package net.perry.prehistorica.register;

import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.screen.ScreenHandlerType;
import net.perry.prehistorica.screen.analyzer.AnalyzerScreen;
import net.perry.prehistorica.screen.analyzer.AnalyzerScreenHandler;
import net.perry.prehistorica.screen.dna_implementer.DnaImplementerScreen;
import net.perry.prehistorica.screen.dna_implementer.DnaImplementerScreenHandler;
import net.perry.prehistorica.screen.incubator.IncubatorScreen;
import net.perry.prehistorica.screen.incubator.IncubatorScreenHandler;
import net.perry.prehistorica.screen.laboratory_cabinet.LaboratoryCabinetScreen;
import net.perry.prehistorica.screen.laboratory_cabinet.LaboratoryCabinetScreenHandler;
import net.perry.prehistorica.screen.sifter.SifterScreen;
import net.perry.prehistorica.screen.sifter.SifterScreenHandler;

public class ModScreenHandlers {
    public static ScreenHandlerType<SifterScreenHandler> SIFTER_SCREEN_HANDLER;
    public static ScreenHandlerType<AnalyzerScreenHandler> ANALYZER_SCREEN_HANDLER;
    public static ScreenHandlerType<DnaImplementerScreenHandler> DNA_IMPLEMENTER_SCREEN_HANDLER;
    public static ScreenHandlerType<IncubatorScreenHandler> INCUBATOR_SCREEN_HANDLER;
    public static ScreenHandlerType<LaboratoryCabinetScreenHandler> LABORATORY_CABINET_SCREEN_HANDLER;

    public static void registerScreenHandlers() {
        SIFTER_SCREEN_HANDLER = new ScreenHandlerType<>(SifterScreenHandler::new);
        ANALYZER_SCREEN_HANDLER = new ScreenHandlerType<>(AnalyzerScreenHandler::new);
        DNA_IMPLEMENTER_SCREEN_HANDLER = new ScreenHandlerType<>(DnaImplementerScreenHandler::new);
        INCUBATOR_SCREEN_HANDLER = new ScreenHandlerType<>(IncubatorScreenHandler::new);
        LABORATORY_CABINET_SCREEN_HANDLER = new ScreenHandlerType<>(LaboratoryCabinetScreenHandler::new);
    }

    public static void registerHandledScreens() {
        HandledScreens.register(SIFTER_SCREEN_HANDLER, SifterScreen::new);
        HandledScreens.register(ANALYZER_SCREEN_HANDLER, AnalyzerScreen::new);
        HandledScreens.register(DNA_IMPLEMENTER_SCREEN_HANDLER, DnaImplementerScreen::new);
        HandledScreens.register(INCUBATOR_SCREEN_HANDLER, IncubatorScreen::new);
        HandledScreens.register(LABORATORY_CABINET_SCREEN_HANDLER, LaboratoryCabinetScreen::new);
    }
}
