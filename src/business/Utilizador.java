
package business;


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

    public boolean validaPass(String codPass){
        return this.codPass.equals(codPass);
    }

    @Override
    public String getTipo() {
        return this.tipo;
    }

    @Override
    public int getClassificacao() {
        return this.classificacao;
    }

    @Override
    public int getPontuacao() {
        return this.pontuacao;
    }

    @Override
    public void setClassificacao(int classificacaoNova) {
        this.classificacao = classificacaoNova;
    }

    @Override
    public void setPontuacao(int pontuacaoNova) {
           this.pontuacao = pontuacaoNova;
    }

    @Override
    public void addPontuacao(int pontos) {
           this.pontuacao += pontos;
    }
}