import Jogo.SubCampeonato.SubCorrida.*;

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
	void addCarro(Jogo.SubCampeonato.SubCorrida.Carro.Carro c);

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

	Circuito getCircuito();

	List<Carro> getClassificacao();

}