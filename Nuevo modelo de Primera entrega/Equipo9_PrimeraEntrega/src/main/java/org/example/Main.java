package org.example;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Partido partido = new Partido();
        List<Partido> partidos = partido.leerPartidos("src/main/resources/resultados.txt");
    }
}