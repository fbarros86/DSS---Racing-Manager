public interface ICarro {

    public void setPneus(String aPneu);

    public void setPiloto(Piloto aP);

    public void setNAfinacoes(int aN);

    public void setModoMotor(String aModo);

    public String getEquipa();

    public void setEquipa(String aE);

    public int getPotencia();

    public Piloto getPiloto();

    public int getFiabilidade();

    public boolean verificarAfinação();

    public void setAfinação(float aDownforce, int aMotorMode);

    public String getID();
}