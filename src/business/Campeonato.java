package business;

import ui.TextUI;

import java.util.*;


public class Campeonato implements ICampeonato {
    private String nome;
    private int corridaAtual;
    private String categoria;
    Map<String, Equipa> equipas;
    List<Corrida> corridas;

    public Campeonato(String nome, String categoria, List<Corrida> corridas) {
        this.nome = nome;
        this.categoria = categoria;
        this.corridaAtual = 0;
        this.equipas = new HashMap<>();
        this.corridas = corridas;
    }

    public Campeonato(String nome, String categoria, Map<String, Equipa> equipas, List<Corrida> corridas) {
        this.nome = nome;
        this.categoria = categoria;
        this.corridaAtual = 0;
        this.equipas = equipas;
        this.corridas = corridas;
    }

    public Campeonato(String nome, int corridaAtual, String categoria, Map<String, Equipa> equipas, List<Corrida> corridas) {
        this.nome = nome;
        this.categoria = categoria;
        this.corridaAtual = corridaAtual;
        this.equipas = equipas;
        this.corridas = corridas;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public Map<String, Equipa> getEquipas() {
        return this.equipas;
    }

    @Override
    public List<Corrida> getCorridas() {
        return this.corridas;
    }

    @Override
    public int getCorridaAtual() {
        return this.corridaAtual;
    }

    public void setCarrosCorridas(List<Carro> carros){
        for (Corrida corrida : corridas) corrida.addCarros(carros);
    }

    // ns oq fazer com isto
    @Override
    public List<Equipa> getClassificacao() {;

        ArrayList<Equipa> liste = new ArrayList<Equipa>(equipas.values());
        sortTeams((liste));
        return liste;
    }

    public void sortTeams(List<Equipa> teams) {
        // Cria um comparador personalizado que ordena as equipes em ordem decrescente pelo número de pontos
        Comparator<Equipa> comparator = new Comparator<Equipa>() {
            @Override
            public int compare(Equipa t1, Equipa t2) {
                return t2.getpontuacao() - t1.getpontuacao();
            }
        };

        // Ordena a lista usando o comparador personalizado
        Collections.sort(teams, comparator);
    }

   // @Override

    public int getNCorridas() {
        return corridas.size();
    }

    @Override
    public int getCorridasRestantes() {
        return (getNCorridas() - corridaAtual);
    }

    @Override
    public void calculaClassificacao(List<Carro> classificacaoCorrida) {
        int j = 18;
        for (int i = 1; i < classificacaoCorrida.size(); i++) {
            for (Carro c : classificacaoCorrida) {
                String s = c.getEquipa();
                Equipa e = equipas.get(s);
                if (i == 1) e.addpontuacao(25);
                if (i == 2) e.addpontuacao(18);
                if (i == 3) e.addpontuacao(15);
                if (i >= 4 && i < 10) e.addpontuacao(j);
                else e.addpontuacao(1);

                j-=2;

            }
        }

    }


    // idUSER??
    @Override
    public void adicionaEquipa(Equipa equipa) {
        equipas.put(equipa.getNome(), equipa);

    }


    // Parte do utilizador escolher
    @Override
    public List<Equipa> simulaCampeonato() {
        TextUI ui = new TextUI();
        for (Corrida c: this.corridas){
            for(Equipa e : this.equipas.values()){
                Carro c1 = e.getCarro1();
                Carro c2 = e.getCarro2();
                if (c1.verificarAfinacao()){
                    int downforceC1 = ui.trataAfinacoes();
                    if(downforceC1 != -1) c1.setAfinacao(downforceC1);
                }
                if (c2.verificarAfinacao()){
                    int downforceC2 = ui.trataAfinacoes();
                    if(downforceC2 != -1) c2.setAfinacao(downforceC2);
                }
            }
            List<Carro> classificacaoFinal = c.simulaCorrida();
            int i = 1, pont = 0;
            for(Carro car: classificacaoFinal){
                pont = switch (i) {
                    case 1 -> 25;
                    case 2 -> 18;
                    case 3 -> 15;
                    default -> 12 - (i - 4);
                };
                ;
                this.equipas.get(car.getEquipa()).addpontuacao(pont);
                i++;
            }
        }

        List<Equipa> ret = (List<Equipa>) this.equipas.values();
        sortTeams(ret);
        return ret;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCorridaAtual(int corridaAtual) {
        this.corridaAtual = corridaAtual;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setEquipas(Map<String, Equipa> equipas) {
        this.equipas = equipas;
    }

    public void setCorridas(List<Corrida> corridas) {
        this.corridas = corridas;
    }
}