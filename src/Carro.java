import java.util.Objects;

public abstract class Carro implements ICarro{

    //@TODO TA TUDO MALLLLLLLLLLLLL

    private float cilindrada;
    private String pneus;
    private int fiabilidade;
    private String marca;
    private String modelo;
    private int potenciaMC;

    private String equipa;
    private String modoMotor;
    private String id;
    private int downforce;
    private Piloto piloto;

    public Carro(float cilindrada,String pneus,int fiabilidade,String marca,String modelo,int potenciaMC,String equipa,String modoMotor,String id,int downforce,Piloto piloto){
        this.cilindrada = cilindrada;
       this.pneus = pneus;
       this.fiabilidade = fiabilidade;
       this.marca = marca;
       this.modelo = modelo;
       this.potenciaMC = potenciaMC;
       this.equipa = equipa;
       this.modoMotor = modoMotor;
       this.id = id;
       this.downforce = downforce;
       this.piloto = piloto;
    }

    @Override
    public void setPneus(String aPneu) {
        pneus = aPneu;
    }

    @Override
    public void setPiloto(Piloto aP) {
        piloto = aP;
    }

    @Override
    public void setNAfinacoes(int aN) {
        nAfinacoes = aN;
    }

    @Override
    public void setModoMotor(String aModo) {
        modoMotor = aModo;
    }

    @Override
    public String getEquipa() {
        return equipa;
    }

    @Override
    public void setEquipa(String aE) {
        equipa = aE;
    }

    @Override
    public int getPotencia() {
        return potenciaMC;
    }

    @Override
    public Piloto getPiloto() {
        return piloto;
    }

    @Override
    public int getFiabilidade() {
        return fiabilidade;
    }

    @Override
    public void setAfinação(float aDownforce, int aMotorMode) {

    }

    @Override
    public String getID() {
        return null;
    }


}