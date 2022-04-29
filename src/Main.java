public class Main {
    public static void main(String[] args) throws Exception {
       SqlReader sqLito = SqlReader.getInstance();
       sqLito.readData();
        KNN k = new KNN();
        k.runKNN(3);
    }
}
