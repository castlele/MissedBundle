package com.castlelecs.missedbundle.items.bundle.inventory;

import java.util.ArrayList;
import java.util.List;

import com.castlelecs.missedbundle.utilities.IntPointer;
import net.minecraft.world.item.ItemStack;

public class InventoryMatrix {

    private record MatrixSize(int rows, int columns) {

        public static MatrixSize M_EMPTY = new MatrixSize(0, 0);
        public static MatrixSize M_1_X_1 = new MatrixSize(1, 1);
        public static MatrixSize M_2_X_2 = new MatrixSize(2, 2);
        public static MatrixSize M_3_X_3 = new MatrixSize(3, 3);
        public static MatrixSize M_4_X_4 = new MatrixSize(4, 4);
        public static MatrixSize M_5_X_5 = new MatrixSize(5, 5);
        public static MatrixSize M_6_X_6 = new MatrixSize(6, 6);
        public static MatrixSize M_7_X_7 = new MatrixSize(7, 7);
        public static MatrixSize M_8_X_8 = new MatrixSize(8, 8);

        public static MatrixSize getMatrixSize(int listSize) {
             if (listSize == M_EMPTY.size())
                 return M_EMPTY;

             else if (listSize == M_1_X_1.size())
                 return M_1_X_1;

             else if (listSize <= M_2_X_2.size() && listSize > M_1_X_1.size())
                 return M_2_X_2;

             else if (listSize <= M_3_X_3.size() && listSize > M_2_X_2.size())
                 return M_3_X_3;

             else if (listSize <= M_4_X_4.size() && listSize > M_3_X_3.size())
                 return M_4_X_4;

             else if (listSize <= M_5_X_5.size() && listSize > M_4_X_4.size())
                 return M_5_X_5;

             else if (listSize <= M_6_X_6.size() && listSize > M_5_X_5.size())
                 return M_6_X_6;

             else if (listSize <= M_7_X_7.size() && listSize > M_6_X_6.size())
                 return M_7_X_7;

             else if (listSize <= M_8_X_8.size() && listSize > M_7_X_7.size())
                 return M_8_X_8;

             return M_EMPTY;

        }

        public int size() {
            return rows * columns;
        }
    }

    private final List<List<ItemStack>> storage;

    // MARK: - Init
    
    public InventoryMatrix(ItemStack[] items) {
        this.storage = generateStorage(items);
    }

    // MARK: - Public methods

    public List<ItemStack> get(int index) {
        return storage.get(index);
    }

    public boolean isEmpty() {
        return storage.isEmpty();
    }

    public int getHeight() {
        return storage.size();
    }

    public int getWidth() {
        if (isEmpty())
            return 0;

        return storage.get(0).size();
    }

    // MARK: - Private methods

    private List<List<ItemStack>> generateStorage(ItemStack[] items) {
        int itemsSize = items.length;
        MatrixSize matrixSize = MatrixSize.getMatrixSize(itemsSize);
        var index = new IntPointer(0);
        int rows = 0;
        List<List<ItemStack>> inventory = new ArrayList<>(rows);

        while (index.getValue() < itemsSize) {
            List<ItemStack> row = generateRow(matrixSize.rows(), index, items);
            rows++;

            var copyInventory = List.copyOf(inventory);
            inventory = new ArrayList<>(rows);

            inventory.addAll(copyInventory);
            inventory.add(row);
        }

        return inventory;
    }

    private List<ItemStack> generateRow(int rowSize, IntPointer index, ItemStack[] items) {
        List<ItemStack> row = new ArrayList<>(rowSize);
        int column = 0;

        while (column < rowSize) {
            if (index.getValue() >= items.length) {
                column++;
                continue;
            }

            row.add(items[index.getValue()]);
            column++;
            index.increase();
        }
        
        return row;
    }
}
