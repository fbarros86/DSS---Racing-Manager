/*
 *  DISCLAIMER: Este código foi criado para discussão e edição durante as aulas práticas de DSS, representando
 *  uma solução em construção. Como tal, não deverá ser visto como uma solução canónica, ou mesmo acabada.
 *  É disponibilizado para auxiliar o processo de estudo. Os alunos são encorajados a testar adequadamente o
 *  código fornecido e a procurar soluções alternativas, à medida que forem adquirindo mais conhecimentos.
 */
package ui;


import business.*;

import java.nio.file.attribute.GroupPrincipal;
import java.util.ArrayList;
import java.util.Arrays;
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
        this.menu.setHandler(3, this::criaUser);
        this.jogo = new Jogo();
        scin = new Scanner(System.in);
    }

    public void menuJogador(){
        Menu jogadorMenu = new Menu(new String[]{
                "Jogar Campeonato",
        }, true);
    }

    public void menuAdmin(){
        // Criar o menu
        Menu adminMenu = new Menu(new String[]{
                "Adicionar Circuito",
                // "Adicionar Carro",
                "Adicionar Piloto",
                "Adicionar Campeonato",
                "Ver Pilotos",
                "Ver Circuito",
        }, true);
        adminMenu.setHandler(1, this::trataAdicionarCircuito);
        adminMenu.setHandler(2, this::trataAdicionarPiloto);
        adminMenu.setHandler(3, this::trataAdicionarCampeonato);
        adminMenu.setHandler(4, this::trataMostrarPiloto);
        adminMenu.setHandler(5, this::trataMostrarCircuito);
        adminMenu.run();
    }

    public void criaUser(){
        System.out.println("Username:");
        String user, pass, tipo;
        do{
            user = scin.nextLine();
            if(user.isBlank()){
                System.out.println("usuario invalido!!!");
            }
        }while (user.isBlank());
        if(jogo.existeUser(user)) {
            System.out.println("Usuario ja existente");
        }else{
            System.out.println("Password:");
            do{
                pass = scin.nextLine();
                if(pass.isBlank()){
                    System.out.println("password invalido!!!");
                }
            }while (pass.isBlank());
            System.out.println("Possui codigo de administrador? [True/False]");
            if(scin.nextBoolean()){
                tipo = "administrador";
                System.out.println("Indique seu codigo de administrado:");
                String codigo = scin.nextLine();
                if(jogo.codigoValido(codigo)){
                 jogo.adicionarUser(user,pass,tipo);
                }
            }else{
                tipo = "jogador";
                jogo.adicionarUser(user,pass,tipo);
            }
    }
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
        System.out.println("Indique nome do novo piloto: ");
        String nome = scin.nextLine();
        while (nome.isBlank()) {
            if (nome.isBlank()) System.out.println("Nome invalido insira outro nome por favor");
            nome = scin.nextLine();
        }
        if(!jogo.existePiloto(nome)) {
            System.out.println("Indique a Segurança (bom com valor 0) vs Agressividade (bom com valor 1) (SVA) [0..1]: ");
            float sva = scin.nextFloat();
            while (sva < 0 || sva > 1) {
                System.out.println("SVA invalido, por favor adicione um valor entre 0 e 1:");
                sva = scin.nextFloat();
            }
            System.out.println("Indique a habilidade em tempo Chuvoso (bom com valor 0) vs Seco (bom com valor 1) (CTS) [0..1]");
            float cts = scin.nextFloat();
            while (cts < 0 || cts > 1) {
                System.out.println("CTS invalido, por favor adicione um valor entre 0 e 1:");
                cts = scin.nextFloat();
            }
            jogo.adicionarPiloto(nome, cts, sva);
        }else System.out.println("Piloto ja existente");
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

    public void trataAdicionarCarro(){
        System.out.println("Indique a Marca do novo carro:");
        String marca = scin.nextLine();
        while(marca.isBlank()){
            System.out.println("Nome invalido insira outro nome por favor");
            scin.nextLine();
        }

    }

    public void trataAdicionarCampeonato(){
        System.out.println("Indique nome do novo campeonato");
        String nome = scin.nextLine();
        while (nome.isBlank()) {
            if (nome.isBlank()) System.out.println("Nome invalido insira outro nome por favor");
            nome = scin.nextLine();
        }
        if (!jogo.existeCampeonato(nome)) {
            String[] categorias = new String[]{
                    "C1",
                    "C2",
                    "GT",
                    "SC"
            };
            System.out.println("Indique a categoria do campeonato [C1/C2/GT/SC]");
            String categoria = scin.nextLine();
            while(!Arrays.asList(categorias).contains(categoria)){
                System.out.println("Categoria invalida");
                categoria = scin.nextLine();
            }
            List<Circuito> circuitos = jogo.getCircuitos().values().stream().toList();
            System.out.println("Escolhas os circuitos que deseja. Escreva o nome do circuito 1 em cada linha");
            System.out.println("Para sair pressione Enter sem nada escrito");
            System.out.println(jogo.printNomeCircuitos(circuitos));
            List<Circuito> circuitosCampeonato = new ArrayList<>();
            String circuito;
            do {
                circuito = scin.nextLine();
                if(jogo.existeCircuito(circuito)){
                    Circuito c = jogo.getCircuito(circuito);
                    circuitosCampeonato.add(c);
                }else{
                    if(!circuito.isBlank()) {
                        System.out.println("Circuito inexistente");
                        System.out.println("Tente o proximo");
                    }
                }
            }while(!circuito.isBlank() || circuitosCampeonato.isEmpty());
            List<Corrida> corridas = circuitosCampeonato.stream().map(circ -> new Corrida(circ)).toList();
            jogo.adicionarCampeonato(nome,categoria,corridas);
        }else System.out.println("Campeonato ja existente");
    }

}
