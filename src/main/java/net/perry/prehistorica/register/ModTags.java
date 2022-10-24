package net.perry.prehistorica.register;

import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.perry.prehistorica.Prehistorica;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> ANALYZER_OUTPUT = createTag("analyzer_output");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(Registry.ITEM_KEY, new Identifier(Prehistorica.MOD_ID, name));
        }
    }
}
