package com.castlelecs.missedbundle.items.bundle.inventory;

public class UIGeneratorTools {

    /**
     * If a given parameter equals to zero method returns 1 in other case it returns the given value
     */
    public static int invalidateZero(int value) {
        if (value == 0)
            return 1;

        return value;
    }
}
