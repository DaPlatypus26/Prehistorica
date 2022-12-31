package net.perry.prehistorica.register;

import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.perry.prehistorica.Prehistorica;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> FOSSIL_ANALYZER_OUTPUT = createTag("fossil_analyzer_output");
        public static final TagKey<Item> MOSQUITO_IN_AMBER_ANALYZER_OUTPUT = createTag("mosquito_in_amber_analyzer_output");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(Registry.ITEM_KEY, new Identifier(Prehistorica.MOD_ID, name));
        }
    }
}
