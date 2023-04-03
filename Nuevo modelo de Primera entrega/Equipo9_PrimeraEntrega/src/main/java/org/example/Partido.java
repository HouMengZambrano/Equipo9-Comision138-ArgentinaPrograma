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
                System.out.println(partido);
                listaPartidos.add(partido);
            }
        }
        scanner.close();
        System.out.println(listaPartidos);
        return listaPartidos;
    };

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
