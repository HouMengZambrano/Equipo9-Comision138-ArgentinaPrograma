import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MainTest extends Main {

    @Test
    public void testJuegoNotNull()  throws FileNotFoundException {
        Map<String, Integer> puntos = juego("src/main/resources/Pronosticos.txt");
        assertNotNull(puntos);
    }

    @Test
    public  void puntosDeDuegoEsperadosHouMeng() throws FileNotFoundException {
        Map<String, Integer> puntosActuales = juego("src/main/resources/Pronosticos.txt");
        Map<String, Integer> puntosEsperados = new HashMap<>();
        puntosEsperados.put("HouMeng" , 0 );
        puntosEsperados.put("Jose" , 1);
        puntosEsperados.put("Andrea" , 2);
        assertEquals(puntosEsperados, puntosActuales);
    }

}