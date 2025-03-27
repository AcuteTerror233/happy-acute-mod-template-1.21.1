package com.acuteterror233.Item.gui;

import com.acuteterror233.HappyAcuteMod;
import com.acuteterror233.Item.Screen.ModClickableWidget;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModItemScreen extends Screen {
    private static final Identifier GUI_PIG = Identifier.of(HappyAcuteMod.MOD_ID,"gui/manual.png");
    public ModItemScreen(Text title) {
        super(title);
//        this.addDrawableChild(new ModClickableWidget(0,0,200,36,Text.of("233")));
    }
    /**
     * 初始化方法
     *
     * 该方法用于在对象创建时进行必要的初始化操作
     * 由于该方法被声明为protected，因此仅对同一包内的类或子类可见
     * 这种访问级别的设计可能用于限制初始化逻辑的执行范围，防止外部类随意调用
     */
    @Override
    protected void init() {
        super.init();
        int buttonWidth = 70;
        int buttonHeight = 20;
        int buttonX = this.width / 2 - buttonWidth / 2;
        int buttonY = this.height / 2 - buttonHeight / 2;
        // 创建并添加原版按钮
        ButtonWidget button1 = ButtonWidget.builder(Text.of("233"), button -> {
                    HappyAcuteMod.LOGGER.info("点击了原版按钮");
                })
                .position(buttonX, buttonY)
                .size(buttonWidth, buttonHeight)
                .build();
        this.addDrawableChild(button1);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        // 调用父类的renderBackground方法，确保基本的背景渲染逻辑得到执行
        super.renderBackground(context, mouseX, mouseY, delta);

        // 获取屏幕的宽度和高度
        int screenWidth = this.width;
        int screenHeight = this.height;

        // 获取纹理的宽度和高度
        int textureWidth = 256; // 假设纹理的宽度为256像素
        int textureHeight = 256; // 假设纹理的高度为256像素

        // 计算纹理左上角的绘制位置，使得纹理的中心与屏幕的中心对齐
        int textureX = screenWidth / 2 - textureWidth / 2;
        int textureY = screenHeight / 2 - textureHeight / 2;

        // 绘制纹理
        context.drawTexture(GUI_PIG, textureX, textureY, 0, 0, textureWidth, textureHeight, textureWidth, textureHeight);
    }
}

