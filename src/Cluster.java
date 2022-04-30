import java.util.ArrayList;
import java.util.List;



public class Cluster {


    private ArrayList<User> users;
    
    private ArrayList<Float> sumDistances;


    private User centroid;

    public Cluster(){
        sumDistances = new ArrayList<Float>();
        users = new ArrayList<User>();
        centroid = new User();
    }

    public Cluster(User centroid, ArrayList<User> users){
        this.centroid = centroid;
        this.users = users;
    }

    public User getCentroid() {
        return centroid;
    }

    public void setCentroid(User centroid) {
        this.centroid = centroid;
    }

    public void setUsersOnCluster(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<User> getUsersOnCluster() {
        return users;
    }

    public void cleanCluster() {
        users.removeAll(users);
    }

    public void clearSumDistances() {
        sumDistances.removeAll(sumDistances);
    }

    public void addUser(User u) {
        users.add(u);
        System.out.println(users.size());
        float d = 0;
        sumDistances.add(0f);
        for(int i = 0; i < users.size(); ++i) {
            float actDist = u.calculateSimilarity(users.get(i));
            d += actDist;
            float d1 = sumDistances.get(i) + actDist;
            sumDistances.set(i,d1);
        }
        sumDistances.set(users.size() -1, d);
    }

    public void recalculateCentroid() {
        float sumMin = 0;
        int iMin = 0;
        for(int i = 0; i <users.size(); ++i) {
            if(sumDistances.get(i) > sumMin) {
                sumMin = sumDistances.get(i);
                iMin = i;
            }
        }
        System.out.println("Suuu " + users.size());
        centroid = users.get(iMin);
    }

    public void printCluster() {
        System.out.println("Centroide: " + centroid.getID() + "\n");
        System.out.print("Usuarios: [");
        int count = 0;
        boolean first = true;
        for (User user : users) {
            if(first) first = false;
            else System.out.print(", ");
            if(count%13 == 0 && count!=0) System.out.println();
            count++;
            System.out.print(user.getID());
        }
        System.out.println("]\n");
    }

}