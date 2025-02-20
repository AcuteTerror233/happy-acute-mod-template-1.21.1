package com.acuteterror233.Item.data;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.util.math.BlockPos;

public record ModItemData(BlockPos pos) implements BlockPosPayload {
    public static final PacketCodec<RegistryByteBuf, ModItemData> CODEC=
            PacketCodec.tuple(BlockPos.PACKET_CODEC, ModItemData::pos, ModItemData::new);
}