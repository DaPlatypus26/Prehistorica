package net.perry.prehistorica.client.renderer;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.perry.prehistorica.register.ModBlocks;

public class BlockRenderer {

    public static void blockRenderer() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DIPLOCAULUS_EGGS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MICROSCOPE, RenderLayer.getCutout());
    }
}
