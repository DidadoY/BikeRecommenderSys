public class Main {
    public static void main(String[] args) throws Exception {
        new SqlReader();
        KNN k = new KNN();
        k.runKNN(3);
    }
}
