package com.acuteterror233.Item;

import com.acuteterror233.HappyAcuteMod;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModItems {


    public static final Item CALL_MACHINE = registerItems("call_machine", new Item(new Item.Settings()));

    public static final Item CASE = registerItems("case",new Item(new Item.Settings()));

    public static final Item COMMUNICATOR = registerItems("communicator",new Item(new Item.Settings()));

    public static final Item TELEPORT_CORE = registerItems("teleport_core",new Item(new Item.Settings()));

    private static Item registerItems(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(HappyAcuteMod.MOD_ID, name), item);
    }

    public static void registerModItems(){
        HappyAcuteMod.LOGGER.info("注册物品类");
    }
}
