import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLClientInfoException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {

    //------------------------------------JUEGO-----------------------------------//
    public static void main(String[] args) throws IOException {
        String resultadosTxt = "src/main/resources/ResultadosNuevoModelo.txt";
        String pronosticoTxt = "src/main/resources/ProsnosticosNuevoModelo.txt";
        juego(resultadosTxt, pronosticoTxt);
    }
    //-----------------------------------JUEGO-------------------------------------//


    //---------------------------------METODOS------------------------------------------//
    public static List<Ronda> crearListaRondas(String archivoTxt) throws IOException {
        List<Ronda> rondas = new ArrayList<>();
        List<Partido> partidos = null;
        Ronda ronda = null;
        List<String> lineasArchivo = Files.readAllLines(Paths.get(archivoTxt));
        //Itero cada linea del archivo con este for
        for (String line : lineasArchivo) {
            //Chequeo si comienza:
            if (line.startsWith("Ronda,")) {
                //Chequeo si la ronda no tiene valor null
                if (ronda != null) {
                    //Seteo a la ronda una lista de partidos
                    ronda.setPartidos(partidos);
                    //Agrego la ronda a la lista de rondas
                    rondas.add(ronda);
                }
                String[] rondaData = line.trim().split(",");
                ronda = new Ronda();
                ronda.setNumero(rondaData[1]);
                partidos = new ArrayList<>();

            } else {
                //Chequeo que la linea comience por Partido, instancio un partido
                String[] partidoData = line.trim().split(",");
                int idEquipo = parseInt(partidoData[1]);
                Equipo equipo1 = new Equipo(partidoData[2]);
                Equipo equipo2 = new Equipo(partidoData[5]);
                int golesEquipo1 = parseInt(partidoData[3]);
                int golesEquipo2 = parseInt(partidoData[4]);
                Partido p = new Partido(idEquipo, equipo1, equipo2, golesEquipo1, golesEquipo2);
                //Agrego el partido instanciado a la lista de partidos
                partidos.add(p);
                //Aqui seteo la lista de partidos a la ronda
                ronda.setPartidos(partidos);
            }
        }
        // Agrego la ronda a la lista de rondas
        rondas.add(ronda);
        return rondas;
    }

    public static List<Participante> crearListaDeParticipantes(String archivoPronosticoTxt) throws IOException {
        List<Participante> listaDeParticipantes = new ArrayList<>();
        List<Pronostico> listaDePronosticos = null;
        Participante participante = null;
        List<String> lineasArchivo = Files.readAllLines(Paths.get(archivoPronosticoTxt));
        for (String linea : lineasArchivo) {
            if (linea.startsWith("Participante")) {
                if (participante != null) {
                    participante.setListaDePronosticos(listaDePronosticos);
                    listaDeParticipantes.add(participante);
                }
                String[] dataParticipante = linea.trim().split(",");
                String nombreParticipante = dataParticipante[1];
                participante = new Participante();
                participante.setNombre(nombreParticipante);
                listaDePronosticos = new ArrayList<>();
            } else {
                String[] dataPronostico = linea.trim().split(",");
                int idPartido = parseInt(dataPronostico[1]);
                Equipo equipo1 = new Equipo(dataPronostico[2]);
                Equipo equipo2 = new Equipo(dataPronostico[4]);
                Partido partido = new Partido(idPartido, equipo1, equipo2);
                ResultadoEnum resultado = ResultadoEnum.valueOf(dataPronostico[3]);
                Pronostico pronostico = new Pronostico(equipo1, partido, resultado);
                listaDePronosticos.add(pronostico);
                participante.setListaDePronosticos(listaDePronosticos);
            }
        }
        listaDeParticipantes.add(participante);
        return listaDeParticipantes;
    }

    ;


    public static void juego(String resultadosTxt, String pronosticosTxt) throws IOException {

        List<Participante> juegoParticipantes = crearListaDeParticipantes(pronosticosTxt);
        List<Ronda> juegoRondas = crearListaRondas(resultadosTxt);
        int puntos = 0;


        for (Ronda r : juegoRondas) {
            System.out.println(r.getNumero() + " ronda :");
            for (Partido partido : r.getPartidos()) {
                System.out.println(partido.getEquipo1().getNombre() + " vs " + partido.getEquipo2().getNombre());
                for (Participante participante : juegoParticipantes) {
                    for (Pronostico pronostico : participante.getListaDePronosticos()) {
                        if (pronostico.getPartido().getId() == partido.getId()) {
                            pronostico.setPartido(partido);
                            if (pronostico.esAcertado()) {
                                puntos = pronostico.puntos() + participante.getPuntos();
                                participante.setPuntos(puntos);
                            }
                        }
                    }
                    }

                }

            }
            for (Participante participante : juegoParticipantes) {
                System.out.println("Participante " + participante.getNombre() + " obtuvo un puntaje final de : " + participante.getPuntos() +
                        " puntos al acertar " + participante.getPuntos() + " pronosticos"
                );
            }
        }
    }
