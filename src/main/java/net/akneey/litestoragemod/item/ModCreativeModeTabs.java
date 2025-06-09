package net.akneey.litestoragemod.item;

import net.akneey.litestoragemod.LiteStorageMod;
import net.akneey.litestoragemod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LiteStorageMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> DIGICRYSTAL_ITEMS_TAB = CREATIVE_MODE_TABS.register("digicrystal_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.DIGICRYSTAL.get()))
                    .title(Component.translatable("creativetab.litestoragemod.digicrystal_items"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.DIGICRYSTAL.get());

                    }).build());


    public static final RegistryObject<CreativeModeTab> DIGICRYSTAL_BLOCKS_TAB = CREATIVE_MODE_TABS.register("digicrystal_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.STORAGE_BLOCK.get()))
                    .withTabsBefore(DIGICRYSTAL_ITEMS_TAB.getId())
                    .title(Component.translatable("creativetab.litestoragemod.digicrystal_blocks"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.STORAGE_BLOCK.get());
                        pOutput.accept(ModBlocks.PIPE_BLOCK.get());

                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
