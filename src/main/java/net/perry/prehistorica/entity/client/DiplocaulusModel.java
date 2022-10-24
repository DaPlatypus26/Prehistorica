package net.perry.prehistorica.entity.client;

import net.minecraft.util.Identifier;
import net.perry.prehistorica.Prehistorica;
import net.perry.prehistorica.entity.custom.DiplocaulusEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DiplocaulusModel extends AnimatedGeoModel<DiplocaulusEntity> {

    @Override
    public Identifier getModelResource(DiplocaulusEntity object) {
        return new Identifier(Prehistorica.MOD_ID, "geo/diplocaulus.geo.json");
    }

    @Override
    public Identifier getTextureResource(DiplocaulusEntity object) {
        return DiplocaulusRenderer.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public Identifier getAnimationResource(DiplocaulusEntity animatable) {
        return new Identifier(Prehistorica.MOD_ID, "animations/diplocaulus.animation.json");
    }
}
