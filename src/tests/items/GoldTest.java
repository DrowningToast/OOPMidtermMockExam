package tests.items;

import items.Gold;
import items.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoldTest {
    @Test
    public void testGetName() {
        Gold gold = new Gold();

        assertEquals("items.Gold", gold.getName());
    }

    @Test
    public void testGoldInheritsFromItem() {
        Gold gold = new Gold();

        // Ensure that Gold is an instance of the Item class
        assertTrue(gold instanceof Item);

        // Ensure that the getName method of Gold returns the expected value
        assertEquals("items.Gold", gold.getName());
    }
}