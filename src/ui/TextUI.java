/*
 *  DISCLAIMER: Este código foi criado para discussão e edição durante as aulas práticas de DSS, representando
 *  uma solução em construção. Como tal, não deverá ser visto como uma solução canónica, ou mesmo acabada.
 *  É disponibilizado para auxiliar o processo de estudo. Os alunos são encorajados a testar adequadamente o
 *  código fornecido e a procurar soluções alternativas, à medida que forem adquirindo mais conhecimentos.
 */
package ui;


import business.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TextUI {
    // O model tem a 'lógica de negócio'.
    private IJogo jogo;

    // Menus da aplicação
    private Menu menu;

    // Scanner para leitura
    private Scanner scin;

    /**
     * Construtor.
     *
     * Cria os menus e a camada de negócio.
     */
    public TextUI() {

        this.menu = new Menu("Login pra ja",new String[]{
                "Admin",
                "Jogador",
                "Criar Usuario"
        });
        this.menu.setHandler(1, this::menuAdmin);
        this.menu.setHandler(2, this::menuJogador);
    }

    public void menuJogador(){
       // Menu jogadorMenu = new Menu()
    }

    public void menuAdmin(){
        // Criar o menu
        Menu adminMenu = new Menu(new String[]{
                "Adicionar Circuito",
                //  "Adicionar Campeonato",
                //  "Adicionar Carro",
                "Adicionar Piloto",
                "Ver Pilotos",
                "Ver Circuito",
        }, true);
        adminMenu.setHandler(1, this::trataAdicionarCircuito);
        adminMenu.setHandler(2, this::trataAdicionarPiloto);
        adminMenu.setHandler(3, this::trataMostrarPiloto);
        adminMenu.setHandler(4, this::trataMostrarCircuito);
        this.jogo = new Jogo();
        scin = new Scanner(System.in);
        adminMenu.run();
    }

    /**
     * Executa o menu principal e invoca o método correspondente à opção seleccionada.
     */
    public void run() {
        this.menu.run();
        System.out.println("Até breve!...");
    }

    // Métodos auxiliares
    // TA TUDO MALLLLLLLLLLL
    public void trataAdicionarCircuito() {
        try {
            System.out.println("Nome do novo circuito: ");
            String nome = scin.nextLine();
            if (!this.jogo.existeCircuito(nome)) {
                System.out.println("Distancia: ");
                Float dist = scin.nextFloat();
                System.out.println("Número de voltas: ");
                int voltas = scin.nextInt();
                System.out.println("Número de segmentos: ");
                List<Segmento> segmentos = new ArrayList<>();
                int nsegmentos = scin.nextInt();
                for (int i = 0; i < nsegmentos; i++) {
                    System.out.println("Tipo de segmento: [Reta/Curva/Chicane] ");
                    String tipo = scin.next();
                    System.out.println("Dificuldade: ");
                    int dificuldade = scin.nextInt();
                    Segmento s = new Segmento(tipo, dificuldade);
                    segmentos.add(s);
                }
                jogo.adicionarCircuito(nome, dist, voltas, segmentos);
                System.out.println("Circuito adicionado");
            } else {
                System.out.println("Esse nome de circuito já existe!");
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void trataMostrarCircuito() {
        try {
            System.out.println(jogo.printNomeCircuitos());
            System.out.println("Nome do circuito: ");
            String nome = scin.nextLine();
            if (jogo.existeCircuito(nome)) {
                Circuito c = jogo.getCircuito(nome);
                System.out.println(c.toString());
            } else {
                System.out.println("Esse nome de circuito não existe!");
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public int trataAfinacoes(){
        System.out.println("Deseja fazer afinações?[True/False]");
        boolean afin = scin.nextBoolean();
        int ret = -1;
        if(afin){
            System.out.println("Indique o valor do downforce [0..1]");
            ret = scin.nextInt();
        }
        return ret;
    }

    public void trataAdicionarPiloto(){
        System.out.println("Nome do novo piloto: ");
        String nome = scin.nextLine();
        System.out.println("Indique a Segurança (bom com valor 0) vs Agressividade (bom com valor 1) (SVA) [0..1]: ");
        float sva = scin.nextFloat();
        while (sva < 0 || sva > 1){
            System.out.println("SVA invalido, por favor adicione um valor entre 0 e 1:");
            sva = scin.nextFloat();
        }
        System.out.println("Indique a habilidade em tempo Chuvoso (bom com valor 0) vs Seco (bom com valor 1) (CTS) [0..1]");
        float cts = scin.nextFloat();
        while (cts < 0 || cts > 1){
            System.out.println("CTS invalido, por favor adicione um valor entre 0 e 1:");
            cts = scin.nextFloat();
        }
        jogo.adicionarPiloto(nome,cts,sva);
    }

    public void trataMostrarPiloto() {
        try {
            System.out.println(jogo.printNomePilotos());
            System.out.println("Nome do piloto: ");
            String nome = scin.nextLine();
            while (nome.isEmpty()) nome = scin.nextLine();
            if (jogo.existePiloto(nome)) {
                Piloto p = jogo.getPiloto(nome);
                System.out.println(p.toString());
            } else {
                System.out.println("Esse nome de Piloto não existe!");
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

}
