import java.util.List;
import java.util.Map;

public interface ICampeonato {

	/**
	 * 
	 * @param nome
	 * @param categoria
	 * @param circuitos
	 */

	Map<String, Equipa> getEquipas();

	List<Corrida> getCorridas();

	int getCorridaAtual();

	List<Equipa> getClassificacao();

	int getNCorridas();

	int getCorridasRestantes();

	/**
	 * 
	 * @param classificacaoCorrida
	 */
	void calculaClassificacao(List<Carro> classificacaoCorrida);

	/**
	 * 
	 * @param idUser
	 * @param nome
	 * @param c
	 * @param p1
	 * @param p2
	 * @param ncorridas
	 */
	void adicionaEquipa(String idUser, String nome, Carro c, Piloto p1, Piloto p2, int ncorridas);

	List<Equipa> simulaCampeonato();

}