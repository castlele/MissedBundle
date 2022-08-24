package com.castlelecs.missedbundle.items.bundle.tooltip;

import com.castlelecs.missedbundle.MissedBundle;
import com.castlelecs.missedbundle.utilities.Constants;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;

import static net.minecraft.client.gui.GuiComponent.blit;

/**
 * A client component which draws components in the tooltip
 */
public class ClientBundleTooltipComponent implements ClientTooltipComponent {

    public ClientBundleTooltipComponent(BundleTooltipComponent component) { }

    @Override
    public int getHeight() {
        return Constants.SLOT_SIZE + Constants.SMALL_PADDING + Constants.DEFAULT_FONT_SIZE;
    }

    @Override
    public int getWidth(Font font) {
        return Constants.SLOT_SIZE * 4;
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

