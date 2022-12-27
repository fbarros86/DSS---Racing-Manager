import java.util.List;

public class Circuito {

    private String  nome;
    private float dist;
    private int nrVoltas;
    private List<Segmento> percurso;

    public Circuito(String  nome, float dist, int nrVoltas, List<Segmento> percurso){
        this.nome = nome;
        this.dist = dist;
        this.nrVoltas = nrVoltas;
        this.percurso = percurso;
    }
}