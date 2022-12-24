public class Utilizador implements IUtilizador{

    private String codNome;
    private String codPass;
    private String tipo;
    private int classificacao;
    private int pontuacao;

    public Utilizador(String codNome, String codPass, String tipo) {
        this.codNome = codNome;
        this.codPass = codPass;
        this.tipo = tipo;
        pontuacao = 0;
    }

    @Override
    public String getTipo() {
        return tipo;
    }

    @Override
    public int getClassificacao() {
        return classificacao;
    }

    @Override
    public int getPontuacao() {
        return pontuacao;
    }

    @Override
    public void setClassificacao(int classificacaoNova) {
        classificacao = classificacaoNova;
    }

    @Override
    public void setPontuacao(int pontuacaoNova) {
           pontuacao = pontuacaoNova;
    }

    @Override
    public void addPontuacao(int pontos) {
           pontuacao += pontos;
    }
}