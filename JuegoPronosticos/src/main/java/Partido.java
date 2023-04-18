public class Partido {
    private Equipo equipo1;
    private Equipo equipo2;
    private int golesEquipo1;
    private int golesEquipo2;

    public Partido(Equipo equipo1, Equipo equipo2, int golesEquipo1, int golesEquipo2) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.golesEquipo1 = golesEquipo1;
        this.golesEquipo2 = golesEquipo2;
    }

    public Partido(Equipo equipo1, Equipo equipo2){
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
    }

    public Partido(){};


    // Este metodo me determina si el equipo que le envio como parametro es gano, empato o perdio;
    public ResultadoEnum calcularResultado(Equipo equipo){
            int golesEquipoSeleccionado = 0;
            int golesEquipoNoSeleccionado = 0;
            ResultadoEnum resultado = null;

            if(equipo.getNombre().equals(equipo1.getNombre())){
                golesEquipoSeleccionado = golesEquipo1;
                golesEquipoNoSeleccionado = golesEquipo2;
            }else if (equipo.getNombre().equals(equipo2.getNombre())){
                golesEquipoSeleccionado = golesEquipo2;
                golesEquipoNoSeleccionado = golesEquipo1;
            }

            if(golesEquipoSeleccionado < golesEquipoNoSeleccionado){

                resultado = ResultadoEnum.PERDEDOR;
            } else if (golesEquipoSeleccionado == golesEquipoNoSeleccionado) {

                resultado =  ResultadoEnum.EMPATE;
            } else if (golesEquipoSeleccionado > golesEquipoNoSeleccionado) {

                resultado = ResultadoEnum.GANADOR;
            }else{
                System.out.println("Hay un error");
            }
            return resultado;
    }

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
                '}';
    }
}
