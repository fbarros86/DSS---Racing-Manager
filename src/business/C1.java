package business;

public class C1 extends Carro {

    public C1(String marca, String modelo, int cilindrada, int potenciaMC,float downforce, String categoria) {
        super(marca, modelo, cilindrada, potenciaMC, downforce, 95, categoria);
    }

    public C1(String id, int cilindrada, int fiabilidade, String marca, String modelo , int potenciaMC,
              float downforce,String categoria) {
        super(id, cilindrada, fiabilidade, marca, modelo, potenciaMC,downforce,categoria);
    }


}