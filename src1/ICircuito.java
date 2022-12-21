public interface ICircuito {

	/**
	 * 
	 * @param nrCurvas
	 * @param nrRetas
	 * @param nrChicanes
	 */
	void setPercurso(int nrCurvas, int nrRetas, int nrChicanes);

	/**
	 * 
	 * @param dist
	 */
	void setDistancia(int dist);

	/**
	 * 
	 * @param nvoltas
	 */
	void setVoltas(int nvoltas);

	/**
	 * 
	 * @param key
	 * @param GDU
	 */
	void atribuirGDU(int key, int GDU);

	/**
	 * 
	 * @param nrCurvas
	 */
	int calculaRetas(int nrCurvas);

	float calculaDistanciaMin();

}