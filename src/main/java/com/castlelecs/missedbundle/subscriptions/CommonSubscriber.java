package com.castlelecs.missedbundle.subscriptions;

import com.castlelecs.missedbundle.MissedBundle;
import com.castlelecs.missedbundle.items.bundle.BundleItem;
import com.castlelecs.missedbundle.items.bundle.tooltip.BundleTooltipComponent;
import com.castlelecs.missedbundle.items.bundle.tooltip.ClientBundleTooltipComponent;
import com.castlelecs.missedbundle.utilities.Constants;
import com.mojang.datafixers.util.Either;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * A subscriber for common events.
 * <p>
 * Every other more "specific" subscriber should be added as an inner class
 * </p>
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = MissedBundle.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSubscriber {
    @SubscribeEvent
    public static void registerTooltip(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(BundleTooltipComponent.class, ClientBundleTooltipComponent::new);
    }

    // MARK: - TooltipSubscriber

    /**
     * A subscriber for rendering custom tooltips
     */
    @Mod.EventBusSubscriber(value = Dist.CLIENT, modid = MissedBundle.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class TooltipSubscriber {
        @SubscribeEvent
        public static void showTooltip(RenderTooltipEvent.GatherComponents event) {
            ItemStack item = event.getItemStack();

            if (item.is(BundleItem.shared)) {
                var toolTipElements = event.getTooltipElements();

                toolTipElements.remove(Constants.DURABILITY_INDEX);
                toolTipElements.add(Constants.BUNDLE_INVENTORY_INDEX, Either.right(new BundleTooltipComponent(item)));
            }
        }
    }
}
