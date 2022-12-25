import java.util.*;

public class Jogo implements IJogo {

	private Map<String,Piloto> pilotos;
	private Map<Integer, Carro> carros;
	private Map<String, Campeonato> campeonatos;
	private Map<String, Circuito> circuitos;
	private Map<String,Utilizador> utilizadores;
	private List<Utilizador> rankingGlobal;
	private List<String> codigosAdmin;

	@Override
	public List<Utilizador> getRanking() {
		return rankingGlobal;
	}

	@Override
	public Map<Integer, Carro> getCarros() {
		return carros;
	}

	@Override
	public Map<String, Piloto> getPilotos() {
		return pilotos;
	}

	@Override
	public Map<String, Campeonato> getCampeonatos() {
		return campeonatos;
	}

	@Override
	public Map<String, Circuito> getCircuitos() {
		return circuitos;
	}

	@Override
	public boolean validarCodigo(String codPremium) {
		return codigosAdmin.contains(codPremium);
	}

	@Override
	public boolean validaUser(String codNome) {
		return utilizadores.containsKey(codNome);
	}

	@Override
	public boolean validarPassAutenticar(String codNome, String codPass) {
		return utilizadores.get(codNome).validaPass(codPass);
	}

	@Override
	public void adicionarUser(String codNome, String codPass, String tipo) {
			Utilizador user = new Utilizador(codNome,codPass,tipo);
			utilizadores.put(codNome,user);
	}

	@Override
	public void adicionarPiloto(String nome, int cts, int sva) {
			Piloto driver = new Piloto(nome,sva,cts);
			pilotos.put(nome,driver);
	}

	@Override
	public void adicionarCampeonato(String nome, String categoria, Map<String,Equipa> equipas, List<Corrida> corridas) {
			Campeonato camp = new Campeonato(nome,categoria,equipas,corridas);
			campeonatos.put(nome,camp);
	}

	@Override
	public void adicionarCircuito(String nome, float dist, int nrVoltas, List<Segmento> percurso) {
			Circuito circuito = new Circuito(nome, dist, nrVoltas, percurso);
			circuitos.put(nome,circuito);
	}

	@Override
	public void registarCarroC1NaoHibrido(int id, String marca, String modelo, float potenciaMotor, float PAC) {

	}

	@Override
	public void registarCarroC1Hibrido(int id, String marca, String modelo, float potenciaMotor, float potenciaMotorElec, float PAC) {

	}

	@Override
	public void registarCarroC2NaoHibrido(int id, String marca, String modelo, float cilindrada, float potenciaMotor, float PAC) {

	}

	@Override
	public void registarCarroC2Hibrido(int id, String marca, String modelo, float cilindrada, float potenciaMotor, float potenciaMotorElec, float PAC) {

	}

	@Override
	public void registarCarroGTNaoHibrido(int id, String marca, String modelo, float cilindrada, float potenciaMotor) {

	}

	@Override
	public void registarCarroGTHibrido(int id, String marca, String modelo, float cilindrada, float potenciaMotor, float potenciaMotorElec) {

	}

	@Override
	public void registarCarroCS(int id, String marca, String modelo, float potenciaMotor) {

	}

	@Override
	public void calculaClassificacao(List<Equipa> classificacaoCampeonato) {

	}

	@Override
	public List<Equipa> simulaCampeonato(String idCampeonato) {
		return null;
	}
}