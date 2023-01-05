package net.perry.prehistorica.register;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.perry.prehistorica.Prehistorica;
import net.perry.prehistorica.block.entity.analyzer.AnalyzerBlockEntity;
import net.perry.prehistorica.block.entity.dna_implementer.DnaImplementerBlockEntity;
import net.perry.prehistorica.block.entity.incubator.IncubatorBlockEntity;
import net.perry.prehistorica.block.entity.sifter.SifterBlockEntity;

public class ModBlocksEntities {
    public static BlockEntityType<SifterBlockEntity> SIFTER;
    public static BlockEntityType<AnalyzerBlockEntity> ANALYZER;
    public static BlockEntityType<DnaImplementerBlockEntity> DNA_IMPLEMENTER;
    public static BlockEntityType<IncubatorBlockEntity> INCUBATOR;

    public static void registerModBlockEntities() {
        SIFTER = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(Prehistorica.MOD_ID, "sifter"),
                FabricBlockEntityTypeBuilder.create(SifterBlockEntity::new,
                        ModBlocks.SIFTER).build(null));

        ANALYZER = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(Prehistorica.MOD_ID, "analyzer"),
                FabricBlockEntityTypeBuilder.create(AnalyzerBlockEntity::new,
                        ModBlocks.ANALYZER).build(null));

        DNA_IMPLEMENTER = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(Prehistorica.MOD_ID, "dna_implementer"),
                FabricBlockEntityTypeBuilder.create(DnaImplementerBlockEntity::new,
                        ModBlocks.DNA_IMPLEMENTER).build(null));

        INCUBATOR = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(Prehistorica.MOD_ID, "incubator"),
                FabricBlockEntityTypeBuilder.create(IncubatorBlockEntity::new,
                        ModBlocks.INCUBATOR).build(null));
    }
}
