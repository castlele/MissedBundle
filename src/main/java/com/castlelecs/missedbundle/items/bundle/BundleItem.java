package com.castlelecs.missedbundle.items.bundle;

import com.castlelecs.missedbundle.utilities.InventoryHelper;
import com.castlelecs.missedbundle.utilities.Singleton;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public final class BundleItem extends Item implements Singleton {

    public static final BundleItem shared = new BundleItem();

    // MARK: - Init

    private BundleItem() {
        super(new BundleProperties());
    }

    // MARK: - Overrided methods

    @Override
    public boolean overrideStackedOnOther(ItemStack otherStack, Slot slot, ClickAction actionType, Player player) {
        return super.overrideStackedOnOther(otherStack, slot, actionType, player);
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack bundleStack,
                                            ItemStack otherStack,
                                            Slot slot,
                                            ClickAction actionType,
                                            Player player,
                                            SlotAccess slotAccess) {

        if (actionType == ClickAction.SECONDARY && otherStack.getItem() != Items.AIR) {
            InventoryHelper.saveItems(bundleStack, otherStack);
            return true;
        }

        return false;
    }
}
