import java.util.*;
import java.lang.Math;



public class Kmeans {

    public static Kmeans instance;

    usersSet us;
    HashMap<Integer, User> users;

    ArrayList<Cluster> clusters;

    public static Kmeans getInstance() {
        if(instance == null) {
            instance = new Kmeans();
        }
        return instance;
    }

    public void runKmeans(int K) {
        us = usersSet.getInstance();
        users = us.getUsersSet();
        System.out.println(users.size());
        clusters = new ArrayList<>();
        createCentroids(K);
        ArrayList<User> centroids = new ArrayList<>();
        boolean eq = false;
        int i = 0;
        while(!eq || i < 10) {
            System.out.println("ANTES" + users.size());
            for(int j = 0; j < K; ++j) {
                clusters.get(j).cleanCluster();
                clusters.get(j).clearSumDistances();
                centroids.add(clusters.get(j).getCentroid());
            }
            System.out.println("DESPUES" + users.size());
            for (Map.Entry<Integer, User> entry : users.entrySet()) {
                assignUserToCluster(entry.getValue(), K);
            }
            eq = true;
            for (int j = 0; j < K; ++j) {
                clusters.get(j).recalculateCentroid();
                if(clusters.get(j).getCentroid().getID() != centroids.get(j).getID()) eq = false;
            }
            ++i;
        }
        for(Cluster c : clusters) {
            c.printCluster();
        }
    }
    private void createCentroids(int K) {
        ArrayList<User> clusterSuitors = new ArrayList<>();
        Random r = new Random();
        int index = 0;
        for(int i = 0; i < K; ++i) {
            User aux;
            Cluster c = new Cluster();
            ArrayList<Integer> keysAsArray = new ArrayList<Integer>(users.keySet());
            do{
                index = r.nextInt(users.size());
                aux = users.get(keysAsArray.get(r.nextInt(keysAsArray.size())));
            }while (clusterSuitors.contains(aux));
            c.addUser(aux);
            c.setCentroid(aux);
            clusters.add(c);
            clusterSuitors.add(aux);
        }
    }


    private void assignUserToCluster(User u, int K) {
        float dMin = 0;
        int iMin = 0;
        for(int i = 0; i < K; ++i) {
            User centroidActual = clusters.get(i).getCentroid();
            float distanciaActual = u.calculateSimilarity(centroidActual);
            if(distanciaActual > dMin) {
                dMin = distanciaActual;
                iMin = i;
            }
        }
        u.setNumCluster(iMin);
        clusters.get(iMin).addUser(u);
    }



}