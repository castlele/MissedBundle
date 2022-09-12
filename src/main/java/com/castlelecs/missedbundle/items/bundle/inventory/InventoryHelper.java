package com.castlelecs.missedbundle.items.bundle.inventory;

import com.castlelecs.missedbundle.utilities.Constants;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class InventoryHelper {

    // MARK: - Public methods

    public static void saveItems(ItemStack newItem, ItemStack bundle) {
        int canAddCount = determineHowMuchCanBeAdded(bundle, newItem);

        if (canAddCount == 0)
            return;

        var list = new ListTag();
        CompoundTag compoundTag = bundle.getOrCreateTag();
        ItemStack itemsToAdd = newItem.copy();

        itemsToAdd.setCount(canAddCount);
        saveItemsToCompoundTag(compoundTag, itemsToAdd, list);
        bundle.setTag(compoundTag);
        newItem.setCount(newItem.getCount() - canAddCount);
    }

    public static ItemStack removeLast(ItemStack bundle) {
        CompoundTag compoundTag = bundle.getOrCreateTag();
        String[] keys = getKeys(compoundTag);

        if (keys.length == 0)
            return new ItemStack(Items.AIR);

        ListTag list = compoundTag.getList(keys[keys.length - 1], Tag.TAG_COMPOUND);
        CompoundTag itemsCompound = list.getCompound(0);

        compoundTag.remove(keys[keys.length - 1]);
        var removedItem = ItemStack.of(itemsCompound);

        return removedItem.getItem() == Items.AIR
                ? removeLast(bundle)
                : removedItem;
    }

    public static ItemStack[] removeAll(ItemStack bundle) {
        int size = getItemsCount(bundle);
        ItemStack[] removedItems = new ItemStack[size];

        for (var index = 0; index < size; index++) {
            removedItems[index] = removeLast(bundle);
        }

        return removedItems;
    }

    public static ItemStack[] getItems(ItemStack bundle) {
        CompoundTag compoundTag = bundle.getOrCreateTag();
        String[] keys = getKeys(compoundTag);
        List<ItemStack> inventory = new ArrayList<>(keys.length);

        for (String key : keys) {
            ListTag list = compoundTag.getList(key, Tag.TAG_COMPOUND);
            CompoundTag compound = list.getCompound(0);

            ItemStack itemStack = ItemStack.of(compound);

            if (itemStack.getItem() == Items.AIR) {
                continue;
            }

            inventory.add(itemStack);
        }

        return inventory.toArray(new ItemStack[0]);
    }

    public static int getItemsCount(ItemStack bundle) {
        ItemStack[] items = getItems(bundle);

        return getItemsCount(items);
    }

    // MARK: - Private methods

    private static int determineHowMuchCanBeAdded(ItemStack bundle, ItemStack newItem) {
        if (newItem.getMaxStackSize() == Constants.NON_STACKED_MAX_STACK_SIZE)
            return 0;

        ItemStack[] items = getItems(bundle);
        int itemsCount = getItemsCount(items);
        int jointCount = itemsCount + newItem.getCount();

        if (itemsCount == Constants.BUNDLE_SIZE) {
            return 0;
        } else if (jointCount < Constants.BUNDLE_SIZE) {
            return newItem.getCount();
        } else {
            return Constants.BUNDLE_SIZE - itemsCount;
        }
    }

    private static int getItemsCount(ItemStack[] items) {
        int itemsCount = 0;

        for (var item : items) {
            itemsCount += item.getCount();
        }

        return itemsCount;
    }

    private static void saveItemsToCompoundTag(CompoundTag tag, ItemStack newItem, ListTag list) {
        ListTag existingTag = tag.getList(newItem.getDescriptionId(), Tag.TAG_COMPOUND);

        if (existingTag.size() != 0) {
            CompoundTag existingCompoundTag = existingTag.getCompound(0);
            ItemStack existingItem = ItemStack.of(existingCompoundTag);

            existingItem.setCount(existingItem.getCount() + newItem.getCount());

            putItemsToCompoundTag(tag, existingItem, list);
        } else {
            putItemsToCompoundTag(tag, newItem, list);
        }
    }

    private static void putItemsToCompoundTag(CompoundTag tag, ItemStack newItems, ListTag list) {
        list.add(newItems.serializeNBT());
        tag.put(newItems.getDescriptionId(), list);
    }

    private static String[] getKeys(CompoundTag tag) {
        Set<String> setOfKeys = tag.getAllKeys();

        return setOfKeys.toArray(new String[0]);
    }
}
