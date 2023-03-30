import java.util.List;

public class Ronda {
    private String numero;
    private List<Partido> partidos;

    public Ronda(String numero, List<Partido> partidos) {
        this.numero = numero;
        this.partidos = partidos;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public int puntos(){
        return 0;
    };

    @Override
    public String toString() {
        return "Ronda{" +
                "numero='" + numero + '\'' +
                ", partidos=" + partidos +
                '}';
    }

}
