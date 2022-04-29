import java.util.HashMap;
import java.util.Map;

public class MotorBikeSet {
    private static HashMap<Integer, MotorBike> motorBikeSet = new HashMap<>();

    public MotorBike getMotorBike(int id){
        return motorBikeSet.get(id);
    }
    public static HashMap<Integer, MotorBike> getMotorBikes(){
        return motorBikeSet;
    }

    public void addMotorBike(int id, String name, int brandID, int year, int fuel, int price){
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
}
