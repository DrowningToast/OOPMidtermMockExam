package tests.entities;
import entities.Knight;
import entities.Thief;
import items.Shield;
import items.Sword;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KnightTest {

    @Test
    public void testAttackWithSwordInRightHand() {
        Knight knight = new Knight(new Sword("Excalibur", 10));
        Thief target = new Thief();

        knight.attack(target);

        // Check if the target took damage based on sword attack power
        assertEquals(5, target.getHp());
    }

    @Test
    public void testAttackWithSwordInLeftHand() {
        Knight knight = new Knight(new Sword("Excalibur", 10));
        Knight target = new Knight(new Shield("Shield", 0.2));

        knight.attack(target);

        // Check if the target took damage based on sword attack power after considering shield defense
        assertEquals(22, target.getHp());
    }

    @Test
    public void testAttackWithNoSword() {
        Knight knight = new Knight();
        Thief target = new Thief();

        knight.attack(target);

        // Check if the target took default damage (5) when knight has no sword
        assertEquals(10, target.getHp());
    }

    @Test
    public void testOnStolenWithSword() {
        Knight knight = new Knight(new Sword("Excalibur", 10));
        Thief stealer = new Thief();

        knight.onStolen(stealer);

        // Check if the thief was attacked by the knight
        assertEquals(5, stealer.getHp());
    }

    @Test
    public void testOnStolenWithoutSword() {
        Knight knight = new Knight(new Shield("KnightShield", 10));
        Thief stealer = new Thief();

        stealer.steal(knight);

        // Check if the knight's items were stolen by the thief
        assertTrue(stealer.viewInventory(0).viewItem() instanceof Shield);
        assertNull(knight.viewRightHand()); // Knight's right hand should be empty
    }

    @Test
    public void testOnDamageWithShield() {
        Knight knight = new Knight(new Shield("Shield", 0.2));
        double initialHp = knight.getHp();

        knight.onDamage(10);

        // Check if the knight's HP was reduced, considering shield defense
        assertEquals(initialHp - 8, knight.getHp());
    }

    @Test
    public void testViewHands() {
        Knight knight = new Knight(new Sword("Excalibur", 10), new Shield("Shield", 0.2));

        // Check if the knight's hands return the expected items
        assertTrue(knight.viewLeftHand() instanceof Shield);
        assertTrue(knight.viewRightHand() instanceof Sword);
    }
}