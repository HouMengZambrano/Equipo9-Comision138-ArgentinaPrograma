public class Main {
    public static void main(String[] args) {
        Equipo e1 = new Equipo("Argentina", "Seleccion");
        Equipo e2 = new Equipo("ArabiaSaudita", "Seleccion");

        Partido p = new Partido(e1,e2,1,1);

        p.resultado(e2);
        p.resultado(e1);
    }

}