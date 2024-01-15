package entities;

import interfaces.Inventory;
import interfaces.Stealable;
import items.Item;
import items.ItemSlot;
import items.Sword;
import utils.Utils;

public class Thief extends Entity implements Inventory, Stealable {

    private ItemSlot[] inventory = {new ItemSlot(), new ItemSlot(), new ItemSlot()};
    private int selectedSlot = 0;

    public Thief() {
        super("Thieve", 15);
    }

    public void steal(Stealable target) {
        target.onStolen(this);
    }

    // On killed, the thief drops items in their inventory
    @Override
    protected void onKilled(Entity killer) {
        System.out.println("You bastard...");
        // If the killer has an inventory
        if (killer instanceof Inventory killWithInventory) {
            Utils.transferInventory(this, killWithInventory);
        }
    }

    @Override
    public ItemSlot[] viewInventory() {
        return inventory;
    }

    @Override
    public ItemSlot viewInventory(int index) {
        return inventory[index];
    }

    @Override
    public Item takeIntoInventory(ItemSlot slot) {
        for (ItemSlot itemSlot: this.inventory) {
            if (itemSlot.isEmpty()) {
                itemSlot.setItem(slot.takeItem());
            }
        }
        return null;
    }

    @Override
    public void selectSlot(int index) {
        if (index < 0 || index > inventory.length) {
            System.out.println("Error: Index out of range.");
            return;
        }
        this.selectedSlot = index;
    }

    @Override
    public ItemSlot getSelectedSlot() {
        return inventory[selectedSlot];
    }

    @Override
    public <T extends Entity & Inventory> boolean loot(T target) {
        if (target.isDead()) {
            Utils.transferInventory(target, this);
        } else {
            System.out.println("Error: You cannot loot the alive target");
        }
        return target.isDead();
    }

    @Override
    public void onStolen(Thief stealer) {
        if (!(getSelectedSlot().viewItem() instanceof Sword) && stealer.getSelectedSlot().viewItem() instanceof Sword) {
            System.out.println("Please do not hurt me!");
            Utils.transferInventory(this, stealer);
        } else {
            System.out.println("I can defend myself!");
        }
    }
}
