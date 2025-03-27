package com.acuteterror233.block.cheshi;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
/**
 * 继承自 ClickableWidget 的自定义组件类
 * 该类旨在通过添加或修改功能来扩展 ClickableWidget 的行为
 */
public class ModClickableWidget extends ClickableWidget {

    /**
     * 构造一个 modClickableWidget 实例
     *
     * @param x 组件的左上角 x 坐标
     * @param y 组件的左上角 y 坐标
     * @param width 组件的宽度
     * @param height 组件的高度
     * @param message 组件上显示的文本
     *
     * 构造方法传递参数给父类 ClickableWidget 的构造方法，以初始化组件的位置和大小以及显示的消息
     */
    public ModClickableWidget(int x, int y, int width, int height, Text message) {
        super(x, y, width, height, message);
    }

    /**
     * 重写 renderWidget 方法以自定义组件的渲染逻辑
     *
     * @param context 绘制上下文，用于渲染的工具和信息
     * @param mouseX 鼠标指针的 x 坐标
     * @param mouseY 鼠标指针的 y 坐标
     * @param delta 自上次渲染以来的时间增量
     *
     * 本方法为空，意在根据需要添加自定义的渲染代码
     */
    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        // 此处可根据需要添加自定义渲染逻辑
    }

    /**
     * 重写 appendClickableNarrations 方法以添加自定义的可点击叙述
     *
     * @param builder 叙述构建器，用于添加叙述信息
     *
     * 本方法为空，提供了一个机会，使得开发者可以添加关于组件的可点击性的叙述性反馈
     */
    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {
        // 此处可根据需要添加自定义的叙述信息
    }
}

