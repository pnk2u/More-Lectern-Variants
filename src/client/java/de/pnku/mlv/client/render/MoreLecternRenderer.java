package de.pnku.mlv.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import de.pnku.mlv.block.MoreLecternBlock;
import de.pnku.mlv.block.MoreLecternBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.BookModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.EnchantTableRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

import static de.pnku.mlv.init.MlvBlockInit.MORE_LECTERN_BLOCK_ENTITY;

@Environment(EnvType.CLIENT)
public class MoreLecternRenderer implements BlockEntityRenderer<MoreLecternBlockEntity> {
    private final BookModel bookModel;

    public MoreLecternRenderer(BlockEntityRendererProvider.Context context) {
        this.bookModel = new BookModel(context.bakeLayer(ModelLayers.BOOK));
    }

    public void render(MoreLecternBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        BlockState blockState = blockEntity.getBlockState();
        if ((Boolean)blockState.getValue(MoreLecternBlock.HAS_BOOK)) {
            poseStack.pushPose();
            poseStack.translate(0.5F, 1.0625F, 0.5F);
            float f = ((Direction)blockState.getValue(MoreLecternBlock.FACING)).getClockWise().toYRot();
            poseStack.mulPose(Axis.YP.rotationDegrees(-f));
            poseStack.mulPose(Axis.ZP.rotationDegrees(67.5F));
            poseStack.translate(0.0F, -0.125F, 0.0F);
            this.bookModel.setupAnim(0.0F, 0.1F, 0.9F, 1.2F);
            VertexConsumer vertexConsumer = EnchantTableRenderer.BOOK_LOCATION.buffer(bufferSource, RenderType::entitySolid);
            this.bookModel.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, -1);
            poseStack.popPose();
        }
    }

    public static void registerMoreLecternRenderer(){
        BlockEntityRenderers.register(MORE_LECTERN_BLOCK_ENTITY, MoreLecternRenderer::new);
    }
}

