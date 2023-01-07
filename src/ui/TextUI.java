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
        // Criar o menu
        this.menu = new Menu(new String[]{
                "Adicionar Circuito",
                "Ver Circuito",
                "Outro Menu"
        });
        this.menu.setHandler(1, this::trataAdicionarCircuito);
        this.menu.setHandler(2, this::trataMostrarCircuito);
        this.jogo = new Jogo();
        scin = new Scanner(System.in);
    }

    /**
     * Executa o menu principal e invoca o método correspondente à opção seleccionada.
     */
    public void run() {
        this.menu.run();
        System.out.println("Até breve!...");
    }

    // Métodos auxiliares

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
                    System.out.println("Tipo de segmento: ");
                    String tipo = scin.nextLine();
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

}
