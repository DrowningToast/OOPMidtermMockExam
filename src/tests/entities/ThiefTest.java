package tests.entities;

import entities.Knight;
import entities.Thief;
import entities.Trader;
import interfaces.Inventory;
import interfaces.Stealable;
import items.*;
import org.junit.jupiter.api.Test;
import trading.TradeOffer;

import static org.junit.jupiter.api.Assertions.*;

class ThiefTest {

    @Test
    public void testStealFromEmptyTarget() {
        Thief thief = new Thief();
        Thief target = new Thief();

        assertDoesNotThrow(() -> thief.steal(target));
        assertNull(target.getSelectedSlot().viewItem());
        assertNull(thief.getSelectedSlot().viewItem());
    }

    @Test public void testStealWithSwordFromTarget() {
        Thief thief = new Thief();
        Thief target = new Thief();

        thief.takeIntoInventory(new ItemSlot(new Sword("StealerSword", 50)));

        assertDoesNotThrow(() -> thief.steal(target));
        assertNull(target.getSelectedSlot().viewItem());
        assertTrue(thief.getSelectedSlot().viewItem() instanceof Sword);
    }

    @Test
    public void testStealWithSwordFromTargetWithSword() {
        Thief thief = new Thief();
        Thief target = new Thief();

        thief.takeIntoInventory(new ItemSlot(new Sword("StealerSword", 50)));
        target.takeIntoInventory(new ItemSlot(new Sword("TargetSword", 50)));

        assertDoesNotThrow(() -> thief.steal(target));
        assertEquals("TargetSword", target.getSelectedSlot().viewItem().getName());
        assertEquals("StealerSword", thief.getSelectedSlot().viewItem().getName());
    }

    @Test
    public void testStealEntireInventoryFromTrader() {
        Thief thief = new Thief();
        Trader trader = new Trader(new TradeOffer[]{}, new ItemSlot[]{
                new ItemSlot(new Sword("TraderSword1", 15.0)),
                new ItemSlot(new Sword("TraderSword2", 20.0)),
                new ItemSlot(new Sword("TraderSword3", 25.0))
        });

        assertDoesNotThrow(() -> thief.steal(trader));

        // Ensure that the thief has stolen all items from the trader
        for (int i = 0; i < Math.min(thief.viewInventory().length, trader.viewInventory().length); i++) {
            assertNull(trader.viewInventory(i).viewItem());
            assertNotNull(thief.viewInventory(i).viewItem());
            assertTrue(thief.viewInventory(i).viewItem() instanceof Sword);
        }
    }

    @Test
    public void testLootDeadTarget() {
        Thief thief = new Thief();
        Thief deadThief = new Thief();
        deadThief.takeIntoInventory(new ItemSlot(new Gold()));
        Knight attacker = new Knight(new Sword("One-shot", 500));
        attacker.attack(deadThief);

        // Ensure that the thief can loot the dead target
        assertTrue(thief.loot(deadThief));
        assertNull(deadThief.viewInventory(0).viewItem());
        assertNotNull(thief.viewInventory(0).viewItem());
    }

    @Test
    public void testLootLivingTarget() {
        Thief thief = new Thief();
        Thief livingThief = new Thief();

        // Ensure that the thief cannot loot a living target
        assertFalse(thief.loot(livingThief));
    }

    @Test
    public void testLootTraderWithFullInventory() {
        Thief thief = new Thief();
        Trader trader = new Trader(new TradeOffer[]{}, new ItemSlot[]{
                new ItemSlot(new Sword("TraderSword1", 15.0)),
                new ItemSlot(new Sword("TraderSword2", 20.0)),
                new ItemSlot(new Sword("TraderSword3", 25.0)),
                new ItemSlot(new Sword("TraderSword4", 30.0))
        });
        Knight knight = new Knight(new Sword("one-shot", 500));
        knight.attack(trader);

        // Fill the thief's inventory
        thief.viewInventory(0).setItem(new Sword("ThiefSword" + "-1", 10.0));

        // Attempt to loot the trader
        assertDoesNotThrow(() -> thief.loot(trader));

        assertNotNull(thief.viewInventory(0).viewItem());
        assertTrue(thief.viewInventory(0).viewItem() instanceof Sword);
        assertEquals("ThiefSword" + "-1", thief.viewInventory(0).viewItem().getName());

        // Ensure that the thief's inventory is full and no additional items are stolen
        for (int i = 1; i < 3; i++) {
            assertNotNull(thief.viewInventory(i).viewItem());
            assertTrue(thief.viewInventory(i).viewItem() instanceof Sword);
            assertEquals("TraderSword" + (i), thief.viewInventory(i).viewItem().getName());
        }

        // Ensure that the partial of trader's inventory remains unchanged
        for (int i = 2; i < 4; i++) {
            assertNotNull(trader.viewInventory(i).viewItem());
            assertTrue(trader.viewInventory(i).viewItem() instanceof Sword);
            assertEquals("TraderSword" + (i + 1), trader.viewInventory(i).viewItem().getName());
        }

        for (int i = 0; i < 2; i++) {
            assertNull(trader.viewInventory(i).viewItem());
        }
    }

    @Test
    public void testStealTraderWithFullInventory() {
        Thief thief = new Thief();
        Trader trader = new Trader(new TradeOffer[]{}, new ItemSlot[]{
                new ItemSlot(new Sword("TraderSword1", 15.0)),
                new ItemSlot(new Sword("TraderSword2", 20.0)),
                new ItemSlot(new Sword("TraderSword3", 25.0)),
                new ItemSlot(new Sword("TraderSword4", 30.0))
        });

        // Fill the thief's inventory
        thief.viewInventory(0).setItem(new Sword("ThiefSword" + "-1", 10.0));

        // Attempt to loot the trader
        assertDoesNotThrow(() -> thief.steal(trader));

        assertNotNull(thief.viewInventory(0).viewItem());
        assertTrue(thief.viewInventory(0).viewItem() instanceof Sword);
        assertEquals("ThiefSword" + "-1", thief.viewInventory(0).viewItem().getName());

        // Ensure that the thief's inventory is full and no additional items are stolen
        for (int i = 1; i < 3; i++) {
            assertNotNull(thief.viewInventory(i).viewItem());
            assertTrue(thief.viewInventory(i).viewItem() instanceof Sword);
            assertEquals("TraderSword" + (i), thief.viewInventory(i).viewItem().getName());
        }

        // Ensure that the partial of trader's inventory remains unchanged
        for (int i = 2; i < 4; i++) {
            assertNotNull(trader.viewInventory(i).viewItem());
            assertTrue(trader.viewInventory(i).viewItem() instanceof Sword);
            assertEquals("TraderSword" + (i + 1), trader.viewInventory(i).viewItem().getName());
        }

        for (int i = 0; i < 2; i++) {
            assertNull(trader.viewInventory(i).viewItem());
        }
    }

    @Test
    public void testStealFromKnight() {
        Thief thief = new Thief();
        Knight knight = new Knight(new Sword("KnightSword", 25.0), new Shield("KnightShield", 0.2));

        // Thief steals from the knight
        assertDoesNotThrow(() -> thief.steal(knight));

        // Ensure that the thief has stolen the knight's items
        assertTrue(thief.viewInventory(0).viewItem() instanceof Sword);
        assertTrue(thief.viewInventory(1).viewItem() instanceof Shield);
        assertEquals("KnightSword", thief.viewInventory(0).viewItem().getName());
        assertEquals("KnightShield", thief.viewInventory(1).viewItem().getName());

        // Ensure that the knight's hands are empty after being stolen
        assertNull(knight.viewLeftHand());
        assertNull(knight.viewRightHand());
    }

}
