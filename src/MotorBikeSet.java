import java.util.HashMap;

public class MotorBikeSet {
    private HashMap<Integer, MotorBike> motorBikeSet;

    public MotorBike getMotorBike(int id){
        return motorBikeSet.get(id);
    }
}
