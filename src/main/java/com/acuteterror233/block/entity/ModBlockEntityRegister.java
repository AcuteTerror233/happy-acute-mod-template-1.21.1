package com.acuteterror233.block.entity;

import com.acuteterror233.HappyAcuteMod;
import com.acuteterror233.block.ModBlocks;
import com.mojang.datafixers.types.Type;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

     public class ModBlockEntityRegister {
         /**
          * 注册BlockEntityType的通用方法
          * @param path BlockEntityType的路径，用于唯一标识
          * @param blockEntityType 要注册的BlockEntityType实例
          * @param <T> BlockEntityType的类型，允许泛型调用
          * @return 返回注册后的BlockEntityType实例
          */
         public static <T extends BlockEntityType<?>> T register(String path, T blockEntityType) {
             return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(HappyAcuteMod.MOD_ID, path), blockEntityType);
         }

         /**
          * 创建并注册特定BlockEntityType的辅助方法
          * @param id BlockEntityType的ID，用于唯一标识
          * @param builder BlockEntityType的构建器，用于创建实例
          * @param <T> BlockEntity的类型，允许泛型调用
          * @return 返回创建并注册后的BlockEntityType实例
          */
         private static <T extends BlockEntity> BlockEntityType<T> create(String id, BlockEntityType.Builder<T> builder) {
             Type<?> type = Util.getChoiceType(TypeReferences.BLOCK_ENTITY, id);
             return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(HappyAcuteMod.MOD_ID, id), builder.build(type));
         }

         /**
          * 创建并注册ModBlockEntity类型的BlockEntityType
          * 这里使用了create方法来构建一个特定的BlockEntityType，并将其与ModBlockEntity关联
          * 同时，它也与特定的Block（如ModBlocks.CHARGING_STATION）关联，意味着这种BlockEntityType只能用于这种Block
          */
         public static final BlockEntityType<ModBlockEntity> BLOCK_ENTITY_TYPE = create(
             "block_entity_type",
             BlockEntityType.Builder.create(ModBlockEntity::new, ModBlocks.CHARGING_STATION)
         );

         // 如果需要注册其他BlockEntityType，可以按照以下格式进行：
         // public static final BlockEntityType<PolishingMachineBlockEntity> MOD_BLOCK_ENTITY = create(
         //         "polishing_machine_block_entity",
         //         BlockEntityType.Builder.create(PolishingMachineBlockEntity::new, ModBlocks.POLISHING_MACHINE)
         // );
         public static void registerBlockEntities() {

         }
     }

