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

public class Main {
    //Bueno vamos a ir por paso :

    //LISTO//1) Leer un archivo que tenga los resultados de los partidos y asociarles una ronda.
    //LISTO//2) Leer un archivo que tenga los pronoticos de las personas para los partidos.
    //LISTO//3) Tiene que ser capaz de poder leer muchas rondas y de muchas personas
    //4) Imprimir por pantalla el nombre de la persona, el puntaje total, la cantidad de pronosticos acertados
    //5) Implementar test de las funcionalidades


  // Dudas?
    //1) como leo estos archivos?

    // Ejemplo de archivo resultados.txt:
    /*
       Ronda,1;
       Partido, nombreEquipo1, goles1, goles2, nombreEquipo2;
       Partido, nombreEquipo1, goles1, goles2, nombreEquipo2;
       Ronda,2;
       Partido, nombreEquipo1, goles1, goles2, nombreEquipo2;
       Partido, nombreEquipo1, goles1, goles2, nombreEquipo2;

        1) Lo leo con un scanner
        2) Una ronda se intancia con el nombre y con una lista de partidos;
        3) Asigno el valor del nombre a una variable
        4) Luego tengo que crear una lista de partidos
        5) Asignar esa lista de partidos a una variable
        6) Instanciar la ronda
        7)Buscar la manera de que lo haga en un loop, para que instancie todas las rondas.
     */
        //Resolucion:    //este metodo quedo pero perfect!
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
                Equipo equipo1 = new Equipo(partidoData[1]);
                Equipo equipo2 = new Equipo(partidoData[4]);
                int golesEquipo1 = Integer.parseInt(partidoData[2]);
                int golesEquipo2 = Integer.parseInt(partidoData[3]);
                Partido p = new Partido(equipo1, equipo2, golesEquipo1, golesEquipo2);
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


    //Ejemplo de archivo de pronosticos.txt;
    /*
        Participante, nombreParticipante;
        Pronostico, equipo, partido, resultado;
        Pronostico, equipo, partido, resultado;
        Participante, nombreParticipante;
        Pronostico, equipo, partido, resultado;
        Pronostico, equipo, partido, resultado;

        1)Crear un metodo que me devuelva una lista Participantes y que cada participante
        tenga como atributos un nombre, lista de pronosticos, puntos;
        2)Leer el archivo con Filereadalllines
        3) Que pregunte si la linea comienza con Participante y haga la logica para
        instanciar el partipante y setearle el nombre
        4) si la linea no arranca con participante entonces es un pronostico,
        y hacer la logica para que instancie el pronostico, y luego lo agrege a la lista de
        pronosticos del participante.
     */
    //Resolucion: QUEDO GOD!
    public static List<Participante> crearListaDeParticipantes(String archivoPronosticoTxt) throws IOException {
        List<Participante> listaDeParticipantes = new ArrayList<>();
        List<Pronostico> listaDePronosticos = null;
        Participante participante = null;
        List<String> lineasArchivo = Files.readAllLines(Paths.get(archivoPronosticoTxt));
        for (String linea : lineasArchivo) {
            if (linea.startsWith("Participante")) {
                if(participante != null){
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
                Equipo equipo1 = new Equipo(dataPronostico[1]);
                Equipo equipo2 = new Equipo(dataPronostico[3]);
                Partido partido = new Partido(equipo1,equipo2);
                ResultadoEnum resultado = ResultadoEnum.valueOf(dataPronostico[2]);
                Pronostico pronostico = new Pronostico(equipo1,partido,resultado);
                listaDePronosticos.add(pronostico);
                participante.setListaDePronosticos(listaDePronosticos);
            }
        }
        listaDeParticipantes.add(participante);
        return listaDeParticipantes;
    };

    public static void main(String[] args) throws IOException {
        String resultadosTxt =  "src/main/resources/ResultadosNuevoModelo.txt";
        String pronosticoTxt = "src/main/resources/ProsnosticosNuevoModelo.txt";
        juego(resultadosTxt,pronosticoTxt);
    }
        //El tema es que no se como voy a hacer para decirle que pronostico pertenece a que ronda :(
    //--------------------------------------Ahora a crear el metodo del juego----------------------------------------------------//
    //1) Imprimir por pantalla el nombre de la persona, el puntaje total, la cantidad de pronosticos acertados
    /*
    Como lo voy a hacer ?
    1) Ya tengo dos metodos que me instancian una lista de participantes  y una lista de Partidos
    A)Imprimir el nombre de la persona
    -Para eso tengo que acceder a la lista de participantes recorrerla y tomar el nombre de cada uno y guardarla en una variable
    B)Impirimir el puntaje
    -Tengo que incrementarlo por cada acierto que el participantes tenga y setearlo al participante (USAR EL METODO DE PRONOSTICO PARA ESO)
    c)Imprimir cantidad de pronosticos acertador
    -Guardar en una variable la cantidad de aciertos por ronda ? ESTO PARA ULTIMO
     */

    //Resolucion:

    //Lo voy a hacer void al metodo por que no me esta pidiendo que guarde todoeso en variables.
    public static void juego(String resultadosTxt, String pronosticosTxt) throws IOException {
        //Leer los archivos e instanciar los objetos correspondientes
        List<Participante> juegoParticipantes = crearListaDeParticipantes(pronosticosTxt);
        List<Ronda> juegoRondas = crearListaRondas(resultadosTxt);
        int puntos = 0;

        //Voy a recorrer la lista de participantes para obtener el nombre de los participantes
       for(Ronda r : juegoRondas){
           System.out.println(r.getNumero() + " ronda :");
           for(Partido partido : r.getPartidos()){
               System.out.println(partido.getEquipo1().getNombre() + " vs " + partido.getEquipo2().getNombre() );
               for(Participante participante : juegoParticipantes){
                   //System.out.println("Participante " + participante.getNombre());
                   for(Pronostico pronostico: participante.getListaDePronosticos()){
                       if(pronostico.getPartido().getEquipo1().getNombre().equals(partido.getEquipo1().getNombre()) &&
                       pronostico.getPartido().getEquipo2().getNombre().equals(partido.getEquipo2().getNombre())){
                           pronostico.setPartido(partido);
                           //System.out.println("Pronostico a " + pronostico.getEquipo().getNombre() + " como '" + pronostico.getResultado()+"'");
                       if(pronostico.getResultado().equals(partido.calcularResultado(pronostico.getEquipo()))){
                           puntos = pronostico.puntos() + participante.getPuntos();
                           participante.setPuntos(puntos);
//Problematica: como asignar los puntos sin que se sobrepongan
/*
 Cada vez que lo corre siempre toma como valor la variable puntos
 LO SOLUCIONE, llamando asignandole a puntos el valor del metodo "pronostico.puntos()" mas el metodo "participante.getPuntos",
 y luego le asigno el valor de la variables puntos a el atributo puntos del participante con el metodo "participante.setPunto"
 */
                           //System.out.println("Acerto el pronostico, su puntaje es : " + participante.getPuntos());
                       }else{
                           //System.out.println("No acerto el pronostico, su puntaje es : " + participante.getPuntos());
                       }
                       }
                   }
               }

           }

       }
        for(Participante participante: juegoParticipantes){
            System.out.println("Participante " +  participante.getNombre() + " obtuvo un puntaje final de : " + participante.getPuntos() +
                    " puntos al acertar " + participante.getPuntos() + " pronosticos"
                    );
        }
    }

//    public static Map<String, Integer> juego(String archivo) throws IOException {
//        List<Ronda> listaDeRondas = crearListaRondas("src/main/resources/Resultados.txt");
//        Map<String, Integer> mapaDePuntos = new HashMap<>();
//        List<Partido> listaDePartidos = null;
//        for(Ronda r : listaDeRondas){
//            listaDePartidos = r.getPartidos();
//            Scanner scanner = new Scanner(new File(archivo));
//            String pronosticador = null;
//            Pronostico pronostico = null;
//            int puntos = 0;
//            while (scanner.hasNextLine()) {
//                String linea = scanner.nextLine().trim();
//                if (linea.startsWith("Pronostico:")) {
//                    if (pronosticador != null) {
//                        mapaDePuntos.put(pronosticador, puntos);
//                        puntos = 0;
//                    }
//                    pronosticador = scanner.nextLine().trim();
//                } else {
//                    String[] pronosticoData = linea.split(",");
//                    Equipo equipo1 = new Equipo(pronosticoData[0]);
//                    Equipo equipo2 = new Equipo(pronosticoData[2]);
//                    ResultadoEnum resultadoPronosticado = ResultadoEnum.valueOf(pronosticoData[1]);
//                    Partido partido = new Partido(equipo1, equipo2);
//                    pronostico = new Pronostico(equipo1,partido,resultadoPronosticado);
//                    for (Partido p : listaDePartidos) {
//                        if (p.getEquipo1().getNombre().equals(pronostico.getPartido().getEquipo1().getNombre())
//                                && p.getEquipo2().getNombre().equals(pronostico.getPartido().getEquipo2().getNombre())) {
//                            partido = p;
//                            // Aqui lo que estoy jugando es que el partido previamente estaba instanciado con
//                            pronostico.setPartido(partido);
//                            break;
//                        }
//                    }
//                    if (partido == null) {
//                        System.out.println("Hay un error en la lectura del pronostico");
//                    } else {
//                        puntos += pronostico.puntos();
//                    }
//                }
//            }
//            if (pronosticador != null) {
//                mapaDePuntos.put(pronosticador, puntos);
//            }
//            System.out.println(r.getNumero() + " ronda!!");
//            System.out.println(mapaDePuntos);
//            scanner.close();
//        }
//
//        return mapaDePuntos;
//    }
    };