package com.acuteterror233.Item.cs;

import com.acuteterror233.HappyAcuteMod;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public record PlayerListPacket(List<String> PlayerList) implements CustomPayload {
    public static final Id<PlayerListPacket> ID = new CustomPayload.Id<>(Identifier.of(HappyAcuteMod.MOD_ID, "player"));
    /**
     * 定义一个用于编解码PlayerListPacket的PacketCodec
     * 该codec用于将PlayerListPacket编码到PacketByteBuf，或将PacketByteBuf解码为PlayerListPacket
     */
    public static final PacketCodec<PacketByteBuf, PlayerListPacket> CODEC = PacketCodec.of(
            // 编码函数，将PlayerListPacket中的玩家列表写入PacketByteBuf
            (packet, buf) -> {
                buf.writeInt(packet.PlayerList.size()); // 写入玩家数量
                for (String s : packet.PlayerList) {
                    buf.writeString(s);
                }
            },
            // 解码函数，从PacketByteBuf中读取数据并构建一个新的PlayerListPacket
            buf -> {
                int size = buf.readInt(); // 读取玩家数量
                List<String> playerNameList = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    playerNameList.add(buf.readString()); // 读取每个玩家的名字
                }
                return new PlayerListPacket(playerNameList);
            }
    );
    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
