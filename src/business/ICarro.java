package business;


public interface ICarro {

    public void setPneus(String aPneu);

    String getPneus();

    void setDownforce(float d);

    float getDownforce();

    void setCilindrada(int c);


    int getCilindrada();

    public void setPiloto(Piloto aP);

    public void setNAfinacoes(int aN);

    public void setModoMotor(int aModo);

    int gettModoMotor();

    public String getEquipa();

    public void setEquipa(String aE);

    public int getPotencia();

    public Piloto getPiloto();

    public int getFiabilidade();


    public String getID();

    public boolean verificarAfinacao();

    void setAfinacao(float aDownforce);
}