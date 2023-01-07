package business;


import java.util.List;
import java.util.Map;

public interface ICampeonato {

	Map<String, Equipa> getEquipas();

	List<Corrida> getCorridas();

	int getCorridaAtual();

	List<Equipa> getClassificacao();

	//int getNCorridas();

	int getCorridasRestantes();

	/**
	 * 
	 * @param classificacaoCorrida
	 */
	void calculaClassificacao(List<Carro> classificacaoCorrida);

	/**
	 * @param equipa
	 */
    void adicionaEquipa(Equipa equipa);

    List<Equipa> simulaCampeonato();
}