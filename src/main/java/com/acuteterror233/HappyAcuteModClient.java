package com.acuteterror233;


import com.acuteterror233.Item.cs.ItemScreen;
import com.acuteterror233.Item.cs.PlayerListPacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.util.List;

public class HappyAcuteModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        PayloadTypeRegistry.playS2C().register(PlayerListPacket.ID, PlayerListPacket.CODEC);
        ClientPlayNetworking.registerGlobalReceiver(PlayerListPacket.ID, (payload, context) -> {
            context.client().execute(() -> {
                MinecraftClient client = context.client();
                client.setScreen(new ItemScreen(Text.of("测试"), payload.PlayerList()));
                HappyAcuteMod.LOGGER.info("客户端收到包了,包内容是:" + payload.toString());
            });
        });
    }
}