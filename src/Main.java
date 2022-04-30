import java.util.Iterator;
import java.util.LinkedHashMap;

public class Main {
    public static void main(String[] args) throws Exception {
        SqlReader sqLito = SqlReader.getInstance();
        sqLito.readData();
        usersSet us = usersSet.getInstance();
        us.createRandomUsers(200);

        KNN k = new KNN();
        k.runKNN(3);



        LinkedHashMap<Integer,Float> l = k.getSortedMap();

        Iterator<Integer> it = l.keySet().iterator();
        Integer key;
        int i = 0;
        while (it.hasNext()){
            key = it.next();
            System.out.println("ID: " + key + " Sim: " + l.get(key));
            ++i;
        }
        sqLito.readUsers(us);

        us.calculateSimilarity(23);


    }
}