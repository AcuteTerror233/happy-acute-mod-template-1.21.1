package com.acuteterror233.Item.gui;

import com.acuteterror233.HappyAcuteMod;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGui extends HandledScreen<ModItemScreenHandler> {
    private static final Identifier GUI_PIG = Identifier.of(HappyAcuteMod.MOD_ID,"gui/manual.png");

    public ModItemGui(ModItemScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;
        context.drawTexture(GUI_PIG,x,y,0,0,backgroundWidth,backgroundHeight);
    }
}
