package net.akneey.litestoragemod.menu;

import net.akneey.litestoragemod.block.entity.StorageBlockEntity;
import net.akneey.litestoragemod.registry.ModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class StorageMenu extends AbstractContainerMenu {

    private final StorageBlockEntity blockEntity;

    public StorageMenu(int containerId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(containerId, playerInventory,
                (StorageBlockEntity) playerInventory.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public StorageMenu(int containerId, Inventory playerInventory, StorageBlockEntity blockEntity) {
        super(ModMenus.STORAGE_MENU.get(), containerId);
        this.blockEntity = blockEntity;

        // В будущем: добавить отображение подключённых предметов

        // Добавим инвентарь игрока
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9,
                        8 + col * 18, 84 + row * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return blockEntity != null && player.distanceToSqr(blockEntity.getBlockPos().getCenter()) < 64.0D;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        // Перетаскивание между слотами — пока выключено
        return ItemStack.EMPTY;
    }

    public StorageBlockEntity getBlockEntity() {
        return blockEntity;
    }
}
