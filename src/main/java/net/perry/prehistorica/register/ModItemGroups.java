package net.perry.prehistorica.register;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.perry.prehistorica.Prehistorica;

public class ModItemGroups {
    public static ItemGroup blockTab;
    public static ItemGroup itemTab;
    public static ItemGroup spawnEggTab;

    public static void registerModItemGroups() {
        blockTab = FabricItemGroupBuilder.create(
                        new Identifier(Prehistorica.MOD_ID, "blocks"))
                .icon(() -> new ItemStack(ModItems.FOSSIL_ORE)).build();

        itemTab = FabricItemGroupBuilder.create(
                        new Identifier(Prehistorica.MOD_ID, "items"))
                .icon(() -> new ItemStack(ModItems.FOSSIL)).build();

        spawnEggTab = FabricItemGroupBuilder.create(
                        new Identifier(Prehistorica.MOD_ID, "spawneggs"))
                .icon(() -> new ItemStack(ModItems.DIPLOCAULUS_SPAWN_EGG)).build();
    }
}
