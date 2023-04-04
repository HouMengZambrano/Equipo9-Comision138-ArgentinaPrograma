package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Pronostico {
    private Partido partido;
    private Equipo equipo;
    private Resultado resultado;

    public Pronostico(Partido partido,Equipo equipo, Resultado resultado) {
        this.partido = partido;
        this.equipo = equipo;
        this.resultado = resultado;
    }
    public Pronostico(){};

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

    //Falta colocar los puntos estaticos
    public void leerPronostico(String archivo) throws FileNotFoundException {
        int puntos = 0;
        Pronostico pronostico = null;
        Partido partido = new Partido();
        List<Partido> listaDePartidos = partido.leerPartidos("src/main/resources/resultados.txt");
        Scanner scanner = new Scanner(new File(archivo));
        while(scanner.hasNextLine()){
            String linea = scanner.nextLine();
            if(linea.startsWith("Pronostico")){
               String[] dataPronostico = scanner.nextLine().trim().split(",");
               Equipo equipo = new Equipo(dataPronostico[0]);
               Resultado resultado = Resultado.valueOf(dataPronostico[1]);

               for(Partido p: listaDePartidos){
                   if(p.getEquipo1().getNombre().equals(equipo.getNombre())){
                       partido = p;
                   }
               }
                //Quiero comparar el resultado selecionado por la persona con el resultado real;
                if(partido.calcularResultado(equipo).equals(resultado)){
                    System.out.println("Acertaste el pronostico recibiste un punto");
                    puntos+= 1;
                }else{
                    System.out.println("no haz acertado el pronostico");
                }

            }
        }
        scanner.close();
        System.out.println("tienes esta cantidad de puntos :" + puntos);
    }

    public int puntos(){
        return 1;
    }


    @Override
    public String toString() {
        return "Pronostico{" +
                "equipo=" + equipo +
                ", resultado=" + resultado +
                '}';
    }
}
