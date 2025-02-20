package com.acuteterror233.datagen;

import com.acuteterror233.Item.ModItemGroups;
import com.acuteterror233.Item.ModItems;
import com.acuteterror233.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
//import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

public class ModZHCNLanProvider extends FabricLanguageProvider {
    public ModZHCNLanProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "zh_cn" ,registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItemGroups.HAPPY_ACUTE_MOD_GROUP, "呼叫机mod");
        translationBuilder.add(ModItems.CALL_MACHINE.getTranslationKey(), "呼叫机");
        translationBuilder.add(ModItems.COMMUNICATOR.getTranslationKey(), "通信器");
        translationBuilder.add(ModItems.TELEPORT_CORE.getTranslationKey(), "传送核心");
        translationBuilder.add(ModItems.CASE.getTranslationKey(), "机壳");
        translationBuilder.add(ModBlocks.CHARGING_STATION.getTranslationKey(), "充电桩");
        translationBuilder.add("item.happy_acute_mod.call_machine.info.shift","当前电量是:\u00A76%1$s\u00A7r");
        translationBuilder.add("item.happy_acute_mod.call_machine.info","按下\u00A76SHIFT\u00A7r获取更多信息");
    }
}
