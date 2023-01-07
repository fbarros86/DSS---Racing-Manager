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
                "Adicionar Piloto",
                "Adicionar Campeonato",
                "Adicionar Carro",
                "Ver Circuito",
                "Ver Piloto",
                "Ver Campeonato",
                "Ver Carros",

        }, true);
        adminMenu.setHandler(1, this::trataAdicionarCircuito);
        adminMenu.setHandler(2, this::trataAdicionarPiloto);
        adminMenu.setHandler(3, this::trataAdicionarCampeonato);
        adminMenu.setHandler(4, this::trataAdicionarCarro);
        adminMenu.setHandler(5, this::trataMostrarCircuito);
        adminMenu.setHandler(6, this::trataMostrarPiloto);
        //adminMenu.setHandler(7, this::trataMostrarCampeonato);
        adminMenu.setHandler(8, this::trataMostrarCarro);
        this.jogo = new Jogo();
        scin = new Scanner(System.in);

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
                System.out.println("Indique o número de curvas: ");
                int curvas = scin.nextInt();
                System.out.println("Indique o número de chicanes ");
                int chicanes = scin.nextInt();
                int retas = curvas+chicanes+1;
                float distmin = (float) (curvas*0.2 + chicanes*0.2 + retas*0.5);
                System.out.printf("O circuito irá ter %d retas e uma distância mínima de %.2f. \n", retas, distmin);
                System.out.println("Indique os kilómetros do circuito tendo em conta a distância mínima: ");
                float dist = scin.nextInt();
                while (dist < distmin){
                    System.out.println("Distância inválida:");
                    dist = scin.nextFloat();
                }

                List<Segmento> segmentos = new ArrayList<>();


                for (int i = 1; i <= curvas; i++) {
                    float dificuldade=1;
                    System.out.printf("Insira o número da dificuldade Curva%d (possível, díficil, imposível): ", i);
                    String dificuldadeString = scin.next();
                    if (dificuldadeString.equals("possível"))  {dificuldade= 1.0F;}
                    else if (dificuldadeString.equals("difícil")) {dificuldade = 0.5F;}
                    else if (dificuldadeString.equals("impossível")) {dificuldade = 0F;}
                    Segmento s = new Segmento("curva", dificuldade);
                    segmentos.add(s);
                }
                for (int i = 1; i <= chicanes; i++) {
                    Segmento s = new Segmento("Chicane", 0.5F);
                    segmentos.add(s);
                }
                for (int i = 1; i <= retas; i++) {
                    float dificuldade=1;
                    System.out.printf("Insira o número da dificuldade Curva%d (possível, díficil, imposível): ", i);
                    String dificuldadeString = scin.next();
                    if (dificuldadeString.equals("possível"))  {dificuldade= 1.0F;}
                    else if (dificuldadeString.equals("difícil")) {dificuldade = 0.5F;}
                    else if (dificuldadeString.equals("impossível")) {dificuldade = 0F;}
                    Segmento s = new Segmento("reta", dificuldade);
                    segmentos.add(s);
                }

                System.out.println("Número de voltas: ");
                int voltas = scin.nextInt();
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

        try {
            int potenciaElec=0;
            Boolean hibrido = false;
            int cilindrada=0;
            System.out.println("Escolha uma das categorias (C1,C2,GT,SC): ");
            String categoria = scin.next();
            if ( categoria != "SC") {
                System.out.println("Deseja que o carro seja híbrido? [True ou False]: ");
                hibrido = scin.nextBoolean();
                if( hibrido ){
                    System.out.println("Insira a potência do motor elétrico: ");
                    potenciaElec = scin.nextInt();
                }
            }
            if (categoria.equals("C2") || categoria.equals("GT")) {
                System.out.println("Insira a cilindrada: ");
                cilindrada = scin.nextInt();
            }
            System.out.println("Insira a marca: ");
            String marca = scin.next();
            System.out.println("Insira o modelo:");
            String modelo = scin.next();
            System.out.println("Insira a potência do motor: ");
            int potenciaMotor = scin.nextInt();
            System.out.println("Insira o downforce (0-1): ");
            int downforce = scin.nextInt();

            List<Carro> carros = new ArrayList<>(jogo.getCarros().values());

            Boolean flag = true;

            for(Carro c: carros){
                if (categoria.equals("C1") && (hibrido)){
                    if( (c instanceof C1H) && (marca.equals(c.getMarca())) && modelo.equals(c.getModelo()) && (potenciaMotor==c.getPotenciaMC()) && (downforce==c.getDownforce())){
                        flag = false;
                        break;
                    }
                }
                else if (categoria.equals("C1") && (!hibrido)){
                    if( (c instanceof C1) && !(c instanceof C1H) && (marca.equals(c.getMarca())) && modelo.equals(c.getModelo()) && (potenciaMotor==c.getPotenciaMC()) && (downforce==c.getDownforce())){
                        flag = false;
                        break;
                    }
                }
                else if (categoria.equals("C2") && (hibrido)){
                    if( (c instanceof C2H) && (marca.equals(c.getMarca())) && modelo.equals(c.getModelo()) && (potenciaMotor==c.getPotenciaMC()) && (downforce==c.getDownforce())){
                        flag = false;
                        break;
                    }
                }
                else if (categoria.equals("C2") && (!hibrido)){
                    if( (c instanceof C2) && !(c instanceof C2H) && (marca.equals(c.getMarca())) && modelo.equals(c.getModelo()) && (potenciaMotor==c.getPotenciaMC()) && (downforce==c.getDownforce())){
                        flag = false;
                        break;
                    }
                }
                else if (categoria.equals("GT") && (hibrido)){
                    if( (c instanceof GTH)  &&  (marca.equals(c.getMarca())) && modelo.equals(c.getModelo()) && (potenciaMotor==c.getPotenciaMC()) && (downforce==c.getDownforce())){
                        flag = false;
                        break;
                    }
                }
                else if (categoria.equals("GT") && (!hibrido)){
                    if( (c instanceof GT) && !(c instanceof GTH) && (marca.equals(c.getMarca())) && modelo.equals(c.getModelo()) && (potenciaMotor==c.getPotenciaMC()) && (downforce==c.getDownforce())){
                        flag = false;
                        break;
                    }
                }
                else if (categoria.equals("SC")){
                    if( (c instanceof SC) && (marca.equals(c.getMarca())) && modelo.equals(c.getModelo()) && (potenciaMotor==c.getPotenciaMC()) && (downforce==c.getDownforce())){
                        flag = false;
                        break;
                    }
                }
            }

            if (!flag) System.out.println("Esse carro já existe!");
            else if( categoria.equals("C1") && !hibrido){
                jogo.registarCarroC1NaoHibrido(marca,modelo,6000,potenciaMotor,downforce);
            }
            else if( categoria.equals("C1") && hibrido){
                jogo.registarCarroC1Hibrido(marca,modelo,6000,potenciaMotor, potenciaElec ,downforce);
            }
            else if( categoria.equals("C2") && !hibrido){
                jogo.registarCarroC2NaoHibrido(marca,modelo,cilindrada, potenciaMotor,downforce);
            }
            else if( categoria.equals("C2") && hibrido){
                jogo.registarCarroC2Hibrido(marca,modelo, cilindrada, potenciaMotor, potenciaElec,downforce);
            }
            else if( categoria.equals("GT") && !hibrido){
                jogo.registarCarroGTNaoHibrido(marca,modelo,cilindrada,potenciaMotor,downforce);
            }
            else if( categoria.equals("GT") && hibrido){
                jogo.registarCarroGTHibrido(marca,modelo,cilindrada, potenciaMotor, potenciaElec, downforce);
            }
            else if( categoria.equals("SC")){
                jogo.registarCarroC1NaoHibrido(marca,modelo,2500,potenciaMotor,downforce);
            }


        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void trataMostrarCarro() {
        try {
            List<Carro> carros = new ArrayList<>(jogo.getCarros().values());

            for(Carro c: carros) {
                System.out.println(c.toString());
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
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
            String circuito;
            do {
                circuito = scin.nextLine();
                if(jogo.existeCircuito(circuito)){
                    circuitos.add(jogo.getCircuito(circuito));
                }else{
                    if(!circuito.isBlank()) {
                        System.out.println("Circuito inexistente");
                        System.out.println("Tente o proximo");
                    }
                }
            }while(!circuito.isBlank());
            List<Corrida> corridas = circuitos.stream().map(circ -> new Corrida(circ)).toList();
            jogo.adicionarCampeonato(nome,categoria,corridas);
        }else System.out.println("Campeonato ja existente");
    }

}
