import java.util.HashMap;
import java.util.Map;

public class MotorBikeSet {
    private static HashMap<Integer, MotorBike> motorBikeSet;

    public MotorBike getMotorBike(int id){
        return motorBikeSet.get(id);
    }
    public static HashMap<Integer, MotorBike> getMotorBikes(){return motorBikeSet;}
    public void printMotorBikeSet(){
        System.out.println("Printing all the MotorBikes...");
        for (Map.Entry<Integer, MotorBike> entry : motorBikeSet.entrySet()) {
            System.out.println("ID: " + entry.getKey());
            entry.getValue().printMotorBike();
        }

    }
}
