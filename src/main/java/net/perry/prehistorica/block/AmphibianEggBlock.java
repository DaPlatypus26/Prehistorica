package net.perry.prehistorica.block;

import com.google.common.annotations.VisibleForTesting;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TadpoleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class AmphibianEggBlock extends Block {
    private EntityType TADPOLE;
    private static final int MIN_TADPOLES = 2;
    private static final int MAX_TADPOLES = 5;
    private static final int MIN_HATCH_TIME = 3600;
    private static final int MAX_HATCH_TIME = 12000;
    protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 1.5, 16.0);

    public AmphibianEggBlock(EntityType entityType, AbstractBlock.Settings settings) {
        super(settings);
        TADPOLE = entityType;
    }


    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return canLayAt(world, pos.down());
    }

    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        world.createAndScheduleBlockTick(pos, this, getHatchTime(world.getRandom()));
    }

    private static int getHatchTime(Random random) {
        return random.nextBetweenExclusive(MIN_HATCH_TIME, MAX_HATCH_TIME);
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return !this.canPlaceAt(state, world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!this.canPlaceAt(state, world, pos)) {
            this.breakWithoutDrop(world, pos);
        } else {
            this.hatch(world, pos, random);
        }
    }

    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity.getType().equals(EntityType.FALLING_BLOCK)) {
            this.breakWithoutDrop(world, pos);
        }
    }

    private static boolean canLayAt(BlockView world, BlockPos pos) {
        FluidState fluidState = world.getFluidState(pos);
        FluidState fluidState2 = world.getFluidState(pos.up());
        return fluidState.getFluid() == Fluids.WATER && fluidState2.getFluid() == Fluids.EMPTY;
    }

    private void hatch(ServerWorld world, BlockPos pos, Random random) {
        this.breakWithoutDrop(world, pos);
        world.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_FROGSPAWN_HATCH, SoundCategory.BLOCKS, 1.0F, 1.0F);
        this.spawnTadpoles(world, pos, random);
    }

    private void breakWithoutDrop(World world, BlockPos pos) {
        world.breakBlock(pos, false);
    }

    private void spawnTadpoles(ServerWorld world, BlockPos pos, Random random) {
        int i = random.nextBetweenExclusive(MIN_TADPOLES, MAX_TADPOLES);

        for(int j = 1; j <= i; ++j) {
            AnimalEntity tadpoleEntity = (AnimalEntity)TADPOLE.create(world);
            double d = (double)pos.getX() + this.getSpawnOffset(random);
            double e = (double)pos.getZ() + this.getSpawnOffset(random);
            int k = random.nextBetweenExclusive(1, 361);
            tadpoleEntity.refreshPositionAndAngles(d, (double)pos.getY() - 0.5, e, (float)k, 0.0F);
            tadpoleEntity.setBaby(true);
            world.spawnEntity(tadpoleEntity);
        }
    }

    private double getSpawnOffset(Random random) {
        double d = (double)(TadpoleEntity.WIDTH / 2.0F);
        return MathHelper.clamp(random.nextDouble(), d, 1.0 - d);
    }
}
