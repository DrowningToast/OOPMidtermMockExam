package entities;

import interfaces.Inventory;
import interfaces.Stealable;
import trading.TradeOffer;

public class Trader extends Entity implements Inventory, Stealable {
    private ItemSlot[] inventory = { new ItemSlot(), new ItemSlot(), new ItemSlot(), new ItemSlot(), new ItemSlot()};
    private TradeOffer[] offers;
    private int selectedSlot = 0;

    public Trader(TradeOffer[] offers, ItemSlot[] stock) {
        super("Trader", 20);
        this.offers = offers;
        for (int i = 0; i < Math.min(inventory.length, stock.length); i++) {
            inventory[i] = stock[i];
        }
        if (stock.length > 5) {
            System.out.println("Stock is bigger than the trader inventory");
        }
    }

    @Override
    public <T extends Entity & Inventory> boolean loot(T looter) {
        return false;
    }
}
