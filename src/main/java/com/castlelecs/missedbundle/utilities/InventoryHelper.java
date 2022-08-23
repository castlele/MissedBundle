package com.castlelecs.missedbundle.utilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;

import java.util.Set;

public class InventoryHelper {

    public static void saveItems(ItemStack bundle, ItemStack newItems) {
        CompoundTag compoundTag = bundle.getOrCreateTag();
        var list = new ListTag();

        list.add(newItems.serializeNBT());

        compoundTag.put(newItems.getDescriptionId(), list);
        bundle.setTag(compoundTag);
    }

    public static ItemStack[] getItems(ItemStack bundle) {
        CompoundTag compoundTag = bundle.getOrCreateTag();
        Set<String> setOfKeys = compoundTag.getAllKeys();
        String[] keys = setOfKeys.toArray(new String[setOfKeys.size()]);
        ItemStack[] inventory = new ItemStack[keys.length];

        for (var index = 0; index < keys.length; index++) {
            ListTag list = compoundTag.getList(keys[index], Tag.TAG_COMPOUND);
            CompoundTag compound = list.getCompound(0);

            ItemStack itemStack = ItemStack.of(compound);
            inventory[index] = itemStack;
        }

        return inventory;
    }
}
