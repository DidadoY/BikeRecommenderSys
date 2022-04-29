import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KNN {
    private HashMap<Integer,MotorBike> motorBikes;
    private int id;

    private ArrayList<Float> weights = new ArrayList<Float>(List.<Float>of((float) 0.2, (float) 0.2, (float) 0.2, (float) 0.2, (float) 0.2));

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
        float result = 0;
        if(mainMotorBike.getName() == motorBike.getName()) result += weights.get(0);

    return 0;
    }

}
