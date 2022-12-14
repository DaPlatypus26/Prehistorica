package net.perry.prehistorica.register;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.perry.prehistorica.Prehistorica;
import net.perry.prehistorica.item.FossilItem;

public class ModItems {
    //Blocks
    public static final BlockItem FOSSIL_ORE = new BlockItem(ModBlocks.FOSSIL_ORE, new FabricItemSettings().group(ModItemGroups.blockTab));
    public static final BlockItem DEEPSLATE_FOSSIL_ORE = new BlockItem(ModBlocks.DEEPSLATE_FOSSIL_ORE, new FabricItemSettings().group(ModItemGroups.blockTab));
    public static final BlockItem AMBER_ORE = new BlockItem(ModBlocks.AMBER_ORE, new FabricItemSettings().group(ModItemGroups.blockTab));
    public static final BlockItem DEEPSLATE_AMBER_ORE = new BlockItem(ModBlocks.DEEPSLATE_AMBER_ORE, new FabricItemSettings().group(ModItemGroups.blockTab));
    public static final BlockItem PERMAFROST = new BlockItem(ModBlocks.PERMAFROST, new FabricItemSettings().group(ModItemGroups.blockTab));
    public static final BlockItem SIFTER = new BlockItem(ModBlocks.SIFTER, new FabricItemSettings().group(ModItemGroups.blockTab));
    public static final BlockItem ANALYZER = new BlockItem(ModBlocks.ANALYZER, new FabricItemSettings().group(ModItemGroups.blockTab));
    public static final BlockItem DNA_IMPLEMENTER = new BlockItem(ModBlocks.DNA_IMPLEMENTER, new FabricItemSettings().group(ModItemGroups.blockTab));
    public static final BlockItem INCUBATOR = new BlockItem(ModBlocks.INCUBATOR, new FabricItemSettings().group(ModItemGroups.blockTab));
    public static final BlockItem MICROSCOPE = new BlockItem(ModBlocks.MICROSCOPE, new FabricItemSettings().group(ModItemGroups.blockTab));
    public static final BlockItem LABORATORY_TABLE = new BlockItem(ModBlocks.LABORATORY_TABLE, new FabricItemSettings().group(ModItemGroups.blockTab));
    public static final BlockItem LABORATORY_CABINET = new BlockItem(ModBlocks.LABORATORY_CABINET, new FabricItemSettings().group(ModItemGroups.blockTab));

    //Items
    public static final Item FOSSIL = new FossilItem(new FabricItemSettings().group(ModItemGroups.itemTab));
    public static final Item PLANT_FOSSIL = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    public static final Item AMBER = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    public static final Item MOSQUITO_IN_AMBER = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    public static final Item BUG_IN_AMBER = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    public static final Item CRAB_IN_AMBER = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    public static final Item PLANT_IN_AMBER = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    public static final Item FROZEN_TISSUE = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    //DNA
    public static final Item TYRANNOSAURUS_REX_DNA = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    public static final Item TORVOSAURUS_DNA = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    public static final Item STEGOSAURUS_DNA = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    public static final Item TRICERATOPS_DNA = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    public static final Item NODOSAURUS_DNA = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    public static final Item UTAHRAPTOR_DNA = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    public static final Item VELOCIRAPTOR_DNA = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    public static final Item DILOPHOSAURUS_DNA = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    public static final Item DIPLOCAULUS_DNA = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    public static final Item ICHTHYOSTEGA_DNA = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    //EGGS
    public static final BlockItem TORVOSAURUS_EGG = new BlockItem(ModBlocks.TORVOSAURUS_EGG, new FabricItemSettings().group(ModItemGroups.itemTab));
    public static final BlockItem DIPLOCAULUS_EGGS = new PlaceableOnWaterItem(ModBlocks.DIPLOCAULUS_EGGS, new FabricItemSettings().group(ModItemGroups.itemTab));
    //HATCHED
    public static final Item DIPLOCAULUS_BUCKET = new EntityBucketItem(ModEntities.DIPLOCAULUS, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_AXOLOTL, new FabricItemSettings().group(ModItemGroups.itemTab));
    //BONES
    public static final Item VELOCIRAPTOR_SKULL = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    public static final Item DILOPHOSAURUS_SKULL = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));
    public static final Item DIPLOCAULUS_SKULL = new Item(new FabricItemSettings().group(ModItemGroups.itemTab));

    //SpawnEggs
    public static final SpawnEggItem TORVOSAURUS_SPAWN_EGG = new SpawnEggItem(ModEntities.TORVOSAURUS, 0x8a604c, 0x392e29, new FabricItemSettings().group(ModItemGroups.spawnEggTab));
    public static final SpawnEggItem DIPLOCAULUS_SPAWN_EGG = new SpawnEggItem(ModEntities.DIPLOCAULUS, 0x673c19, 0xaba621, new FabricItemSettings().group(ModItemGroups.spawnEggTab));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Prehistorica.MOD_ID, name), item);
    }

    public static void registerModItems() {
        //Blocks
        registerItem("fossil_ore", FOSSIL_ORE);
        registerItem("deepslate_fossil_ore", DEEPSLATE_FOSSIL_ORE);
        registerItem("amber_ore", AMBER_ORE);
        registerItem("deepslate_amber_ore", DEEPSLATE_AMBER_ORE);
        registerItem("permafrost", PERMAFROST);
        registerItem("sifter", SIFTER);
        registerItem("analyzer", ANALYZER);
        registerItem("dna_implementer", DNA_IMPLEMENTER);
        registerItem("incubator", INCUBATOR);
        registerItem("microscope", MICROSCOPE);
        registerItem("laboratory_table", LABORATORY_TABLE);
        registerItem("laboratory_cabinet", LABORATORY_CABINET);
        //Items
        registerItem("fossil", FOSSIL);
        registerItem("plant_fossil", PLANT_FOSSIL);
        registerItem("amber", AMBER);
        registerItem("mosquito_in_amber", MOSQUITO_IN_AMBER);
        registerItem("bug_in_amber", BUG_IN_AMBER);
        registerItem("crab_in_amber", CRAB_IN_AMBER);
        registerItem("plant_in_amber", PLANT_IN_AMBER);
        registerItem("frozen_tissue", FROZEN_TISSUE);
        registerItem("tyrannosaurus_rex_dna", TYRANNOSAURUS_REX_DNA);
        registerItem("torvosaurus_dna", TORVOSAURUS_DNA);
        registerItem("stegosaurus_dna", STEGOSAURUS_DNA);
        registerItem("triceratops_dna", TRICERATOPS_DNA);
        registerItem("nodosaurus_dna", NODOSAURUS_DNA);
        registerItem("utahraptor_dna", UTAHRAPTOR_DNA);
        registerItem("velociraptor_dna", VELOCIRAPTOR_DNA);
        registerItem("dilophosaurus_dna", DILOPHOSAURUS_DNA);
        registerItem("diplocaulus_dna", DIPLOCAULUS_DNA);
        registerItem("ichthyostega_dna", ICHTHYOSTEGA_DNA);
        registerItem("torvosaurus_egg", TORVOSAURUS_EGG);
        registerItem("diplocaulus_eggs", DIPLOCAULUS_EGGS);
        registerItem("diplocaulus_bucket", DIPLOCAULUS_BUCKET);
        registerItem("velociraptor_skull", VELOCIRAPTOR_SKULL);
        registerItem("dilophosaurus_skull", DILOPHOSAURUS_SKULL);
        registerItem("diplocaulus_skull", DIPLOCAULUS_SKULL);
        //SpawnEggs
        registerItem("torvosaurus_spawn_egg", TORVOSAURUS_SPAWN_EGG);
        registerItem("diplocaulus_spawn_egg", DIPLOCAULUS_SPAWN_EGG);
    }
}
