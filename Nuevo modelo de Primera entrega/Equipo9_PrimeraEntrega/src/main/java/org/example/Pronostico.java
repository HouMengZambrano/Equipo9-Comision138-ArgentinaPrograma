package org.example;

public class Pronostico {

    private Equipo equipo;
    private Resultado resultado;

    public Pronostico(Equipo equipo, Resultado resultado) {
        this.equipo = equipo;
        this.resultado = resultado;
    }
    public Pronostico(){};

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
                "equipo=" + equipo +
                ", resultado=" + resultado +
                '}';
    }
}
