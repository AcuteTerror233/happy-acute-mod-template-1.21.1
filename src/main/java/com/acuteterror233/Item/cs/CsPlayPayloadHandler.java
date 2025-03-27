package com.acuteterror233.Item.cs;

import com.acuteterror233.Item.gui.ModItemScreen;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class CsPlayPayloadHandler implements ClientPlayNetworking.PlayPayloadHandler<NamePayload> {
    @Override
    public void receive(NamePayload namePayload, ClientPlayNetworking.Context context) {
//        MinecraftClient.getInstance().execute(() -> {
            MinecraftClient client = MinecraftClient.getInstance();
            client.setScreen(new ModItemScreen(Text.empty()));
//        });
    }
}
