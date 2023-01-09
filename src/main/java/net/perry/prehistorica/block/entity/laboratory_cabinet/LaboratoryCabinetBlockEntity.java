package net.perry.prehistorica.block.entity.laboratory_cabinet;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
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
import net.perry.prehistorica.register.ModBlocksEntities;
import net.perry.prehistorica.screen.laboratory_cabinet.LaboratoryCabinetScreenHandler;
import org.jetbrains.annotations.Nullable;

public class LaboratoryCabinetBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(9, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;

    public LaboratoryCabinetBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocksEntities.LABORATORY_CABINET, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                return 0;
            }

            public void set(int index, int value) { }

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
        return Text.literal("Laboratory Cabinet");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new LaboratoryCabinetScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {
        return side != Direction.DOWN;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, @Nullable Direction side) {
        return side == Direction.DOWN;
    }

    public static void tick(World world, BlockPos blockPos, BlockState state, LaboratoryCabinetBlockEntity entity) {
        if(world.isClient()) {
            return;
        }
    }
}