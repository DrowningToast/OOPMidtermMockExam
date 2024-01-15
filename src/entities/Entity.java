package entities;

public abstract class Entity {
    private String name;
    private double maxHp = 0;
    private double hp = 0;

    public Entity (String name, double hp) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
    }

    public String getName() {
        return this.name;
    }

    public double getHp() {
        return hp;
    }

    public boolean isDead() {
        return this.hp <= 0.0;
    }

    protected boolean onDamage(double damage) {
        this.hp = Math.max(0, this.hp -= damage);
        return isDead();
    }

    protected abstract void onKilled(Entity killer);
}
