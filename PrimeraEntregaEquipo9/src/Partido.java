import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Partido {
    private int id;
    private Equipo equipo1;
    private Equipo equipo2;
    private int golesEquipo1;
    private int golesEquipo2;

    public Partido(){

    }
    public Partido(int id,Equipo equipo1, Equipo equipo2, int golesEquipo1, int golesEquipo2) {
        this.id =id;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.golesEquipo1 = golesEquipo1;
        this.golesEquipo2 = golesEquipo2;
    }
// Este metodo esa bien pero ahora tengo que hacerlo para que lea mas de un partido;
    public List<Partido> leerPartidos(String archivo) throws FileNotFoundException{
        List<Partido> listaPartidos = new ArrayList<>();
        Scanner scanner = new Scanner(new File(archivo));
        Partido partido = null;
        while (scanner.hasNextLine()) {
            String linea = scanner.nextLine();
            if (linea.startsWith("Partido")) {
                int id = Integer.parseInt(scanner.nextLine().trim().split(",")[1]);
                String[] equipo1Data = scanner.nextLine().trim().split(",");
                int idEquipo1 = Integer.parseInt(equipo1Data[0]);
                String nombreEquipo1 = equipo1Data[1];
                String descripcionEquipo1 = equipo1Data[2];
                Equipo equipo1 = new Equipo(idEquipo1, nombreEquipo1, descripcionEquipo1);
                String[] equipo2Data = scanner.nextLine().trim().split(",");
                int idEquipo2 = Integer.parseInt(equipo2Data[0]);
                String nombreEquipo2 = equipo2Data[1];
                String descripcionEquipo2 = equipo2Data[2];
                Equipo equipo2 = new Equipo(idEquipo2, nombreEquipo2, descripcionEquipo2);
                int golesEquipo1 = Integer.parseInt(scanner.nextLine().trim().split(",")[1]);
                int golesEquipo2 = Integer.parseInt(scanner.nextLine().trim().split(",")[1]);
                partido = new Partido(id, equipo1, equipo2, golesEquipo1, golesEquipo2);
                System.out.println(partido);
                listaPartidos.add(partido);
            }
        }
        scanner.close();
        System.out.println(listaPartidos);
        return listaPartidos;
    };


//Con este metodo individual pude desifrar el siguiente Gracias!!!
//    public Partido leerPartido(String archivo) throws FileNotFoundException {
//        Scanner scanner = new Scanner(new File(archivo));
//        Partido partido = null;
//        while (scanner.hasNextLine()) {
//            String linea = scanner.nextLine();
//            if (linea.startsWith("Partido")) {
//                int id = Integer.parseInt(scanner.nextLine().trim().split(",")[1]);
//                String[] equipo1Data = scanner.nextLine().trim().split(",");
//                int idEquipo1 = Integer.parseInt(equipo1Data[0]);
//                String nombreEquipo1 = equipo1Data[1];
//                String descripcionEquipo1 = equipo1Data[2];
//                Equipo equipo1 = new Equipo(idEquipo1, nombreEquipo1, descripcionEquipo1);
//                String[] equipo2Data = scanner.nextLine().trim().split(",");
//                int idEquipo2 = Integer.parseInt(equipo2Data[0]);
//                String nombreEquipo2 = equipo2Data[1];
//                String descripcionEquipo2 = equipo2Data[2];
//                Equipo equipo2 = new Equipo(idEquipo2, nombreEquipo2, descripcionEquipo2);
//                int golesEquipo1 = Integer.parseInt(scanner.nextLine().trim().split(",")[1]);
//                int golesEquipo2 = Integer.parseInt(scanner.nextLine().trim().split(",")[1]);
//                partido = new Partido(id, equipo1, equipo2, golesEquipo1, golesEquipo2);
//            }
//        }
//        scanner.close();
//        System.out.println(partido);
//        return partido;
//    }

    //Este metodo resultado quedo GOD!!
    public Resultado calcularResultado(Equipo equipo){
        int golesEquipoSeleccionado = 0;
        int golesEquipoNoSeleccionado = 0;

        if(equipo.getNombre().equals(equipo1.getNombre())){
            golesEquipoSeleccionado = golesEquipo1;
            golesEquipoNoSeleccionado = golesEquipo2;
        }else if (equipo.getNombre().equals(equipo2.getNombre())){
            golesEquipoSeleccionado = golesEquipo2;
            golesEquipoNoSeleccionado = golesEquipo1;
        }

        if(golesEquipoSeleccionado < golesEquipoNoSeleccionado){
            System.out.println("Perdio: " + equipo.getNombre());
            return Resultado.PERDEDOR;
        } else if (golesEquipoSeleccionado == golesEquipoNoSeleccionado) {
            System.out.println("Empato: " + equipo.getNombre());
            return Resultado.EMPATE;
        } else if (golesEquipoSeleccionado > golesEquipoNoSeleccionado) {
            System.out.println("Gano: " + equipo.getNombre());
            return Resultado.GANADOR;
        }
        return null;
    };


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", equipo1=" + equipo1 +
                ", equipo2=" + equipo2 +
                ", golesEquipo1=" + golesEquipo1 +
                ", golesEquipo2=" + golesEquipo2 +
                '}';
    }
}
