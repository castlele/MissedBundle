package com.castlelecs.missedbundle.items.bundle.inventory;

import com.castlelecs.missedbundle.utilities.Constants;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Arrays;
import java.util.Set;

public class InventoryHelper {

    // MARK: - Public methods

    public static void saveItems(ItemStack newItem, ItemStack bundle, Runnable deleteItemCompletion) {
        if (!isEnoughSpace(bundle, newItem))
            return;

        bundle.setDamageValue(50);

        CompoundTag compoundTag = bundle.getOrCreateTag();
        var list = new ListTag();

        saveItemsToCompoundTag(compoundTag, newItem, list);
        bundle.setTag(compoundTag);

        deleteItemCompletion.run();

        System.out.printf(Arrays.toString(getItems(bundle)) + "\n");
    }

    public static ItemStack removeLast(ItemStack bundle) {
        CompoundTag compoundTag = bundle.getOrCreateTag();
        String[] keys = getKeys(compoundTag);

        if (keys.length == 0)
            return new ItemStack(Items.AIR);;

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
        ItemStack[] inventory = new ItemStack[keys.length];

        for (var index = 0; index < keys.length; index++) {
            ListTag list = compoundTag.getList(keys[index], Tag.TAG_COMPOUND);
            CompoundTag compound = list.getCompound(0);

            ItemStack itemStack = ItemStack.of(compound);
            inventory[index] = itemStack;
        }

        return inventory;
    }

    public static int getItemsCount(ItemStack bundle) {
        ItemStack[] items = getItems(bundle);

        return getItemsCount(items);
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

    private static String[] getKeys(CompoundTag tag) {
        Set<String> setOfKeys = tag.getAllKeys();

        return setOfKeys.toArray(new String[0]);
    }
}
