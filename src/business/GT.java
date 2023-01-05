package business;


public class GT extends Carro {

    public GT(String marca, String modelo, float cilindrada, int potenciaMC, float downforce) {
        super(marca, modelo, potenciaMC, downforce);
        this.cilindrada = cilindrada;
    }

    public void diminuirFiabilidade() {
    }
}
