package com.acuteterror233.Item.cs;

import com.acuteterror233.HappyAcuteMod;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record PlayerListPacket() implements CustomPayload {
    public static final Id<PlayerListPacket> ID = new CustomPayload.Id<>(Identifier.of(HappyAcuteMod.MOD_ID,"player_list_packet_id"))

    public static final PacketCodec<PacketByteBuf, PlayerListPacket> CODEC = PacketCodec.of()

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
