package business;

import java.util.List;

public interface ICorrida {

	int getVoltasOcorridas();

	Circuito getCircuito();

	void getMeteorologia();

	List<Carro> getClassificacao();

	int getNPilotosInativos();

	/**
	 * 
	 * @param c
	 */
	void addCarro(Carro c);

	void diminuiFiabilidade();

	void calcUltrapassagem();

	void calcAvaria();

	void calcDespiste();

	/**
	 * 
	 * @param idCarro
	 */
	void removeCarro(String idCarro);

	List<Carro> simulaCorrida();

}