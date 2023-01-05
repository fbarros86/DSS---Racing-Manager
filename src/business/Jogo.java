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
		codigosAdmin = new ArrayList<>(); //ver melhor isto
		rankingGlobal = new ArrayList<>(); //ver melhor isto
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
	public void adicionaEquipa(String nome, String codJogador, String codCampeonato, Carro c, Piloto p1, Piloto p2) {
		Campeonato campeonato = campeonatos.get(codCampeonato);
		int ncorridas = campeonato.getNCorridas();
		campeonato.adicionaEquipa(nome,codJogador,c,p1,p2,ncorridas);
	}


	@Override
	public void registarCarroC1NaoHibrido(String marca, String modelo, int potenciaMotor, float PAC) {
		Carro c = new C1(marca,modelo, potenciaMotor,PAC);
		String id = c.getID();
		carros.put(id,c);
	}

	@Override
	public void registarCarroC1Hibrido(String marca, String modelo, int potenciaMotor, int potenciaMotorElec, float PAC) {
		Carro c = new C1H(marca,modelo, potenciaMotor,potenciaMotorElec,PAC);
		String id = c.getID();
		carros.put(id,c);

	}

	@Override
	public void registarCarroC2NaoHibrido(String marca, String modelo, float cilindrada, int potenciaMotor, float PAC) {
		Carro c = new C2(marca,modelo, cilindrada, potenciaMotor,PAC);
		String id = c.getID();
		carros.put(id,c);
	}

	@Override
	public void registarCarroC2Hibrido(String marca, String modelo, float cilindrada, int potenciaMotor, int potenciaMotorElec, float PAC) {
		Carro c = new C2H(marca,modelo, cilindrada, potenciaMotor, potenciaMotorElec,PAC);
		String id = c.getID();
		carros.put(id,c);
	}

	@Override
	public void registarCarroGTNaoHibrido(String marca, String modelo, float cilindrada, int potenciaMotor, float PAC) {
		Carro c = new GT(marca,modelo, cilindrada, potenciaMotor,PAC);
		String id = c.getID();
		carros.put(id,c);
	}

	@Override
	public void registarCarroGTHibrido(String marca, String modelo, float cilindrada, int potenciaMotor, int potenciaMotorElec, float PAC) {
		Carro c = new GTH(marca,modelo, cilindrada, potenciaMotor, potenciaMotorElec,PAC);
		String id = c.getID();
		carros.put(id,c);
	}

	@Override
	public void registarCarroSC(String marca, String modelo, int potenciaMotor, float PAC) {
		Carro c = new SC(marca,modelo, potenciaMotor,PAC);
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
		return c.simulaCampeonato(idCampeonato);
	}
}