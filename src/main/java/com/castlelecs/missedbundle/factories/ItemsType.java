package com.castlelecs.missedbundle.factories;

import com.castlelecs.missedbundle.items.bundle.BundleItem;
import com.castlelecs.missedbundle.utilities.RegistryType;
import net.minecraft.world.item.Item;

public enum ItemsType implements RegistryType<Item> {
    BUNDLE("bundle");

    public final String rawValue;

    ItemsType(String rawValue) {
        this.rawValue = rawValue;
    }

    // MARK: - Overrided methods

    @Override
    public String getRawValue() {
        return this.rawValue;
    }

    @Override
    public Item getItem() {
        switch (this) {
            case BUNDLE:
                return BundleItem.shared;
            default:
                return null;
        }
    }
}
