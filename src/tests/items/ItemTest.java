package tests.items;

import items.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    public void testGetName() {
        String itemName = "TestItem";
        Item item = new MockItem(itemName);

        assertEquals(itemName, item.getName());
    }

    // MockItem class for testing
    private static class MockItem extends Item {
        public MockItem(String name) {
            super(name);
        }
    }
}