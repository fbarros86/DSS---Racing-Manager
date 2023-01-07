package business;

public class C1 extends Carro {

    public C1(String marca, String modelo, int cilindrada, int potenciaMC,float downforce) {
        super(marca, modelo, cilindrada, potenciaMC, downforce);
    }

    public C1(String id, int cilindrada, int fiabilidade, String marca, String modelo , int potenciaMC,
              float downforce) {
        super(id, cilindrada, fiabilidade, marca, modelo, potenciaMC,downforce);
    }


}