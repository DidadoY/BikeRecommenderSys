import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*;


public class SqlReader {
    public static SqlReader instance;
    brandSet bs = new brandSet();
    MotorBikeSet mbs = new MotorBikeSet();
    int maxYear = -1;
    int minYear = -1;
    float maxPrice = -1;
    float minPrice = -1;

    public void readData() {
        String cvsSplitBy = ",";

        try {
            File myObj = new File("./data/init.sql");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data.length() > 20) {
                    String dataAux = data.substring(0,18);
                    if(dataAux.equals("INSERT INTO brands")) {
                        data = data.substring(38,data.length()-2);
                        String[] brandData = data.split(", '");
                        Integer ID = Integer.parseInt(brandData[0]);
                        String brand = brandData[1];
                        brand = brand.substring(0,brand.length()-1);
                        bs.addBrand(ID,brand);
                        //System.out.println(brand);
                    }
                    else {
                        dataAux = data.substring(0,20);
                        if(dataAux.equals("INSERT INTO versions")) {
                            data = data.substring(69,data.length()-2);
                            System.out.println(data);
                            String[] brandData = data.split(", ");
                            int ID = Integer.parseInt(brandData[0]);
                            String name = brandData[1];
                            int brand_id = Integer.parseInt(brandData[2]);
                            int year = Integer.parseInt(brandData[3]);
                            int fuel = Integer.parseInt(brandData[4]);
                            float rangeMin = 0.0f;
                            float rangeMax = 1.0f;
                            Random r = new Random();
                            float createdRanNum = (float) (rangeMin + (rangeMax - rangeMin) * r.nextDouble());
                            float price = createdRanNum*9500+1500;//Integer.parseInt(brandData[5]);
                            mbs.addMotorBike(ID,name,brand_id,year,fuel,price);
                            if(maxPrice == -1|| maxPrice < price) maxPrice = price;
                            if(minPrice == -1 || minPrice > price) minPrice = price;
                            if(maxYear == -1 || maxYear < year) maxYear = year;
                            if(minYear == -1 || minYear > year) minYear = year;

                            //System.out.println(brand);
                        }
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        bs.printBrandSet();
        mbs.printMotorBikeSet();
        mbs.SetMaxsMins(maxYear,minYear,maxPrice,minPrice);
        System.out.println("Prices: " + minPrice + ' ' + maxPrice);
        System.out.println("Years: " + minYear + ' ' + maxYear);
    }

    public static SqlReader getInstance() {
        if(instance == null) {
            instance = new SqlReader();
        }
        return instance;
    }

    public MotorBikeSet getMotorBikes() {
        return mbs;
    }

    public MotorBike getMotorbike(int ID) {
        return mbs.getMotorBike(ID);
    }

    public void readUsers(usersSet us) {
        File myObj = new File("./data/users.txt");
        try {
            File o = new File("./data/users.txt");
            Scanner myReader = new Scanner(o);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] a = data.split(":");
                String[] b = a[1].split(",");
                Set<Integer> visited = new HashSet<>();
                for(String s: b) {
                    visited.add(Integer.parseInt(s));
                }
                User u = new User(Integer.parseInt(a[0]), visited);
                us.addUser(Integer.parseInt(a[0]),u);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}