package com.acuteterror233.Item.Screen;

import com.acuteterror233.HappyAcuteMod;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

/**
 * ModClickableWidget 类扩展了 ClickableWidget，用于在手动页面中创建可点击的元素
 * 它特别适用于在图形界面上添加具有特定功能的按钮或小部件
 */
public class ModClickableWidget extends ClickableWidget {

    // 手动元素的标识符，用于引用特定的纹理资源
    private final Identifier manual_elements = Identifier.of(HappyAcuteMod.MOD_ID, "gui/manual_elements.png");

    /**
     * 构造一个 ModClickableWidget 实例
     *
     * @param x 按钮的x坐标
     * @param y 按钮的y坐标
     * @param width 按钮的宽度
     * @param height 按钮的高度
     * @param message 按钮上显示的文本信息
     *
     * 构造函数调用父类 ClickableWidget 的构造函数，初始化按钮的位置、大小和显示文本
     */
    public ModClickableWidget(int x, int y, int width, int height, Text message) {
        super(x, y, width, height,message);;
    }


    /**
     * 重写渲染小部件的方法
     *
     * @param context 绘制上下文，用于渲染操作
     * @param mouseX 鼠标X坐标，用于确定鼠标位置
     * @param mouseY 鼠标Y坐标，用于确定鼠标位置
     * @param delta 时间间隔，用于平滑动画或逻辑更新
     *
     * 此方法用于在用户界面上渲染小部件通常包括绘制图形或文本等操作
     * 重写此方法是为了根据具体需求定制渲染逻辑
     */
    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        if (isMouseOver(mouseX, mouseY)) {
            context.drawTexture(manual_elements, getX(), getY(), 0, 0, 200, 12);
        }else {
            context.drawTexture(manual_elements, getX(), getY(), 0, 12, 200, 12);
        }
    }
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isMouseOver(mouseX, mouseY)) {
            HappyAcuteMod.LOGGER.info("点击了");
            return true; // 返回true表示事件已处理
        }
        return false; // 返回false表示事件未处理
    }

    /**
     * 重写添加可点击元素的叙述
     *
     * @param builder 叙述构建器，用于添加可点击元素的叙述
     *
     * 此方法用于无障碍功能添加界面元素的语音描述通过重写此方法
     * 可以为特定元素提供更丰富的叙述信息以提升用户的无障碍体验
     */
    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {

    }
}
