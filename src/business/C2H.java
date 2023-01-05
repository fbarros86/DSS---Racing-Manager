package business;


public class C2H extends C2 {
    private int potenciaME;

    public C2H(String marca, String modelo, float cilindrada, int potenciaMC, int potenciaME, float downforce) {
        super(marca, modelo,potenciaMC, potenciaME, downforce);
        this.cilindrada = cilindrada;
    }

    public void setPotenciaHibrido(int aPotenciaMotor) {
        throw new UnsupportedOperationException();
    }

    public int getPotencia() {
        throw new UnsupportedOperationException();
    }
}