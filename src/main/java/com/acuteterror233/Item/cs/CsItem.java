package com.acuteterror233.Item.cs;

import com.acuteterror233.HappyAcuteMod;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CsItem extends Item {
    public CsItem(Settings settings) {
        super(settings);
    }

    @Override
    /**
     * 重写物品使用方法，主要用于在服务器端执行自定义逻辑
     * 当世界不是客户端时，执行自定义的服务器端逻辑
     *
     * @param world 当前世界对象，用于访问世界数据
     * @param user 使用物品的玩家对象，用于获取玩家相关信息
     * @param hand 指明玩家使用的哪只手，主手或副手
     * @return 返回物品使用结果，指示物品是否成功使用
     */
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient()) {
            MinecraftClient client = MinecraftClient.getInstance();
            return super.use(world, user, hand);
        }else {
        BlockPos blockPos = user.getBlockPos();
        ServerPlayerEntity serverPlayer = (ServerPlayerEntity) user;
        MinecraftServer server = serverPlayer.getServer();
        // 发送数据包，实现自定义网络通信
        ServerPlayNetworking.send(serverPlayer,new NamePayload(server) );
        // 返回成功使用物品的结果，以反馈给玩家
//        client.setScreen(new ModItemScreen(Text.empty()));
        if (server != null) {
            HappyAcuteMod.LOGGER.info(server.getPlayerManager().getPlayerList().toString());
        }
        return TypedActionResult.success(user.getHandItems().iterator().next());
        }
    }
}
