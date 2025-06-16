package net.akneey.litestoragemod.registry;

import net.akneey.litestoragemod.LiteStorageMod;
import net.akneey.litestoragemod.block.ModBlocks;
import net.akneey.litestoragemod.block.entity.StorageBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BlOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, LiteStorageMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<StorageBlockEntity>> STORAGE_BLOCK_ENTITY =
            BlOCK_ENTITIES.register("storage_block_entity", () ->
                    BlockEntityType.Builder.of(StorageBlockEntity::new,
                            ModBlocks.STORAGE_BLOCK.get()).build(null));

    public static void register() {
        BlOCK_ENTITIES.register(net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext.get().getModEventBus());
    }
}
