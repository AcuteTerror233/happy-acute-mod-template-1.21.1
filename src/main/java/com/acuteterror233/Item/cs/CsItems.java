package com.acuteterror233.Item.cs;

import com.acuteterror233.HappyAcuteMod;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class CsItems {
    public static final Item CS =  registerItems("cs",new CsItem(new CsItem.Settings()));

    public static CsItem registerItems(String name, CsItem csitem) {
        return Registry.register(Registries.ITEM, Identifier.of(HappyAcuteMod.MOD_ID, name), csitem);
    }
    public static void registerCsItems(){
        HappyAcuteMod.LOGGER.info("测试");
    }
}