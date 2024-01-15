package items;

public class Sword extends Item {
    private double atkPow;

    public Sword(String name, double atkPow) {
        super(name);
        this.atkPow = atkPow;
    }

    public double getAtkPow() {
        return atkPow;
    }
}
