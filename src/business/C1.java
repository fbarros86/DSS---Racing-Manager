package business;

public class C1 extends Carro {

    public C1(String marca, String modelo, int potenciaMC,float downforce) {
        super(marca, modelo,potenciaMC, downforce,6000);
    }

    public C1(String id, int cilindrada, int fiabilidade, String marca, String modelo, String pneus, int potenciaMC,
              String equipa, int modoMotor, float downforce, int afinacoes, Piloto piloto) {
        super(id, cilindrada, fiabilidade, marca, modelo, pneus, potenciaMC, equipa, modoMotor, downforce, afinacoes, piloto);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof C1)) return false;
        C1 carro = (C1) o;
        return this.getCilindrada() == carro.getCilindrada() && getPotencia() == carro.getPotencia() && getMarca().equals(carro.getMarca()) && getModelo().equals(carro.getModelo());
    }
}