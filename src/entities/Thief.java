package entities;

import interfaces.Inventory;
import interfaces.Stealable;
import items.ItemSlot;
import items.Sword;

public class Thief extends Entity implements Inventory, Stealable {
    private ItemSlot[] inventory = {new ItemSlot(), new ItemSlot(), new ItemSlot()};
}
