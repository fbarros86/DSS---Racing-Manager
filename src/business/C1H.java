package business;
public class C1H extends C1 {
    private int potenciaME;

    public C1H( String marca, String modelo, int cilindrada, int potenciaMotor, int potenciaMotorElec, float PAC, String categoria) {
        super(marca, modelo, cilindrada, potenciaMotor, PAC,categoria);
        this.potenciaME = potenciaMotorElec;
    }

    public C1H(String id, int cilindrada, int fiabilidade, String marca, String modelo, String pneus, int potenciaMC,
               String equipa, int modoMotor, float downforce, int afinacoes, int potenciaEletrica, Piloto piloto) {
        super(id, cilindrada, fiabilidade, marca, modelo, pneus, potenciaMC, equipa, modoMotor, downforce, afinacoes, piloto);
        this.potenciaME = potenciaEletrica;
    }

    public void setPotenciaHibrido(int aPotenciaMotor) {
        this.potenciaME=potenciaME;
    }

    public int getPotencia() {
        return potenciaME;
    }

    public String toOptionalString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toOptionalString()).append("Potencia do motor eletrico: ").append(potenciaME);
        return sb.toString();
    }
}