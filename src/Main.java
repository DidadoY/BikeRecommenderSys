import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        SqlReader sqLito = SqlReader.getInstance();
        sqLito.readData();
        usersSet us = usersSet.getInstance();
        //us.createRandomUsers(20000);
        MotorBikeSet mbs = sqLito.getMotorBikes();
        sqLito.readUsers(us);

        mainWindow mainWindow = new mainWindow(mbs.getMBS(), mbs);
        mainWindow.showWindow(200,200);
    }
}