package com.acuteterror233.Item.cs;

import com.acuteterror233.HappyAcuteMod;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

/**
 * NamePayload类用于表示带有方块位置的名称负载
 * 它实现了CustomPayload接口，用于在网络中传输自定义数据
 * 该类的主要作用是封装方块的位置信息，以便在网络中进行传输
 */
public record NamePayload(BlockPos blockPos) implements CustomPayload {
//     NamePayload的唯一标识符
    public static final Id<NamePayload> ID = new CustomPayload.Id<>(Identifier.of(HappyAcuteMod.MOD_ID, "network_id"));
//     NamePayload的包编码解码器
    public static final PacketCodec<PacketByteBuf, NamePayload> CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC, NamePayload::blockPos, NamePayload::new);

    /**
     * 获取NamePayload的唯一标识符
     * @return NamePayload的唯一标识符
     */
    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}

