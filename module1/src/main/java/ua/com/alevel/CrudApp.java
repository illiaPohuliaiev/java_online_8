package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CrudApp {
    private static Car[] cars = new Car[10];
    private static int carCount = 0;
    private static int carCounter = 1;
    private static CarDealerShip[] carDealerShips = new CarDealerShip[10];
    private static int cardealershipCount = 0;
    private static int cardealershipCounter = 1;
    private static final int DEFAULT_CARDEALERSHIP_ID = -1;

    private static void resizeCarsArray() {
        int newSize = cars.length * 2;
        Car[] newArray = new Car[newSize];
        System.arraycopy(cars, 0, newArray, 0, carCount);
        cars = newArray;
    }
    private static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return !str.isEmpty();
    }
    private static void listAllCarDealerShips() {
        System.out.println("Car dealership list:");
        for (int i = 0; i < cardealershipCount; i++) {
            CarDealerShip carDealerShip = carDealerShips[i];
            System.out.println("ID: " + carDealerShip.getId() + ", Name: " + carDealerShip.getName());
        }
    }
    private static void resizeCarDealerShipsArray() {
        int newSize = carDealerShips.length * 2;
        CarDealerShip[] newArray = new CarDealerShip[newSize];
        System.arraycopy(carDealerShips, 0, newArray, 0, cardealershipCount);
        carDealerShips = newArray;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("1. Create Car");
            System.out.println("2. Delete Car");
            System.out.println("3. Show all cars");
            System.out.println("4. Create Car Dealerships");
            System.out.println("5. Delete Car Dealerships");
            System.out.println("6. Show all Car Dealerships");
            System.out.println("7. Add cars to Car Dealerships");
            System.out.println("8. Show cars in Car Dealerships");
            System.out.println("9. Close App");

            int choice = 0;
            try {
                choice = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println("ERROR. Try one more.");
                continue;
            }
            switch (choice) {
                case 1: createCar(reader);
                    break;
                case 2: deleteCar(reader);
                    break;
                case 3: listAllCars();
                    break;
                case 4: createCarDealerShip(reader);
                    break;
                case 5: deleteCarDealerShip(reader);
                    break;
                case 6: listAllCarDealerShips();
                    break;
                case 7: addCarsToCarDealerShip(reader);
                    break;
                case 8: listCarsInCarDealerShip(reader);
                    break;
                case 9: System.exit(0);
                    break;
                default: System.out.println("ERROR. Try one more.");
            }
        }
    }
    private static void createCar(BufferedReader reader) throws IOException {
        if (carCount == cars.length) {
            resizeCarsArray();
        }
        System.out.print("Please enter car name: ");
        String name = reader.readLine();
        cars[carCount] = new Car(carCounter, name, DEFAULT_CARDEALERSHIP_ID);
        carCount++;
        carCounter++;
        System.out.println("Car is created.");
    }
    private static void deleteCar(BufferedReader reader) throws IOException {
        listAllCars();
        System.out.print("Please enter car ID, which you want to delete: ");
        String carIdStr = reader.readLine();

        if (isNumeric(carIdStr)) {
            int carId = Integer.parseInt(carIdStr);
            boolean carExists = false;

            for (int i = 0; i < carCount; i++) {
                if (cars[i].getId() == carId) {
                    carExists = true;
                    int carDealerShipId = cars[i].getCarDealerShipId();

                    for (int j = i; j < carCount - 1; j++) {
                        cars[j] = cars[j + 1];
                    }
                    carCount--;

                    if (carDealerShipId != DEFAULT_CARDEALERSHIP_ID) {
                        for (int j = 0; j < carCount; j++) {
                            if (cars[j].getCarDealerShipId() == carDealerShipId) {
                                cars[j].setCarDealerShipId(DEFAULT_CARDEALERSHIP_ID);
                            }
                        }
                    }
                    System.out.println("Car is deleted.");
                    if (i == carCount) {
                        carCounter--;
                    }
                    break;
                }
            }

            if (!carExists) {
                System.out.println(" ERROR. Enter the correct ID.");
            }
        } else {
            System.out.println("ERROR. ID must have a number.");
        }
    }

    private static void createCarDealerShip(BufferedReader reader) throws IOException {
        if (cardealershipCount == carDealerShips.length) {
            resizeCarDealerShipsArray();
        }
        System.out.print("Please enter car dealership name: ");
        String cardealershipName = reader.readLine();
        carDealerShips[cardealershipCount] = new CarDealerShip(cardealershipCounter, cardealershipName);
        cardealershipCounter++;
        cardealershipCount++;
        System.out.println("Car dealership is created.");
    }

    private static void deleteCarDealerShip(BufferedReader reader) throws IOException {
        listAllCarDealerShips();
        System.out.print("Please enter car dealership ID, which you want to delete: ");
        String carDealerShipIdStr = reader.readLine();

        if (isNumeric(carDealerShipIdStr)) {
            int carDealerShipId = Integer.parseInt(carDealerShipIdStr);
            boolean carDealerShipExists = false;

            for (int i = 0; i < cardealershipCount; i++) {
                if (carDealerShips[i].getId() == carDealerShipId) {
                    carDealerShipExists = true;
                    for (int j = i; j < cardealershipCount - 1; j++) {
                        carDealerShips[j] = carDealerShips[j + 1];
                    }
                    cardealershipCount--;

                    for (int j = 0; j < carCount; j++) {
                        if (cars[j].getCarDealerShipId() == carDealerShipId) {
                            cars[j].setCarDealerShipId(DEFAULT_CARDEALERSHIP_ID);
                        }
                    }
                    System.out.println("Car dealership is deleted.");

                    if (i == cardealershipCount) {
                        cardealershipCounter--;
                    }
                    break;
                }
            }

            if (!carDealerShipExists) {
                System.out.println("ERROR. Enter the correct ID.");
            }
        } else {
            System.out.println("ERROR. ID must have a number.");
        }
    }

    private static void listAllCars() {
        if (carCount != 0) {
            System.out.println("Car list:");
            for (int i = 0; i < carCount; i++) {
                Car car = cars[i];
                int carId = car.getId();
                String carName = car.getName();
                int carDealerShipId = car.getCarDealerShipId();

                String carDealerShipName = "default";

                for (int j = 0; j < cardealershipCount; j++) {
                    if (carDealerShips[j].getId() == carDealerShipId) {
                        carDealerShipName = carDealerShips[j].getName();
                        break;
                    }
                }
                String idIfDefault = "default";
                if (carDealerShipId == -1) {
                    System.out.println("ID: " + carId + ", Car name: " + carName + ", Car dealership ID: " + idIfDefault + ", Car dealership name: " + carDealerShipName);
                } else {
                    System.out.println("ID: " + carId + ", Car name: " + carName + ", Car dealership ID: " + carDealerShipId + ", Car dealership name: " + carDealerShipName);

                }
            }
        } else {
            System.out.println("ERROR. Try one more.");
        }

    }

    private static void listCarsInCarDealerShip(BufferedReader reader) throws IOException {
        if (carCount != 0 && cardealershipCount != 0) {
            listAllCarDealerShips();
            System.out.print("Please enter car dealership ID: ");
            String carDealerShipIdStr = reader.readLine();

            if (isNumeric(carDealerShipIdStr)) {
                int carDealerShipId = Integer.parseInt(carDealerShipIdStr);
                boolean carDealerShipExists = false;
                int carsInCarDealerShip = 0;


                for (int i = 0; i < cardealershipCount; i++) {
                    if (carDealerShips[i].getId() == carDealerShipId) {
                        carDealerShipExists = true;
                        System.out.println("Car dealership name: " + carDealerShips[i].getName());
                        System.out.println("Car dealership cars:");

                        for (int j = 0; j < carCount; j++) {
                            if (cars[j].getCarDealerShipId() == carDealerShipId) {
                                System.out.println("ID: " + cars[j].getId() + ", Name: " + cars[j].getName());
                                carsInCarDealerShip++;
                            }
                        }
                        break;
                    }
                }

                if (!carDealerShipExists) {
                    System.out.println("ERROR. Try one more.");
                }
                if (carsInCarDealerShip == 0) {
                    System.out.println("ERROR. Car dealership is empty.");
                }
            } else {
                System.out.println("ERROR. Enter the correct ID.");
            }
        } else {
            System.out.println("ERROR. Try one more.");
        }
    }

    private static void addCarsToCarDealerShip(BufferedReader reader) throws IOException {
        if (carCount != 0 && cardealershipCount != 0) {
            listAllCars();
            System.out.print("Please enter car ID: ");
            String carIdStr = reader.readLine();

            if (isNumeric(carIdStr)) {
                int carId = Integer.parseInt(carIdStr);
                listAllCarDealerShips();
                System.out.print("Please enter car dealership ID: ");
                String carDealerShipIdStr = reader.readLine();

                if (isNumeric(carDealerShipIdStr)) {
                    int carDealerShipId = Integer.parseInt(carDealerShipIdStr);

                    boolean carExists = false;
                    boolean carDealerShipExists = false;

                    for (int i = 0; i < carCount; i++) {
                        if (cars[i].getId() == carId) {
                            carExists = true;
                            for (int j = 0; j < cardealershipCount; j++) {
                                if (carDealerShips[j].getId() == carDealerShipId) {
                                    carDealerShipExists = true;
                                    cars[i].setCarDealerShipId(carDealerShipId);
                                    System.out.println("Car added to car dealership.");
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    if (!carExists) {
                        System.out.println("ERROR. Enter the correct ID.");
                    }

                    if (!carDealerShipExists) {
                        System.out.println("ERROR. Enter the correct ID.");
                    }
                } else {
                    System.out.println("ERROR. ID must have a number.");
                }
            } else {
                System.out.println("ERROR. ID must have a number.");
            }
        } else {
            System.out.println("ERROR. Try one more.");
        }
    }
}