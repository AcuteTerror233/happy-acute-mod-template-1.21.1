package com.acuteterror233.block.Screen;

import com.acuteterror233.HappyAcuteMod;
import com.acuteterror233.Item.gui.ModItemScreenHandler;
import com.acuteterror233.block.data.ModBlockData;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModBlockScreenHandlerRegister {
    public static final ScreenHandlerType<ModBlockScreenHandler> BLOCK_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(HappyAcuteMod.MOD_ID, "block_screen"),
                    new ExtendedScreenHandlerType<>(ModBlockScreenHandler::new, ModBlockData.CODEC));
    public static void registerScreenHandlers() {

    }
}
