public class C2 extends Carro {
    private int nAfinacoes;

    public abstract boolean verificarAfinação();

    public void setAfinação(float aDownforce, int aMotorMode) {
        throw new UnsupportedOperationException();
    }
}