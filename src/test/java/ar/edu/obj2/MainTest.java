package ar.edu.obj2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainTest {

    Socio socio;
    Libro libro;

    @BeforeEach
    void setup() {
        Umbral.getInstancia().setValor(1500);
    }

    @Test
    public void testSocioGetters() {
        socio = new Socio(25, 1000, 12);
        assertEquals(25, socio.getEdad());
        assertEquals(1000, socio.getDeuda());
        assertEquals(12, socio.getAntiguedadMeses());
    }

    @Test
    public void testSinDeuda() {
        socio = new Socio(30, 0, 12);
        libro = new LibroBuilder("Libro").sinDeuda().build();
        assertTrue(libro.puedeSerPrestadoA(socio));

        socio = new Socio(30, 500, 12);
        assertFalse(libro.puedeSerPrestadoA(socio));
    }

    @Test
    public void testDebajoDelUmbral() {
        socio = new Socio(30, 1000, 12);
        libro = new LibroBuilder("Libro").conUmbral().build();
        assertTrue(libro.puedeSerPrestadoA(socio));

        socio = new Socio(30, 2000, 12);
        assertFalse(libro.puedeSerPrestadoA(socio));
    }

    @Test
    public void testMayorDeEdad() {
        socio = new Socio(20, 0, 10);
        libro = new LibroBuilder("Libro").soloMayores().build();
        assertTrue(libro.puedeSerPrestadoA(socio));

        socio = new Socio(16, 0, 10);
        libro = new LibroBuilder("Libro").soloMayores().build();
        assertFalse(libro.puedeSerPrestadoA(socio));
    }

    @Test
    public void testClasicoAntiguosSocios() {
        socio = new Socio(30, 0, 6);
        libro = new LibroBuilder("Libro").clasico().build();
        assertTrue(libro.puedeSerPrestadoA(socio));

        socio = new Socio(30, 0, 4);
        libro = new LibroBuilder("Libro").clasico().build();
        assertFalse(libro.puedeSerPrestadoA(socio));
    }

    @Test
    public void testLibroNombre() {
        Libro libro = new Libro("Rayuela");
        assertEquals("Rayuela", libro.getNombre());
    }

    @Test
    public void testLibroPuedePrestarse() {
        socio = new Socio(30, 0, 10);
        libro = new LibroBuilder("Test").build();
        assertTrue(libro.puedeSerPrestadoA(socio));

        socio = new Socio(17, 0, 2);
        libro = new LibroBuilder("Test").soloMayores().build();
        assertFalse(libro.puedeSerPrestadoA(socio));
    }

    @Test
    public void testLibroBuilder() {
        socio = new Socio(30, 0, 10);
        libro = new LibroBuilder("1984")
            .clasico()
            .soloMayores()
            .sinDeuda()
            .build();
        assertTrue(libro.puedeSerPrestadoA(socio));

        socio = new Socio(30, 1200, 10);
        Libro otro = new LibroBuilder("Animal Farm")
            .conUmbral()
            .build();
        assertTrue(otro.puedeSerPrestadoA(socio));

        socio = new Socio(30, 0, 3);
        Libro clasico = new LibroBuilder("Don Quijote")
            .clasico()
            .build();
        assertFalse(clasico.puedeSerPrestadoA(socio));
    }

    @Test
    public void testCambioDeUmbral() {
        libro = new LibroBuilder("El Principito")
            .conUmbral()
            .build();

        socio = new Socio(30, 2000, 10);
        assertFalse(libro.puedeSerPrestadoA(socio));

        Umbral.getInstancia().setValor(2500);
        assertTrue(libro.puedeSerPrestadoA(socio));
    }

    @Test
    public void testCambioDinamicoDeAnalizador() {
        libro = new LibroBuilder("Cien Años de Soledad")
            .clasico()
            .conUmbral()
            .build();

        socio = new Socio(30, 1400, 6);
        assertTrue(libro.puedeSerPrestadoA(socio));

        socio = new Socio(30, 1600, 6);
        assertFalse(libro.puedeSerPrestadoA(socio));
    }

    @Test
    void testSinDeuda_conDeudaCeroPeroDecoradoFalla() {
        socio = new Socio(30, 0, 10);
        libro = new Libro("Libro sin deuda");
        libro.setAnalizadorPrestamos(new SinDeuda(s -> false));

        assertFalse(libro.puedeSerPrestadoA(socio));
    }

    @Test
    void testDebajoDelUmbral_conDeudaBajaPeroDecoradoFalla() {
        Umbral.getInstancia().setValor(2000);
        socio = new Socio(30, 1000, 10);
        libro = new Libro("Libro con umbral");
        libro.setAnalizadorPrestamos(new DebajoDelUmbral(s -> false));

        assertFalse(libro.puedeSerPrestadoA(socio));
    }

    @Test
    void testMayorDeEdad_mayorDeEdadPeroDecoradoFalla() {
        socio = new Socio(25, 0, 10);
        libro = new Libro("Libro adulto");
        libro.setAnalizadorPrestamos(new MayorDeEdad(s -> false));

        assertFalse(libro.puedeSerPrestadoA(socio));
    }

    @Test
    void testClasicoAntiguosSocios_antiguoPeroDecoradoFalla() {
        socio = new Socio(40, 0, 10);
        libro = new Libro("Libro clásico");
        libro.setAnalizadorPrestamos(new ClasicoAntiguosSocios(s -> false));

        assertFalse(libro.puedeSerPrestadoA(socio));
    }
}
