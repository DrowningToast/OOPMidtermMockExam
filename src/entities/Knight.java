package entities;

import interfaces.Inventory;
import interfaces.Stealable;
import items.Item;
import items.ItemSlot;
import items.Shield;
import items.Sword;

public class Knight extends Entity implements Stealable {
    private ItemSlot leftHand = new ItemSlot();
    private ItemSlot rightHand = new ItemSlot();

    public Knight() {
        super("Knight", 30);
    }

    public Knight(Item rightItem) {
        this();
        this.rightHand.setItem(rightItem);
    }

    public Knight(Item rightItem, Item leftItem) {
        this(rightItem);
        this.leftHand.setItem(leftItem);
    }

    public void attack(Entity target) {
        if (this.rightHand.viewItem() instanceof Sword sword) {
            target.onDamage(sword.getAtkPow());
        } else if (this.leftHand.viewItem() instanceof Sword sword) {
            target.onDamage(sword.getAtkPow());
        } else {
            target.onDamage(5);
        }
    }

    @Override
    protected void onKilled(Entity killer) {
        System.out.println("No!");
        if (killer instanceof Inventory killerWithInventory) {
            if (!leftHand.isEmpty()) {
                killerWithInventory.takeIntoInventory(leftHand);
            }
            if (!rightHand.isEmpty()) {
                killerWithInventory.takeIntoInventory(rightHand);
            }
        }
    }

    @Override
    public void onStolen(Thief stealer) {
        if (rightHand.viewItem() instanceof Sword) {
            System.out.println("Don't be so cheeky!");
            attack(stealer);
            return;
        }
        System.out.println("Z...z...");
        if (!rightHand.isEmpty()) {
            stealer.takeIntoInventory(rightHand);
        }
        if (!leftHand.isEmpty()) {
            stealer.takeIntoInventory(leftHand);
        }
    }

    @Override
    public boolean onDamage(double damage) {
        if (leftHand.viewItem() instanceof Shield shield) {
            return super.onDamage(damage * (1 - shield.getDefPow()));
        } else if (rightHand.viewItem() instanceof Shield shield) {
            return super.onDamage(damage * (1 - shield.getDefPow()));
        } else {
            return super.onDamage(damage);
        }
    }

    public Item viewLeftHand() {
        return this.leftHand.viewItem();
    }

    public Item viewRightHand() {
        return this.rightHand.viewItem();
    }
}
