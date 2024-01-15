package interfaces;

import entities.Entity;
import items.Item;
import items.ItemSlot;

public interface Inventory {
    ItemSlot[] viewInventory();
    ItemSlot viewInventory(int index);
    Item takeIntoInventory(ItemSlot slot);
    void selectSlot(int index);
    ItemSlot getSelectedSlot();
    <T extends Entity & Inventory> boolean loot(T looter);
}
