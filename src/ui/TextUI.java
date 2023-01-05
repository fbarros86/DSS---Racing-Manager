/*
 *  DISCLAIMER: Este código foi criado para discussão e edição durante as aulas práticas de DSS, representando
 *  uma solução em construção. Como tal, não deverá ser visto como uma solução canónica, ou mesmo acabada.
 *  É disponibilizado para auxiliar o processo de estudo. Os alunos são encorajados a testar adequadamente o
 *  código fornecido e a procurar soluções alternativas, à medida que forem adquirindo mais conhecimentos.
 */
package ui;


import business.*;

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
                "Adicionar Aluno",
                "Consultar Aluno",
                "Listar Alunos",
                "Adicionar Turma",
                "Mudar Sala à Turma",
                "Listar Turmas",
                "Adicionar Aluno a Turma",
                "Remover Aluno da Turma",
                "Listar Alunos de Turma",
                "Adicionar Sala",
                "Consular Sala",
                "Listar Salas",
                "Listar Turmas de Sala"
        });
        //this.menu.setHandler(1, this::trataAdicionarAluno);
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

}
