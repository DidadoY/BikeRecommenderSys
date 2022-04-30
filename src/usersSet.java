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
                if(createdRanNum <= 0.005) {
                    u.addMoto(key);
                }
            }
            usersSet.put(i,u);
        }
        System.out.println(usersSet.size());
    }

    public HashMap<Integer, User> getUsersSet() {
        return usersSet;
    }
}
