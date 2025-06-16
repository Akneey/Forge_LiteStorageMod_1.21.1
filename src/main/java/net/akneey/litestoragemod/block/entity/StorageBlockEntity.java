package net.akneey.litestoragemod.block.entity;

import net.akneey.litestoragemod.menu.StorageMenu;
import net.akneey.litestoragemod.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class StorageBlockEntity extends BlockEntity implements MenuProvider {

    public  StorageBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.STORAGE_BLOCK_ENTITY.get(), pos,state);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Storage Block");
    }

    @Override
    public AbstractContainerMenu createMenu(int windowsId, Inventory inv, Player player) {
        return new StorageMenu(windowsId, inv, this); // Ваш контейнер
    }
}
