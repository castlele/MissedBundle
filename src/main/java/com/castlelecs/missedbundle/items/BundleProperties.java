package com.castlelecs.missedbundle.items;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public final class BundleProperties extends Item.Properties {
    public BundleProperties() {
        super();

        setup();
    }

    private final void setup() {
        tab(CreativeModeTab.TAB_TOOLS);
    }
}
