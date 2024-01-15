package tests;

import entities.Entity;
import interfaces.Inventory;
import items.Gold;
import items.Item;
import items.ItemSlot;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    public void testTransferInventory() {
        // Create source and target inventories
        InventoryMock source = new InventoryMock(new ItemSlot[]{
                new ItemSlot(new MockItem("Item1")),
                new ItemSlot(new MockItem("Item2")),
                new ItemSlot(new MockItem("Item3"))
        });
        InventoryMock target = new InventoryMock();

        // Ensure the transfer is successful
        Utils.transferInventory(source, target);

        System.out.println(target.viewInventory(0).viewItem());


        // Check the contents of the inventories after transfer
        assertArrayEquals(new Item[]{null, null, null}, new Item[]{target.viewInventory(0).viewItem(), target.viewInventory(1).viewItem(), target.viewInventory(2).viewItem()} );
        assertArrayEquals(new String[]{"Item1", "Item2", "Item3"}, new String[]{
                source.viewInventory(0).viewItem().getName(),
                source.viewInventory(1).viewItem().getName(),
                source.viewInventory(2).viewItem().getName()}
        );
    }

    // Add more tests as needed

    // Mock class for Inventory
    private static class InventoryMock implements Inventory {
        private ItemSlot[] inventory = {new ItemSlot(), new ItemSlot(), new ItemSlot()};

        public InventoryMock() {}

        public InventoryMock(ItemSlot[] inventory) {
            this.inventory = inventory;
        }

        @Override
        public ItemSlot[] viewInventory() {
            ItemSlot[] slots = new ItemSlot[inventory.length];
            for (int i = 0; i < inventory.length; i++) {
                slots[i] = new ItemSlot(inventory[i].viewItem());
            }
            return slots;
        }

        // Additional methods for testing purposes
        public Item[] getInventory() {
            return (Item[]) Arrays.stream(inventory).map( slot -> slot.viewItem()).toArray();
        }

        @Override
        public ItemSlot viewInventory(int index) {
            return new ItemSlot(inventory[index].viewItem());
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
            return null;        }

        @Override
        public void selectSlot(int index) {
        }

        @Override
        public ItemSlot getSelectedSlot() {
            return null;
        }

        @Override
        public <T extends Entity & Inventory>boolean loot(T looter) {
            return false;
        }
    }

    // MockItem class for testing
    private static class MockItem extends Item {
        public MockItem(String name) {
            super(name);
        }
    }
}