package com.acuteterror233.datagen;

import com.acuteterror233.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModENUSLanProvider extends FabricLanguageProvider {
    public ModENUSLanProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput,"en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add("itemGroup.happy_acute_mod_group", "call machine mod");
        translationBuilder.add("item.happy_acute_mod.call_machine", "Call Machine");
        translationBuilder.add("item.happy_acute_mod.communicator", "Communicator");
        translationBuilder.add("item.happy_acute_mod.teleport_core", "Teleport Core");
        translationBuilder.add("item.happy_acute_mod.case", "Case");
        translationBuilder.add(ModBlocks.CHARGING_STATION.getTranslationKey(),"Charging station");
    }
}
