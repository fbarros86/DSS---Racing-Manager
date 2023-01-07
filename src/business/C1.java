package business;

public class C1 extends Carro {

    public C1(String marca, String modelo, int cilindrada, int potenciaMC,float downforce,String tipo) {
        super(marca, modelo, cilindrada, potenciaMC, downforce,95,tipo);
    }

    public C1(String id, int cilindrada, int fiabilidade, String marca, String modelo, String pneus, int potenciaMC,
              String equipa, int modoMotor, float downforce, int afinacoes, Piloto piloto) {
        super(id, cilindrada, fiabilidade, marca, modelo, pneus, potenciaMC, equipa, modoMotor, downforce, afinacoes, piloto);
    }


}