package net.perry.prehistorica.entity.client.torvosaurus;

import net.minecraft.util.Identifier;
import net.perry.prehistorica.Prehistorica;
import net.perry.prehistorica.entity.custom.TorvosaurusEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TorvosaurusModel extends AnimatedGeoModel<TorvosaurusEntity> {

    @Override
    public Identifier getModelResource(TorvosaurusEntity object) {
        return new Identifier(Prehistorica.MOD_ID, "geo/torvosaurus.geo.json");
    }

    @Override
    public Identifier getTextureResource(TorvosaurusEntity object) {
        return TorvosaurusRenderer.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public Identifier getAnimationResource(TorvosaurusEntity animatable) {
        return new Identifier(Prehistorica.MOD_ID, "animations/torvosaurus.animation.json");
    }
}
