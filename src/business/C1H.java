package business;
public class C1H extends C1 {
    private int potenciaME;

    public C1H( String marca, String modelo, int potenciaMotor, int potenciaMotorElec, float PAC) {
        super(marca, modelo, potenciaMotor, PAC);
        this.potenciaME = potenciaMotorElec;
    }

    public void setPotenciaHibrido(int aPotenciaMotor) {
        throw new UnsupportedOperationException();
    }

    public int getPotencia() {
        throw new UnsupportedOperationException();
    }
}