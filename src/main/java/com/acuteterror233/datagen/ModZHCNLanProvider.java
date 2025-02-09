package com.acuteterror233.datagen;

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
        translationBuilder.add("itemGroup.happy_acute_mod_group", "呼叫机mod");
        translationBuilder.add("item.happy_acute_mod.call_machine", "呼叫机");
        translationBuilder.add("item.happy_acute_mod.communicator", "通信器");
        translationBuilder.add("item.happy_acute_mod.teleport_core", "传送核心");
        translationBuilder.add("item.happy_acute_mod.case", "机壳");
        translationBuilder.add(ModBlocks.CHARGING_STATION.getTranslationKey(), "充电桩");
    }
}
