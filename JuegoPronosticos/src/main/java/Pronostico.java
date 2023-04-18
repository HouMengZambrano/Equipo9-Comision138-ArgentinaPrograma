public class Pronostico {
    private Equipo equipo;
    private Partido partido;
    private ResultadoEnum resultado;

    public Pronostico(Equipo equipo, Partido partido, ResultadoEnum resultado) {
        this.equipo = equipo;
        this.partido = partido;
        this.resultado = resultado;
    }

    public Pronostico(){};

    public int puntos() {
            // this.resultado -> pred
            ResultadoEnum resultadoReal = this.partido.calcularResultado(this.equipo);
            if (this.resultado.equals(resultadoReal)) {
                return 1;
            } else {
                return 0;
            }
    }


    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public ResultadoEnum getResultado() {
        return resultado;
    }

    public void setResultado(ResultadoEnum resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "Pronostico{" +
                "equipo=" + equipo +
                ", partido=" + partido +
                ", resultado=" + resultado +
                '}';
    }
}
