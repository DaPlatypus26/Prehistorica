package net.perry.prehistorica.register;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.perry.prehistorica.Prehistorica;
import net.perry.prehistorica.block.entity.AnalyzerBlockEntity;

public class ModBlocksEntities {
    public static BlockEntityType<AnalyzerBlockEntity> ANALYZER;

    public static void registerModBlockEntities() {
        ANALYZER = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(Prehistorica.MOD_ID, "analyzer"),
                FabricBlockEntityTypeBuilder.create(AnalyzerBlockEntity::new,
                        ModBlocks.ANALYZER).build(null));
    }
}
