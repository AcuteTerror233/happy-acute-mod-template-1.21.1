package com.acuteterror233.Item.cs;

import com.acuteterror233.HappyAcuteMod;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class CsItem extends Item {
    public CsItem(Settings settings) {
        super(settings);
    }

@Override
public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
    if (!world.isClient) { // 确保逻辑在客户端执行
        MinecraftServer server = user.getServer();
        if (server != null) {
            List<ServerPlayerEntity> playerList = server.getPlayerManager().getPlayerList();
            List<String> playerNameList = new ArrayList<>();
            if (!playerList.isEmpty()) {
                for (ServerPlayerEntity player : playerList) {
                    playerNameList.add(player.getName().getString());
                    HappyAcuteMod.LOGGER.info(player.getName().getString());
                }
            }
            for (ServerPlayerEntity player : playerList) {
                ServerPlayNetworking.send(player, new PlayerListPacket(playerNameList));
            }
            HappyAcuteMod.LOGGER.info("发包了,内容是:" + playerNameList.toString());
        }
    }
    return TypedActionResult.success(user.getStackInHand(hand));
    }
}

