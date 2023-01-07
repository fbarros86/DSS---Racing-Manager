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

	Utilizador getUser(String codUser);

	/**
	 * 
	 * @param codNome
	 */
	boolean validaUser(String codNome, String pass);

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
	 *  @param nome
	 * @param cts
	 * @param sva
	 */
	void adicionarPiloto(String nome, float cts, float sva);

	/**
	 * 
	 * @param nome
	 * @param categoria
	 * @param corridas
	 */
	void adicionarCampeonato(String nome, String categoria, List<Corrida> corridas);

	public boolean existePiloto(String nome);

	public Piloto getPiloto(String nome);

	public String printNomePilotos();

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
	public void adicionaEquipa(String nome, String codJogador, String codCampeonato, Carro c, Piloto p1, Piloto p2);

	/**
	 *
	 * @param marca
	 * @param modelo
	 * @param potenciaMotor
	 * @param PAC
	 */

	void registarCarroC1NaoHibrido(String marca, String modelo, int cilindrada, int potenciaMotor, float PAC, String categoria);

	/**
	 *
	 * @param marca
	 * @param modelo
	 * @param potenciaMotor
	 * @param potenciaMotorElec
	 * @param PAC
	 */

	void registarCarroC1Hibrido(String marca, String modelo, int cilindrada, int potenciaMotor, int potenciaMotorElec, float PAC, String categoria);

	/**
	 * 

	 * @param marca
	 * @param modelo
	 * @param cilindrada
	 * @param potenciaMotor
	 * @param PAC
	 */

	void registarCarroC2NaoHibrido(String marca, String modelo, int cilindrada, int potenciaMotor, float PAC, String categoria);

	/**
	 *

	 * @param marca
	 * @param modelo
	 * @param cilindrada
	 * @param potenciaMotor
	 * @param PAC
	 */

	void registarCarroC2Hibrido(String marca, String modelo, int cilindrada, int potenciaMotor, int potenciaMotorElec, float PAC, String categoria);


	void registarCarroGTNaoHibrido(String marca, String modelo, int cilindrada, int potenciaMotor, float PAC, String categoria);

	/**
	 *
	 * @param marca
	 * @param modelo
	 * @param cilindrada
	 * @param potenciaMotor
	 * @param potenciaMotorElec
	 */


    void registarCarroGTHibrido(String marca, String modelo, int cilindrada, int potenciaMotor, int potenciaMotorElec, float PAC, String categoria);

    /**
	 *
	 * @param marca
	 * @param modelo
	 * @param potenciaMotor
	 * @param PAC
	 */

	void registarCarroSC(String marca, String modelo, int cilindrada, int potenciaMotor, float PAC, String categoria);

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

	public String printNomeCircuitos();

	public boolean existeCampeonato(String nome);

	public boolean existeUser(String nome);

	public String printNomeCircuitos(List<Circuito> circuitos);

	public boolean codigoValido(String cod);

	public String printNomeCampeonato();

	public boolean existeCarro(String idCarro, String categoria);

	public String printCarros(String categoria);

}