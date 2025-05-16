package ar.edu.obj2;

public class Socio {
    private int edad;
    private double deuda;
    private int antiguedadMeses;

    public Socio(int edad, double deuda, int antiguedadMeses) {
        this.edad = edad;
        this.deuda = deuda;
        this.antiguedadMeses = antiguedadMeses;
    }

    public int getEdad() {
        return edad;
    }

    public double getDeuda() {
        return deuda;
    }

    public int getAntiguedadMeses() {
        return antiguedadMeses;
    }
}
