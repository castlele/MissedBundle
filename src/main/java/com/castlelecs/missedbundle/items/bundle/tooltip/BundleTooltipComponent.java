package com.castlelecs.missedbundle.items.bundle.tooltip;

import net.minecraft.world.inventory.tooltip.TooltipComponent;

/**
 * A component which will be used to draw bundle's tooltip
 */
public record BundleTooltipComponent(String currentAmount, String maxAmount) implements TooltipComponent { }
