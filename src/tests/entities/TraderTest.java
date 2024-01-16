package tests.entities;

import entities.Trader;
import items.Gold;
import items.ItemSlot;
import items.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import trading.TradeOffer;

import static org.junit.jupiter.api.Assertions.*;

class TraderTest {

    Trader trader;
    Trader buyer;

    @BeforeEach
    protected void setup() {
        trader = new Trader(
                new TradeOffer[] { new TradeOffer(new Gold(), new Sword("TraderSword",0)) },
                new ItemSlot[] {}
        );
        buyer = new Trader(
                null,
                new ItemSlot[] {new ItemSlot(new Gold())}
        );
    }

    @Test
    public void testTradeGoldForSword() {
        buyer.takeIntoInventory(new ItemSlot(new Gold()));
        trader.takeIntoInventory(new ItemSlot(new Sword("TraderSword", 50)));

        assertDoesNotThrow( () -> trader.trade(buyer, 0));

        assertEquals(new Gold().getName(), trader.viewInventory(0).viewItem().getName());
        assertEquals(new Sword("TraderSword", 50).getName(), buyer.viewInventory(0).viewItem().getName());
    }

    @Test
    public void testTradeNoGoldForSword() {
        trader.takeIntoInventory(new ItemSlot(new Sword("TraderSword", 50)));
        assertDoesNotThrow( () -> trader.trade(buyer, 0));

        assertEquals(new Sword("TraderSword", 0).getName(), trader.viewInventory(0).viewItem().getName());
        assertNull(buyer.viewInventory(0));
    }

    @Test
    public void testTradeGoldForNoSword() {
        buyer.takeIntoInventory(new ItemSlot(new Gold()));

        assertDoesNotThrow( () -> trader.trade(buyer, 0));

        assertEquals(new Gold().getName(), buyer.viewInventory(0).viewItem().getName());
        assertNull(trader.viewInventory(0).viewItem());
    }

    @Test
    public void testTradeGoldForSwordFullInventory() {
        for (int i = 0; i < trader.viewInventory().length; i++) {
            trader.takeIntoInventory(new ItemSlot(new Sword("TraderSword" + i, 50)));
            buyer.takeIntoInventory(new ItemSlot(new Gold()));
        }
    }


}