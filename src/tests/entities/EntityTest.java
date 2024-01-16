package tests.entities;

import entities.Entity;
import entities.Knight;
import entities.Thief;
import items.Sword;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityTest {

    @Test
    public void testGetName() {
        String entityName = "TestEntity";
        double initialHp = 100.0;
        MockEntity entity = new MockEntity(entityName, initialHp);

        assertEquals(entityName, entity.getName());
    }

    @Test
    public void testGetHp() {
        String entityName = "TestEntity";
        double initialHp = 100.0;
        MockEntity entity = new MockEntity(entityName, initialHp);

        assertEquals(initialHp, entity.getHp(), 0.001);
    }

    @Test
    public void testIsDead() {
        String entityName = "TestEntity";
        double initialHp = 50.0;
        MockEntity entity = new MockEntity(entityName, initialHp);

        assertFalse(entity.isDead());

        entity.onDamage(50.0);

        assertTrue(entity.isDead());
    }

    @Test
    public void testOnDamage() {
        String entityName = "TestEntity";
        double initialHp = 100.0;
        MockEntity entity = new MockEntity(entityName, initialHp);

        double damage = 20.0;
        assertFalse(entity.onDamage(damage)); // MockEntity is not dead after taking damage
        assertEquals(initialHp - damage, entity.getHp(), 0.001);

        // Inflict damage greater than initial HP
        damage = 150.0;
        assertTrue(entity.onDamage(damage)); // MockEntity is dead after taking excessive damage
        assertEquals(0.0, entity.getHp(), 0.001);
    }

    // MockEntity class for testing
    private static class MockEntity extends Entity {
        public MockEntity(String name, double hp) {
            super(name, hp);
        }

        @Override
        public boolean onDamage(double damage) {
            return super.onDamage(damage);
        }

        @Override
        public void onKilled(Entity killer) {

        }
    }

    @Test
    public void testHealThief() {
        Thief thief = new Thief();
        double initialHealth = thief.getHp();

        // Simulate the thief taking damage
        double damage = 10.0;
        Knight knight = new Knight(new Sword("Sword", damage));
        knight.attack(thief);

        // Ensure that the thief's health is reduced after taking damage
        assertEquals(initialHealth - damage, thief.getHp());

        // Simulate healing the thief
        double healingAmount = 5.0;
        thief.heal(healingAmount);

        // Ensure that the thief's health is increased after healing
        assertEquals(initialHealth - damage + healingAmount, thief.getHp());

        // Attempt to heal a dead thief
        Knight knight2 = new Knight(new Sword("", 999));
        knight2.attack(thief);// Simulate fatal damage
        assertTrue(thief.isDead());
    }
}