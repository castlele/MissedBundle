package com.castlelecs.missedbundle.utilities;

public class IntPointer {
    private int value;

    public IntPointer(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void increase() {
        value++;
    }

    public void decrease() {
        value--;
    }
}
