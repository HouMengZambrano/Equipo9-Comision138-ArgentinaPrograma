import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Partido partido = new Partido();
        partido = partido.leerPartido("src/resultados.txt");

        Equipo equipo1 = partido.getEquipo1();
        Equipo equipo2 = partido.getEquipo2();
        System.out.println(equipo1);
        System.out.println(equipo2);

    }
}