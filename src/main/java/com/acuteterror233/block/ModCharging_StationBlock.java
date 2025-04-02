package com.acuteterror233.block;

import com.acuteterror233.HappyAcuteMod;
import com.acuteterror233.block.entity.ModBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ModCharging_StationBlock extends BlockWithEntity implements BlockEntityProvider {
    // 创建一个MapCodec实例，用于序列化和反序列化ModCharging_StationBlock块。
    public static final MapCodec<ModCharging_StationBlock> CODEC = createCodec(ModCharging_StationBlock::new);
    // 定义一个DirectionProperty属性，用于表示块的朝向。
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    /**
     * ModCharging_StationBlock块的构造函数。
     * @param settings 块的设置。
     */
    public ModCharging_StationBlock(Settings settings) {
        super(settings);
        // 将块的默认状态设置为朝北。
        setDefaultState(getStateManager().getDefaultState().with(FACING, Direction.NORTH));
    }

    /**
     * 获取块的序列化编解码器。
     * @return 块的MapCodec编解码器。
     */
    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    /**
     * 向块的状态管理器添加属性。
     * @param builder 状态管理器的构建器。
     *
     */
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        // Add the facing direction property to the builder.
        builder.add(FACING);
    }
    /**
     * 获取块放置时的状态。
     * @param ctx 放置上下文，包含放置块的环境信息。
     * @return 放置时的块状态。
     */
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        // 获取玩家的水平朝向（忽略上下方向）
        Direction facing = ctx.getHorizontalPlayerFacing();
        // 根据玩家朝向设置方块朝向，并返回该状态的方块
        return getDefaultState().with(FACING, facing);
    }

    /**
     * 注册ModCharging_StationBlock块。
     * @param id 块的标识符。
     * @param block 要注册的块实例。
     * @return 注册后的块实例。
     */
    public static ModCharging_StationBlock registerBlocks(String id, ModCharging_StationBlock block) {
        // 先注册块对应的物品。
        registerBlockItems(id, block);
        // 然后注册块本身。
        return Registry.register(Registries.BLOCK, Identifier.of(HappyAcuteMod.MOD_ID, id), block);
    }

    /**
     * 注册ModCharging_StationBlock块对应的物品。
     * @param id 块物品的标识符。
     * @param block 对应的块实例。
     */
    public static void registerBlockItems(String id, ModCharging_StationBlock block) {
        // 创建并注册块物品。
        Item item = Registry.register(Registries.ITEM, Identifier.of(HappyAcuteMod.MOD_ID, id), new BlockItem(block, new Item.Settings()));
        // 如果注册的物品是BlockItem类型，则将其添加到BlockItem的列表中。
        if (item instanceof BlockItem) {
            ((BlockItem)item).appendBlocks(Item.BLOCK_ITEMS, item);
        }
    }
    /**
     * 创建块实体。
     * @param pos 块的位置。
     * @param state 块的状态。
     * @return 对应的块实体实例，如果没有块实体则返回null。
     */
    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ModBlockEntity(pos, state);
    }
    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    /**
     * 当玩家使用方块时触发此方法
     *
     * @param state 方块的状态
     * @param world 世界对象
     * @param pos 方块的位置
     * @param player 使用方块的玩家
     * @param hit 方块被击中的信息
     * @return 使用方块的结果
     */
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        // 仅在服务器端执行打开屏幕操作
        if (!world.isClient()){
            // 获取方块实体并尝试转换为自定义的ModBlockEntity
            NamedScreenHandlerFactory screenHandlerFactory = (ModBlockEntity) world.getBlockEntity(pos);
            // 如果转换成功，则为玩家打开自定义的屏幕界面
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        // 返回成功结果，表示方块使用成功
        return ActionResult.SUCCESS;
    }
}
