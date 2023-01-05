package business;



public class Equipa {

	private Carro carro;
	private Piloto piloto1;
	private Piloto piloto2;
	private String nome;
	private int pontuacao;
	private String idUser;

	/**
	 *  @param idUser
	 * @param nome
	 * @param c
	 * @param p1
	 * @param p2
	 */
	public Equipa(String idUser, String nome, Carro c, Piloto p1, Piloto p2) {
		this.nome = nome;
		this.idUser = idUser;
		this.carro = c;
		this.piloto2 = p1;
		this.piloto2 = p2;
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

	public Carro getCarro() {
		// TODO - implement Equipa.getCarro1
		return this.carro;
	}


}