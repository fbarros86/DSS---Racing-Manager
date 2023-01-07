/*
 *  DISCLAIMER: Este código foi criado para discussão e edição durante as aulas práticas de DSS, representando
 *  uma solução em construção. Como tal, não deverá ser visto como uma solução canónica, ou mesmo acabada.
 *  É disponibilizado para auxiliar o processo de estudo. Os alunos são encorajados a testar adequadamente o
 *  código fornecido e a procurar soluções alternativas, à medida que forem adquirindo mais conhecimentos.
 */
package ui;


import business.*;

import java.nio.file.attribute.GroupPrincipal;
import java.util.*;
import java.util.stream.Collectors;

public class TextUI {
    // O model tem a 'lógica de negócio'.
    private IJogo jogo;

    // Menus da aplicação
    private Menu menu;

    // Scanner para leitura
    private Scanner scin;

    private Utilizador userAtual;

    /**
     * Construtor.
     *
     * Cria os menus e a camada de negócio.
     */
    public TextUI() {

        this.menu = new Menu("Login",new String[]{
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

    public boolean validaUser(){
        System.out.println("Usuario: ");
        String user = scin.next();
        System.out.println("Password: ");
        String pass = scin.next();
        userAtual = jogo.getUser(user);
        return jogo.validaUser(user,pass);
    }

    public void menuJogador(){
        if(validaUser()){
            Menu jogadorMenu = new Menu(new String[]{
                    "Simular Campeonato",
            }, true);
            jogadorMenu.setHandler(1,this::trataSimulaCampeonato);
            jogadorMenu.run();
        }
        else System.out.println("Utilizador ou password inválidos");

    }

    public void menuAdmin(){
        if(validaUser()) {
            // Criar o menu
            Menu adminMenu = new Menu(new String[]{
                    "Adicionar Circuito",
                    "Adicionar Carro",
                    "Adicionar Piloto",
                    "Adicionar Campeonato",
                    "Ver Circuito",
                    "Ver Piloto",
                    "Ver Campeonato",
                    "Ver Carros",

            }, true);
            adminMenu.setHandler(1, this::trataAdicionarCircuito);
            adminMenu.setHandler(2, this::trataAdicionarCarro);
            adminMenu.setHandler(3, this::trataAdicionarPiloto);
            adminMenu.setHandler(4, this::trataAdicionarCampeonato);
            adminMenu.setHandler(5, this::trataMostrarCircuito);
            adminMenu.setHandler(6, this::trataMostrarPiloto);
            adminMenu.setHandler(7, this::trataMostrarCampeonato);
            adminMenu.setHandler(8, this::trataMostrarCarro);
            this.jogo = new Jogo();
            scin = new Scanner(System.in);

            adminMenu.run();
        }
        else System.out.println("Utilizador ou password inválidos");
    }

    public void criaUser(){
        System.out.println("Username:");
        String user, pass, tipo;
        do{
            user = scin.next();
            if(user.isBlank()){
                System.out.println("usuario invalido!!!");
            }
        }while (user.isBlank());
        if(jogo.existeUser(user)) {
            System.out.println("Usuario ja existente");
        }else{
            System.out.println("Password:");
            do{
                pass = scin.next();
                if(pass.isBlank()){
                    System.out.println("password invalido!!!");
                }
            }while (pass.isBlank());
            System.out.println("Possui codigo de administrador? [True/False]");
            if(scin.nextBoolean()){
                tipo = "administrador";
                System.out.println("Indique seu codigo de administrado:");
                String codigo = scin.next();
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
            String nome = scin.next();
            if (!this.jogo.existeCircuito(nome)) {
                System.out.println("Indique o número de curvas: ");
                int curvas = scin.nextInt();
                System.out.println("Indique o número de chicanes: ");
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
            String nome = scin.next();
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
        String nome = scin.next();
        while (nome.isBlank()) {
            if (nome.isBlank()) System.out.println("Nome invalido insira outro nome por favor");
            nome = scin.next();
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
            String nome = scin.next();
            while (nome.isEmpty()) nome = scin.next();
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
                jogo.registarCarroC1NaoHibrido(marca,modelo,6000,potenciaMotor,downforce, categoria);
            }
            else if( categoria.equals("C1") && hibrido){
                jogo.registarCarroC1Hibrido(marca,modelo,6000,potenciaMotor, potenciaElec ,downforce, categoria);
            }
            else if( categoria.equals("C2") && !hibrido){
                jogo.registarCarroC2NaoHibrido(marca,modelo,cilindrada, potenciaMotor,downforce, categoria);
            }
            else if( categoria.equals("C2") && hibrido){
                jogo.registarCarroC2Hibrido(marca,modelo, cilindrada, potenciaMotor, potenciaElec,downforce, categoria);
            }
            else if( categoria.equals("GT") && !hibrido){
                jogo.registarCarroGTNaoHibrido(marca,modelo,cilindrada,potenciaMotor,downforce,categoria);
            }
            else if( categoria.equals("GT") && hibrido){
                jogo.registarCarroGTHibrido(marca,modelo,cilindrada, potenciaMotor, potenciaElec, downforce,categoria);
            }
            else if( categoria.equals("SC")){
                jogo.registarCarroC1NaoHibrido(marca,modelo,2500,potenciaMotor,downforce,categoria);
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
        String nome = scin.next();
        while (nome.isBlank()) {
            if (nome.isBlank()) System.out.println("Nome invalido insira outro nome por favor");
            nome = scin.next();
        }
        if (!jogo.existeCampeonato(nome)) {
            String[] categorias = new String[]{
                    "C1",
                    "C2",
                    "GT",
                    "SC"
            };
            System.out.println("Indique a categoria do campeonato [C1/C2/GT/SC]");
            String categoria = scin.next();
            while(!Arrays.asList(categorias).contains(categoria)){
                System.out.println("Categoria invalida");
                categoria = scin.next();

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

    public void trataSimulaCampeonato(){
        if(!jogo.getCampeonatos().isEmpty()) {
            System.out.println(jogo.printNomeCampeonato());
            String campNome;
            int numGuest = 1;
            do {
                campNome = scin.nextLine();
            } while (campNome.isBlank() || !jogo.existeCampeonato(campNome));
            Campeonato campeonatoJogar = jogo.getCampeonatos().get(campNome);
            System.out.println("Quantos jogadores deseja adicionar? [1..5]");
            int numPlayer;
            do {
                numPlayer = scin.nextInt();
            } while (numPlayer < 0 || numPlayer > 5);
            List<Carro> carros = new ArrayList<>();
            for (int i = 0; i < numPlayer; i++) {
                String user, nome, idCarro, piloto1, piloto2;
                if (i == 0) {
                    user = userAtual.getCodNome();
                } else {
                    System.out.println("Se possuir conta de jogador adicione o seu ID caso contrario pressione Enter");
                    user = scin.next();
                    if (user.isBlank()) {
                        user = "Guest" + numGuest;
                        numGuest++;
                    }
                }
                System.out.println(user + " escolha o nome de sua equipa:");
                do {
                    nome = scin.nextLine();
                } while (nome.isBlank());
                System.out.println("Escolha um carro: (Escreva o ID)");
                System.out.println(jogo.printCarros(campeonatoJogar.getCategoria()));
                do {
                    idCarro = scin.next();
                } while (!jogo.existeCarro(idCarro,campeonatoJogar.getCategoria()));
                Carro c1 = jogo.getCarros().get(idCarro);
                Carro c2 = new Carro(c1);
                System.out.println("Escolha seus pilotos: ");
                System.out.println(jogo.printNomePilotos());
                System.out.println("Piloto 1:");
                do {
                    piloto1 = scin.next();
                } while (!jogo.existePiloto(piloto1));
                Piloto p1 = jogo.getPiloto(piloto1);
                System.out.println("Piloto 2:");
                do {
                    piloto2 = scin.next();
                } while (!jogo.existePiloto(piloto2));
                Piloto p2 = jogo.getPiloto(piloto2);
                c1.setPiloto(p1);
                c2.setPiloto(p2);
                c1.setEquipa(nome);
                c2.setEquipa(nome);
                c1.setNAfinacoes(campeonatoJogar.getNCorridas()*2/3);
                c2.setNAfinacoes(campeonatoJogar.getNCorridas()*2/3);
                carros.add(c1);
                carros.add(c2);
                campeonatoJogar.adicionaEquipa(new Equipa(user, nome, c1, c2));
            }
            campeonatoJogar.setCarrosCorridas(carros);
            campeonatoJogar.simulaCampeonato();
        }else{
            System.out.println("Não há campeonatos criados");
        }
    }

    public void trataMostrarCampeonato(){
        try {
            System.out.println(jogo.printNomeCampeonato());
            System.out.println("Nome do Campeonato: ");
            String nome = scin.next();
            while (nome.isEmpty()) nome = scin.next();
            if (jogo.existeCampeonato(nome)) {
                Campeonato campeonato = jogo.getCampeonatos().get(nome);
                System.out.println(campeonato.toString());
            } else {
                System.out.println("Esse nome de Campeonato não existe!");
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void trataStanding(String standing){
        System.out.println(standing);
        scin.next();
    }

    public void trataInicio(String user, Carro c){
            System.out.println(c.toString());
            System.out.println(user+" Indique o inteiro correspondente ao modo do motor que deseja: [-1(Agressivo)/0(Normal)/1(Conservador)]");
            int modoMotor = scin.nextInt();
            while( modoMotor != -1 && modoMotor != 0 && modoMotor != 1) modoMotor = scin.nextInt();
            System.out.println("Indique o tipo de pneu que deseja utilizar [Chuva/Duro/Macio]");
            String pneus = scin.next();
            while (!pneus.equals("Chuva") && !pneus.equals("Duro") && !pneus.equals("Macio")) pneus = scin.next();
            c.setPneus(pneus);
            c.setModoMotor(modoMotor);

    }

}
