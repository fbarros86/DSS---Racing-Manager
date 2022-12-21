import Jogo.SubUtilizador.*;

public interface IUtilizador {

	/**
	 * 
	 * @param codNome
	 * @param codPass
	 * @param tipo
	 */
	Utilizador Utilizador(String codNome, String codPass, String tipo);

	String getCodPass();

	String getTipo();

	void getClassificacao();

	void getPontuacao();

	void setClassificacao();

	void setPontuacao();

	/**
	 * 
	 * @param pontos
	 */
	void addPontuacao(int pontos);

}