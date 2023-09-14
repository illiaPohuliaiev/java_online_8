package ua.com.alevel;

public class CarDealerShip {
    private int id;
    private String name;

    public CarDealerShip(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setDefaultCarDealerShip() {
        this.id = -1;
    }
}
