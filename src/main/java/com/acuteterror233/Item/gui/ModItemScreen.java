package com.acuteterror233.Item.gui;

import com.acuteterror233.HappyAcuteMod;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModItemScreen extends Screen {
    private static final Identifier GUI_PIG = Identifier.of(HappyAcuteMod.MOD_ID, "gui/manual.png");
    private final ModItemScreenHandler handler; // 添加成员变量

    public ModItemScreen(Text title, ModItemScreenHandler handler) {
        super(title);
        this.handler = handler; // 在构造函数中初始化
    }
    public ModItemScreen(Text title) {
        super(title);
        this.handler = null; // 在构造函数中初始化
    }

//    @Override
//    protected void init() {
//        super.init();
//        // 假设从ScreenHandler获取玩家列表
//        ModItemScreenHandler handler = (ModItemScreenHandler) this.getScreenHandler();
//        List<String> playerList = handler.playerList;
//
//        int buttonWidth = 70;
//        int buttonHeight = 20;
//        int startX = this.width / 2 - buttonWidth / 2;
//        int startY = this.height / 2 - (buttonHeight * playerList.size()) / 2;
//
//        for (int i = 0; i < playerList.size(); i++) {
//            String playerName = playerList.get(i);
//            ButtonWidget playerButton = ButtonWidget.builder(Text.of(playerName), button -> {
//                this.handler.selectPlayer(playerName); // 使用成员变量
//            }).position(startX, startY + i * buttonHeight).size(buttonWidth, buttonHeight).build();
//            this.addDrawableChild(playerButton);
//        }
//
//        ButtonWidget cancelButton = ButtonWidget.builder(Text.of("取消"), button -> {
//            this.close();
//        }).position(startX, startY + playerList.size() * buttonHeight + 10).size(buttonWidth, buttonHeight).build();
//        this.addDrawableChild(cancelButton);
//
//        ButtonWidget confirmButton = ButtonWidget.builder(Text.of("确定"), button -> {
//            this.handler.confirmSelection(this.client.player); // 使用成员变量
//            this.close();
//        }).position(startX, startY + playerList.size() * buttonHeight + 30).size(buttonWidth, buttonHeight).build();
//        this.addDrawableChild(confirmButton);
//    }

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
        super.renderBackground(context, mouseX, mouseY, delta);

        int screenWidth = this.width;
        int screenHeight = this.height;

        int textureWidth = 256;
        int textureHeight = 256;

        int textureX = screenWidth / 2 - textureWidth / 2;
        int textureY = screenHeight / 2 - textureHeight / 2;

        context.drawTexture(GUI_PIG, textureX, textureY, 0, 0, textureWidth, textureHeight, textureWidth, textureHeight);
    }
}
