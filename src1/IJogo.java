import Jogo.*;
import Jogo.SubCampeonato.*;

public interface IJogo {

	List<Utilizador> getRanking();

	Map<Integer, Carro> getCarros();

	Map<Integer, Piloto> getPilotos();

	Map<Integer, Jogo.Campeonato> getCampeonatos();

	Map<Integer, Jogo.Circuito> getCircuitos();

	/**
	 * 
	 * @param codPremium
	 */
	boolean validarCodigo(String codPremium);

	/**
	 * 
	 * @param codNome
	 */
	boolean validaUser(String codNome);

	/**
	 * 
	 * @param codNome
	 * @param codPass
	 */
	boolean validarPassAutenticar(String codNome, String codPass);

	/**
	 * 
	 * @param codNome
	 * @param codPass
	 * @param tipo
	 */
	void adicionarUser(String codNome, String codPass, String tipo);

	/**
	 * 
	 * @param nome
	 * @param cts
	 * @param sva
	 */
	void adicionarPiloto(String nome, int cts, int sva);

	/**
	 * 
	 * @param nome
	 * @param categoria
	 * @param equipas
	 * @param corridas
	 */
	void adicionarCampeonato(String nome, String categoria, List<Jogo.SubCampeonato.Equipa> equipas, List<Jogo.SubCampeonato.Corrida> corridas);

	/**
	 * 
	 * @param nome
	 * @param dist
	 * @param nrVoltas
	 * @param percurso
	 */
	void adicionarCircuito(String nome, float dist, int nrVoltas, List<Jogo.SubCampeonato.SubCorrida.SubCircuito.Segmento> percurso);

	/**
	 * 
	 * @param nome
	 * @param codJogador
	 * @param codCampeonato
	 * @param c
	 * @param p1
	 * @param p2
	 */
	void adicionaEquipa(String nome, String codJogador, String codCampeonato, Carro c, Piloto p1, Piloto p2);

	/**
	 * 
	 * @param marca
	 * @param modelo
	 * @param potenciaMotor
	 * @param PAC
	 * @param id
	 */
	void registarCarroC1NaoHibrido(String marca, String modelo, float potenciaMotor, float PAC, int id);

	/**
	 * 
	 * @param marca
	 * @param modelo
	 * @param potenciaMotor
	 * @param potenciaMotorElec
	 * @param PAC
	 * @param id
	 */
	void registarCarroC1Hibrido(String marca, String modelo, float potenciaMotor, float potenciaMotorElec, float PAC, int id);

	/**
	 * 
	 * @param marca
	 * @param modelo
	 * @param cilindrada
	 * @param potenciaMotor
	 * @param PAC
	 * @param id
	 */
	void registarCarroC2NaoHibrido(String marca, String modelo, float cilindrada, float potenciaMotor, float PAC, int id);

	/**
	 * 
	 * @param marca
	 * @param modelo
	 * @param cilindrada
	 * @param potenciaMotor
	 * @param potenciaMotorElec
	 * @param PAC
	 * @param id
	 */
	void registarCarroC2Hibrido(String marca, String modelo, float cilindrada, float potenciaMotor, float potenciaMotorElec, float PAC, int id);

	/**
	 * 
	 * @param marca
	 * @param modelo
	 * @param cilindrada
	 * @param potenciaMotor
	 * @param id
	 */
	void registarCarroGTNaoHibrido(String marca, String modelo, float cilindrada, float potenciaMotor, int id);

	/**
	 * 
	 * @param marca
	 * @param modelo
	 * @param cilindrada
	 * @param potenciaMotor
	 * @param potenciaMotorElec
	 * @param id
	 */
	void registarCarroGTHibrido(String marca, String modelo, float cilindrada, float potenciaMotor, float potenciaMotorElec, int id);

	/**
	 * 
	 * @param marca
	 * @param modelo
	 * @param potenciaMotor
	 * @param id
	 */
	void registarCarroCS(String marca, String modelo, float potenciaMotor, int id);

	/**
	 * 
	 * @param classificacaoCampeonato
	 */
	void calculaClassificacao(List<Equipa> classificacaoCampeonato);

	/**
	 * 
	 * @param idCampeonato
	 */
	List<Equipa> simulaCampeonato(string idCampeonato);

}