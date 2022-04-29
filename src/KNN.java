import java.util.ArrayList;
import java.util.HashMap;

public class KNN {
    private HashMap<Integer,MotorBike> motorBikes;
    private int id;

    private ArrayList<Float> weights = new ArrayList<>();

    public KNN() {
        motorBikes = MotorBikeSet.getMotorBikes();
    }
    public void runKNN(int id) {
        this.id = id;
    }

    private void a() {
        MotorBike mainMotorBike = motorBikes.get(id);
        for(Integer key : motorBikes.keySet()) {
            if(key != id) {
                MotorBike motorBike = motorBikes.get(key);
            }

        }
    }

    private float calculateSimilarity(MotorBike mainMotorBike, MotorBike motorBike) {
        mainMotorBike.getName();
    return 0;
    }

}
