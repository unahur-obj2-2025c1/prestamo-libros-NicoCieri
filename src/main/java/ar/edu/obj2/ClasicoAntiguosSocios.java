package ar.edu.obj2;

public class ClasicoAntiguosSocios implements AnalisisPrestamo {
    private AnalisisPrestamo decorado;

    public ClasicoAntiguosSocios(AnalisisPrestamo decorado) {
        this.decorado = decorado;
    }

    @Override
    public boolean puedeSerPrestadoA(Socio socio) {
        return socio.getAntiguedadMeses() >= 6 && decorado.puedeSerPrestadoA(socio);
    }
}
