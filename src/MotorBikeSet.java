import java.util.HashMap;

public class MotorBikeSet {
    private static HashMap<Integer, MotorBike> motorBikeSet;

    public MotorBike getMotorBike(int id){
        return motorBikeSet.get(id);
    }
    public static HashMap<Integer, MotorBike> getMotorBikes(){return motorBikeSet;}
}
