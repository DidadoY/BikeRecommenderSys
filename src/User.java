import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class User {
    private Integer ID;
    private Set<Integer> visitedMotorBikes;

    private Set<User> similarUsers;

    private Set<MotorBike> similarUsersMotorbikes;

    private int numCluster;

    public User(Integer ID, Set<Integer> visitedMotorBikes){
        this.ID = ID;
        this.visitedMotorBikes = visitedMotorBikes;
        numCluster = -1;
        similarUsers = new HashSet<>();
        similarUsersMotorbikes = new HashSet<>();
    }

    public User() {

    }

    public Set<Integer> getMotorbikes() {
        return visitedMotorBikes;
    }
    public Integer getID() {
        return ID;
    }

    public void addMoto(Integer id){
        visitedMotorBikes.add(id);
    }

    public Set<Integer> getVisitedMotorBikes(){
        return visitedMotorBikes;
    }

    public float calculateSimilarity(User u) {
        Set<Integer> vMU = u.getVisitedMotorBikes();
        int sizeVU = vMU.size();
        int size = visitedMotorBikes.size();
        vMU.retainAll(visitedMotorBikes);
        int common = vMU.size();
        //System.out.println(common);
        return (float)common/(float)(sizeVU + size);
    }

    public void addSimilarUser(User u) {
        similarUsers.add(u);
        Set<Integer> mb = u.getMotorbikes();
        SqlReader sqLillo = SqlReader.getInstance();
        for(Integer i : mb) {
            u.addSimilarMoto(sqLillo.getMotorbike(i));
        }
        similarUsersMotorbikes.addAll(u.getSimilarUsersMotorbikes());

    }

    public void addSimilarMoto(MotorBike m) {
        similarUsersMotorbikes.add(m);
    }

    public Set<MotorBike> getSimilarUsersMotorbikes() {
        return similarUsersMotorbikes;
    }




}
