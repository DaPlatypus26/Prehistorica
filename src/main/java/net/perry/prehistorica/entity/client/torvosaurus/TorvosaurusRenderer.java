package net.perry.prehistorica.entity.client.torvosaurus;

import com.google.common.collect.Maps;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.perry.prehistorica.Prehistorica;
import net.perry.prehistorica.entity.custom.TorvosaurusEntity;
import net.perry.prehistorica.entity.variant.TorvosaurusVariant;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Map;

public class TorvosaurusRenderer extends GeoEntityRenderer<TorvosaurusEntity> {

    public static final Map<TorvosaurusVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(TorvosaurusVariant.class), (map) -> {
                map.put(TorvosaurusVariant.BROWN,
                        new Identifier(Prehistorica.MOD_ID, "textures/entity/torvosaurus/torvosaurus.png"));
                map.put(TorvosaurusVariant.GREEN,
                        new Identifier(Prehistorica.MOD_ID, "textures/entity/torvosaurus/torvosaurus.png"));
            });

    public TorvosaurusRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new TorvosaurusModel());
    }

    @Override
    public Identifier getTextureResource(TorvosaurusEntity instance) {
        return LOCATION_BY_VARIANT.get(instance.getVariant());
    }

    @Override
    public RenderLayer getRenderType(TorvosaurusEntity animatable, float partialTicks, MatrixStack stack, @Nullable VertexConsumerProvider renderTypeBuffer,
                                     @Nullable VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {
        if(animatable.isBaby()) {
            stack.scale(0.5F, 0.5F, 0.5F);
        } else {
            stack.scale(1.0F, 1.0F, 1.0F);
        }
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
