package com.castlelecs.missedbundle.items.bundle.tooltip;

import com.castlelecs.missedbundle.MissedBundle;
import com.castlelecs.missedbundle.utilities.Constants;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;

import static net.minecraft.client.gui.GuiComponent.blit;

/**
 * A client component which draws components in the tooltip
 */
public class ClientBundleTooltipComponent implements ClientTooltipComponent {

    private final BundleTooltipComponent component;

    public ClientBundleTooltipComponent(BundleTooltipComponent component) {
        this.component = component;
    }

    @Override
    public int getHeight() {
        return Constants.SLOT_SIZE + Constants.SMALL_PADDING + Constants.DEFAULT_FONT_SIZE;
    }

    @Override
    public int getWidth(Font font) {
        return font.width(component.currentAmount() + " / " + component.maxAmount());
    }

    @Override
    public void renderText(Font font, int x, int y, Matrix4f matrixStack, MultiBufferSource.BufferSource bufferSource) {
        String amountText = component.currentAmount() + " / " + component.maxAmount();

        var textComponent = ClientTooltipComponent.create(FormattedCharSequence.forward(amountText, Style.EMPTY));
        var y_pos = y + Constants.SLOT_SIZE + Constants.SMALL_PADDING;

        textComponent.renderText(font, x, y_pos, matrixStack, bufferSource);
    }

    @Override
    public void renderImage(Font font, int x, int y, PoseStack poseStack, ItemRenderer itemRenderer, int p_194053_) {
        renderEmptySlot(poseStack, x, y);
    }

    private void renderEmptySlot(PoseStack poseStack, int x, int y) {
        final int textureStartXLocation = 0;
        final int textureStartYLocation = 0;
        final int textureEndXLocation = Constants.SLOT_SIZE;
        final int textureEndYLocation = Constants.SLOT_SIZE;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, MissedBundle.location(Constants.EMPTY_PLUS_SLOT));

        blit(poseStack,
                x, y,
                textureStartXLocation, textureStartYLocation,
                textureEndXLocation, textureEndYLocation,
                Constants.SLOT_SIZE, Constants.SLOT_SIZE);
    }
}

