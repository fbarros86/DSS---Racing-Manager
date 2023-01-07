package business;


public class GT extends Carro {

    private int fiabilidade;

    public GT(String marca, String modelo, int cilindrada, int potenciaMC, float downforce, String categoria) {
        super(marca, modelo, cilindrada, potenciaMC, downforce, categoria);
    }

    public GT(String id, int cilindrada, int fiabilidade, String marca, String modelo, String pneus, int potenciaMC,
               String equipa, int modoMotor, float downforce, int afinacoes, Piloto piloto) {
        super(id, cilindrada, fiabilidade, marca, modelo, pneus, potenciaMC, equipa, modoMotor, downforce, afinacoes, piloto);
    }


    public void diminuirFiabilidade() {
        this.fiabilidade = (int) (fiabilidade-1);
    }
}
