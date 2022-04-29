

public class MotorBike {
    private int id;
    private String name;
    private int year;
    private int fuel;
    private int price;

    // =========================================== Getters ==================================================
    public int getFuel() {
        return fuel;
    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    // ============================================= Setters =====================================================


    public void setId(int id) {
        this.id = id;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setYear(int year) {
        this.year = year;
    }

    // ===================================== Constructor ==================================================

    public MotorBike(int id, String name, int year, int fuel, int price) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.fuel = fuel;
        this.price = price;
    }
}
