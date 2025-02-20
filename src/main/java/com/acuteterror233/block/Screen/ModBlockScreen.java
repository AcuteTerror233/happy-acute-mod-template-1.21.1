package com.acuteterror233.block.Screen;

import com.acuteterror233.HappyAcuteMod;
import com.acuteterror233.Item.gui.ModItemScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

/**
 * ModBlockScreen 类继承自 HandledScreen，用于渲染自定义方块的GUI界面
 * 该界面包含一些特定的绘制逻辑，比如背景纹理的绘制和进度箭头的条件绘制
 */
public class ModBlockScreen extends HandledScreen<ModBlockScreenHandler> {
    // 定义GUI背景纹理的资源标识符
    private static final Identifier GUI_PIG = Identifier.of(HappyAcuteMod.MOD_ID,"gui/manual.png");

    /**
     * 构造函数
     *
     * @param handler 屏幕处理器，用于处理屏幕上的交互逻辑
     * @param inventory 玩家的背包库存，用于在屏幕上显示玩家的物品
     * @param title 屏幕的标题，显示在屏幕的顶部
     */
    public ModBlockScreen(ModBlockScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    /**
     * 渲染进度箭头的方法
     *
     * @param context 绘制上下文，用于绘制纹理
     * @param x 背景纹理的x坐标
     * @param y 背景纹理的y坐标
     * 根据是否正在制作物品和是否正在下雨来决定是否绘制进度箭头
     */
    private void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting() && handler.isRaining()) {
            context.drawTexture(GUI_PIG, x + 85, y + 30, 176, 0, 8, handler.getScaledProgress());
        }
    }

    /**
     * 渲染屏幕的方法，每帧调用一次
     *
     * @param context 绘制上下文
     * @param mouseX 鼠标x坐标
     * @param mouseY 鼠标y坐标
     * @param delta 上一帧到当前帧的时间差
     * 调用父类的render方法来处理默认的渲染逻辑
     */
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
    }

    /**
     * 绘制背景的方法
     *
     * @param context 绘制上下文
     * @param delta 上一帧到当前帧的时间差
     * @param mouseX 鼠标x坐标
     * @param mouseY 鼠标y坐标
     * 计算背景的绘制位置，并绘制背景纹理和进度箭头
     */
    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;
        context.drawTexture(GUI_PIG,x,y,0,0,backgroundWidth,backgroundHeight);
        renderProgressArrow(context, x, y);
    }
}

