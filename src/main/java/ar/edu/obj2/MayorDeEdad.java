package ar.edu.obj2;

public class MayorDeEdad implements AnalisisPrestamo {
    private AnalisisPrestamo decorado;

    public MayorDeEdad(AnalisisPrestamo decorado) {
        this.decorado = decorado;
    }

    @Override
    public boolean puedeSerPrestadoA(Socio socio) {
        return socio.getEdad() >= 18 && decorado.puedeSerPrestadoA(socio);
    }
}
