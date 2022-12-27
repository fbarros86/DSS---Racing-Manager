import java.util.List;
import java.util.Map;

public interface IJogo {

	List<Utilizador> getRanking();

	Map<Integer, Carro> getCarros();

	Map<String, Piloto> getPilotos();

	Map<String, Campeonato> getCampeonatos();

	Map<String, Circuito> getCircuitos();

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
	 * @param id
	 * @param marca
	 * @param modelo
	 * @param potenciaMotor
	 * @param PAC
	 */
	void registarCarroC1NaoHibrido(int id, String marca, String modelo, float potenciaMotor, float PAC);

	/**
	 * 
	 * @param id
	 * @param marca
	 * @param modelo
	 * @param potenciaMotor
	 * @param potenciaMotorElec
	 * @param PAC
	 */
	void registarCarroC1Hibrido(int id, String marca, String modelo, float potenciaMotor, float potenciaMotorElec, float PAC);

	/**
	 * 
	 * @param id
	 * @param marca
	 * @param modelo
	 * @param cilindrada
	 * @param potenciaMotor
	 * @param PAC
	 */
	void registarCarroC2NaoHibrido(int id, String marca, String modelo, float cilindrada, float potenciaMotor, float PAC);

	/**
	 * 
	 * @param id
	 * @param marca
	 * @param modelo
	 * @param cilindrada
	 * @param potenciaMotor
	 * @param potenciaMotorElec
	 * @param PAC
	 */
	void registarCarroC2Hibrido(int id, String marca, String modelo, float cilindrada, float potenciaMotor, float potenciaMotorElec, float PAC);

	/**
	 * 
	 * @param id
	 * @param marca
	 * @param modelo
	 * @param cilindrada
	 * @param potenciaMotor
	 */
	void registarCarroGTNaoHibrido(int id, String marca, String modelo, float cilindrada, float potenciaMotor);

	/**
	 * 
	 * @param id
	 * @param marca
	 * @param modelo
	 * @param cilindrada
	 * @param potenciaMotor
	 * @param potenciaMotorElec
	 */
	void registarCarroGTHibrido(int id, String marca, String modelo, float cilindrada, float potenciaMotor, float potenciaMotorElec);

	/**
	 * 
	 * @param id
	 * @param marca
	 * @param modelo
	 * @param potenciaMotor
	 */
	void registarCarroCS(int id, String marca, String modelo, float potenciaMotor);

	/**
	 * 
	 * @param classificacaoCampeonato
	 */
	void calculaClassificacao(List<Equipa> classificacaoCampeonato);

	/**
	 * 
	 * @param idCampeonato
	 */
	List<Equipa> simulaCampeonato(String idCampeonato);

}