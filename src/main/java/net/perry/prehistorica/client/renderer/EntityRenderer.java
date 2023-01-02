package net.perry.prehistorica.client.renderer;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.perry.prehistorica.entity.client.diplocaulus.DiplocaulusRenderer;
import net.perry.prehistorica.entity.client.torvosaurus.TorvosaurusRenderer;
import net.perry.prehistorica.register.ModEntities;

public class EntityRenderer {

    public static void entityRenderer() {
        EntityRendererRegistry.register(ModEntities.TORVOSAURUS, TorvosaurusRenderer::new);
        EntityRendererRegistry.register(ModEntities.DIPLOCAULUS, DiplocaulusRenderer::new);
    }
}
