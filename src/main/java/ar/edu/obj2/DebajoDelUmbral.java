package ar.edu.obj2;

public class DebajoDelUmbral implements AnalisisPrestamo{
    private AnalisisPrestamo decorado;

    public DebajoDelUmbral(AnalisisPrestamo decorado) {
        this.decorado = decorado;
    }

    @Override
    public boolean puedeSerPrestadoA(Socio socio) {
        return socio.getDeuda() < Umbral.getInstancia().getValor() && decorado.puedeSerPrestadoA(socio);
    }
}
