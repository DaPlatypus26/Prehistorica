package net.perry.prehistorica.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class BigAnimalEggBlock extends Block {
    private EntityType ENTITY;
    public static final int field_31272 = 2;
    public static final int field_31273 = 1;
    public static final int field_31274 = 4;
    private static final VoxelShape SMALL_SHAPE = Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 9.0, 11.0);
    private static final VoxelShape LARGE_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 13.0, 16.0);
    public static final IntProperty HATCH;
    public static final IntProperty EGGS;
    private static final int MIN_HATCH_TIME = 3600;
    private static final int MAX_HATCH_TIME = 12000;

    public BigAnimalEggBlock(EntityType entity, AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(HATCH, 0)).with(EGGS, 1));
        ENTITY = entity;
    }

    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (!entity.bypassesSteppingEffects()) {
            this.tryBreakEgg(world, state, pos, entity, 100);
        }

        super.onSteppedOn(world, pos, state, entity);
    }

    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (!(entity instanceof ZombieEntity)) {
            this.tryBreakEgg(world, state, pos, entity, 3);
        }

        super.onLandedUpon(world, state, pos, entity, fallDistance);
    }

    private void tryBreakEgg(World world, BlockState state, BlockPos pos, Entity entity, int inverseChance) {
        if (this.breaksEgg(world, entity)) {
            if (!world.isClient && world.random.nextInt(inverseChance) == 0 && state.isOf(this)) {
                this.breakEgg(world, pos, state);
            }
        }
    }

    private void breakEgg(World world, BlockPos pos, BlockState state) {
        world.playSound((PlayerEntity)null, pos, SoundEvents.ENTITY_TURTLE_EGG_BREAK, SoundCategory.BLOCKS, 0.7F, 0.9F + world.random.nextFloat() * 0.2F);
        int i = (Integer)state.get(EGGS);
        if (i <= 1) {
            world.breakBlock(pos, false);
        } else {
            world.setBlockState(pos, (BlockState)state.with(EGGS, i - 1), 2);
            world.emitGameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Emitter.of(state));
            world.syncWorldEvent(2001, pos, Block.getRawIdFromState(state));
        }

    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        /*if (this.shouldHatchProgress(world) && isDirtBelow(world, pos)) {
            int i = (Integer)state.get(HATCH);
            if (i < 2) {
                world.playSound((PlayerEntity)null, pos, SoundEvents.ENTITY_TURTLE_EGG_CRACK, SoundCategory.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
                world.setBlockState(pos, (BlockState)state.with(HATCH, i + 1), 2);
            } else {
                world.playSound((PlayerEntity)null, pos, SoundEvents.ENTITY_TURTLE_EGG_HATCH, SoundCategory.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
                world.removeBlock(pos, false);

                for(int j = 0; j < (Integer)state.get(EGGS); ++j) {
                    world.syncWorldEvent(2001, pos, Block.getRawIdFromState(state));
                    AnimalEntity animalEntity = (AnimalEntity) ENTITY.create(world);
                    animalEntity.refreshPositionAndAngles((double)pos.getX() + 0.3 + (double)j * 0.2, (double)pos.getY(), (double)pos.getZ() + 0.3, 0.0F, 0.0F);
                    animalEntity.setBaby(true);
                    world.spawnEntity(animalEntity);
                }
            }
        }*/

        if(isDirtBelow(world, pos)) {
            int i = (Integer)state.get(HATCH);
            if (i < 2) {
                world.playSound((PlayerEntity)null, pos, SoundEvents.ENTITY_TURTLE_EGG_CRACK, SoundCategory.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
                world.setBlockState(pos, (BlockState)state.with(HATCH, i + 1), 2);
            } else {
                world.playSound((PlayerEntity)null, pos, SoundEvents.ENTITY_TURTLE_EGG_HATCH, SoundCategory.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
                world.removeBlock(pos, false);

                for(int j = 0; j < (Integer)state.get(EGGS); ++j) {
                    world.syncWorldEvent(2001, pos, Block.getRawIdFromState(state));
                    AnimalEntity animalEntity = (AnimalEntity) ENTITY.create(world);
                    animalEntity.refreshPositionAndAngles((double)pos.getX() + 0.3 + (double)j * 0.2, (double)pos.getY(), (double)pos.getZ() + 0.3, 0.0F, 0.0F);
                    animalEntity.setBaby(true);
                    world.spawnEntity(animalEntity);
                }
            }
        }
    }

    public static boolean isDirtBelow(BlockView world, BlockPos pos) {
        return isDirt(world, pos.down());
    }

    public static boolean isDirt(BlockView world, BlockPos pos) {
        return world.getBlockState(pos).isIn(BlockTags.DIRT);
    }

    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (isDirtBelow(world, pos) && !world.isClient) {
            world.syncWorldEvent(2005, pos, 0);
        }
        world.createAndScheduleBlockTick(pos, this, getHatchTime(world.getRandom()));
    }

    private static int getHatchTime(Random random) {
        return random.nextBetweenExclusive(MIN_HATCH_TIME, MAX_HATCH_TIME);
    }

    private boolean shouldHatchProgress(World world) {
        float f = world.getSkyAngle(1.0F);
        if ((double)f < 0.69 && (double)f > 0.65) {
            return true;
        } else {
            return world.random.nextInt(500) == 0;
        }
    }

    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        super.afterBreak(world, player, pos, state, blockEntity, stack);
        this.breakEgg(world, pos, state);
    }

    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return !context.shouldCancelInteraction() && context.getStack().isOf(this.asItem()) && (Integer)state.get(EGGS) < 4 ? true : super.canReplace(state, context);
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos());
        return blockState.isOf(this) ? (BlockState)blockState.with(EGGS, Math.min(3, (Integer)blockState.get(EGGS) + 1)) : super.getPlacementState(ctx);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return (Integer)state.get(EGGS) > 1 ? LARGE_SHAPE : SMALL_SHAPE;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{HATCH, EGGS});
    }

    private boolean breaksEgg(World world, Entity entity) {
        if (!(entity.getType() == ENTITY) && !(entity instanceof BatEntity)) {
            if (!(entity instanceof LivingEntity)) {
                return false;
            } else {
                return entity instanceof PlayerEntity || world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING);
            }
        } else {
            return false;
        }
    }

    static {
        HATCH = Properties.HATCH;
        EGGS = IntProperty.of("eggs", 1, 3);
    }
}
