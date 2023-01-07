package business;


public class SC extends Carro {



    public SC(String marca, String modelo, int cilindrada, int potenciaMC,float downforce, String categoria) {
        super(marca, modelo, cilindrada, potenciaMC, downforce, 100,categoria);
    }

    public SC(String id, int cilindrada, int fiabilidade, String marca, String modelo , int potenciaMC,
              float downforce, String categoria) {
        super(id, cilindrada, fiabilidade, marca, modelo, potenciaMC,downforce,categoria);
    }


    public SC(String id, int cilindrada, int fiabilidade, String marca, String modelo, String pneus, int potenciaMC,
              String equipa, int modoMotor, float downforce, int afinacoes, Piloto piloto) {
        super(id, cilindrada, fiabilidade, marca, modelo, pneus, potenciaMC, equipa, modoMotor, downforce, afinacoes, piloto);
    }



}
