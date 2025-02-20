package com.acuteterror233.compoennt;

import com.acuteterror233.HappyAcuteMod;
import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModComponents {
    public static final ComponentType<Integer> POWER = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(HappyAcuteMod.MOD_ID, "power"),
            ComponentType.<Integer>builder().codec(Codec.INT).build()
    );
    public static void registerComponents(){
        HappyAcuteMod.LOGGER.info("注册组件");
    }
}
