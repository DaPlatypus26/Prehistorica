package net.perry.prehistorica.entity.client.diplocaulus;

import com.google.common.collect.Maps;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.perry.prehistorica.Prehistorica;
import net.perry.prehistorica.entity.custom.DiplocaulusEntity;
import net.perry.prehistorica.entity.variant.DiplocaulusVariant;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Map;

public class DiplocaulusRenderer extends GeoEntityRenderer<DiplocaulusEntity> {

    public static final Map<DiplocaulusVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(DiplocaulusVariant.class), (map) -> {
                map.put(DiplocaulusVariant.BROWN,
                        new Identifier(Prehistorica.MOD_ID, "textures/entity/diplocaulus/diplocaulus_brown.png"));
                map.put(DiplocaulusVariant.GREEN,
                        new Identifier(Prehistorica.MOD_ID, "textures/entity/diplocaulus/diplocaulus_green.png"));
            });

    public DiplocaulusRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new DiplocaulusModel());
    }

    @Override
    public Identifier getTextureResource(DiplocaulusEntity instance) {
        return LOCATION_BY_VARIANT.get(instance.getVariant());
    }

    @Override
    public RenderLayer getRenderType(DiplocaulusEntity animatable, float partialTicks, MatrixStack stack, @Nullable VertexConsumerProvider renderTypeBuffer,
                                     @Nullable VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {
        if(animatable.isBaby()) {
            stack.scale(0.5F, 0.5F, 0.5F);
        } else {
            stack.scale(1.0F, 1.0F, 1.0F);
        }
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
