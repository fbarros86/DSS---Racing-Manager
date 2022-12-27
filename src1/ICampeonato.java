import Jogo.SubCampeonato.*;
import Jogo.*;
import java.util.*;

public interface ICampeonato {

	/**
	 * 
	 * @param nome
	 * @param categoria
	 * @param circuitos
	 */
	void Campeonato(String nome, String categoria, List<Circuitos> circuitos);

	Map<String, Equipa> getEquipas();

	List<Circuito> getCircuitos();

	List<Corrida> getCorridas();

	int getCorridaAtual();

	List<Equipa> getClassificacao();

	int getNCorridas();

	int getCorridasRestantes();

	/**
	 * 
	 * @param classificacaoCorrida
	 */
	void calculaClassificacao(List<Carro> classificacaoCorrida, List<Carro> classificacaoCorrida);

	List<Equipa> simulaCampeonato();

	Map<String, Equipa> getEquipas();

	List<Circuito> getCircuitos();

	List<Corrida> getCorridas();

	List<Equipa> getClassificacao();

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

	Collection<Circuito> getCircuitos();

	Collection<Corrida> getCorridas();

	Collection<Equipa> getClassificacao();

}