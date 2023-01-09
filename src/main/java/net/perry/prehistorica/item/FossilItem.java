package net.perry.prehistorica.item;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FossilItem extends Item {
    public FossilItem(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        double x = user.raycast(4.0, 0, false).getPos().getX();
        double y = user.raycast(4.0, 0, false).getPos().getY();
        double z = user.raycast(4.0, 0, false).getPos().getZ();
        BlockPos pos = new BlockPos(x, y, z);
        BlockPos posX1 = new BlockPos(x + 0.000001, y, z);
        BlockPos posX2 = new BlockPos(x - 0.000001, y, z);
        BlockPos posY1 = new BlockPos(x, y + 0.000001, z);
        BlockPos posY2 = new BlockPos(x, y - 0.000001, z);
        BlockPos posZ1 = new BlockPos(x, y, z + 0.000001);
        BlockPos posZ2 = new BlockPos(x, y, z - 0.000001);

        if(world.getBlockState(posX1).isOf(Blocks.WATER_CAULDRON) || world.getBlockState(posX2).isOf(Blocks.WATER_CAULDRON) ||
                world.getBlockState(posY1).isOf(Blocks.WATER_CAULDRON) || world.getBlockState(posY2).isOf(Blocks.WATER_CAULDRON) ||
                world.getBlockState(posZ1).isOf(Blocks.WATER_CAULDRON) || world.getBlockState(posZ2).isOf(Blocks.WATER_CAULDRON) ||
                world.getBlockState(pos).isOf(Blocks.WATER_CAULDRON)) {
            user.incrementStat(Stats.USED.getOrCreateStat(this));
            return ItemUsage.consumeHeldItem(world, user, hand);
        } else {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }
    }
}
