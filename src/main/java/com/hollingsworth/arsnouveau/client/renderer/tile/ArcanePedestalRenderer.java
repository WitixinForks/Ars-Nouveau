package com.hollingsworth.arsnouveau.client.renderer.tile;

import com.hollingsworth.arsnouveau.api.util.MappingUtil;
import com.hollingsworth.arsnouveau.common.block.tile.ArcanePedestalTile;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class ArcanePedestalRenderer extends BlockEntityRenderer<ArcanePedestalTile> {

    public ArcanePedestalRenderer(BlockEntityRenderDispatcher p_i226006_1_) {
        super(p_i226006_1_);
    }

    public void renderFloatingItem(ArcanePedestalTile tileEntityIn, ItemEntity entityItem, double x, double y, double z, PoseStack stack, MultiBufferSource iRenderTypeBuffer){
        stack.pushPose();
        tileEntityIn.frames += 1.5f * Minecraft.getInstance().getDeltaFrameTime();
        entityItem.setYHeadRot(tileEntityIn.frames);
        ObfuscationReflectionHelper.setPrivateValue(ItemEntity.class, entityItem, (int) tileEntityIn.frames, MappingUtil.getItemEntityAge());
        Minecraft.getInstance().getEntityRenderDispatcher().render(entityItem, 0.5,1,0.5, entityItem.yRot, 2.0f,stack, iRenderTypeBuffer,15728880);
        Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(entityItem);
        stack.popPose();
    }

    @Override
    public void render(ArcanePedestalTile tileEntityIn, float v, PoseStack matrixStack, MultiBufferSource iRenderTypeBuffer, int i, int i1) {
        double x = tileEntityIn.getBlockPos().getX();
        double y = tileEntityIn.getBlockPos().getY();
        double z = tileEntityIn.getBlockPos().getZ();

        if(tileEntityIn.stack == null)
            return;

        if (tileEntityIn.entity == null || !ItemStack.matches(tileEntityIn.entity.getItem(), tileEntityIn.stack)) {
            tileEntityIn.entity = new ItemEntity(tileEntityIn.getLevel(), x, y, z, tileEntityIn.stack);
        }

        ItemEntity entityItem = tileEntityIn.entity;
        x = x + .5;
        y = y + 0.9;
        z = z +.5;
        renderFloatingItem(tileEntityIn, entityItem, x, y , z, matrixStack, iRenderTypeBuffer);
    }
}
