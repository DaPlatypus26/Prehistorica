package net.perry.prehistorica.register;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.perry.prehistorica.Prehistorica;
import net.perry.prehistorica.block.AmphibianEgg;
import net.perry.prehistorica.block.entity.AnalyzerBlock;

public class ModBlocks {
    //Blocks
    public static final Block FOSSIL_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE, MapColor.STONE_GRAY)
            .strength(3.0f).resistance(5.0F).sounds(BlockSoundGroup.STONE).requiresTool());
    public static final Block DEEPSLATE_FOSSIL_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE, MapColor.STONE_GRAY)
            .strength(3.0f).resistance(6.0F).sounds(BlockSoundGroup.DEEPSLATE).requiresTool());
    public static final Block AMBER_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE, MapColor.STONE_GRAY)
            .strength(3.0f).resistance(5.0F).sounds(BlockSoundGroup.STONE).requiresTool());
    public static final Block DEEPSLATE_AMBER_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE, MapColor.STONE_GRAY)
            .strength(3.0f).resistance(6.0F).sounds(BlockSoundGroup.DEEPSLATE).requiresTool());
    public static final Block ANALYZER = new AnalyzerBlock(FabricBlockSettings.of(Material.METAL, MapColor.IRON_GRAY)
            .strength(4.0f).resistance(5.0F).sounds(BlockSoundGroup.METAL).requiresTool().nonOpaque());
    //Items
    public static final AmphibianEgg DIPLOCAULUS_EGG = new AmphibianEgg(ModEntities.DIPLOCAULUS, FabricBlockSettings.of(Material.EGG, MapColor.BROWN)
            .noCollision().nonOpaque().breakInstantly().sounds(BlockSoundGroup.FROGSPAWN));

    private static void registerBlock(String name, Block block) {
        Registry.register(Registry.BLOCK, new Identifier(Prehistorica.MOD_ID, name), block);
    }

    public static void registerModBlocks() {
        registerBlock("fossil_ore", FOSSIL_ORE);
        registerBlock("deepslate_fossil_ore", DEEPSLATE_FOSSIL_ORE);
        registerBlock("amber_ore", AMBER_ORE);
        registerBlock("deepslate_amber_ore", DEEPSLATE_AMBER_ORE);
        registerBlock("analyzer", ANALYZER);

        registerBlock("diplocaulus_egg", DIPLOCAULUS_EGG);
    }
}
