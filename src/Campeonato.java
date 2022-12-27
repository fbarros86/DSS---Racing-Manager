import java.util.List;
import java.util.Map;

public class Campeonato implements ICampeonato{
    private String nome;
    private int corridaAtual;
    private String categoria;
    Map<String, Equipa> equipas;
    List<Corrida> corridas;

    public Campeonato(String nome, String categoria, Map<String, Equipa> equipas ,List<Corrida> corridas) {
        this.nome = nome;
        this.categoria = categoria;
        this.corridaAtual = 0;
        this.equipas = equipas;
        this.corridas = corridas;
    }

    @Override
    public Map<String, Equipa> getEquipas() {
        return equipas;
    }

    @Override
    public List<Corrida> getCorridas() {
        return corridas;
    }

    @Override
    public int getCorridaAtual() {
        return corridaAtual;
    }

    // ns oq fazer com isto
    @Override
    public List<Equipa> getClassificacao() {
        return null;
    }

    @Override
    public int getNCorridas() {
        return 0;
    }

    @Override
    public int getCorridasRestantes() {
        return 0;
    }

    @Override
    public void calculaClassificacao(List<Carro> classificacaoCorrida) {

    }

    @Override
    public void adicionaEquipa(String idUser, String nome, Carro c, Piloto p1, Piloto p2, int ncorridas) {
            Equipa equipaNova = new Equipa(idUser,nome,c,p1,p2);
            equipas.put(nome, equipaNova);

    }

    @Override
    public List<Equipa> simulaCampeonato() {
        return null;
    }
}