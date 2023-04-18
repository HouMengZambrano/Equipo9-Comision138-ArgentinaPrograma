import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLClientInfoException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        String pronosticosTxt = "src/main/resources/Pronosticos.txt";

       try {
           Map<String, Integer> mapaDePuntos = juego(pronosticosTxt);

       } catch (FileNotFoundException e) {
           System.out.println("Archivo no encontrado");
       }


    }

    public static List<Ronda> crearListaRondas(String archivo) throws FileNotFoundException {
        List<Ronda> listaRondas = new ArrayList<>();
        Scanner scanner = new Scanner(new File(archivo));
        Partido partido = null;
        String rondaNombre = null;
        List<Partido> listaPartidos = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String linea = scanner.nextLine();
            if (linea.startsWith("Ronda")) {
                if (rondaNombre != null) {
                    Ronda ronda = new Ronda(rondaNombre, listaPartidos);
                    listaRondas.add(ronda);
                    listaPartidos = new ArrayList<>();
                }
                rondaNombre = scanner.nextLine().trim();
            }
            else if (linea.startsWith("Partido")) {
                String[] partido1Data = scanner.nextLine().trim().split(",");
                String nombreEquipo1 = partido1Data[0];
                String nombreEquipo2 = partido1Data[3];
                Equipo equipo1 = new Equipo(nombreEquipo1);
                Equipo equipo2 = new Equipo(nombreEquipo2);
                int golesEquipo1 = Integer.parseInt(partido1Data[1]);
                int golesEquipo2 = Integer.parseInt(partido1Data[2]);
                partido = new Partido(equipo1, equipo2, golesEquipo1, golesEquipo2);
                listaPartidos.add(partido);
            }
        }
        if (rondaNombre != null) {
            Ronda ronda = new Ronda(rondaNombre, listaPartidos);
            listaRondas.add(ronda);
        }
        scanner.close();
        return listaRondas;
    }
    //

    // Este metodo lee pronosticos, los compara con los resultados y les otorga a puntos a los participantes por ronda:

    //El tema es que no se como voy a hacer para decirle que pronostico pertenece a que ronda :(
    public static Map<String, Integer> juego(String archivo) throws FileNotFoundException {
        List<Ronda> listaDeRondas = crearListaRondas("src/main/resources/Resultados.txt");
        Map<String, Integer> mapaDePuntos = new HashMap<>();
        List<Partido> listaDePartidos = null;
        for(Ronda r : listaDeRondas){
            listaDePartidos = r.getPartidos();
            Scanner scanner = new Scanner(new File(archivo));
            String pronosticador = null;
            Pronostico pronostico = null;
            int puntos = 0;
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine().trim();
                if (linea.startsWith("Pronostico:")) {
                    if (pronosticador != null) {
                        mapaDePuntos.put(pronosticador, puntos);
                        puntos = 0;
                    }
                    pronosticador = scanner.nextLine().trim();
                } else {
                    String[] pronosticoData = linea.split(",");
                    Equipo equipo1 = new Equipo(pronosticoData[0]);
                    Equipo equipo2 = new Equipo(pronosticoData[2]);
                    ResultadoEnum resultadoPronosticado = ResultadoEnum.valueOf(pronosticoData[1]);
                    Partido partido = new Partido(equipo1, equipo2);
                    pronostico = new Pronostico(equipo1,partido,resultadoPronosticado);
                    for (Partido p : listaDePartidos) {
                        if (p.getEquipo1().getNombre().equals(pronostico.getPartido().getEquipo1().getNombre())
                                && p.getEquipo2().getNombre().equals(pronostico.getPartido().getEquipo2().getNombre())) {
                            partido = p;
                            // Aqui lo que estoy jugando es que el partido previamente estaba instanciado con
                            pronostico.setPartido(partido);
                            break;
                        }
                    }
                    if (partido == null) {
                        System.out.println("Hay un error en la lectura del pronostico");
                    } else {
                        puntos += pronostico.puntos();
                    }
                }
            }
            if (pronosticador != null) {
                mapaDePuntos.put(pronosticador, puntos);
            }
            System.out.println(r.getNumero() + " ronda!!");
            System.out.println(mapaDePuntos);
            scanner.close();
        }

        return mapaDePuntos;
    }

}