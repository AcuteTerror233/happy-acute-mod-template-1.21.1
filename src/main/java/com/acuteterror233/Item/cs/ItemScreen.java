package com.acuteterror233.Item.cs;

import com.acuteterror233.HappyAcuteMod;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ItemScreen extends Screen {
    private static final Identifier GUI_PIG = Identifier.of(HappyAcuteMod.MOD_ID, "gui/manual.png");
    public static List<String> List= new ArrayList<>();
    public ItemScreen(Text title, List<String> List) {
        super(title);
        ItemScreen.List = List;
    }
    @Override
    protected void init() {
        super.init();

        int buttonWidth = 70;
        int buttonHeight = 20;
        int startX = this.width / 2 - buttonWidth / 2;
        int startY = this.height / 2 - buttonHeight / 2;
        int startYjian = 0;
        for (String s : List) {
            ButtonWidget cancelButton = ButtonWidget.builder(Text.of(s), button -> {
                this.close();
            }).position(startX, startY - startYjian).size(buttonWidth, buttonHeight).build();
            this.addDrawableChild(cancelButton);
            startYjian = startYjian + 20;
        }
        ButtonWidget cancelButton = ButtonWidget.builder(Text.of("取消"), button -> {
            this.close();
        }).position(startX - 90, startY + 100).size(buttonWidth, buttonHeight).build();
        this.addDrawableChild(cancelButton);
        ButtonWidget confirmButton = ButtonWidget.builder(Text.of("确定"), button -> {
            this.close();
        }).position(startX + 90, startY + 100).size(buttonWidth, buttonHeight).build();
        this.addDrawableChild(confirmButton);
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
