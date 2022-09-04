package com.castlelecs.missedbundle.items.bundle.inventory;

import static net.minecraft.client.gui.GuiComponent.blit;

import com.castlelecs.missedbundle.gui.SlotType;
import com.castlelecs.missedbundle.utilities.Constants;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemStack;

/**
 * A generator of inventory GUI components
 */
public class InventoryUIGeneratorHelper {

    private final InventoryMatrix storage;

    // MARK: - Init

    public InventoryUIGeneratorHelper(ItemStack bundleStack) {
        this.storage = new InventoryMatrix(InventoryHelper.getItems(bundleStack));
    }

    // MARK: - Public methods

    public void drawInventoryUI(Font font, PoseStack poseStack, ItemRenderer itemRenderer, int x, int y) {
        renderInventory(poseStack, x, y);
        renderItemsIcons(font, itemRenderer, x, y);
    }

    public int getHeight() {
        int storageMatrixHeight = UIGeneratorTools.invalidateZero(storage.getHeight());

        return storageMatrixHeight * Constants.SLOT_SIZE + Constants.SMALL_PADDING;
    }

    public int getWidth() {
        int storageMatrixWidth = UIGeneratorTools.invalidateZero(storage.getWidth());

        return storageMatrixWidth * Constants.SLOT_SIZE;
    }

    // MARK: - Private methods

    private void renderInventory(PoseStack poseStack, int x, int y) {
        if (storage.isEmpty())
            renderEmptyInventory(poseStack, x, y);
        else
            renderStandardInventory(poseStack, x, y);
    }

    private void renderItemsIcons(Font font, ItemRenderer itemRenderer, int x, int y) {
       for (var rowIndex = 0; rowIndex < storage.getHeight(); rowIndex++) {
            var row = storage.get(rowIndex);

            for (var columnIndex = 0; columnIndex < row.size(); columnIndex++) {
                ItemStack item = row.get(columnIndex);

                int slotShiftX = Constants.SLOT_SIZE * columnIndex;
                int slotShiftY = Constants.SLOT_SIZE * rowIndex;
                int itemShiftX = slotShiftX + Constants.SLOT_OFFSET;
                int itemShiftY = slotShiftY + Constants.SLOT_OFFSET;

                itemRenderer.renderAndDecorateItem(item, x + itemShiftX, y + itemShiftY);
                itemRenderer.renderGuiItemDecorations(font, item, x + itemShiftX, y + itemShiftY);
            }
        } 
    }

    private void renderStandardInventory(PoseStack poseStack, int x, int y) {
        int rowsCount = storage.getHeight();
        int columnsCount = storage.getWidth();

        int textureStartXLocation = 0;
        int textureStartYLocation = 0;
        int textureHeight = Constants.SLOT_SIZE * rowsCount;
        int textureWidth = Constants.SLOT_SIZE * columnsCount;

        RenderSystem.setShaderTexture(0, SlotType.STANDARD.getResource());

        blit(poseStack,
                x, y,
                textureStartXLocation, textureStartYLocation,
                textureWidth, textureHeight,
                Constants.SLOT_SIZE, Constants.SLOT_SIZE);
    }

    private void renderEmptyInventory(PoseStack poseStack, int x, int y) {
        int textureStartXLocation = 0;
        int textureStartYLocation = 0;
        int textureEndXLocation = Constants.SLOT_SIZE;
        int textureEndYLocation = Constants.SLOT_SIZE;

        RenderSystem.setShaderTexture(0, SlotType.EMPTY.getResource());

        blit(poseStack,
                x, y,
                textureStartXLocation, textureStartYLocation,
                textureEndXLocation, textureEndYLocation,
                Constants.SLOT_SIZE, Constants.SLOT_SIZE);
    }
}
