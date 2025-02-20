package com.acuteterror233.block.cheshi;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.AlwaysSelectedEntryListWidget;

/**
 * ModAlwaysSelectedEntryListWidget类继承自AlwaysSelectedEntryListWidget，用于在模组选择界面中展示一个始终选中的条目列表
 * 此类的主要作用是为模组选择界面提供一个可视化的列表组件，其中所有条目都保持选中状态
 * 它的特殊行为和表现通常由其父类AlwaysSelectedEntryListWidget定义的方法和属性决定
 *
 * @param minecraftClient Minecraft客户端实例，用于访问Minecraft游戏的各种功能和数据
 * @param i 列表组件的宽度
 * @param j 列表组件的高度
 * @param k 列表内容区域的顶部位置偏移量
 * @param l 列表内容区域的底部位置偏移量
 */
public class ModAlwaysSelectedEntryListWidget extends AlwaysSelectedEntryListWidget {
    public ModAlwaysSelectedEntryListWidget(MinecraftClient minecraftClient, int i, int j, int k, int l) {
        super(minecraftClient, i, j, k, l);
    }
}

