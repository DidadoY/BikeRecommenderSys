import java.util.HashMap;


public class usersSet {
    public HashMap<Integer, User> usersSet;

    public usersSet(){
        this.usersSet = new HashMap<>();
    }

    public void addUser(Integer id, String nombre){
        brandSet.put(id, nombre);
    }
}
