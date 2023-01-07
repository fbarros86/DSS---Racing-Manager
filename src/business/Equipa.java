package business;



public class Equipa {

	private Carro carro1;
	private Carro carro2;
	private String nome;
	private int pontuacao;
	private String idUser;

	public Equipa(String idUser, String nome, Carro c1, Carro c2 ) {
		this.nome = nome;
		this.idUser = idUser;
		this.carro1 = c1;
		this.carro2 = c2;
		this.pontuacao = 0;
	}

	public String getidUser() {
		// TODO - implement Equipa.addpontuacao
		return this.idUser;
	}

	public void setIdUser(String id) {
		// TODO - implement Equipa.addpontuacao
		this.idUser=id;
	}
	public int getpontuacao() {
		// TODO - implement Equipa.addpontuacao
		return this.pontuacao;
	}

	public void addpontuacao(int pontos) {
		// TODO - implement Equipa.addpontuacao
		this.pontuacao+=pontos;
	}

	public  Carro getCarro2(){
		return  this.carro2;
	}

	public Carro getCarro1() {
		// TODO - implement Equipa.getCarro1
		return this.carro1;
	}


}