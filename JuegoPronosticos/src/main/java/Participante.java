import java.util.List;

public class Participante {
    private  String nombre;
    private List<Pronostico> listaDePronosticos;
    private int puntos;

    public Participante(String nombre, List<Pronostico> listaDePronosticos, int puntos) {
        this.nombre = nombre;
        this.listaDePronosticos = listaDePronosticos;
        this.puntos = puntos;
    }
    public Participante(){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Pronostico> getListaDePronosticos() {
        return listaDePronosticos;
    }

    public void setListaDePronosticos(List<Pronostico> listaDePronosticos) {
        this.listaDePronosticos = listaDePronosticos;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return "Participante{" +
                "nombre='" + nombre + '\'' +
                ", listaDePronosticos=" + listaDePronosticos +
                ", puntos=" + puntos +
                '}';
    }
}

