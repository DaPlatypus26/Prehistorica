package net.perry.prehistorica;

import net.fabricmc.api.ModInitializer;
import net.perry.prehistorica.register.*;
import net.perry.prehistorica.screen.ModScreenHandlers;
import software.bernie.geckolib3.GeckoLib;

public class Prehistorica implements ModInitializer {
    public static String MOD_ID = "prehistorica";

    @Override
    public void onInitialize() {
        registerModStuffs();
        GeckoLib.initialize();
    }

    private static void registerModStuffs() {
        ModItemGroups.registerModItemGroups();
        ModBlocks.registerModBlocks();
        ModBlocksEntities.registerModBlockEntities();
        ModItems.registerModItems();
        ModEntities.registerModAttributes();
        ModScreenHandlers.registerScreenHandlers();
        ModRecipes.registerModRecipes();

        new ModTags();
    }
}
