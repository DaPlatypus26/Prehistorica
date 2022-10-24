package net.perry.prehistorica.register;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.perry.prehistorica.Prehistorica;
import net.perry.prehistorica.recipe.AnalyzerRecipe;

public class ModRecipes {
    public static void registerModRecipes() {
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(Prehistorica.MOD_ID, AnalyzerRecipe.Serializer.ID),
                AnalyzerRecipe.Serializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(Prehistorica.MOD_ID, AnalyzerRecipe.Type.ID),
                AnalyzerRecipe.Type.INSTANCE);
    }
}
