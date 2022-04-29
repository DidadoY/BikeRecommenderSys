
import java.util.HashMap;

public class brandSet {
    public HashMap<Integer, String> brandSet;

    public brandSet(){
        this.brandSet = new HashMap<>();
    }

    public void addBrand(Integer id, String nombre){
        brandSet.put(id, nombre);
    }

    public String getName(Integer id){
        return brandSet.get(id);
    }
}
