package net.perry.prehistorica.client.renderer;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.perry.prehistorica.entity.client.DiplocaulusRenderer;
import net.perry.prehistorica.register.ModEntities;

public class EntityRenderer {

    public static void entityRenderer() {
        EntityRendererRegistry.register(ModEntities.DIPLOCAULUS, DiplocaulusRenderer::new);
    }
}
