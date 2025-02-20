package com.acuteterror233.block.entity;

import com.acuteterror233.block.Screen.ModBlockScreenHandler;
import com.acuteterror233.block.data.ModBlockData;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ModBlockEntity extends BlockEntity implements ImplementedInventory, ExtendedScreenHandlerFactory<ModBlockData> {
    // 定义一个大小为2的默认空ItemStack列表，用于存储输入和输出槽的ItemStack
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);

    // 定义输入槽的索引常量
    private static final int INPUT_SLOT = 0;
    // 定义输出槽的索引常量
    private static final int OUTPUT_SLOT = 1;

    // 定义一个属性代理，用于同步进度等信息到客户端
    protected final PropertyDelegate propertyDelegate;

    // 当前进度初始化为0
    private int progress = 0;
    // 最大进度初始化为72，代表完成任务所需的总时间或步骤数
    private int maxProgress = 72;

    /**
     * ModBlockEntity构造函数
     *
     * @param pos BlockEntity的位置
     * @param state BlockEntity的状态
     */
    public ModBlockEntity(BlockPos pos, BlockState state) {
        // 调用父类构造函数初始化BlockEntity
        super(ModBlockEntityRegister.BLOCK_ENTITY_TYPE, pos, state);
        // 初始化propertyDelegate，用于处理进度等属性的同步
        this.propertyDelegate = new PropertyDelegate() {

            /**
             * 根据索引获取属性值
             *
             * @param index 属性索引，0代表当前进度，1代表最大进度
             * @return 属性值，如果不是有效索引则返回0
             */
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> ModBlockEntity.this.progress;
                    case 1 -> ModBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            /**
             * 设置属性值
             *
             * @param index 属性索引，0代表当前进度，1代表最大进度
             * @param value 要设置的属性值
             */
            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> ModBlockEntity.this.progress = value;
                    case 1 -> ModBlockEntity.this.maxProgress = value;
                }
            }

            /**
             * 获取属性的数量
             *
             * @return 属性数量，此处固定为2
             */
            @Override
            public int size() {
                return 2;
            }
        };
    }
    /**
     * 重写getItems方法，用于获取项目堆的列表
     * 此方法返回null，表示当前上下文中没有项目堆可供获取
     *
     * @return DefaultedList<ItemStack> 返回一个ItemStack的列表，此处返回null表示列表为空或不可用
     */
    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    /**
     * 重写获取显示名称的方法
     *
     * 此方法返回一个文本对象，用于在界面上显示容器的名称
     * 通过使用Text.translatable方法，实现名称的国际化和本地化
     *
     * @return Text.translatable("container.polishing_machine") 返回一个可翻译的文本对象，其翻译键为"container.polishing_machine"
     */
    @Override
    public Text getDisplayName() {
        return Text.translatable("container.polishing_machine");
    }

    /**
     * 重写获取屏幕打开数据的方法
     *
     * 当玩家打开容器界面时，此方法被调用，用于提供关于容器的数据
     * 主要用于多人游戏模式下，同步容器的状态和内容给客户端
     *
     * @param player 服务器玩家实体，即尝试打开容器界面的玩家
     * @return new ModBlockData(pos) 返回一个新的ModBlockData对象，包含容器的位置信息
     */
    @Override
    public ModBlockData getScreenOpeningData(ServerPlayerEntity player) {
        return new ModBlockData(pos);
    }
    /**
     * 重写createMenu方法以提供自定义的ScreenHandler
     * 此方法用于创建与服务器交互的屏幕处理器，允许玩家与自定义方块进行交互
     *
     * @param syncId 同步ID，用于标识和同步屏幕处理器的状态
     * @param playerInventory 玩家的背包，包含玩家所携带的所有物品
     * @param player 玩家实体，表示与自定义方块交互的玩家
     * @return 返回一个自定义的ModBlockScreenHandler实例，用于处理玩家与方块的交互
     */
    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ModBlockScreenHandler(syncId, playerInventory, this.propertyDelegate, this);
    }

    /**
     * 重写getMaxCountPerStack方法以定义每堆物品的最大数量
     * 此方法用于指定在方块中每堆物品的最大数量，以便进行物品堆叠管理
     *
     * @return 返回每堆物品的最大数量，在这个实现中设置为64
     */
    @Override
    public int getMaxCountPerStack() {
        return 64;
    }
    /**
     * 重写writeNbt方法以保存抛光机的进度和库存信息到NBT复合对象中
     * 此方法确保在游戏保存或加载时，抛光机的状态能够被正确记录和还原
     *
     * @param nbt NBT复合对象，用于存储数据
     * @param registryLookup 注册表包装器查找对象，用于处理注册表相关的操作
     */
    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, this.inventory, false, registryLookup);
        nbt.putInt("polishing_machine", progress);
    }

    /**
     * 重写readNbt方法以从NBT复合对象中读取抛光机的进度和库存信息
     * 此方法确保在游戏加载时，抛光机的状态能够被正确还原
     *
     * @param nbt NBT复合对象，用于读取数据
     * @param registryLookup 注册表包装器查找对象，用于处理注册表相关的操作
     */
    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, this.inventory, registryLookup);
        progress = nbt.getInt("polishing_machine");
    }
    /**
     * 该类提供了一个方块的Tick更新逻辑，主要用于处理 crafting（合成）过程
     */
        /**
         * 执行方块的Tick更新逻辑
         *
         * @param world 世界对象，用于访问世界数据
         * @param pos 方块的位置，用于标识方块的位置
         * @param state 方块的状态，用于获取方块的当前状态
         */
        public void tick(World world, BlockPos pos, BlockState state) {
            // 如果是客户端，则直接返回，不执行后续逻辑
            if (world.isClient()) {
                return;
            }

            // 如果输出槽可用，则检查是否有合成配方
            if (isOutputSlotAvailable()) {
                if (hasRecipe()) {
                    // 增加合成进度并标记方块状态为脏
                    increaseCraftProgress();
                    markDirty(world, pos, state);

                    // 如果合成完成，则合成物品并重置进度
                    if (hasCraftingFinished()) {
                        craftItem();
                        resetProgress();
                    }
                } else {
                    // 如果没有配方，则重置合成进度
                    resetProgress();
                }
            } else {
                // 如果输出槽不可用，则重置进度并标记方块状态为脏
                resetProgress();
                markDirty(world, pos, state);
            }
        }

        /**
         * 重置合成进度到0
         */
        private void resetProgress() {
            this.progress = 0;
        }

        /**
         * 合成物品并更新输出槽的物品堆
         */
        private void craftItem() {
            ItemStack result = new ItemStack(Items.DAMAGED_ANVIL);
            this.setStack(OUTPUT_SLOT, new ItemStack(result.getItem(), getStack(OUTPUT_SLOT).getCount() + result.getCount()));
        }

        /**
         * 检查合成进度是否达到或超过最大进度，判断合成是否完成
         *
         * @return 如果合成完成返回true，否则返回false
         */
        private boolean hasCraftingFinished() {
            return this.progress >= this.maxProgress;
        }

        /**
         * 增加合成进度
         */
        private void increaseCraftProgress() {
            this.progress++;
        }

        /**
         * 检查是否有合成配方
         *
         * @return 如果有合成配方返回true，否则返回false
         */
        private boolean hasRecipe() {
            ItemStack result = new ItemStack(Items.DAMAGED_ANVIL);
            boolean hasInput = getStack(INPUT_SLOT).getItem() == Items.ICE;
            return hasInput && canInsertAmountIntoOutputSlot(result) && canInsertIntoOutputSlot(result.getItem());
        }

        /**
         * 检查是否可以将物品插入输出槽
         *
         * @param item 要插入的物品
         * @return 如果可以插入返回true，否则返回false
         */
        private boolean canInsertIntoOutputSlot(Item item) {
            return this.getStack(OUTPUT_SLOT).isEmpty() ||
                    this.getStack(OUTPUT_SLOT).getItem() == item;
        }

        /**
         * 检查是否可以将物品堆的数量插入输出槽
         *
         * @param result 要插入的物品堆
         * @return 如果可以插入返回true，否则返回false
         */
        private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
            return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= this.getMaxCountPerStack();
        }

        /**
         * 检查输出槽是否可用，即是否为空或物品堆数量未满
         *
         * @return 如果输出槽可用返回true，否则返回false
         */
        private boolean isOutputSlotAvailable() {
            return this.getStack(OUTPUT_SLOT).isEmpty() ||
                    this.getStack(OUTPUT_SLOT).getCount() <= this.getMaxCountPerStack();
        }
    }
