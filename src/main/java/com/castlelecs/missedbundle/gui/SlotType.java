package com.castlelecs.missedbundle.gui;

import com.castlelecs.missedbundle.MissedBundle;
import com.castlelecs.missedbundle.utilities.Constants;
import net.minecraft.resources.ResourceLocation;

public enum SlotType {
    EMPTY,
    STANDARD;

    public ResourceLocation getResource() {
        return switch (this) {
            case EMPTY -> MissedBundle.location(Constants.EMPTY_PLUS_SLOT);
            case STANDARD -> MissedBundle.location(Constants.EMPTY_SLOT);
        };
    }
}
