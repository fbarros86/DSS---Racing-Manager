public class C1 extends Carro {

    private int nAfinacoes;

    public C1(float cilindrada, String pneus, int fiabilidade, String marca, String modelo, int potenciaMC, int nAfinacoes, String equipa, String modoMotor, String id, int downforce, Piloto piloto) {
        super(cilindrada, pneus, fiabilidade, marca, modelo, potenciaMC, nAfinacoes, equipa, modoMotor, id, downforce, piloto);
    }

    public boolean verificarAfinação(){

    };


    public void setAfinação(float aDownforce, int aMotorMode) {
        throw new UnsupportedOperationException();
    }
}
