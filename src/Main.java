public class Main {
    public static void main(String[] args) throws Exception {
       SqlReader sqLito = SqlReader.getInstance();
       sqLito.readData();
       usersSet us = usersSet.getInstance();
       us.createRandomUsers(20);
        KNN k = new KNN();
        k.runKNN(3);
    }
}
