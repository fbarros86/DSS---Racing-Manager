package business;


import java.util.List;
import java.util.Map;

public interface IJogo {

	List<Utilizador> getRanking();

	Map<String, Carro> getCarros();

	Map<String, Piloto> getPilotos();

	Map<String, Campeonato> getCampeonatos();

	Map<String, Circuito> getCircuitos();

	boolean existeCircuito(String nome);

	Circuito getCircuito(String key);

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
	void adicionarCampeonato(String nome, String categoria, Map<String,Equipa> equipas, List<Corrida> corridas);

	/**
	 * 
	 * @param nome
	 * @param dist
	 * @param nrVoltas
	 * @param percurso
	 */
	void adicionarCircuito(String nome, float dist, int nrVoltas, List<Segmento> percurso);

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
	 */

	void registarCarroC1NaoHibrido(String marca, String modelo, int potenciaMotor, float PAC);

	/**
	 *
	 * @param marca
	 * @param modelo
	 * @param potenciaMotor
	 * @param potenciaMotorElec
	 * @param PAC
	 */

	void registarCarroC1Hibrido(String marca, String modelo, int potenciaMotor, int potenciaMotorElec, float PAC);

	/**
	 * 

	 * @param marca
	 * @param modelo
	 * @param cilindrada
	 * @param potenciaMotor
	 * @param PAC
	 */

	void registarCarroC2NaoHibrido(String marca, String modelo, float cilindrada, int potenciaMotor, float PAC);

	/**
	 *

	 * @param marca
	 * @param modelo
	 * @param cilindrada
	 * @param potenciaMotor
	 * @param PAC
	 */

	void registarCarroC2Hibrido(String marca, String modelo, float cilindrada, int potenciaMotor, int potenciaMotorElec, float PAC);

	/**
	 *
	 * @param marca
	 * @param modelo
	 * @param cilindrada
	 * @param potenciaMotor
	 */

	void registarCarroGTNaoHibrido(String marca, String modelo, float cilindrada, int potenciaMotor, float PAC);

	/**
	 *
	 * @param marca
	 * @param modelo
	 * @param cilindrada
	 * @param potenciaMotor
	 * @param potenciaMotorElec
	 */

	void registarCarroGTHibrido(String marca, String modelo, float cilindrada, int potenciaMotor, int potenciaMotorElec, float PAC);

	/**
	 *
	 * @param marca
	 * @param modelo
	 * @param potenciaMotor
	 * @param PAC
	 */

	void registarCarroSC(String marca, String modelo, int potenciaMotor, float PAC);

	/**
	 * 
	 * @param classificacaoCampeonato
	 */
	void calculaClassificacao(List<Equipa> classificacaoCampeonato);

	/**
	 * 
	 * @param idCampeonato
	 */

	List<Equipa> simulaJogo(String idCampeonato);
}