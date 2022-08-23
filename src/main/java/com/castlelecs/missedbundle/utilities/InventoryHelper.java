package com.castlelecs.missedbundle.utilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;
import java.util.Set;

public class InventoryHelper {

    // MARK: - Public methods

    public static void saveItems(ItemStack newItem, ItemStack bundle, Runnable deleteItemCompletion) {
        if (!isEnoughSpace(bundle, newItem))
            return;

        CompoundTag compoundTag = bundle.getOrCreateTag();
        var list = new ListTag();

        saveItemsToCompoundTag(compoundTag, newItem, list);
        bundle.setTag(compoundTag);

        deleteItemCompletion.run();

        System.out.printf(Arrays.toString(getItems(bundle)) + "\n");
    }

    public static ItemStack[] getItems(ItemStack bundle) {
        CompoundTag compoundTag = bundle.getOrCreateTag();
        Set<String> setOfKeys = compoundTag.getAllKeys();
        String[] keys = setOfKeys.toArray(new String[0]);
        ItemStack[] inventory = new ItemStack[keys.length];

        for (var index = 0; index < keys.length; index++) {
            ListTag list = compoundTag.getList(keys[index], Tag.TAG_COMPOUND);
            CompoundTag compound = list.getCompound(0);

            ItemStack itemStack = ItemStack.of(compound);
            inventory[index] = itemStack;
        }

        return inventory;
    }

    // MARK: - Private methods

    private static boolean isEnoughSpace(ItemStack bundle, ItemStack newItem) {
        if (newItem.getMaxStackSize() == Constants.NON_STACKED_MAX_STACK_SIZE)
            return false;

        ItemStack[] items = getItems(bundle);
        int count = getItemsCount(items) + newItem.getCount();

        return count <= Constants.BUNDLE_SIZE;
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
}
