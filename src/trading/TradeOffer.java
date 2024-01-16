package trading;

import items.Item;

public class TradeOffer<T extends Item, K extends Item> {
    private T receiveItem;
    private K offerItem;

    public TradeOffer(T receive, K offer) {
        this.receiveItem =  receive;
        this.offerItem = offer;
    }

    public String getReceiveItem() {
        return this.receiveItem.getName();
    }

    public String getOfferItem() {
        return this.offerItem.getName();
    }

    public boolean checkReceiveItem(T item) {
        return receiveItem.getName().equals(item.getName());
    }

    public boolean checkOfferItem(K item) {
        return offerItem.getName().equals(item.getName());
    }
}
