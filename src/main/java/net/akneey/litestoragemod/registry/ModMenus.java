package net.akneey.litestoragemod.registry;

import net.akneey.litestoragemod.LiteStorageMod;
import net.akneey.litestoragemod.menu.StorageMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, LiteStorageMod.MOD_ID);

    public static final RegistryObject<MenuType<StorageMenu>> STORAGE_MENU =
            MENUS.register("storage_menu",
                    () -> IForgeMenuType.create(StorageMenu::new));

    public static void register() {
        MENUS.register(net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext.get().getModEventBus());
    }
}
