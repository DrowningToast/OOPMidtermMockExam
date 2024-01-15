package tests.interfaces;

import entities.Entity;
import interfaces.Inventory;
import items.Gold;
import items.Item;
import items.ItemSlot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {


    @Test
    public void testViewInventory() {
        Inventory inventory = new InventoryImplementation();
        ItemSlot[] slots = inventory.viewInventory();

        assertNotNull(slots);
        assertEquals(3, slots.length);
    }

    @Test
    public void testViewInventoryAtIndex() {
        Inventory inventory = new InventoryImplementation();
        ItemSlot slot = inventory.viewInventory(2);

        assertNotNull(slot);
    }

    @Test
    public void testTakeIntoInventory() {
        Inventory inventory = new InventoryImplementation();
        ItemSlot sourceSlot = new ItemSlot(new Gold());
        Item item = inventory.takeIntoInventory(sourceSlot);

        assertNull(item);
    }

    @Test
    public void testSelectSlot() {
        Inventory inventory = new InventoryImplementation();
        assertDoesNotThrow(() -> inventory.selectSlot(2));
    }

    @Test
    public void testGetSelectedSlot() {
        Inventory inventory = new InventoryImplementation();
        ItemSlot selectedSlot = inventory.getSelectedSlot();

        assertNull(selectedSlot);
    }

    @Test
    public void testLoot() {
        Inventory inventory = new InventoryImplementation();
        InventoryImplementation looter = new InventoryImplementation();

        assertFalse(inventory.loot(looter));
    }

    // Updated InventoryImplementation class
    private static class InventoryImplementation extends Entity implements Inventory {

        public InventoryImplementation () {
            super("InventoryImplementation", 10);
        }

        public ItemSlot[] inventory = {new ItemSlot(), new ItemSlot(), new ItemSlot()};

        @Override
        public ItemSlot[] viewInventory() {
            return inventory;
        }

        @Override
        public ItemSlot viewInventory(int index) {
            if (index < 0 || index >= inventory.length) {
                return null;
            }
            return inventory[index];
        }

        @Override
        public Item takeIntoInventory(ItemSlot slot) {
            for (ItemSlot itemSlot : this.inventory) {
                if (itemSlot.isEmpty()) {
                    itemSlot.setItem(slot.takeItem());
                }
            }
            return null;
        }

        @Override
        public void selectSlot(int index) {
            // No-op for simplicity
        }

        @Override
        public ItemSlot getSelectedSlot() {
            return null; // Return null for simplicity
        }

        @Override
        public <T extends Entity & Inventory> boolean loot(T looter) {
            return false;
        }

        @Override
        protected void onKilled(Entity killer) {

        }
    }
}
