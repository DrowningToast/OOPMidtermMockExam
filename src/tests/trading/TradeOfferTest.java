package tests.trading;

import items.Gold;
import items.Sword;
import org.junit.jupiter.api.Test;
import trading.TradeOffer;

import static org.junit.jupiter.api.Assertions.*;

class TradeOfferTest {
    @Test
    public void testGetReceiveItem() {
        Sword sword = new Sword("TestSword", 30.5);
        TradeOffer<Sword, Gold> tradeOffer = new TradeOffer<>(sword, new Gold());

        assertEquals(Sword.class, tradeOffer.getReceiveItem());
    }

    @Test
    public void testGetOfferItem() {
        Gold gold = new Gold();
        TradeOffer<Sword, Gold> tradeOffer = new TradeOffer<>(new Sword("TestSword", 30.5), gold);

        assertEquals(Gold.class, tradeOffer.getOfferItem());
    }

    @Test
    public void testCheckReceiveItem() {
        Sword sword = new Sword("TestSword", 30.5);
        TradeOffer<Sword, Gold> tradeOffer = new TradeOffer<>(sword, new Gold());

        assertTrue(tradeOffer.checkReceiveItem(sword));
    }

    @Test
    public void testCheckOfferItem() {
        Gold gold = new Gold();
        TradeOffer<Sword, Gold> tradeOffer = new TradeOffer<>(new Sword("TestSword", 30.5), gold);

        assertTrue(tradeOffer.checkOfferItem(gold));
    }
}