package interfaces;

import entities.Entity;
import items.Item;

public interface Inventory {
    <T extends Entity & Inventory> boolean loot(T looter);
}
