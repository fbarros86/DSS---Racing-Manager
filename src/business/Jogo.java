package business;
import data.*;


import java.util.*;

public class Jogo implements IJogo {

	private Map<String,Piloto> pilotos;
	private Map<String, Carro> carros;
	private Map<String, Campeonato> campeonatos;
	private Map<String, Circuito> circuitos;
	private Map<String,Utilizador> utilizadores;
	private List<Utilizador> rankingGlobal;
	private List<String> codigosAdmin;

	public Jogo(){
		campeonatos = DAOCampeonato.getInstance();
		circuitos = DAOCircuito.getInstance();
		pilotos = DAOPiloto.getInstance();
		carros = DAOCarro.getInstance();
		utilizadores = DAOUtilizador.getInstance();
		codigosAdmin = new ArrayList<>();
		codigosAdmin.add("codigoSecreto");
		rankingGlobal = new ArrayList<>();
	}
/*
	public Jogo(){
		campeonatos = new HashMap<>();
		circuitos = new HashMap<>();
		pilotos = new HashMap<>();
		carros = new HashMap<>();
		utilizadores = new HashMap<>();
		codigosAdmin = new ArrayList<>();
		rankingGlobal = new ArrayList<>();
	}
	*/

	public Utilizador getUser(String codUser){
		return utilizadores.get(codUser);
	}

	@Override
	public List<Utilizador> getRanking() {
		return rankingGlobal;
	}

	@Override
	public Map<String, Carro> getCarros() {
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

	public Circuito getCircuito(String key) {
		return circuitos.get(key);
	}

	@Override
	public boolean validarCodigo(String codPremium) {
		return codigosAdmin.contains(codPremium);
	}

	@Override
	public boolean validaUser(String codNome, String pass) {
		if(utilizadores.containsKey(codNome)) return utilizadores.get(codNome).validaPass(pass);
		else return false;
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
	public void adicionarPiloto(String nome, float cts, float sva) {
			Piloto driver = new Piloto(nome,sva,cts);
			pilotos.put(nome,driver);
	}

	@Override
	public void adicionarCampeonato(String nome, String categoria, List<Corrida> corridas) {
			Campeonato camp = new Campeonato(nome,categoria,corridas);
			campeonatos.put(nome,camp);
	}

	@Override
	public void adicionarCircuito(String nome, float dist, int nrVoltas, List<Segmento> percurso) {
			Circuito circuito = new Circuito(nome, dist, nrVoltas, percurso);
			circuitos.put(nome,circuito);
	}

	@Override
	public void adicionaEquipa(String nome, String codJogador, String codCampeonato, Carro c, Piloto p1, Piloto p2) {
		Campeonato campeonato = campeonatos.get(codCampeonato);
		Carro c2 = new Carro(c);
		c.setPiloto(p1);
		c2.setPiloto(p2);
		campeonato.adicionaEquipa(new Equipa(codJogador,nome,c,c2));
	}


	@Override
	public void registarCarroC1NaoHibrido(String marca, String modelo, int cilindrada, int potenciaMotor, float PAC) {
		Carro c = new C1(marca,modelo, cilindrada, potenciaMotor,PAC);
		String id = c.getID();
		carros.put(id,c);
	}

	@Override
	public void registarCarroC1Hibrido(String marca, String modelo, int cilindrada, int potenciaMotor, int potenciaMotorElec, float PAC) {
		Carro c = new C1H(marca,modelo, cilindrada, potenciaMotor,potenciaMotorElec,PAC);
		String id = c.getID();
		carros.put(id,c);

	}

	@Override
	public void registarCarroC2NaoHibrido(String marca, String modelo, int cilindrada, int potenciaMotor, float PAC) {
		Carro c = new C2(marca,modelo, cilindrada, potenciaMotor,PAC);
		String id = c.getID();
		carros.put(id,c);
	}


	@Override
	public void registarCarroC2Hibrido(String marca, String modelo, int cilindrada, int potenciaMotor, int potenciaMotorElec, float PAC) {
		Carro c = new C2H(marca,modelo, cilindrada, potenciaMotor, potenciaMotorElec,PAC);
		String id = c.getID();
		carros.put(id,c);
	}

	@Override
	public void registarCarroGTNaoHibrido(String marca, String modelo, int cilindrada, int potenciaMotor, float PAC) {
		Carro c = new GT(marca,modelo, cilindrada, potenciaMotor,PAC);
		String id = c.getID();
		carros.put(id,c);
	}



	@Override
	public void registarCarroGTHibrido(String marca, String modelo, int cilindrada, int potenciaMotor, int potenciaMotorElec, float PAC) {
		Carro c = new GTH(marca,modelo, cilindrada, potenciaMotor, potenciaMotorElec,PAC);
		String id = c.getID();
		carros.put(id,c);
	}

	@Override
	public void registarCarroSC(String marca, String modelo, int cilindrada, int potenciaMotor, float PAC) {
		Carro c = new SC(marca,modelo, cilindrada, potenciaMotor,PAC);
		String id = c.getID();
		carros.put(id,c);
	}

	// SO atribui as 3 primeitas equipas
	@Override
	public void calculaClassificacao(List<Equipa> classificacaoCampeonato) {
		for ( int i=0; i < 3;i++){
			Equipa e = classificacaoCampeonato.get(i);
			String idUser = e.getidUser();
			Utilizador u = utilizadores.get(idUser);
			if(i==0) u.addPontuacao(5);
			else if (i==1) u.addPontuacao(3);
			else u.addPontuacao(1);
		}
	}


	@Override
	public List<Equipa> simulaJogo(String idCampeonato) {
		Campeonato c = campeonatos.get(idCampeonato);
		return c.simulaCampeonato();
	}

	public boolean existeCircuito(String nome){
		return circuitos.containsKey(nome);
	}

	public String printNomeCircuitos(){
		StringBuilder sb = new StringBuilder();
		for(Circuito circuito: this.circuitos.values()){
			sb.append(circuito.getNome()).append("\n");
		}
		return sb.toString();
	}

	public String printNomeCircuitos(List<Circuito> circuitos){
		StringBuilder sb = new StringBuilder();
		for(Circuito circuito: circuitos){
			sb.append(circuito.getNome()).append("\n");
		}
		return sb.toString();
	}

	public Piloto getPiloto(String nome){
		return this.pilotos.get(nome);
	}
	public boolean existePiloto(String nome){
		return this.pilotos.containsKey(nome);
	}

	public boolean existeCampeonato(String nome){
		return this.campeonatos.containsKey(nome);
	}

	public boolean existeUser(String nome){
		return this.utilizadores.containsKey(nome);
	}

	public String printNomePilotos(){
		StringBuilder sb = new StringBuilder();
		for(Piloto piloto: this.pilotos.values()){
			sb.append(piloto.getNome()).append("\n");
		}
		return sb.toString();
	}

	public String printNomeCampeonato(){
		StringBuilder sb = new StringBuilder();
		campeonatos.keySet().stream().map(nome -> sb.append(nome));
		return sb.toString();
	}

	public boolean codigoValido(String cod){
		return codigosAdmin.contains(cod);
	}

	public boolean existeCarro(String idCarro){
		return carros.containsKey(idCarro);
	}

	public String printCarros(){
		StringBuilder sb = new StringBuilder();
		carros.values().stream().map(car -> sb.append(car.toOptionalString()));
		return sb.toString();
	}
}