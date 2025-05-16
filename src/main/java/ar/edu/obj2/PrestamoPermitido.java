package ar.edu.obj2;

public class PrestamoPermitido implements AnalisisPrestamo {
    @Override
    public boolean puedeSerPrestadoA(Socio socio) {
        return true;
    }
}


