package com.acuteterror233.block.data;
/*
* 此文件是根据MIT许可证 (MIT) 许可的RebornCore的一部分。
*
* 版权所有 (c) 2024 TeamReborn
*
* 特此免费向任何获得副本的人授予许可
* 本软件和相关文档文件 (“软件”)，以处理
* 在软件中不受限制，包括但不限于权利
* 使用、复制、修改、合并、发布、分发、再许可和/或销售
* 软件的副本，并允许接受软件的人员
* 提供这样做，但须符合以下条件:
*
* 上述版权声明和本许可通知应包含在所有
* 软件的副本或实质性部分。
*
* 该软件是 “按原样” 提供的，没有任何形式的保证，明示或
* 默示，包括但不限于适销性的保证，
* 适合特定目的且不侵权。在任何情况下，
* 作者或版权所有者对任何索赔、损害或其他
* 责任，无论是在合同、侵权或其他诉讼中，
* 与本软件或本软件的使用或其他交易无关或与之相关
* 软件。
*/



import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.function.Predicate;

public interface BlockPosPayload {
    BlockPos pos();

    default boolean isWithinDistance(PlayerEntity player,double distance){
        return player.getBlockPos().isWithinDistance(pos(), distance);
    }

    default boolean canUse(ServerPlayerEntity player, Predicate<ScreenHandler> screenHandlerPredicate) {
        ScreenHandler currentScreenHandler = player.currentScreenHandler;

        if (currentScreenHandler == null) {
            return false;
        }
        if (!screenHandlerPredicate.test(currentScreenHandler)) {
            return false;
        }
        return currentScreenHandler.canUse(player);
    }

    default <T extends BlockEntity> T getBlockEntity(BlockEntityType<T> type, PlayerEntity player) {
        if (!isWithinDistance(player, 64)) {
            throw new IllegalStateException("Player cannot use this block entity as its too far away");
        }
        BlockEntity blockEntity = getBlockEntity(player);
        if (type != blockEntity.getType()) {
            throw new IllegalStateException("Block entity is not of the correct type. Expected: " +
                    Registries.BLOCK_ENTITY_TYPE.getId(type) + " but got: " + Registries.BLOCK_ENTITY_TYPE.getId(blockEntity.getType()));
        }
        return (T) blockEntity;
    }

    default <T extends BlockEntity> T getBlockEntity(Class<T> baseClass, PlayerEntity player) {
        if (!isWithinDistance(player, 64)) {
            throw new IllegalStateException("Player cannot use this block entity as its too far away");
        }

        BlockEntity blockEntity = getBlockEntity(player);

        if (!baseClass.isInstance(blockEntity)) {
            throw new IllegalStateException("Block entity is not of the correct class");
        }

        //noinspection unchecked
        return (T) blockEntity;
    }

    default BlockEntity getBlockEntity(PlayerEntity player) {
        if (!isWithinDistance(player, 64)) {
            throw new IllegalStateException("Player cannot use this block entity as its too far away");
        }

        BlockEntity blockEntity = player.getWorld().getBlockEntity(pos());

        if (blockEntity == null) {
            throw new IllegalStateException("Block entity is null");
        }

        return blockEntity;
    }
}