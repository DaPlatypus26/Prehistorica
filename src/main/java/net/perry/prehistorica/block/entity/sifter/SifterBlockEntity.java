package net.perry.prehistorica.block.entity.sifter;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntryList;
import net.minecraft.world.World;
import net.perry.prehistorica.block.entity.ImplementedInventory;
import net.perry.prehistorica.recipe.SifterRecipe;
import net.perry.prehistorica.register.ModBlocksEntities;
import net.perry.prehistorica.register.ModTags;
import net.perry.prehistorica.screen.sifter.SifterScreenHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class SifterBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(5, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

    public SifterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocksEntities.SIFTER, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch(index) {
                    case 0: return SifterBlockEntity.this.progress;
                    case 1: return SifterBlockEntity.this.maxProgress;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: SifterBlockEntity.this.progress = value; break;
                    case 1: SifterBlockEntity.this.maxProgress = value; break;
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
        return Text.literal("Sifter");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new SifterScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("sifter.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        progress = nbt.getInt("sifter.progress");
    }

    private void resetProgress() {
        this.progress = 0;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {
        return side != Direction.DOWN && slot < 1;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, @Nullable Direction side) {
        return side == Direction.DOWN && slot > 0;
    }

    public static void tick(World world, BlockPos blockPos, BlockState state, SifterBlockEntity entity) {
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

    private static void craftItem(SifterBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<SifterRecipe> recipe = entity.getWorld().getRecipeManager()
                .getFirstMatch(SifterRecipe.Type.INSTANCE, inventory, entity.getWorld());

        if(hasRecipe(entity)) {
            entity.removeStack(0, 1);

            //TagLists for output
            Optional<RegistryEntryList.Named<Item>> dirtSifterOutputTagList = Registry.ITEM.getEntryList(ModTags.Items.DIRT_SIFTER_OUTPUT);
            Optional<RegistryEntryList.Named<Item>> coarseDirtSifterOutputTagList = Registry.ITEM.getEntryList(ModTags.Items.COARSE_DIRT_SIFTER_OUTPUT);
            Optional<RegistryEntryList.Named<Item>> sandSifterOutputTagList = Registry.ITEM.getEntryList(ModTags.Items.SAND_SIFTER_OUTPUT);
            Optional<RegistryEntryList.Named<Item>> redSandSifterOutputTagList = Registry.ITEM.getEntryList(ModTags.Items.RED_SAND_SIFTER_OUTPUT);
            Optional<RegistryEntryList.Named<Item>> gravelSifterOutputTagList = Registry.ITEM.getEntryList(ModTags.Items.GRAVEL_SIFTER_OUTPUT);

            Item output;
            String recipeName = recipe.get().getOutput().getItem().toString() + "_sifter_output.json";

            if(recipeName.equals("dirt_sifter_output.json"))
                output = dirtSifterOutputTagList.flatMap(list -> list.getRandom(Random.create())).get().value();
            else if(recipeName.equals("coarse_dirt_sifter_output.json"))
                output = coarseDirtSifterOutputTagList.flatMap(list -> list.getRandom(Random.create())).get().value();
            else if(recipeName.equals("sand_sifter_output.json"))
                output = sandSifterOutputTagList.flatMap(list -> list.getRandom(Random.create())).get().value();
            else if(recipeName.equals("red_sand_sifter_output.json"))
                output = redSandSifterOutputTagList.flatMap(list -> list.getRandom(Random.create())).get().value();
            else if(recipeName.equals("gravel_sifter_output.json"))
                output = gravelSifterOutputTagList.flatMap(list -> list.getRandom(Random.create())).get().value();
            else
                output = Items.AIR;

            if(entity.getStack(1).isEmpty())
                entity.setStack(1, new ItemStack(output, 1));
            else if(entity.getStack(2).isEmpty())
                entity.setStack(2, new ItemStack(output, 1));
            else if(entity.getStack(3).isEmpty())
                entity.setStack(3, new ItemStack(output, 1));
            else if(entity.getStack(4).isEmpty())
                entity.setStack(4, new ItemStack(output, 1));

            entity.resetProgress();
        }
    }

    private static boolean hasRecipe(SifterBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for(int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<SifterRecipe> match = entity.getWorld().getRecipeManager()
                .getFirstMatch(SifterRecipe.Type.INSTANCE, inventory, entity.getWorld());

        return match.isPresent() && canInsertItemIntoOutputSlot(inventory);
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(1).isEmpty() ||
                inventory.getStack(2).isEmpty() ||
                inventory.getStack(3).isEmpty() ||
                inventory.getStack(4).isEmpty();
    }
}