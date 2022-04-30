

public class MotorBike {
    private int id;
    private String name;
    private int brandID;
    private int year;
    private int fuel;
    private float price;

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

    public float getPrice() {
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

    public int getBrandID() {
        return brandID;
    }

    // ===================================== Constructor ==================================================

    public MotorBike(int id, String name, int brandID, int year, int fuel, float price) {
        this.id = id;
        this.name = name;
        this.brandID = brandID;
        this.year = year;
        this.fuel = fuel;
        this.price = price;
    }

    public void printMotorBike() {
        System.out.println("Name: " + name + " BrandID: " + brandID + " Year: " + year + " Fuel: " + fuel + " Price: " + price);
    }
}
