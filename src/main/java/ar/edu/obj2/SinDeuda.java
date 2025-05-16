package ar.edu.obj2;

public class SinDeuda implements AnalisisPrestamo {
    private AnalisisPrestamo decorado;

    public SinDeuda(AnalisisPrestamo decorado) {
        this.decorado = decorado;
    }

    @Override
    public boolean puedeSerPrestadoA(Socio socio) {
        return socio.getDeuda() == 0 && decorado.puedeSerPrestadoA(socio);
    };
}
