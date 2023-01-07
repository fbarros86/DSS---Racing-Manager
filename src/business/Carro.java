package business;
import java.util.UUID;


public class  Carro implements ICarro{

    final int AGRESIVO = -1;
    final int NORMAL = 0;
    final int CONSERVADOR = 1;

    //@TODO TA TUDO MALLLLLLLLLLLLL

    float cilindrada;
    private String pneus;
    private int fiabilidade;
    private String marca;
    private String modelo;
    private int potenciaMC;
    private String equipa;
    private int modoMotor;
    private String id;
    float downforce;
    private Piloto piloto;
    private int nAfinacoes;

    public Carro(String marca, String modelo, int potenciaMC,float downforce){

        this.id = UUID.randomUUID().toString();
        this.marca = marca;
        this.modelo = modelo;
        this.potenciaMC = potenciaMC;
        this.downforce = downforce;

    }

    public String getId() {
        return id;
    }

    @Override
    public void setPneus(String aPneu) {
        pneus = aPneu;
    }

    @Override
    public String getPneus() {
        return pneus;
    }

    @Override
    public void setDownforce(float d) {
        downforce = d;
    }

    @Override
    public float getDownforce() {
        return downforce;
    }

    @Override
    public void setCilindrada(float c) {
        cilindrada=c;
    }

    @Override
    public float getCilindrada() {
        return cilindrada;
    }

    @Override
    public void setPiloto(Piloto aP) {
        piloto = aP;
    }

    @Override
    public void setNAfinacoes(int aN) {
        nAfinacoes=aN;
    }


    @Override
    public void setModoMotor(int aModo) {
        modoMotor = aModo;
    }

    @Override
    public int gettModoMotor() {
        return modoMotor;
    }


    @Override
    public String getEquipa() {
        return equipa;
    }

    @Override
    public void setEquipa(String e) {
        equipa = e;
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
    public String getID() {
        return id;
    }

    @Override
    public boolean verificarAfinação(){
        if ( this instanceof C1 || this instanceof C2) return nAfinacoes > 0;
        else return false;
    }

    @Override
    public void setAfinação(float aDownforce) {
        nAfinacoes--;
        this.downforce = aDownforce;
    }

    public void setFiabilidade(int fiabilidade) {
        this.fiabilidade = fiabilidade;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getPotenciaMC() {
        return potenciaMC;
    }

    public void setPotenciaMC(int potenciaMC) {
        this.potenciaMC = potenciaMC;
    }

    public int getModoMotor() {
        return modoMotor;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getnAfinacoes() {
        return nAfinacoes;
    }

    public void setnAfinacoes(int nAfinacoes) {
        this.nAfinacoes = nAfinacoes;
    }
}