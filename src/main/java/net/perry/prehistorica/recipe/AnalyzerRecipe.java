package net.perry.prehistorica.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class AnalyzerRecipe implements Recipe<SimpleInventory> {

    private final Identifier id;
    private final ItemStack output;
    private final DefaultedList<Ingredient> recipeItems;
    private static int slot;

    public AnalyzerRecipe(Identifier id, ItemStack output, DefaultedList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if(world.isClient())
            return false;

        if(recipeItems.get(0).test(inventory.getStack(0))) {
            slot = 0;
            return true;
        } else if(recipeItems.get(0).test(inventory.getStack(1))) {
            slot = 1;
            return true;
        } else if(recipeItems.get(0).test(inventory.getStack(2))) {
            slot = 2;
            return true;
        } else if(recipeItems.get(0).test(inventory.getStack(3))) {
            slot = 3;
            return true;
        } else if(recipeItems.get(0).test(inventory.getStack(4))) {
            slot = 4;
            return true;
        } else if(recipeItems.get(0).test(inventory.getStack(5))) {
            slot = 5;
            return true;
        } else if(recipeItems.get(0).test(inventory.getStack(6))) {
            slot = 6;
            return true;
        } else if(recipeItems.get(0).test(inventory.getStack(7))) {
            slot = 7;
            return true;
        } else if(recipeItems.get(0).test(inventory.getStack(8))) {
            slot = 8;
            return true;
        } else {
            return false;
        }
    }

    public static int getSlot() {
        return slot;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory) {
        return output;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput() {
        return output.copy();
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<AnalyzerRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "analyzing";
    }

    public static class Serializer implements RecipeSerializer<AnalyzerRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "analyzing";
        // this is the name given in the json file

        @Override
        public AnalyzerRecipe read(Identifier id, JsonObject json) {
            ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"));

            JsonArray ingredients = JsonHelper.getArray(json, "ingredients");
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(1, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new AnalyzerRecipe(id, output, inputs);
        }

        @Override
        public AnalyzerRecipe read(Identifier id, PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromPacket(buf));
            }

            ItemStack output = buf.readItemStack();
            return new AnalyzerRecipe(id, output, inputs);
        }

        @Override
        public void write(PacketByteBuf buf, AnalyzerRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.write(buf);
            }
            buf.writeItemStack(recipe.getOutput());
        }
    }
}
