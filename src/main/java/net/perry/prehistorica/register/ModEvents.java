package net.perry.prehistorica.register;

import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntryList;

import java.util.Optional;

public class ModEvents {
    private static void fossilWashEvent() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            BlockPos pos = ((BlockHitResult) hitResult).getBlockPos();
            if(world.getBlockState(pos).isOf(Blocks.WATER_CAULDRON) && player.getStackInHand(hand).isOf(ModItems.FOSSIL)) {
                int count = player.getStackInHand(hand).getCount();

                Optional<RegistryEntryList.Named<Item>> fossilCauldronOutputTagList = Registry.ITEM.getEntryList(ModTags.Items.FOSSIL_CAULDRON_OUTPUT);
                Item item = fossilCauldronOutputTagList.flatMap(list -> list.getRandom(Random.create())).get().value();
                ItemStack output = new ItemStack(item, 1);

                player.getStackInHand(hand).getItem().use(world, player, hand);
                player.getStackInHand(hand).setCount(count - 1);

                boolean empty = false;
                for(int i = 0; i < 35; i++) {
                    if(player.getInventory().getStack(i).isEmpty())
                        empty = true;
                }
                if(empty) {
                    player.giveItemStack(output);
                } else if(player.getOffHandStack().isEmpty()) {
                    player.equipStack(EquipmentSlot.OFFHAND, output);
                } else {
                    world.spawnEntity(new ItemEntity(world, player.getPos().getX(),
                            player.getPos().getY() + 1, player.getPos().getZ(), output));
                }
            }

            return ActionResult.PASS;
        });
    }

    public static void registerModEvents() {
        fossilWashEvent();
    }
}
