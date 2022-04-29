import java.util.HashMap;

public class KNN {
    private HashMap<Integer,MotorBike> motorBikes;
    private int id;

    public KNN() {
        motorBikes = MotorBikeSet.getMotorBikes();

    }
    public void runKNN(int id) {
        this.id = id;

    }

    public float calculateSimilarity() {
        for(Integer key : motorBikes.keySet()) {
            if(iMotorBike motorBike = motorBikes.get(key);
        }
        return 0;
    }

}
