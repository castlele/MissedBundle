package com.castlelecs.missedbundle.items.bundle;

import com.castlelecs.missedbundle.utilities.Constants;
import com.castlelecs.missedbundle.items.bundle.inventory.InventoryHelper;
import com.castlelecs.missedbundle.utilities.Singleton;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public final class BundleItem extends Item implements Singleton {

    public static final BundleItem shared = new BundleItem();

    // MARK: - Init

    private BundleItem() { super(new BundleProperties()); }

    // MARK: - Overrided methods


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack activeItem = player.getInventory().getSelected();

        if (hand == InteractionHand.MAIN_HAND && activeItem.getItem() == BundleItem.shared) {
            for (var index = 0; index < InventoryHelper.getItemsCount(activeItem); index++) {
                player.drop(InventoryHelper.removeLast(activeItem), false);
            }

            updateFullnessIndicator(activeItem);
        }

        return super.use(level, player, hand);
    }

    @Override
    public boolean overrideStackedOnOther(ItemStack bundleStack, Slot slot, ClickAction actionType, Player player) {
        ItemStack otherStack = slot.getItem();

        // Put an item into the bundle
        if (actionType == ClickAction.SECONDARY && otherStack.getItem() != Items.AIR) {
            InventoryHelper.saveItems(otherStack, bundleStack, () -> {
                removeItemFromSlot(slot, otherStack);
            });

            updateFullnessIndicator(bundleStack);

            return true;
        }

        // Put an item from the bundle to the slot
        if (actionType == ClickAction.SECONDARY && otherStack.getItem() == Items.AIR) {
            ItemStack removedFromBundleItem = InventoryHelper.removeLast(bundleStack);
            slot.set(removedFromBundleItem);

            updateFullnessIndicator(bundleStack);

            return true;
        }

        return false;
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack bundleStack,
                                            ItemStack otherStack,
                                            Slot slot,
                                            ClickAction actionType,
                                            Player player,
                                            SlotAccess slotAccess) {

        // Put an item into the bundle
        if (actionType == ClickAction.SECONDARY && otherStack.getItem() != Items.AIR) {
            InventoryHelper.saveItems(otherStack, bundleStack, () -> {
                removeCarriedItem(player);
            });

            updateFullnessIndicator(bundleStack);

            return true;
        }

        // Get an item out from the bundle
        if (actionType == ClickAction.SECONDARY && otherStack.getItem() == Items.AIR) {
            ItemStack removedFromBundleItem = InventoryHelper.removeLast(bundleStack);

            if (removedFromBundleItem.getItem() != Items.AIR)
                player.inventoryMenu.setCarried(removedFromBundleItem);

            updateFullnessIndicator(bundleStack);

            return true;
        }

        return false;
    }

    @Override
    public void appendHoverText(ItemStack bundleStack, Level level, List<Component> componentList, TooltipFlag tooltipFlag) {
        int currentValue = InventoryHelper.getItemsCount(bundleStack);

        componentList.add(Component.translatable(Constants.BUNDLE_FULLNESS_TEXT_INDICATOR, currentValue, Constants.BUNDLE_SIZE));

        super.appendHoverText(bundleStack, level, componentList, tooltipFlag);
    }

    private void removeCarriedItem(Player player) {
        player.inventoryMenu.setCarried(new ItemStack(Items.AIR));
    }

    private void removeItemFromSlot(Slot slot, ItemStack item) {
        slot.remove(item.getCount());
    }

    private void updateFullnessIndicator(ItemStack bundle) {
        int currentCount = InventoryHelper.getItemsCount(bundle);
        float fillingValue = (float) currentCount / Constants.BUNDLE_SIZE * 100;

        // Max durability value isn't used because if fillingValue == 0, bundle item doesn't have fullness indicator
        int maxValue = Constants.MAX_BUNDLE_FULLNESS + 1;
        int formattedFillingValue = maxValue - (int) fillingValue;

        bundle.setDamageValue(formattedFillingValue);
    }
}
