package com.acuteterror233;

import com.acuteterror233.Item.cs.CsPlayPayloadHandler;
import com.acuteterror233.Item.cs.NamePayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class HappyAcuteModClient implements ClientModInitializer {
    private static void receive(NamePayload payload, ClientPlayNetworking.Context context) {
        // 在客户端线程中执行接收处理逻辑
//        MinecraftClient.getInstance().execute(() -> {
//            HappyAcuteMod.LOGGER.info(NamePayload.CODEC.toString());
//        });
        MinecraftClient.getInstance().execute(() -> {
            // 获取玩家实体
            PlayerEntity player = MinecraftClient.getInstance().player;
            if (player != null) {
                // 将玩家传送到指定坐标
                System.out.println("执行到if后了");
                BlockPos blockPos = payload.blockPos();
                player.teleport(blockPos.getX() + 10 , blockPos.getY(), blockPos.getZ(),true);
            }
        });
    }

    @Override
    public void onInitializeClient() {
        // 在服务端注册名称载荷的类型
        PayloadTypeRegistry.playS2C().register(NamePayload.ID, NamePayload.CODEC);
        // 注册一个全局接收器，用于处理特定ID的消息
        // 这里的NamePayload.ID是指消息的唯一标识符
        // HappyAcuteModClient::receive是指当接收到该消息时所调用的方法
        ClientPlayNetworking.registerGlobalReceiver(NamePayload.ID, new CsPlayPayloadHandler());
    }
}