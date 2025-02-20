package com.acuteterror233.Item;

import com.acuteterror233.Item.gui.ModItemGui;
import com.acuteterror233.Item.gui.ModItemScreenHandler;
import com.acuteterror233.Item.gui.ModItemScreenHandlerRegister;
import com.acuteterror233.compoennt.ModComponents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;

public class ModItem extends Item {
    public ModItem(Settings settings) {
        super(settings);
        settings.component(ModComponents.POWER, 0);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        Integer count = stack.get(ModComponents.POWER);
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("item.happy_acute_mod.call_machine.info.shift", Objects.requireNonNullElse(count, 0)));
        } else {
            tooltip.add(Text.translatable("item.happy_acute_mod.call_machine.info"));
        }
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return super.useOnBlock(context);
    }

//    @Override
//    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
//        if (!world.isClient()){
////            MinecraftClient.getInstance().setScreen(new ModItemGui());
////            NamedScreenHandlerFactory screenHandlerFactory = (PolishingMachineBlockEntity) world.getBlockEntity(pos);
////            user.openHandledScreen((NamedScreenHandlerFactory) new ModItemScreenHandler(1,null,null,null));
//        }
//        return null;
}
