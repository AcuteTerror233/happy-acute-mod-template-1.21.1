package com.acuteterror233.datagen;

import com.acuteterror233.HappyAcuteMod;
import com.acuteterror233.Item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider  {

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CALL_MACHINE)
                .pattern("   ")
                .pattern("bac")
                .pattern("   ")
                .input('a', ModItems.COMMUNICATOR)
                .input('b', ModItems.TELEPORT_CORE)
                .input('c', ModItems.CASE)
                .criterion("has_item", conditionsFromItem(ModItems.TELEPORT_CORE))
                .criterion("has_item", conditionsFromItem(ModItems.COMMUNICATOR))
                .criterion("has_item", conditionsFromItem(ModItems.CASE))
                .offerTo(exporter, Identifier.of(HappyAcuteMod.MOD_ID, "call_machine"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TELEPORT_CORE)
                .pattern("cbc")
                .pattern("bab")
                .pattern("cbc")
                .input('a', Items.ENDER_PEARL)
                .input('b', Items.DIAMOND)
                .input('c', Items.REDSTONE)
                .criterion("has_item", conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter, Identifier.of(HappyAcuteMod.MOD_ID, "teleport_core"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COMMUNICATOR)
                .pattern(" a ")
                .pattern(" b ")
                .pattern(" c ")
                .input('a', Items.REDSTONE_TORCH)
                .input('b', Items.IRON_BARS)
                .input('c', Items.COPPER_INGOT)
                .criterion("has_item", conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter, Identifier.of(HappyAcuteMod.MOD_ID, "communicator"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,ModItems.CASE)
                .pattern("aac")
                .pattern("   ")
                .pattern("aba")
                .input('a', Items.IRON_INGOT)
                .input('b', Items.COPPER_INGOT)
                .input('c', Items.REDSTONE)
                .criterion("has_item", conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter, Identifier.of(HappyAcuteMod.MOD_ID, "case"));
    }
}
