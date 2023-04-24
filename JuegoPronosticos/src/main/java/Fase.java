import java.util.List;

public class Fase {
    private String numero;
    private List<Ronda> rondas;

    public Fase(String numero, List<Ronda> rondas) {
        this.numero = numero;
        this.rondas = rondas;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public List<Ronda> getRondas() {
        return rondas;
    }

    public void setRondas(List<Ronda> rondas) {
        this.rondas = rondas;
    }

    @Override
    public String toString() {
        return "Fase{" +
                "numero='" + numero + '\'' +
                ", rondas=" + rondas +
                '}';
    }
}
