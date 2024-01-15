package items;

public class ItemSlot {
    private Item item;

    public ItemSlot() {}
    public ItemSlot(Item item) {
        this.item = item;
    }

    public Item viewItem() {
        return item;
    }

    public Item takeItem() {
        Item take = this.item;
        this.item = null;
        return take;
    }

    public Item setItem(Item item) {
        Item swap = this.item;
        this.item = item;
        return swap;
    }

    public boolean isEmpty () {
        return item == null;
    }
}
