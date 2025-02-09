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

    public static final Block CHARGING_STATION = registerBlocks("charging_station", new Block(AbstractBlock.Settings.create().strength(5f, 10f).sounds(BlockSoundGroup.METAL)));
    private static Block registerBlocks(String id, Block block) {
        registerBlockItems(id, block);
        return Registry.register(Registries.BLOCK, Identifier.of(HappyAcuteMod.MOD_ID, id), block);
    }
    public static void registerBlockItems(String id, Block block) {
        Item item = Registry.register(Registries.ITEM, Identifier.of(HappyAcuteMod.MOD_ID, id), new BlockItem(block, new Item.Settings()));
        if (item instanceof BlockItem) {
            ((BlockItem)item).appendBlocks(Item.BLOCK_ITEMS, item);
        }
    }

    public static void registerBlockItems(){
        HappyAcuteMod.LOGGER.info("注册方块类");
    }
}
