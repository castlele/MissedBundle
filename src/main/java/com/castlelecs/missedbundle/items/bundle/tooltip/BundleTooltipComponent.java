package com.castlelecs.missedbundle.items.bundle.tooltip;

import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

/**
 * A component which will be used to draw bundle's tooltip
 */
public record BundleTooltipComponent(ItemStack bundleStack) implements TooltipComponent { }
