public class C2H extends C2 {
    private int potenciaME;

    public C2H(float cilindrada, String pneus, int fiabilidade, String marca, String modelo, int potenciaMC, String equipa, String modoMotor, String id, int downforce, Piloto piloto, int nAfinacoes, int potenciaME) {
        super(cilindrada, pneus, fiabilidade, marca, modelo, potenciaMC, equipa, modoMotor, id, downforce, piloto, nAfinacoes);
        this.potenciaME = potenciaME;
    }

    public void setPotenciaHibrido(int aPotenciaMotor) {
        throw new UnsupportedOperationException();
    }

    public int getPotencia() {
        throw new UnsupportedOperationException();
    }
}