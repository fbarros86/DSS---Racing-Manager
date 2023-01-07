package business;


public class Segmento {

	//final int POSSIVEL = 1;
	//final int DIFICL = 0.5;
	//final int IMPOSSIVEL = 0;
	private String tipo;
	private float GDU;

	public Segmento(String tipo, float GDU){
		this.tipo = tipo;
		this.GDU = GDU;
	}

	/**
	 * 
	 * @param GDU
	 */
	public void setGDU(float GDU) {
		// TODO - implement Segmento.setGDU
		this.GDU=GDU;
	}

	/**
	 * 
	 * @param tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public float getGDU() {
		// TODO - implement Segmento.getGDU
		return this.GDU;
	}

	public String getTipo() {
		return this.tipo;
	}

	@Override
	public String toString() {
		return "Segmento{" +
				"tipo='" + tipo + '\'' +
				", GDU=" + GDU +
				'}';
	}
}