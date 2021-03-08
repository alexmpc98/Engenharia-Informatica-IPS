package pt.pa.model;

public class Bridge {
    private String name;
    private int cost;

    public Bridge(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }
}
