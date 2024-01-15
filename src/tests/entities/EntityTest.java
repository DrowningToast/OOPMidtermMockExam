package tests.entities;

import entities.Entity;
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
}