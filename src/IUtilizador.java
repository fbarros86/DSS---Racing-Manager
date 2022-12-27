
public interface IUtilizador {

	String getTipo();

	int getClassificacao();

	int getPontuacao();

	void setClassificacao(int classificacaoNova);

	void setPontuacao(int pontuacaoNova);

	/**
	 * 
	 * @param pontos
	 */
	void addPontuacao(int pontos);

}