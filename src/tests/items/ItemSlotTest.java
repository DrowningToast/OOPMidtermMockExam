package tests.items;

import items.Item;
import items.ItemSlot;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ItemSlotTest {
    @Test
    public void testViewItem() {
        Item item = new MockItem("TestItem");
        ItemSlot itemSlot = new ItemSlot(item);

        assertEquals(item, itemSlot.viewItem());
    }

    @Test
    public void testTakeItem() {
        Item item = new MockItem("TestItem");
        ItemSlot itemSlot = new ItemSlot(item);

        assertEquals(item, itemSlot.takeItem());
        assertTrue(itemSlot.isEmpty());
    }

    @Test
    public void testSetItem() {
        Item item1 = new MockItem("TestItem1");
        Item item2 = new MockItem("TestItem2");
        ItemSlot itemSlot = new ItemSlot(item1);

        assertEquals(item1, itemSlot.setItem(item2));
        assertEquals(item2, itemSlot.viewItem());
    }

    @Test
    public void testIsEmpty() {
        ItemSlot emptyItemSlot = new ItemSlot();
        ItemSlot nonEmptyItemSlot = new ItemSlot(new MockItem("TestItem"));

        assertTrue(emptyItemSlot.isEmpty());
        assertFalse(nonEmptyItemSlot.isEmpty());
    }

    // MockItem class for testing
    private static class MockItem extends Item {
        public MockItem(String name) {
            super(name);
        }
    }
}