
import java.util.HashMap;
import java.util.Map;

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

    public void printBrandSet(){
        System.out.println("Printing all brands...");
        for (Map.Entry<Integer, String> entry : brandSet.entrySet()) {
            System.out.println("ID: " + entry.getKey() + "  Name: " + entry.getValue());
        }

    }
}
