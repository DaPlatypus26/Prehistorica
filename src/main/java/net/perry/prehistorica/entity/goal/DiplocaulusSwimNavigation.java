package net.perry.prehistorica.entity.goal;

import net.minecraft.entity.ai.pathing.AmphibiousPathNodeMaker;
import net.minecraft.entity.ai.pathing.PathNodeNavigator;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.perry.prehistorica.entity.custom.DiplocaulusEntity;

public class DiplocaulusSwimNavigation extends SwimNavigation {

    public DiplocaulusSwimNavigation(DiplocaulusEntity diplocalus, World world) {
        super(diplocalus, world);
    }

    @Override
    protected boolean isAtValidPosition() {
        return true;
    }

    @Override
    protected PathNodeNavigator createPathNodeNavigator(int range) {
        this.nodeMaker = new AmphibiousPathNodeMaker(false);
        return new PathNodeNavigator(this.nodeMaker, range);
    }

    @Override
    public boolean isValidPosition(BlockPos pos) {
        return !this.world.getBlockState(pos.down()).isAir();
    }
}
