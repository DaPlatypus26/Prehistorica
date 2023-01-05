package net.perry.prehistorica.register;

import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.perry.prehistorica.Prehistorica;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> DIRT_SIFTER_OUTPUT = createTag("dirt_sifter_output");
        public static final TagKey<Item> COARSE_DIRT_SIFTER_OUTPUT = createTag("coarse_dirt_sifter_output");
        public static final TagKey<Item> SAND_SIFTER_OUTPUT = createTag("sand_sifter_output");
        public static final TagKey<Item> RED_SAND_SIFTER_OUTPUT = createTag("red_sand_sifter_output");
        public static final TagKey<Item> GRAVEL_SIFTER_OUTPUT = createTag("gravel_sifter_output");

        public static final TagKey<Item> FOSSIL_ANALYZER_OUTPUT = createTag("fossil_analyzer_output");
        public static final TagKey<Item> MOSQUITO_IN_AMBER_ANALYZER_OUTPUT = createTag("mosquito_in_amber_analyzer_output");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(Registry.ITEM_KEY, new Identifier(Prehistorica.MOD_ID, name));
        }
    }
}
