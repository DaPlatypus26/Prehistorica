package net.perry.prehistorica.register;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.perry.prehistorica.Prehistorica;
import net.perry.prehistorica.entity.custom.DiplocaulusEntity;

public class ModEntities {
    public static final EntityType<DiplocaulusEntity> DIPLOCAULUS = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Prehistorica.MOD_ID, "diplocaulus"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, DiplocaulusEntity::new)
                    .dimensions(EntityDimensions.fixed(1.0f, 1.0f)).build());

    private static void registerAttributes(EntityType entityType, DefaultAttributeContainer.Builder builder) {
        FabricDefaultAttributeRegistry.register(entityType, builder);
    }

    public static void registerModAttributes() {
        registerAttributes(ModEntities.DIPLOCAULUS, DiplocaulusEntity.setAttributes());
    }
}
