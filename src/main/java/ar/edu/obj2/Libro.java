package ar.edu.obj2;

public class Libro {
    private String nombre;
    private AnalisisPrestamo analizador;

    public Libro(String nombre) {
        this.nombre = nombre;
        this.analizador = new PrestamoPermitido();
    }

    public void setAnalizadorPrestamos(AnalisisPrestamo analizador) {
        this.analizador = analizador;
    }

    public Boolean puedeSerPrestadoA(Socio socio) {
        return this.analizador.puedeSerPrestadoA(socio);
    }

    public String getNombre() {
        return nombre;
    }
}
