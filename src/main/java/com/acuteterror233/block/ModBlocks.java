package com.acuteterror233.block;

import com.acuteterror233.HappyAcuteMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

    // 定义一个名为CHARGING_STATION的静态常量Block，表示充电站方块
    public static final Block CHARGING_STATION = ModCharging_StationBlock.registerBlocks
            (
                "charging_station", // 方块的注册名称
                new ModCharging_StationBlock(
                        AbstractBlock
                                .Settings
                                .create()
                                .strength(5f, 10f) // 设置方块的硬度和抗爆性
                                .sounds(BlockSoundGroup.METAL) // 设置方块的声音类型
                                .emissiveLighting(((state, world, pos) -> true)) // 设置方块是否发光
                                .luminance((state) -> 5) // 设置方块的亮度
                )
            );
    public static void registerBlockItems(){
        HappyAcuteMod.LOGGER.info("注册方块类");
    }
}
