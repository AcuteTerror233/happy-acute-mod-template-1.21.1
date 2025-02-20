package com.acuteterror233.Item;

import com.acuteterror233.HappyAcuteMod;
import com.acuteterror233.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    private static RegistryKey<ItemGroup> registry(String id){
        return RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(HappyAcuteMod.MOD_ID, id));
    }

    public static final RegistryKey<ItemGroup> HAPPY_ACUTE_MOD_GROUP = registry("happy_acute_mod_group");


    public static void registerModItemGroups(){
        HappyAcuteMod.LOGGER.info("注册物品组");
        Registry.register(Registries.ITEM_GROUP, HAPPY_ACUTE_MOD_GROUP,
                ItemGroup.create(ItemGroup.Row.TOP, 0)
                        .displayName(Text.translatable("itemGroup.happy_acute_mod_group"))
                                .icon(() -> new ItemStack(ModItems.CALL_MACHINE))
                        .entries((displayContext, entries) -> {
                            entries.add(ModItems.CALL_MACHINE);
                            entries.add(ModItems.COMMUNICATOR);
                            entries.add(ModItems.TELEPORT_CORE);
                            entries.add(ModItems.CASE);
                            entries.add(ModBlocks.CHARGING_STATION);
                        }).build());
    }
}
