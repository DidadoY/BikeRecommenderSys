
import java.util.HashMap;

public class brandSet<hashMap> {
    public HashMap<Integer, String> brandSet;

    public brandSet(){
        this.brandSet = new HashMap<>();
    }

    public String getName(Integer id){
        return brandSet.get(id);
    }
}
