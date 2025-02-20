package com.acuteterror233.block.data;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.util.math.BlockPos;

public record ModBlockData(BlockPos pos) implements BlockPosPayload{
    public static final PacketCodec<RegistryByteBuf, ModBlockData> CODEC=
            PacketCodec.tuple(BlockPos.PACKET_CODEC, ModBlockData::pos, ModBlockData::new);
}