package ua.com.alevel;

public class Car {
    private int id;
    private String name;
    private int carDealerShipId;

    public Car(int id, String name, int groupId) {
        this.id = id;
        this.name = name;
        this.carDealerShipId = groupId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCarDealerShipId() {
        return carDealerShipId;
    }

    public void setCarDealerShipId(int carDealerShipId) {
        this.carDealerShipId = carDealerShipId;
    }
}
