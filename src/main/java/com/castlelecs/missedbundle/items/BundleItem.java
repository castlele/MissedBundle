package com.castlelecs.missedbundle.items;

import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public final class BundleItem extends Item {

    // MARK: - Init

    public BundleItem() {
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
            return true;
        }

        return false;
    }
}
