import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Pronostico {
    private int id;
    private int idPartido;
    private String nombreEquipo;
    private Resultado resultado;

    public Pronostico(int id, int idPartido, String nombreEquipo, Resultado resultado) {
        this.id = id;
        this.idPartido = idPartido;
        this.nombreEquipo = nombreEquipo;
        this.resultado = resultado;
    }
    public Pronostico(){

    }

    //Tengo que buscar hacer un metodo que me lea el archivo y me instancie el el objeto
    public List<Pronostico> leerPronosticos(String archivo) throws FileNotFoundException {
        List<Pronostico> listaPronosticos = new ArrayList<>();
        Scanner scanner = new Scanner(new File(archivo));
        while(scanner.hasNextLine()){
            String linea = scanner.nextLine();
            if(linea.startsWith("Pronostico")){
                int idPronostico = Integer.parseInt(scanner.nextLine().trim().split(",")[1]);
                int idPartido = Integer.parseInt(scanner.nextLine().trim().split(",")[1]);
                String nombreEquipo = scanner.nextLine().trim().split(",")[1];
                String enumResultado = scanner.nextLine().trim().split(",")[1];
                Resultado resultado = Resultado.valueOf(enumResultado);
                Pronostico pronostico = new Pronostico(idPronostico,idPartido,nombreEquipo,resultado);
                listaPronosticos.add(pronostico);
            }
        }
        scanner.close();
        return listaPronosticos;
    }


    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }


    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Pronostico{" +
                "id=" + id +
                ", idPartido=" + idPartido +
                ", nombreEquipo='" + nombreEquipo + '\'' +
                ", resultado=" + resultado +
                '}';
    }

    public int puntosGanados() throws FileNotFoundException {

        return 0;
    }

}

