package net.perry.prehistorica.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntryList;
import net.minecraft.world.World;
import net.perry.prehistorica.recipe.AnalyzerRecipe;
import net.perry.prehistorica.register.ModBlocksEntities;
import net.perry.prehistorica.register.ModItems;
import net.perry.prehistorica.register.ModTags;
import net.perry.prehistorica.screen.analyzer.AnalyzerScreenHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class AnalyzerBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(18, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

    public AnalyzerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocksEntities.ANALYZER, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch(index) {
                    case 0: return AnalyzerBlockEntity.this.progress;
                    case 1: return AnalyzerBlockEntity.this.maxProgress;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: AnalyzerBlockEntity.this.progress = value; break;
                    case 1: AnalyzerBlockEntity.this.maxProgress = value; break;
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
        return Text.literal("Analyzer");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new AnalyzerScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("gem_infusing_station.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        progress = nbt.getInt("gem_infusing_station.progress");
    }

    private void resetProgress() {
        this.progress = 0;
    }

    public static void tick(World world, BlockPos blockPos, BlockState state, AnalyzerBlockEntity entity) {
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

    private static void craftItem(AnalyzerBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for(int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        if(hasRecipe(entity)) {
            entity.removeStack(0, 1);

            Optional<RegistryEntryList.Named<Item>> tagList = Registry.ITEM.getEntryList(ModTags.Items.ANALYZER_OUTPUT);
            Item output = tagList.flatMap(list -> list.getRandom(Random.create())).get().value();

            if(entity.getStack(9).isEmpty())
                entity.setStack(9, new ItemStack(output, 1));
            else if(entity.getStack(10).isEmpty())
                entity.setStack(10, new ItemStack(output, 1));
            else if(entity.getStack(11).isEmpty())
                entity.setStack(11, new ItemStack(output, 1));
            else if(entity.getStack(12).isEmpty())
                entity.setStack(12, new ItemStack(output, 1));
            else if(entity.getStack(13).isEmpty())
                entity.setStack(13, new ItemStack(output, 1));
            else if(entity.getStack(14).isEmpty())
                entity.setStack(14, new ItemStack(output, 1));
            else if(entity.getStack(15).isEmpty())
                entity.setStack(15, new ItemStack(output, 1));
            else if(entity.getStack(16).isEmpty())
                entity.setStack(16, new ItemStack(output, 1));
            else if(entity.getStack(17).isEmpty())
                entity.setStack(17, new ItemStack(output, 1));

            entity.resetProgress();
        }
    }

    private static boolean hasRecipe(AnalyzerBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for(int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<AnalyzerRecipe> match = entity.getWorld().getRecipeManager()
                .getFirstMatch(AnalyzerRecipe.Type.INSTANCE, inventory, entity.getWorld());

        return match.isPresent() && canInsertItemIntoOutputSlot(inventory);
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(9).isEmpty() ||
                inventory.getStack(10).isEmpty() ||
                inventory.getStack(11).isEmpty() ||
                inventory.getStack(12).isEmpty() ||
                inventory.getStack(13).isEmpty() ||
                inventory.getStack(14).isEmpty() ||
                inventory.getStack(15).isEmpty() ||
                inventory.getStack(16).isEmpty() ||
                inventory.getStack(17).isEmpty();
    }
}