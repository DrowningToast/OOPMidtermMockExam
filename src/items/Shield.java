package items;

public class Shield extends Item{
    private double defPow;

    public Shield(String name, double defPow) {
        super(name);
        this.defPow = defPow;
    }

    public double getDefPow() {
        return defPow;
    }
}
