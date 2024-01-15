package tests.items;

import items.Shield;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShieldTest {

    @Test
    public void testGetDefPow() {
        String shieldName = "TestShield";
        double defPow = 25.5;
        Shield shield = new Shield(shieldName, defPow);

        assertEquals(defPow, shield.getDefPow(), 0.001);
    }

    @Test
    public void testGetName() {
        String shieldName = "TestShield";
        double defPow = 25.5;
        Shield shield = new Shield(shieldName, defPow);

        assertEquals(shieldName, shield.getName());
    }
}