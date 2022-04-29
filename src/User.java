import java.util.HashMap;
import java.util.Set;

public class User {
    private Integer ID;
    private Set<Integer> visitedMotorBikes;

    public User(Integer ID, Set<Integer> visitedMotorBikes){
        this.ID = ID;
        this.visitedMotorBikes = visitedMotorBikes;
    }

    public void addMoto(Integer id){
        visitedMotorBikes.add(id);
    }

    public Set<Integer> getVisitedMotorBikes(){
        return visitedMotorBikes;
    }

}
