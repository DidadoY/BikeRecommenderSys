import java.util.*;

public class KNN {
    private HashMap<Integer,MotorBike> motorBikes;
    private int id;

    private ArrayList<Float> weights = new ArrayList<Float>(List.<Float>of((float) 0.25, (float) 0.25, (float) 0.05, (float) 0.2, (float) 0.25));
    private LinkedHashMap<Integer,Float> similarityMap = new LinkedHashMap<>();

    public KNN() {
        motorBikes = MotorBikeSet.getMotorBikes();
    }
    public void runKNN(int id) {
        similarityMap.clear();
        this.id = id;
        comparate();
    }

    private void comparate() {
        HashMap<Integer,Float> aux = new HashMap<>();
        MotorBike mainMotorBike = motorBikes.get(id);
        for(Integer key : motorBikes.keySet()) {
            if(key != id) {
                MotorBike motorBike = motorBikes.get(key);
                float s = calculateSimilarity(mainMotorBike,motorBike);
                aux.put(key, s);
            }
        }
        sortMap(aux,similarityMap);
    }

    private float calculateSimilarity(MotorBike mainMotorBike, MotorBike motorBike) {
        float result = 0;
        if(mainMotorBike.getName() == motorBike.getName()) result += weights.get(0);
        if(mainMotorBike.getBrandID() == motorBike.getBrandID()) result += weights.get(1);
        if(mainMotorBike.getFuel() == motorBike.getFuel()) result += weights.get(3);

        int yearDif = mainMotorBike.getYear() - motorBike.getYear();
        int diference = MotorBikeSet.getYearDif();
        if(diference != 0) result += ((diference - yearDif)/diference) * weights.get(2);
        else result += weights.get(2);

        float priceDif = mainMotorBike.getPrice() - motorBike.getPrice();
        float diference2 = MotorBikeSet.getPriceDif();
        if(diference != 0) result += ((diference2 - priceDif)/diference2) * weights.get(4);
        else result += weights.get(4);

        return result;
    }


    private void sortMap(HashMap<Integer, Float> desordenado, LinkedHashMap<Integer, Float> ordenado){
        desordenado.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> ordenado.put(x.getKey(), x.getValue()));

    }

    public LinkedHashMap<Integer,Float> getSortedMap() {
        return similarityMap;
    }
}
