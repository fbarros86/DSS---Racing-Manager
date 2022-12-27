public class Carro implements ICarro{

    //@TODO TA TUDO MALLLLLLLLLLLLL

    private float cilindrada;
    private String pneus;
    private int fiabilidade;
    private String marca;
    private String modelo;
    private int potenciaMC;
    private int nAfinacoes;
    private String equipa;
    private String modoMotor;
    private String id;
    private int downforce;
    private Piloto piloto;

    public Carro()
    @Override
    public void setPneus(String aPneu) {
        pneus = aPneu;
    }

    @Override
    public void setPiloto(Piloto aP) {

    }

    @Override
    public void setNAfinacoes(int aN) {

    }

    @Override
    public void setModoMotor(String aModo) {

    }

    @Override
    public String getEquipa() {
        return null;
    }

    @Override
    public void setEquipa(String aE) {

    }

    @Override
    public int getPotencia() {
        return 0;
    }

    @Override
    public Piloto getPiloto() {
        return null;
    }

    @Override
    public int getFiabilidade() {
        return 0;
    }

    @Override
    public boolean verificarAfinação() {
        return false;
    }

    @Override
    public void setAfinação(float aDownforce, int aMotorMode) {

    }

    @Override
    public String getID() {
        return null;
    }
}