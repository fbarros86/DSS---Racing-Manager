import java.util.*;

public class Jogo implements IJogo {

	private Piloto pilotos;
	private Carro carros;
	private Campeonato campeonatos;
	private Circuito circuitos;
	private Utilizador utilizadores;
	private Collection<Utilizador> rankingGlobal;
	private List<String> codigosAdmin;

	@Override
	public List<Utilizador> getRanking() {
		return null;
	}

	@Override
	public Map<Integer, Carro> getCarros() {
		return null;
	}

	@Override
	public Map<Integer, Piloto> getPilotos() {
		return null;
	}

	@Override
	public Map<Integer, Campeonato> getCampeonatos() {
		return null;
	}

	@Override
	public Map<Integer, Circuito> getCircuitos() {
		return null;
	}

	@Override
	public boolean validarCodigo(String codPremium) {
		return false;
	}

	@Override
	public boolean validaUser(String codNome) {
		return false;
	}

	@Override
	public boolean validarPassAutenticar(String codNome, String codPass) {
		return false;
	}

	@Override
	public void adicionarUser(String codNome, String codPass, String tipo) {

	}

	@Override
	public void adicionarPiloto(String nome, int cts, int sva) {

	}

	@Override
	public void adicionarCampeonato(String nome, String categoria, List<Equipa> equipas, List<Corrida> corridas) {

	}

	@Override
	public void adicionarCircuito(String nome, float dist, int nrVoltas, List<Segmento> percurso) {

	}

	@Override
	public void adicionaEquipa(String nome, String codJogador, String codCampeonato, Carro c, Piloto p1, Piloto p2) {

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