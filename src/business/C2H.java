package business;


public class C2H extends C2 {
    private int potenciaME;
    private int cilindrada;

    public C2H(String marca, String modelo, int cilindrada, int potenciaMC, int potenciaME, float downforce) {
        super(marca, modelo,potenciaMC, potenciaME, downforce);
        this.cilindrada = cilindrada;
    }

    public C2H(String id, int cilindrada, int fiabilidade, String marca, String modelo , int potenciaMC,
               float downforce, int potenciaEletrica) {
        super(id, cilindrada, fiabilidade, marca, modelo, potenciaMC,downforce);
        this.potenciaME = potenciaEletrica;
    }
    public C2H(String id, int cilindrada, int fiabilidade, String marca, String modelo, String pneus, int potenciaMC,
               String equipa, int modoMotor, float downforce, int afinacoes, int potenciaEletrica, Piloto piloto) {
        super(id, cilindrada, fiabilidade, marca, modelo, pneus, potenciaMC, equipa, modoMotor, downforce, afinacoes, piloto);
        this.potenciaME = potenciaEletrica;
    }


    public void setPotenciaHibrido(int aPotenciaMotor) {
        this.potenciaME=aPotenciaMotor;
    }

    public int getPotencia() {
        return this.potenciaME;
    }

    public String toOptionalString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toOptionalString()).append("Potencia do motor eletrico: ").append(potenciaME);
        return sb.toString();
    }
}