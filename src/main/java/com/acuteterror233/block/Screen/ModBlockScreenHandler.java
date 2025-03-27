package com.acuteterror233.block.Screen;

import com.acuteterror233.block.data.ModBlockData;
import com.acuteterror233.block.entity.ModBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class ModBlockScreenHandler extends ScreenHandler {
    // 定义一个特殊的屏幕处理器类，用于处理模组中的方块交互界面
    /**
     * 库存对象，用于管理物品或资源的存储
     * 该字段被声明为final，意味着它只能在构造器中初始化，并且之后不能修改
     */
    private final Inventory inventory;

    /**
     * 属性代理对象，用于管理与特定属性相关的操作
     * 该字段被声明为final，意味着它只能在构造器中初始化，并且之后不能修改
     */
    private final PropertyDelegate propertyDelegate;

    /**
     * ModBlock实体对象，代表一个特定的方块实体，用于模组开发
     * 该字段被声明为final，意味着它只能在构造器中初始化，并且之后不能修改
     * 它被声明为public，意味着它可以被外部类访问
     */
    public final ModBlockEntity blockEntity;
    /**
     * 构造函数，初始化屏幕处理器
     * @param syncId 界面同步ID
     * @param playerInventory 玩家背包
     * @param propertyDelegate 属性代理，用于获取方块状态
     * @param blockEntity 方块实体，此处转换为Inventory以处理物品槽
     */
    public ModBlockScreenHandler(int syncId, PlayerInventory playerInventory, PropertyDelegate propertyDelegate, BlockEntity blockEntity) {
        super(ModBlockScreenHandlerRegister.BLOCK_SCREEN_HANDLER, syncId);
        checkSize((Inventory) blockEntity, 2);
        this.inventory = (Inventory) blockEntity;
        inventory.onOpen(playerInventory.player);

        this.propertyDelegate = propertyDelegate;
        this.blockEntity = (ModBlockEntity) blockEntity;

        // 添加方块的物品槽
        this.addSlot(new Slot(inventory, 0, 80, 11));
        this.addSlot(new Slot(inventory, 1, 80, 59));

        // 添加玩家背包和热键栏的物品槽
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        // 添加属性代理，用于同步方块状态
        addProperties(propertyDelegate);
    }

    /**
     * 重载的构造函数，简化创建屏幕处理器的步骤
     * @param syncId 界面同步ID
     * @param playerInventory 玩家背包
     * @param data 模组方块数据，包含方块位置等信息
     */
    public ModBlockScreenHandler(int syncId, PlayerInventory playerInventory, ModBlockData data) {
        this(syncId, playerInventory, new ArrayPropertyDelegate(2), playerInventory.player.getWorld().getBlockEntity(data.pos()));
    }

    /**
     * 添加玩家热键栏的物品槽
     * @param playerInventory 玩家背包
     */
    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    /**
     * 添加玩家背包的物品槽
     * @param playerInventory 玩家背包
     */
    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
    }

    /**
     * 处理玩家快捷移动（Shift+点击）物品的操作
     * @param player 玩家实体
     * @param slot 物品槽ID
     * @return 移动后的物品堆
     */
    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot invSlot = this.slots.get(slot);
        if (invSlot != null && invSlot.hasStack()) {
            ItemStack originalStack = invSlot.getStack();
            newStack = originalStack.copy();
            if (slot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                invSlot.setStack(ItemStack.EMPTY);
            } else {
                invSlot.markDirty();
            }
        }
        return newStack;
    }

    /**
     * 检查玩家是否可以使用此屏幕处理器
     * @param player 玩家实体
     * @return 是否可以使用
     */
    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    /**
     * 检查是否正在合成物品
     * @return 是否正在合成
     */
    public boolean isCrafting() {
        return propertyDelegate.get(0) > 0;
    }

    /**
     * 检查当前世界是否正在下雨
     * @return 是否正在下雨
     */
    public boolean isRaining() {
        return blockEntity.getWorld().isRaining();
    }

    /**
     * 获取合成进度的缩放值，用于界面显示
     * @return 缩放后的合成进度
     */
    public int getScaledProgress() {
        int progress = propertyDelegate.get(0);
        int maxProgress = propertyDelegate.get(1);
        int progressArrowSize = 26;
        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }
}
