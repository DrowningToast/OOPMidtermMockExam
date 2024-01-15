package trading;

import items.Item;

public class TradeOffer<T extends Item, K extends Item> {
    private Class<T> receiveItem;
    private Class<K> offerItem;

    public TradeOffer(T receive, K offer) {
        this.receiveItem = (Class<T>) receive.getClass();
        this.offerItem = (Class<K>) offer.getClass();
    }

    public Class<T> getReceiveItem() {
        return this.receiveItem;
    }

    public Class<K> getOfferItem() {
        return this.offerItem;
    }

    public boolean checkReceiveItem(T item) {
        return receiveItem.getName().equals(item.getClass().getName());
    }

    public boolean checkOfferItem(K item) {
        return offerItem.getName().equals((item.getClass().getName()));
    }
}
