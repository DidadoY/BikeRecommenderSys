import java.util.*;


public class usersSet {
    private HashMap<Integer, User> usersSet;

    public static usersSet instance;

    public usersSet(){
        this.usersSet = new HashMap<>();
    }

    public static usersSet getInstance() {
        if(instance == null) {
            instance = new usersSet();
        }
        return instance;
    }

    public void addUser(Integer id, User u){
        usersSet.put(id, u);
    }

    public void createRandomUsers(int N) {
        SqlReader sqlr = SqlReader.getInstance();
        HashMap<Integer, MotorBike> mbs = sqlr.getMotorBikes().getMBS();
        float rangeMin = 0.0f;
        float rangeMax = 1.0f;
        Random r = new Random();
        for(int i = 0; i < N; ++i) {
            Set<Integer> vM = new HashSet<>();
            User u = new User(i,vM);
            for(Integer key : mbs.keySet()) {
                float createdRanNum = (float) (rangeMin + (rangeMax - rangeMin) * r.nextDouble());
                if(createdRanNum <= 0.05) {
                    u.addMoto(key);
                }
            }
            usersSet.put(i,u);
        }
        System.out.println(usersSet.size());
    }

    public void calculateSimilarity(int ID) {
        User u = usersSet.get(ID);
        HashMap<User, Float> similairties = new HashMap<>();
        for(Integer key : usersSet.keySet()) {
            User u2 = usersSet.get(key);
            if(ID != u2.getID()) {
                float similarity = u.calculateSimilarity(u2);
                similairties.put(u2,similarity);
            }
        }
        LinkedHashMap<User, Float> sortedSimilarities = new LinkedHashMap<>();
        sortMap(similairties, sortedSimilarities);
        int count = 0;
        for(User u3 : sortedSimilarities.keySet()) {
            u.addSimilarUser(u3);
            ++count;
            System.out.println(u3.getID() + "suususu " + sortedSimilarities.get(u3));
            if(count > 10) break;
        }

    }
    public HashMap<Integer, User> getUsersSet() {
        return usersSet;
    }

    private void sortMap(HashMap<User, Float> desordenado, LinkedHashMap<User, Float> ordenado){
        desordenado.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> ordenado.put(x.getKey(), x.getValue()));

    }
}
