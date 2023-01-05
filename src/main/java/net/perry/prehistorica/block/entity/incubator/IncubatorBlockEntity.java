package net.perry.prehistorica.block.entity.incubator;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.perry.prehistorica.block.entity.ImplementedInventory;
import net.perry.prehistorica.recipe.IncubatorRecipe;
import net.perry.prehistorica.register.ModBlocksEntities;
import net.perry.prehistorica.screen.incubator.IncubatorScreenHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class IncubatorBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

    public IncubatorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocksEntities.INCUBATOR, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch(index) {
                    case 0: return IncubatorBlockEntity.this.progress;
                    case 1: return IncubatorBlockEntity.this.maxProgress;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: IncubatorBlockEntity.this.progress = value; break;
                    case 1: IncubatorBlockEntity.this.maxProgress = value; break;
                }
            }

            public int size() {
                return 2;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Incubator");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new IncubatorScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("incubator.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        progress = nbt.getInt("incubator.progress");
    }

    private void resetProgress() {
        this.progress = 0;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {
        return side != Direction.DOWN && slot < 2;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, @Nullable Direction side) {
        return side == Direction.DOWN && slot > 1;
    }

    public static void tick(World world, BlockPos blockPos, BlockState state, IncubatorBlockEntity entity) {
        if(world.isClient()) {
            return;
        }

        if(hasRecipe(entity)) {
            entity.progress++;
            markDirty(world, blockPos, state);
            if(entity.progress >= entity.maxProgress) {
                craftItem(entity);
            }
        } else {
            entity.resetProgress();
            markDirty(world, blockPos, state);
        }
    }

    private static void craftItem(IncubatorBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<IncubatorRecipe> recipe = entity.getWorld().getRecipeManager()
                .getFirstMatch(IncubatorRecipe.Type.INSTANCE, inventory, entity.getWorld());

        if(hasRecipe(entity)) {
            ItemStack recipeRemainder0 = new ItemStack(entity.getStack(0).getItem().getRecipeRemainder());
            entity.removeStack(0, 1);
            if(entity.getStack(1).isEmpty())
                entity.setStack(0, recipeRemainder0);
            else
                entity.getWorld().spawnEntity(new ItemEntity(entity.getWorld(), entity.getPos().getX(),
                        entity.getPos().getY() + 1, entity.getPos().getZ(), recipeRemainder0));

            ItemStack recipeRemainder1 = new ItemStack(entity.getStack(1).getItem().getRecipeRemainder());
            entity.removeStack(1, 1);
            if(entity.getStack(1).isEmpty())
                entity.setStack(1, recipeRemainder1);
            else
                entity.getWorld().spawnEntity(new ItemEntity(entity.getWorld(), entity.getPos().getX(),
                        entity.getPos().getY() + 1, entity.getPos().getZ(), recipeRemainder1));

            entity.setStack(2, new ItemStack(recipe.get().getOutput().getItem(),
                    entity.getStack(2).getCount() + 1));

            entity.resetProgress();
        }
    }

    private static boolean hasRecipe(IncubatorBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for(int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<IncubatorRecipe> match = entity.getWorld().getRecipeManager()
                .getFirstMatch(IncubatorRecipe.Type.INSTANCE, inventory, entity.getWorld());

        return match.isPresent() && canInsertItemIntoOutputSlot(inventory);
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(2).isEmpty();
    }
}