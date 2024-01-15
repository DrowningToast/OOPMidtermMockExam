package tests.items;

import items.Item;
import items.Sword;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwordTest {

    @Test
    public void testGetAtkPow() {
        String swordName = "TestSword";
        double atkPow = 35.7;
        Sword sword = new Sword(swordName, atkPow);

        assertEquals(atkPow, sword.getAtkPow(), 0.001);
    }

    @Test
    public void testGetName() {
        String swordName = "TestSword";
        double atkPow = 35.7;
        Sword sword = new Sword(swordName, atkPow);

        assertEquals(swordName, sword.getName());
    }

    @Test
    public void testSwordInheritsFromItem() {
        String swordName = "TestSword";
        double atkPow = 35.7;
        Sword sword = new Sword(swordName, atkPow);

        // Ensure that Sword is an instance of the Item class
        assertTrue(sword instanceof Item);

        // Ensure that the getName method of Sword returns the expected value
        assertEquals(swordName, sword.getName());
    }
}