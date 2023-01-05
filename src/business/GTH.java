package business;

public class GTH extends GT {
    private int potenciaME;

    public GTH(String marca, String modelo, float cilindrada, int potenciaMC, int potenciaME, float downforce) {
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
