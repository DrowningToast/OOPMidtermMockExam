package utils;

import interfaces.Inventory;
import items.ItemSlot;

public class Utils {
    public static void transferInventory(Inventory from, Inventory to) {
        ItemSlot[] fromInventory = from.viewInventory();
        for (ItemSlot fromSlot: fromInventory) {
            if (!fromSlot.isEmpty()) {
                for (ItemSlot toSlot: to.viewInventory()) {
                    // If the source has an item in the slot
                    if (toSlot.isEmpty()) {
                        // Find an empty space in the to inventory
                        // If the slot is empty, transfer the item into it
                        toSlot.setItem(fromSlot.takeItem());
                        break;
                    }
                }
            }
        }
    }
}
