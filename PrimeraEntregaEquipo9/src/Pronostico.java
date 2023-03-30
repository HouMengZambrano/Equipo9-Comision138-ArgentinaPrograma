import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pronostico {
    private Partido partido;
    private Equipo equipo;
    private Resultado resultado;

    public Pronostico(Partido partido, Equipo equipo, Resultado resultado) {
        this.partido = partido;
        this.equipo = equipo;
        this.resultado = resultado;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "Pronostico{" +
                "partido=" + partido +
                ", equipo=" + equipo +
                ", resultado=" + resultado +
                '}';
    }

    public int puntosGanados(){
        return 0;
    }


    public static List<Pronostico> leerPronostico(String archivo) throws FileNotFoundException {
        List<Pronostico> pronosticos = new ArrayList<>();
        Scanner scanner = new Scanner(new File(archivo));
        while(scanner.hasNextLine()){
            String linea =scanner.nextLine();
            if(linea.startsWith("-Pronostico")){

            }

        }
    }
}

