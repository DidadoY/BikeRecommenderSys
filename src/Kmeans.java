import java.util.*;
import java.lang.Math;



/** @file Kmeans.java
 *  @brief Clase que divide a los usuarios de nuestra base de datos en clusters segun sus valoraciones
 *  @author Alejandro Salvat Navarro
 */
public class Kmeans {

    /**
     * @brief Instancia de la base de datos que usaremos en la actual ejecucion de kmeans
     */
    private final DataBase instance = DataBase.getInstance();

    /**
     * @brief Lista de usuarios pertenecientes a nuestra base de datos
     */
    private final List<Integer> usuarios = instance.getUsuarios();



    /**
     * @brief Booleano que indica si el algoritmo K-means para una k concreta ha acabado o no
     */
    private boolean acabado;



    /*******************************************************************************
     * @brief Repite el algoritmo de K-means hasta encontrar una k optima y devuelve los clusters resultantes
     ******************************************************************************/
    public List<Cluster> calcula(){
        int k = 5;
        KMeansConInercia antiguo = calculaparak(k);
        k+=2;
        KMeansConInercia nuevo = calculaparak(k);
        while(nuevo.getInercia()*2 > antiguo.getInercia()*3){
            antiguo = nuevo;
            k++;
            nuevo = calculaparak(k);
        }
        return nuevo.getClusters();
    }



    /*******************************************************************************
     * @brief Divide a los usuarios de nuestra base de datos en k clusters segun sus valoraciones
     * @param k Numero de clusters en los que dividiremos a los usuarios
     ******************************************************************************/
    private KMeansConInercia calculaparak(Integer k){
        List<Cluster> clusters = elegirCentroides(k);
        int i = 0;
        acabado = false;
        while(!acabado && i < 10){
            prepararClusters(clusters);
            asignarusuarios(clusters);
            recalcularCentroides(clusters, k);
            ++i;
        }
        Double inercia = calcularInercia(clusters);
        return new KMeansConInercia(clusters,inercia);
    }



    /*******************************************************************************
     * @brief Calcula la similaridad entre 2 usuarios segun sus valoraciones por cosine-based approach"
     * @param idUsuario1 ID del primer usuario.
     * @param idUsuario2 ID del segundo usuario.
     ******************************************************************************/
    private Double similaridad(String idUsuario1, String idUsuario2){
        List<String> itemsValorats1 = instance.getItemsValorats(idUsuario1);
        List<String> itemsValorats2 = instance.getItemsValorats(idUsuario2);
        List<String> valoradosPorLos2 = instance.interseccion(itemsValorats1, itemsValorats2);
        double sumatorio1 = 0d;
        double sumatorio2 = 0d;
        double sumatorio3 = 0d;
        for(String i : valoradosPorLos2){
            sumatorio1 += instance.getRating(idUsuario1,i)*instance.getRating(idUsuario2,i);
        }
        for(String j : itemsValorats1){
            sumatorio2 += Math.pow(instance.getRating(idUsuario1,j),2);
        }
        for(String k : itemsValorats2){
            sumatorio3 += Math.pow(instance.getRating(idUsuario2,k),2);
        }
        return sumatorio1/(Math.sqrt(sumatorio2)*Math.sqrt(sumatorio3));
    }



    /*******************************************************************************
     * @brief Recalcula el centroide de cada cluster escogiendo al mas similar al resto de usuarios dentro de este
     * @param clusters Lista de clusters formados por nuestro algoritmo.
     * @param k numero de clusters existentes.
     ******************************************************************************/
    private void recalcularCentroides(List<Cluster> clusters, int k) {
        int contador = 0;
        for (Cluster c : clusters) {
            String nuevocentroide;
            nuevocentroide = c.getCentroide();
            double sumatoriosimilaridadcentroide = Double.MIN_VALUE;

            for (String pretendienteacentroide : c.getUsuariosencluster()) {
                double sumatorio = 0.0;
                for(String resto : c.getUsuariosencluster()) {
                    sumatorio += similaridad(pretendienteacentroide, resto);
                }
                if (sumatorio > sumatoriosimilaridadcentroide){
                    nuevocentroide = pretendienteacentroide;
                    sumatoriosimilaridadcentroide = sumatorio;
                }
            }
            if(nuevocentroide.equals(c.getCentroide())){
                ++contador;

            }
            else {
                c.setCentroide(nuevocentroide);
            }
        }
        if (contador == k) acabado = true;
    }


    /*******************************************************************************
     * @brief Getter de la funcion recalcularCentroides, necesario para el testing
     * @param clusters Lista de clusters formados por nuestro algoritmo.
     * @param k numero de clusters existentes.
     ******************************************************************************/
    public void getRecalcularCentroides(List<Cluster> clusters, int k){
        recalcularCentroides(clusters, k);
    }

    /*******************************************************************************
     * @brief Asigna cada usuario de nuestra base de datos al cluster cuyo centroide es mas similar a el"
     * @param clusters Lista de los clusters con unicamente el centroide.
     ******************************************************************************/
    private void asignarusuarios(List<Cluster> clusters) {
        for (String usuario : usuarios) {
            Cluster masCercano = clusters.get(0);
            double similaridadMaxima = -1.0;
            for (Cluster cluster : clusters) {
                double similaridad;
                if(cluster.getCentroide().equals(usuario)){
                    masCercano = cluster;
                    similaridad = 2.0;
                }
                else similaridad = similaridad(usuario, cluster.getCentroide());
                if (similaridadMaxima < similaridad) {
                    similaridadMaxima = similaridad;
                    masCercano = cluster;
                }
            }
            masCercano.getUsuariosencluster().add(usuario);
        }
    }



    /*******************************************************************************
     * @brief Funcion publica para asignar usuarios a una lista de clusters"
     * @param clusters Lista de los clusters con unicamente el centroide.
     ******************************************************************************/
    public void getasignarusuarios(List<Cluster> clusters){
        asignarusuarios(clusters);
    }


    /*******************************************************************************
     * @brief Vacia la lista de usuarios pertenecientes a cada cluster"
     * @param clusters Lista de los clusters formados por nuestro algoritmo
     ******************************************************************************/
    private void prepararClusters(List<Cluster> clusters) {
        for (Cluster c : clusters) {
            c.limpiarusuarios();
        }
    }



    /*******************************************************************************
     * @brief Calcula el promedio de similaridad de los usuarios con el centroide del cluster del que forman parte"
     * @param clusters Lista de los clusters formados por nuestro algoritmo
     ******************************************************************************/
    private Double calcularInercia(List<Cluster> clusters) {
        double inercia = 0d;
        for (Cluster cluster : clusters) {
            for (String usuario : cluster.getUsuariosencluster()) {
                inercia += similaridad(cluster.getCentroide(), usuario);
            }
        }
        inercia /= usuarios.size();
        return inercia;
    }



    /*******************************************************************************
     * @brief Funcion publica para calcular la inercia de una lista de clusters
     * @param clusters Lista de los clusters formados por nuestro algoritmo
     ******************************************************************************/
    public Double getCalcularInercia(List<Cluster> clusters) {
        return calcularInercia(clusters);
    }



    /*******************************************************************************
     * @brief Devuelve una lista con los pretendientes a centroide ordenados ascendientemente por similaridad con el resto"
     * @param similaridades Hashmap que contiene a los pretendientes a centroides ordenados por similaridad con el resto, y su siimilaridad con el resto
     ******************************************************************************/
    public List<String> masLejanosAList(HashMap<String,Double> similaridades){
        List<String> users = new ArrayList<>();
        for (Map.Entry<String, Double> entry : similaridades.entrySet()) {
            users.add(entry.getKey());
        }
        return users;
    }



    /*******************************************************************************
     * @brief Devuelve k clusters formados por los centroides mas lejanos respecto a todos los demas pretendientes"
     * @param pretendientesacentroide ArrayList de todos los pretendientes a centroide.
     * @param k Número de clusters en los que queremos dividir a nuestros usuarios.
     ******************************************************************************/
    private List<Cluster> maslejanos(ArrayList<String>pretendientesacentroide, int k){
        LinkedHashMap<String, Double> similaridades = new LinkedHashMap<>();
        for (String indice : pretendientesacentroide){
            double sumatoriosimilaridad = 0d;
            for(String resto : pretendientesacentroide){
                sumatoriosimilaridad += similaridad(indice, resto);
            }
            similaridades.put(indice, sumatoriosimilaridad);
        }
        LinkedHashMap<String, Double> ordenadosPorsimilaridad = new LinkedHashMap<>();
        OrdenarPorsimilaridad(ordenadosPorsimilaridad, similaridades);
        List<String> centroidesMasAlejados = masLejanosAList(ordenadosPorsimilaridad);
        List<Cluster> clusters = new ArrayList<>();
        for(int i = 0; i < k; ++i){
            Cluster c = new Cluster();
            c.setCentroide(centroidesMasAlejados.get(i));
            clusters.add(c);
        }
        return clusters;
    }



    /*******************************************************************************
     * @brief Funcion publica para obtener a los usuarios mas lejanos dentro de los pretendientes y seleccionarlos como centroides
     * @param pretendientesacentroide ArrayList de todos los pretendientes a centroide.
     * @param k Número de clusters en los que queremos dividir a nuestros usuarios.
     ******************************************************************************/
    public List<Cluster> getMasLejanos(ArrayList<String>pretendientesacentroide, int k){
        return maslejanos(pretendientesacentroide, k);
    }



    /*******************************************************************************
     * @brief Ordena los pretendientes ascendientemente a los pretendeintes a centroide respecto a su similaridad con el resto"
     * @param similaridadesordenadas LinkedHashMap vacia que acabara conteniendo a los pretendientes a centroide ordenados.
     * @param similaridades HashMap que contiene a todos los pretendientes a centroide junto a su similaridad con el resto.
     ******************************************************************************/
    private void OrdenarPorsimilaridad(LinkedHashMap<String, Double> similaridadesordenadas, HashMap<String,Double> similaridades){
        similaridades.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> similaridadesordenadas.put(x.getKey(), x.getValue()));
    }



    /*******************************************************************************
     * @brief Crea k clusters formados unicamente por su centroide, los centroides son escogidos al azar dentro de los usuarios de la base de datos"
     * @param k numero de clusters que crearemos
     ******************************************************************************/
    private List<Cluster> elegirCentroides(Integer k) {
        ArrayList<String> pretendientesacentroide = new ArrayList<>();
        for (int i = 0; i < k*10; i++) {
            Random random = new Random();
            int indicealazar = random.nextInt(usuarios.size());
            if(!pretendientesacentroide.contains(usuarios.get(indicealazar))) pretendientesacentroide.add(usuarios.get(indicealazar));
        }
        return maslejanos(pretendientesacentroide,k);
    }



    /*******************************************************************************
     * @brief Devuelve la similaridad entre los usuarios
     * @param idUsuario1 ID del primer usuario.
     * @param idUsuario2 ID del segundo usuario.
     ******************************************************************************/
    public Double getSimilaridad(String idUsuario1, String idUsuario2){
        return similaridad(idUsuario1, idUsuario2);
    }




    /*******************************************************************************
     * @brief Devuelve el cluster al que pertenece el usuario con ID idUsuario
     * @param idUsuario ID del usuario del que queremos obtener el cluster
     ******************************************************************************/
    public Cluster getClusterUsuario(String idUsuario){
        List<Cluster> clusters = calcula();
        for(Cluster clust : clusters){
            if (clust.getUsuariosencluster().contains(idUsuario)) return clust;
        }
        return clusters.get(0);
    }
}