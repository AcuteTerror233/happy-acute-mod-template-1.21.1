package com.acuteterror233.Item.gui;

import com.acuteterror233.HappyAcuteMod;
import com.acuteterror233.Item.data.ModItemData;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModItemScreenHandlerRegister {
    public static final ScreenHandlerType<ModItemScreenHandler> MOD_SCREEN_HANDLER =
        Registry.register(Registries.SCREEN_HANDLER, Identifier.of(HappyAcuteMod.MOD_ID, "polishing_machine"),
                new ExtendedScreenHandlerType<>(ModItemScreenHandler::new, ModItemData.CODEC));
    public static void registerScreenHandlers() {

    }
}
