package com.castlelecs.missedbundle.items.bundle;

import com.castlelecs.missedbundle.utilities.Constants;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public final class BundleProperties extends Item.Properties {
    public BundleProperties() {
        super();

        setup();
    }

    private void setup() {
        tab(CreativeModeTab.TAB_TOOLS);
        stacksTo(Constants.NON_STACKED_MAX_STACK_SIZE);
        durability(Constants.MAX_BUNDLE_FULLNESS);
    }
}
