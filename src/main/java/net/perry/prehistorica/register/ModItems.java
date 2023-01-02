package net.perry.prehistorica.register;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.PlaceableOnWaterItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.perry.prehistorica.Prehistorica;

public class ModItems {
    //Blocks
    public static final BlockItem FOSSIL_ORE = new BlockItem(ModBlocks.FOSSIL_ORE, new FabricItemSettings().group(ModItemGroups.blockTab));
    public static final BlockItem DEEPSLATE_FOSSIL_ORE = new BlockItem(ModBlocks.DEEPSLATE_FOSSIL_ORE, new FabricItemSettings().group(ModItemGroups.blockTab));
    public static final BlockItem AMBER_ORE = new BlockItem(ModBlocks.AMBER_ORE, new FabricItemSettings().group(ModItemGroups.blockTab));
    public static final BlockItem DEEPSLATE_AMBER_ORE = new BlockItem(ModBlocks.DEEPSLATE_AMBER_ORE, new FabricItemSettings().group(ModItemGroups.blockTab));
    public static final BlockItem ANALYZER = new BlockItem(ModBlocks.ANALYZER, new FabricItemSettings().group(ModItemGroups.blockTab));
    public static final BlockItem INCUBATOR = new BlockItem(ModBlocks.INCUBATOR, new FabricItemSettings().group(ModItemGroups.blockTab));

    //Items
    public static final Item FOSSIL = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    public static final Item PLANT_FOSSIL = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    public static final Item AMBER = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    public static final Item MOSQUITO_IN_AMBER = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    public static final Item BUG_IN_AMBER = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    public static final Item CRAB_IN_AMBER = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    public static final Item PLANT_IN_AMBER = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    //DNA
    public static final Item TYRANNOSAURUS_REX_DNA = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    public static final Item TORVOSAURUS_DNA = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    public static final Item STEGOSAURUS_DNA = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    public static final Item TRICERATOPS_DNA = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    public static final Item VELOCIRAPTOR_DNA = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    public static final Item DILOPHOSAURUS_DNA = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    public static final Item DIPLOCAULUS_DNA = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    //EGGS
    public static final BlockItem DIPLOCAULUS_EGGS = new PlaceableOnWaterItem(ModBlocks.DIPLOCAULUS_EGGS, new FabricItemSettings().group(ModItemGroups.itemTab));

    //SpawnEggs
    public static final SpawnEggItem TORVOSAURUS_SPAWN_EGG = new SpawnEggItem(ModEntities.TORVOSAURUS, 0x392e29, 0x8a604c, new FabricItemSettings().group(ModItemGroups.spawnEggTab));
    public static final SpawnEggItem DIPLOCAULUS_SPAWN_EGG = new SpawnEggItem(ModEntities.DIPLOCAULUS, 0xaba621, 0x673c19, new FabricItemSettings().group(ModItemGroups.spawnEggTab));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Prehistorica.MOD_ID, name), item);
    }

    public static void registerModItems() {
        //Blocks
        registerItem("fossil_ore", FOSSIL_ORE);
        registerItem("deepslate_fossil_ore", DEEPSLATE_FOSSIL_ORE);
        registerItem("amber_ore", AMBER_ORE);
        registerItem("deepslate_amber_ore", DEEPSLATE_AMBER_ORE);
        registerItem("analyzer", ANALYZER);
        registerItem("incubator", INCUBATOR);
        //Items
        registerItem("fossil", FOSSIL);
        registerItem("plant_fossil", PLANT_FOSSIL);
        registerItem("amber", AMBER);
        registerItem("mosquito_in_amber", MOSQUITO_IN_AMBER);
        registerItem("bug_in_amber", BUG_IN_AMBER);
        registerItem("crab_in_amber", CRAB_IN_AMBER);
        registerItem("plant_in_amber", PLANT_IN_AMBER);
        registerItem("tyrannosaurus_rex_dna", TYRANNOSAURUS_REX_DNA);
        registerItem("torvosaurus_dna", TORVOSAURUS_DNA);
        registerItem("stegosaurus_dna", STEGOSAURUS_DNA);
        registerItem("triceratops_dna", TRICERATOPS_DNA);
        registerItem("velociraptor_dna", VELOCIRAPTOR_DNA);
        registerItem("dilophosaurus_dna", DILOPHOSAURUS_DNA);
        registerItem("diplocaulus_dna", DIPLOCAULUS_DNA);
        registerItem("diplocaulus_eggs", DIPLOCAULUS_EGGS);
        //SpawnEggs
        registerItem("torvosaurus_spawn_egg", TORVOSAURUS_SPAWN_EGG);
        registerItem("diplocaulus_spawn_egg", DIPLOCAULUS_SPAWN_EGG);
    }
}
