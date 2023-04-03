package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Partido {
    private Equipo equipo1;
    private Equipo equipo2;
    private int golesEquipo1;
    private int golesEquipo2;

    public Partido(){}
    public Partido(Equipo equipo1, Equipo equipo2, int golesEquipo1, int golesEquipo2) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.golesEquipo1 = golesEquipo1;
        this.golesEquipo2 = golesEquipo2;
    }
    public List<Partido> leerPartidos(String archivo) throws FileNotFoundException {
        List<Partido> listaPartidos = new ArrayList<>();
        Scanner scanner = new Scanner(new File(archivo));
        Partido partido = null;
        while (scanner.hasNextLine()) {
            String linea = scanner.nextLine();
            if (linea.startsWith("Partido")) {
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
        scanner.close();
        return listaPartidos;
    };

    public Resultado calcularResultado(Equipo equipo){
        int golesEquipoSeleccionado = 0;
        int golesEquipoNoSeleccionado = 0;
        Resultado resultado = null;

        if(equipo.getNombre().equals(equipo1.getNombre())){
            golesEquipoSeleccionado = golesEquipo1;
            golesEquipoNoSeleccionado = golesEquipo2;
        }else if (equipo.getNombre().equals(equipo2.getNombre())){
            golesEquipoSeleccionado = golesEquipo2;
            golesEquipoNoSeleccionado = golesEquipo1;
        }

        if(golesEquipoSeleccionado < golesEquipoNoSeleccionado){
            System.out.println("Perdio: " + equipo.getNombre());
            resultado = Resultado.PERDEDOR;
        } else if (golesEquipoSeleccionado == golesEquipoNoSeleccionado) {
            System.out.println("Empato: " + equipo.getNombre());
            resultado =  Resultado.EMPATE;
        } else if (golesEquipoSeleccionado > golesEquipoNoSeleccionado) {
            System.out.println("Gano: " + equipo.getNombre());
            resultado = Resultado.GANADOR;
        }else{
            System.out.println("Hay un error");
        }
        return resultado;
    };

    public Equipo getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }

    public int getGolesEquipo1() {
        return golesEquipo1;
    }

    public void setGolesEquipo1(int golesEquipo1) {
        this.golesEquipo1 = golesEquipo1;
    }

    public int getGolesEquipo2() {
        return golesEquipo2;
    }

    public void setGolesEquipo2(int golesEquipo2) {
        this.golesEquipo2 = golesEquipo2;
    }

    @Override
    public String toString() {
        return "Partido{" +
                "equipo1=" + equipo1 +
                ", equipo2=" + equipo2 +
                ", golesEquipo1=" + golesEquipo1 +
                ", golesEquipo2=" + golesEquipo2 +
                '}';
    }
}
