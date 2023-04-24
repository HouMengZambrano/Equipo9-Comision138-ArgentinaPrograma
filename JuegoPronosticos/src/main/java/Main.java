import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.toBinaryString;

public class Main {

    //------------------------------------JUEGO-----------------------------------//
    public static void main(String[] args) throws IOException {
        juego();
    }
    //-----------------------------------JUEGO-------------------------------------//







    //---------------------------------METODOS------------------------------------------//
    // Crear la Fase y agregarle la lista de rondas :  FUNCIONA
    public static Fase crearFase() throws IOException {
        Fase fase =  null;
        String numeroFase = null;
        List<Ronda> listaRondas = crearListaRondasDB();
        try{
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM fases");
            while(rs.next()){
                numeroFase = rs.getString("numero");
                fase = new Fase(numeroFase, listaRondas);
            }
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  fase;
    }

    // Crear un lista de rondas leyendo la base de datos: FUNCIONA
    public static List<Ronda> crearListaRondasDB(){
        List<Ronda> rondas = new ArrayList<>();
        List<Partido> partidos = new ArrayList<>();
        Ronda ronda = null;
        try{
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Rondas.numero AS nombre_ronda, P.idPartido, E1.nombre AS equipo1_nombre, E2.nombre As equipo2_nombre, R1.goles AS goles_equipo1, R2.goles AS goles_equipo2, R1.resultado \n" +
                    "AS resultado_equipo1, R2.resultado AS resultado_equipo2\n" +
                    "FROM partidos P\n" +
                    "JOIN resultados R1 ON P.idPartido = R1.Partidos_idPartido AND R1.Equipos_idEquipo = P.equipo1_idEquipo\n" +
                    "JOIN resultados R2 ON P.idPartido = R2.Partidos_idPartido AND R2.Equipos_idEquipo = P.equipo2_idEquipo\n" +
                    "JOIN equipos E1 ON P.equipo1_idEquipo = E1.idEquipo\n" +
                    "JOIN equipos E2 ON P.equipo2_idEquipo = E2.idEquipo\n" +
                    "JOIN rondas Rondas ON P.rondas_id = Rondas.id\n" +
                    "GROUP BY P.idPartido, Rondas.numero;");
            String nombreRondaActual = null;
            String nombreRondaAnterior = "";
            while(rs.next()){
                    nombreRondaActual= rs.getString("nombre_ronda");
                    if(!nombreRondaActual.equals(nombreRondaAnterior)){
                        if (ronda != null) {
                            ronda.setPartidos(partidos);
                            rondas.add(ronda);
                        }
                        ronda = new Ronda();
                        ronda.setNumero(rs.getString("nombre_ronda"));
                        partidos = new ArrayList<>();
                    }
                    int idPartido = rs.getInt("idPartido");
                    Equipo equipo1 = new Equipo(rs.getString("equipo1_nombre"));
                    Equipo equipo2 = new Equipo(rs.getString("equipo2_nombre"));
                    int golesEquipo1 = rs.getInt("goles_equipo1");
                    int golesEquipo2 = rs.getInt("goles_equipo2");
                    Partido p = new Partido(idPartido, equipo1, equipo2, golesEquipo1, golesEquipo2);
                    partidos.add(p);
                    nombreRondaAnterior = nombreRondaActual;
                    }
            if(ronda != null){
                ronda.setPartidos(partidos);
                rondas.add(ronda);
            }
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  rondas;
    }
    //Crear la lista de participantes: FUNCIONA

    public static List<Participante> crearListaParticipantesDB(){
        List<Participante> listaDeParticipantes = new ArrayList<>();
        List<Pronostico> listaDePronosticos = null;
        Participante participante = null;
        try{
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Participantes.nombre, Partidos.idPartido, Equipo1.nombre AS equipo1, Equipo2.nombre AS equipo2, Pronosticos.resultado, Equipos.nombre AS equipoSeleccionado\n" +
                    "FROM Participantes\n" +
                    "JOIN Pronosticos ON Participantes.idParticipantes = Pronosticos.Participantes_idParticipantes\n" +
                    "JOIN Partidos ON Pronosticos.Partidos_idPartido = Partidos.idPartido\n" +
                    "JOIN Equipos AS Equipo1 ON Partidos.equipo1_idEquipo = Equipo1.idEquipo\n" +
                    "JOIN Equipos AS Equipo2 ON Partidos.equipo2_idEquipo = Equipo2.idEquipo\n" +
                    "JOIN Equipos ON Pronosticos.Equipos_idEquipo = Equipos.idEquipo\n" +
                    "ORDER BY Participantes.nombre;");
            String participanteActual = null;
            String participanteAnterior = "";
            while(rs.next()){
                participanteActual = rs.getString("nombre");
                if(!participanteActual.equals(participanteAnterior)){
                    if (participante != null) {
                        participante.setListaDePronosticos(listaDePronosticos);
                        listaDeParticipantes.add(participante);
                    }
                    participante = new Participante();
                    participante.setNombre(rs.getString("nombre"));
                    listaDePronosticos = new ArrayList<>();
                }
                int idPartido = rs.getInt("idPartido");
                Equipo equipo1 = new Equipo(rs.getString("equipo1"));
                Equipo equipo2 = new Equipo(rs.getString("equipo2"));
                Partido partido = new Partido(idPartido, equipo1, equipo2);
                Equipo equipoSeleccionado = new Equipo(rs.getString("equipoSeleccionado"));
                ResultadoEnum resultado = ResultadoEnum.valueOf(rs.getString("resultado").toUpperCase());
                Pronostico pronostico = new Pronostico(equipoSeleccionado, partido, resultado);
                listaDePronosticos.add(pronostico);
                participanteAnterior = participanteActual;
            }
            if(participante != null){
                participante.setListaDePronosticos(listaDePronosticos);
                listaDeParticipantes.add(participante);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listaDeParticipantes;
    }


    public static void juego() throws IOException {
        Fase fase = crearFase();
        List<Participante> juegoParticipantes = crearListaParticipantesDB();
        System.out.println("Fase : " +  fase.getNumero());
        for (Ronda r : fase.getRondas()) {
            System.out.println("Ronda : " + r.getNumero());
            int partidosXRonda = r.getPartidos().size();

            for (Partido partido : r.getPartidos()) {
                System.out.println(partido.getEquipo1().getNombre() + " vs " + partido.getEquipo2().getNombre());
            }
            for (Participante participante : juegoParticipantes) {
                int aciertosXRonda = 0;
                for (Partido partido : r.getPartidos()) {
                    for (Pronostico pronostico : participante.getListaDePronosticos()) {
                        if (pronostico.getPartido().getId() == partido.getId()) {
                            pronostico.setPartido(partido);
                            if (pronostico.esAcertado()) {
                                int puntos = pronostico.puntos() + participante.getPuntos();
                                participante.setPuntos(puntos);
                                aciertosXRonda++;
                            } else{
                                participante.setAcertoTodosLosPartidosXFase(false);
                            }
                        }
                    }
                }
                System.out.println("Participante " + participante.getNombre() + " acerto " + aciertosXRonda + " de " + partidosXRonda + " partidos de la ronda " + r.getNumero());
                if(aciertosXRonda == partidosXRonda){
                    System.out.println("El participante " + participante.getNombre() + " obtiene 5 puntos extras por acertar todos los partidos de una Ronda");
                    participante.puntosExtraXRonda();
                }

            }
        }
        for (Participante participante : juegoParticipantes) {
            if(participante.acertoTodosLosPartidosXFase()){
                System.out.println("El participante " + participante.getNombre() + " obtiene 10 puntos extras por acertar todos los partidos de una Fase");
                participante.puntosExtraXFase();
            }
            System.out.println("Participante " + participante.getNombre() + " obtuvo un puntaje final de : " + participante.getPuntos() + " puntos!"
            );
        }
    }

};














































