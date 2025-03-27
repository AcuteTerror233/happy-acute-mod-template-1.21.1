package com.acuteterror233.Item;

import com.acuteterror233.Item.Screen.ModScreen;
import com.acuteterror233.Item.gui.ModItemScreen;
import com.acuteterror233.compoennt.ModComponents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
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

    /**
     * 使用物品对准方块时的交互逻辑
     * 此方法被调用时，表示玩家正在尝试使用某个物品与世界中的方块进行交互
     * 它允许修改方块的状态，放置新方块，或者触发特定的事件
     *
     * @param context 包含了使用物品的上下文信息，如玩家位置、物品堆、世界等
     * @return ActionResult 表示交互结果，可以是成功、失败或无操作
     *
     * 注意：此方法重写了父类的方法，以提供特定的方块交互行为
     */
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return super.useOnBlock(context);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
    ItemStack itemStack = user.getStackInHand(hand);

    // 确保只在客户端运行
    if (world.isClient()) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null) {
            try {
                client.setScreen(new ModItemScreen(Text.empty()));
            } catch (Exception e) {
                // 记录异常日志或进行其他处理
                System.err.println("Failed to set screen: " + e.getMessage());
            }
        }
    }

    return TypedActionResult.success(itemStack);
}

}
