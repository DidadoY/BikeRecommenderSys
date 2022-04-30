import java.util.ArrayList;
import java.util.List;



public class Cluster {


    private List<Integer> usuariosencluster = new ArrayList<>();


    private Integer centroide;

    public Cluster(){}

    public Cluster(Integer centroide, List<Integer> usuariosencluster){
        this.centroide = centroide;
        this.usuariosencluster = usuariosencluster;
    }

    public Integer getCentroide() {
        return centroide;
    }

    public void setCentroide(Integer centroide) {
        this.centroide = centroide;
    }

    public void setUsuariosencluster(List<Integer> usuariosencluster) {
        this.usuariosencluster = usuariosencluster;
    }

    public List<Integer> getUsuariosencluster() {
        return usuariosencluster;
    }

    public void limpiarusuarios() {
        usuariosencluster.clear();
    }

}