public class C1H extends C1 {
    private int potenciaME;

    public C1H(float cilindrada, String pneus, int fiabilidade, String marca, String modelo, int potenciaMC, int nAfinacoes, String equipa, String modoMotor, String id, int downforce, Piloto piloto, int potenciaME) {
        super(cilindrada, pneus, fiabilidade, marca, modelo, potenciaMC, nAfinacoes, equipa, modoMotor, id, downforce, piloto);
        this.potenciaME = potenciaME;
    }

    public void setPotenciaHibrido(int aPotenciaMotor) {
        throw new UnsupportedOperationException();
    }

    public int getPotencia() {
        throw new UnsupportedOperationException();
    }
}
