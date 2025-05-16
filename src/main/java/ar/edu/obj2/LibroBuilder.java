package ar.edu.obj2;

public class LibroBuilder {
    private String nombre;
    private AnalisisPrestamo analizador = new PrestamoPermitido();

    public LibroBuilder(String nombre) {
        this.nombre = nombre;
    }

    public LibroBuilder clasico() {
        this.analizador = new ClasicoAntiguosSocios(this.analizador);
        return this;
    }

    public LibroBuilder soloMayores() {
        this.analizador = new MayorDeEdad(this.analizador);
        return this;
    }

    public LibroBuilder sinDeuda() {
        this.analizador = new SinDeuda(this.analizador);
        return this;
    }

    public LibroBuilder conUmbral() {
        this.analizador = new DebajoDelUmbral(this.analizador);
        return this;
    }

    public Libro build() {
        Libro libro = new Libro(nombre);
        libro.setAnalizadorPrestamos(this.analizador);
        return libro;
    }
}
