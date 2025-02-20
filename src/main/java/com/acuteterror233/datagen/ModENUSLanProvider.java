package com.acuteterror233.datagen;

import com.acuteterror233.Item.ModItemGroups;
import com.acuteterror233.Item.ModItems;
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
        translationBuilder.add(ModItemGroups.HAPPY_ACUTE_MOD_GROUP, "call machine mod");
        translationBuilder.add(ModItems.CALL_MACHINE.getTranslationKey(), "Call Machine");
        translationBuilder.add(ModItems.COMMUNICATOR.getTranslationKey(), "Communicator");
        translationBuilder.add(ModItems.TELEPORT_CORE.getTranslationKey(), "Teleport Core");
        translationBuilder.add(ModItems.CASE.getTranslationKey(), "Case");
        translationBuilder.add(ModBlocks.CHARGING_STATION.getTranslationKey(),"Charging station");
        translationBuilder.add("item.happy_acute_mod.call_machine.info.shift","The current battery level is \u00A76%1$s\u00A7r");
        translationBuilder.add("item.happy_acute_mod.call_machine.info","Press \u00A76SHIFT\u00A7r for more information");
    }
}
