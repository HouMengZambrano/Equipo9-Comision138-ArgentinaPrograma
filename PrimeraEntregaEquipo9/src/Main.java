import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Partido partido = new Partido();
        List<Partido> partidos = partido.leerPartidos("src/resultados.txt");
        //Quiero accerder al primer partido por su id
        for (Partido pt: partidos) {
            if(pt.getId() == 2){
                Equipo e = pt.getEquipo1();
                pt.calcularResultado(e);
            }
        }
        //Este for each me permitio acceder a los partidos y sus atributos y metodos!!

    }
}