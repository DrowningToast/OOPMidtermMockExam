package entities;

import interfaces.Inventory;
import interfaces.Stealable;
import items.Item;
import items.ItemSlot;
import trading.TradeOffer;
import utils.Utils;

public class Trader extends Entity implements Inventory, Stealable {
    private ItemSlot[] inventory = { new ItemSlot(), new ItemSlot(), new ItemSlot(), new ItemSlot(), new ItemSlot()};
    private TradeOffer[] offers;
    private int selectedSlot = 0;

    public Trader(TradeOffer[] offers, ItemSlot[] stock) {
        super("Trader", 20);
        this.offers = offers;
        System.arraycopy(stock, 0, inventory, 0, Math.min(stock.length, inventory.length));
        if (stock.length > 5) {
            System.out.println("Stock is bigger than the trader inventory");
        }
    }

    public void trade(Inventory buyer, int offerIndex) {
        if (offerIndex < 0 || offerIndex > offers.length) {
            System.out.println("Error: Index out of range");
            return;
        }
        TradeOffer offer = offers[offerIndex];
        // Check if the trader has offered item in the inventory or not
        for (ItemSlot sellerSlot: inventory) {
            if (offer.checkOfferItem(sellerSlot.viewItem())) {
                // Trader has the item, now check if the buyer has or not
                for (ItemSlot buyerSlot: buyer.viewInventory()) {
                    if (offer.checkReceiveItem(buyerSlot.viewItem())) {
                        // The buyer has the item, initialize the trading
                        Item payment = buyerSlot.takeItem();
                        Item offeredItem = sellerSlot.takeItem();
                        buyer.takeIntoInventory(new ItemSlot(offeredItem));
                        takeIntoInventory(new ItemSlot(payment));
                        return;
                    }
                }
            }
        }
        System.out.println("You do not have item required to trade.");
    }

    @Override
    protected void onKilled(Entity killer) {
        System.out.println("No! Please spare me...");
    }

    @Override
    public ItemSlot[] viewInventory() {
        return inventory;
    }

    @Override
    public ItemSlot viewInventory(int index) {
        if (index < 0 || index > inventory.length) {
            System.out.println("Index out of range");
            return null;
        }
        return inventory[index];
    }

    @Override
    public Item takeIntoInventory(ItemSlot slot) {
        if (slot.isEmpty()) {
            System.out.println("Error: ItemSlot is empty");
            return null;
        }
        for (int i = 0; i < inventory.length; i++) {
            ItemSlot s = viewInventory(i);
            if (s.isEmpty()) {
                s.setItem(slot.takeItem());
                return null;
            }
        }
        return null;
    }

    @Override
    public void selectSlot(int index) {
        if (index < 0 || index > inventory.length) {
            System.out.println("Error: Index out of range");
            return;
        }
        this.selectedSlot = index;
    }

    @Override
    public ItemSlot getSelectedSlot() {
        return inventory[selectedSlot];
    }

    @Override
    public <T extends Entity & Inventory> boolean loot(T looter) {
        return false;
    }

    @Override
    public void onStolen(Thief stealer) {
        Utils.transferInventory(this, stealer);
    }
}
