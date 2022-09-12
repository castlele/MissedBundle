package com.castlelecs.missedbundle.items.bundle.tooltip;

import com.castlelecs.missedbundle.items.bundle.inventory.InventoryUIGeneratorHelper;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.entity.ItemRenderer;

/**
 * A client component which draws components in the tooltip
 */
public class ClientBundleTooltipComponent implements ClientTooltipComponent {

    private final InventoryUIGeneratorHelper uiGenerator;

    public ClientBundleTooltipComponent(BundleTooltipComponent component) {
        uiGenerator = new InventoryUIGeneratorHelper(component.bundleStack());
    }

    @Override
    public int getHeight() {
        return uiGenerator.getHeight();
    }

    @Override
    public int getWidth(Font font) {
        return uiGenerator.getWidth();
    }

    @Override
    public void renderImage(Font font, int x, int y, PoseStack poseStack, ItemRenderer itemRenderer, int p_194053_) {
        uiGenerator.drawInventoryUI(font, poseStack, itemRenderer, x, y);
    }
}
