package com.acuteterror233;

import com.acuteterror233.block.ModBlocks;
import com.acuteterror233.block.Screen.ModBlockScreen;
import com.acuteterror233.block.Screen.ModBlockScreenHandler;
import com.acuteterror233.block.Screen.ModBlockScreenHandlerRegister;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;

public class HappyAcuteModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModBlockScreenHandlerRegister.BLOCK_SCREEN_HANDLER, ModBlockScreen::new);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CHARGING_STATION, RenderLayer.getCutout());
    }
}
