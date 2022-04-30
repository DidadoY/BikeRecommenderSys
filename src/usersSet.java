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


    public Set<MotorBike> calculateSimilarity(int ID) {
        User u = usersSet.get(ID);
        HashMap<User, Float> similairties = new HashMap<>();
        System.out.println(usersSet.size());
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
            //System.out.println(u3.getID() + "suususu " + sortedSimilarities.get(u3));
            if(count > 10) break;
        }
        return u.getSimilarUsersMotorbikes();
    }


    private void sortMap(HashMap<User, Float> desordenado, LinkedHashMap<User, Float> ordenado){
        desordenado.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> ordenado.put(x.getKey(), x.getValue()));

    }
    public void createRandomUsers(int N) throws Exception {
        TxtWritter t = new TxtWritter();
        SqlReader sqlr = SqlReader.getInstance();
        HashMap<Integer, MotorBike> mbs = sqlr.getMotorBikes().getMBS();
        float rangeMin = 0.0f;
        float rangeMax = 1.0f;
        Random r = new Random();
        for(int i = 0; i < N; ++i) {
            ArrayList<Integer> visited = new ArrayList<>();
            for(Integer key : mbs.keySet()) {
                float createdRanNum = (float) (rangeMin + (rangeMax - rangeMin) * r.nextDouble());
                if(createdRanNum <= 0.015) {
                    visited.add(key);
                }
            }

            t.addToMap(i, visited);
            //System.out.println("User " + i + ' ' + vM.size());
        }
        t.txtWrite();
    }

    public HashMap<Integer, User> getUsersSet() {
        return usersSet;
    }
}
