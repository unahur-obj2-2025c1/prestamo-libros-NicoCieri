package ar.edu.obj2;

public class Umbral {
    private static final Umbral instancia = new Umbral();
    private double valor = 0;

    private Umbral() {}

    public static Umbral getInstancia() {
        return instancia;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
