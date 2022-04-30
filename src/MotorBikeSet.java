import java.util.HashMap;
import java.util.Map;

public class MotorBikeSet {
    private static HashMap<Integer, MotorBike> motorBikeSet = new HashMap<>();
    private static int maxYear;
    private static int minYear;
    private static float maxPrice;
    private static float minPrice;
    public MotorBike getMotorBike(int id){
        return motorBikeSet.get(id);
    }
    public static HashMap<Integer, MotorBike> getMotorBikes(){
        return motorBikeSet;
    }

    public void addMotorBike(int id, String name, int brandID, int year, int fuel, float price){
        MotorBike bike = new MotorBike(id, name, brandID, year, fuel, price);
        motorBikeSet.put(id, bike);
    }
    public void printMotorBikeSet(){
        System.out.println("Printing all the MotorBikes...");
        for (Map.Entry<Integer, MotorBike> entry : motorBikeSet.entrySet()) {
            System.out.println("ID: " + entry.getKey());
            entry.getValue().printMotorBike();
        }
    }

    public void SetMaxsMins(int maxYear,int minYear,float maxPrice,float minPrice) {
        this.maxYear = maxYear;
        this.minYear = minYear;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
    }

    public static float getPriceDif() {
        return maxPrice - minPrice;
    }

    public static int getYearDif() {
        return maxYear - minYear;
    }
}
