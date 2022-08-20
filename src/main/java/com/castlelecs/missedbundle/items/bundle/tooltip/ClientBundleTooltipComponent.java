package com.castlelecs.missedbundle.items.bundle.tooltip;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;


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
        // TODO: if will be used in the future move to constants
        // 9 - default font size
        return 9;
    }

    @Override
    public int getWidth(Font font) {
        return font.lineHeight;
    }

    @Override
    public void renderText(Font font, int x, int y, Matrix4f matrixStack, MultiBufferSource.BufferSource bufferSource) {
        String amountText = component.currentAmount() + " / " + component.maxAmount();

        var textComponent = ClientTooltipComponent.create(FormattedCharSequence.forward(amountText, Style.EMPTY));

        textComponent.renderText(font, x, y, matrixStack, bufferSource);
    }

    @Override
    public void renderImage(Font font, int x, int y, PoseStack poseStack, ItemRenderer itemRenderer, int p_194053_) {
        ClientTooltipComponent.super.renderImage(font, x, y, poseStack, itemRenderer, p_194053_);
    }
}

