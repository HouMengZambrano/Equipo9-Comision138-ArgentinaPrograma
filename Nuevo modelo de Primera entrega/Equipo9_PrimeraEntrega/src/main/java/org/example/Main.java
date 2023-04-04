package org.example;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Pronostico pronostico = new Pronostico();
        pronostico.leerPronostico("src/main/resources/Pronosticos.txt");

    }
}