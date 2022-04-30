import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        SqlReader sqLito = SqlReader.getInstance();
        sqLito.readData();
        usersSet us = usersSet.getInstance();
        //us.createRandomUsers(20000);

        KNN k = new KNN();
        k.runKNN(3);



        LinkedHashMap<Integer,Float> l = k.getSortedMap();
        MotorBikeSet mbs = sqLito.getMotorBikes();


        Iterator<Integer> it = l.keySet().iterator();
        Integer key;
        int i = 0;
        while (it.hasNext()){
            key = it.next();
            System.out.println("ID: " + key + " Sim: " + l.get(key));
            ++i;
        }
        sqLito.readUsers(us);

        mainWindow mainWindow = new mainWindow(mbs.getMBS(), mbs);

        mainWindow.showWindow(200,200);

    }
}