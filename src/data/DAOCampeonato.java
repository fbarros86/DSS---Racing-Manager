package data;


import business.*;

import java.sql.*;
import java.util.*;

import static java.util.stream.Collectors.toList;

import static java.util.stream.Collectors.*;

public class DAOCampeonato implements Map<String,Campeonato>{
    private static DAOCampeonato singleton = null;

    private DAOCampeonato() {

        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String campeonato = "CREATE TABLE IF NOT EXISTS campeonatos (" +
                    "Nome varchar(15) NOT NULL PRIMARY KEY," +
                    "CorridaAtual int NOT NULL," +
                    "Categoria varchar(15) NOT NULL)";
            stm.executeUpdate(campeonato);
            String circuito ="CREATE TABLE IF NOT EXISTS circuitos (" +
                    "Nome varchar(15) NOT NULL PRIMARY KEY," +
                    "Distancia float(8) NOT NULL," +
                    "Voltas int NOT NULL)";
            stm.executeUpdate(circuito);
            String segmento = "CREATE TABLE IF NOT EXISTS segmentos (" +
                    "Id int NOT NULL PRIMARY KEY AUTO_INCREMENT,"+
                    "Indice int NOT NULL," +
                    "NomeCircuito varchar(15) NOT NULL," +
                    "Tipo varchar(15) NOT NULL," +
                    "Gdu int NOT NULL,"+
                    "FOREIGN KEY (NomeCircuito) references circuitos(Nome))";
            stm.executeUpdate(segmento);
            String corridas = "CREATE TABLE IF NOT EXISTS corridas (" +
                    "Id int NOT NULL PRIMARY KEY AUTO_INCREMENT,"+
                    "Indice int NOT NULL," +
                    "NomeCampeonato varchar(15) NOT NULL, " +
                    "Metereologia varchar(15) NOT NULL," +
                    "VoltasOcorridas int NOT NULL," +
                    "PilotosInativos int NOT NULL," +
                    "Circuito varchar(15) NOT NULL,"+
                    "FOREIGN KEY (Circuito) REFERENCES circuitos(Nome),"+
                    "FOREIGN KEY (NomeCampeonato) references campeonatos(Nome))";
            stm.executeUpdate(corridas);
            String utilizador ="CREATE TABLE IF NOT EXISTS utilizadores (" +
                    "Nome varchar(15) NOT NULL PRIMARY KEY," +
                    "Pass varchar(15) NOT NULL," +
                    "Tipo varchar(15) NOT NULL," +
                    "Classificacao int NOT NULL," +
                    "Pontuacao int NOT NULL)";
            stm.executeUpdate(utilizador);
            String piloto ="CREATE TABLE IF NOT EXISTS pilotos (" +
                    "Nome varchar(15) NOT NULL PRIMARY KEY," +
                    "Sva float(8) NOT NULL," +
                    "Cts float(8) NOT NULL)";
            stm.executeUpdate(piloto);

            String carro ="CREATE TABLE IF NOT EXISTS carros (" +
                    "Id varchar(15) NOT NULL PRIMARY KEY," +
                    "Cilindrada int NOT NULL," +
                    "Fiabilidade int NOT NULL," +
                    "Marca varchar(15) NOT NULL," +
                    "Modelo varchar(15) NOT NULL," +
                    "Pneus varchar(15) NULL," +
                    "PotenciaMC int NOT NULL," +
                    "Equipa varchar(15) NULL," +
                    "ModoMotor int NULL," +
                    "Downforce float NULL," +
                    "Afinacoes int NOT NULL," +
                    "PotenciaEletrica int NULL," +
                    "Tipo varchar(15) NOT NULL," +
                    "Piloto varchar(15) NULL,"+
                    "Indice int NULL," +
                    "CorridaId int NULL," +
                    "FOREIGN KEY (Piloto) REFERENCES pilotos(Nome)," +
                    "FOREIGN KEY (CorridaId) REFERENCES corridas(Id))";

            stm.executeUpdate(carro);

            String equipas = "CREATE TABLE IF NOT EXISTS equipas (" +
                    "Nome varchar(15) NOT NULL PRIMARY KEY," +
                    "Pontuacao int NOT NULL," +
                    "Iduser varchar(15) NOT NULL,"+
                    "Campeonato varchar(15) NOT NULL,"+
                    "Carro1 varchar(15) NULL,"+
                    "Carro2 varchar(15) NULL,"+
                    "FOREIGN KEY (Iduser) REFERENCES utilizadores(Nome),"+
                    "FOREIGN KEY (Campeonato) REFERENCES campeonatos(Nome),"+
                    "FOREIGN KEY (Carro1) REFERENCES carros(Id),"+
                    "FOREIGN KEY (Carro2) REFERENCES carros(Id))";
            stm.executeUpdate(equipas);

        } catch (SQLException e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    /**
     * Implementação do padrão Singleton
     *
     * @return devolve a instância única desta classe
     */
    public static DAOCampeonato getInstance() {
        if (DAOCampeonato.singleton == null) {
            DAOCampeonato.singleton = new DAOCampeonato();
        }
        return DAOCampeonato.singleton;
    }

    @Override
    public int size() {

        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM campeonatos")) {
            if(rs.next()) {
                i = rs.getInt(1);
            }
        }
        catch (Exception e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return i;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        boolean r;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT Nome FROM campeonatos WHERE Nome='"+key.toString()+"'")) {
            r = rs.next();
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    @Override
    public boolean containsValue(Object value) {
        Campeonato t = (Campeonato) value;
        return this.containsKey(t.getNome());
    }

    private List<Carro> getCarros(String key, Statement stm) throws SQLException{
        List<Carro> carros = new ArrayList<>();
        try (ResultSet rsa = stm.executeQuery("SELECT * FROM carros WHERE CorridaId='"+key+"'")) {
            while (rsa.next()) {
                DAOCarro c = DAOCarro.getInstance();
                Carro carro =c.get(rsa.getString("Id"));
                carros.add(rsa.getInt("Indice"), carro);
            }
        }
        return carros;

    }

    private List<Corrida> getCorridasCampeonato(String key, Statement stm)throws SQLException{
        List<Corrida> corridas = new ArrayList<>();
        try (ResultSet rsa = stm.executeQuery("SELECT * FROM corridas WHERE NomeCampeonato='"+key+"'")) {
            while (rsa.next()) {
                DAOCircuito c = DAOCircuito.getInstance();
                Circuito circuito =c.get(rsa.getString("Circuito"));
                List <Carro> carros  = getCarros(rsa.getString("Id"),stm);
                Corrida corrida = new Corrida(rsa.getString("Metereologia"),
                        rsa.getInt("VoltasOcorridas"),
                        rsa.getInt("PilotosInativos"),
                        circuito,new ArrayList<Carro>());
                corridas.add(rsa.getInt("Indice"), corrida);
            }
        }
        return corridas;
    }

    private Map<String,Equipa> getEquipasCampeonato(String key, Statement stm) throws SQLException{
        Map<String,Equipa> equipas = new HashMap<>();
        try (ResultSet rsa = stm.executeQuery("SELECT * FROM equipas WHERE Campeonato='"+key+"'")) {
            while (rsa.next()) {

            }
        }
        return equipas;
    }

    @Override
    public Campeonato get(Object key) {
        Campeonato c = null;

        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM circuitos WHERE Nome='"+key+"'")) {
            if (rs.next()) {  // A chave existe na tabela
                // Reconstruir as corridas
                List<Corrida> corridas = getCorridasCampeonato(key.toString(), stm);

                //Reconstruir as equipas
                Map<String,Equipa> equipas = getEquipasCampeonato(key.toString(),stm);

                c = new Campeonato(rs.getString("Nome"),
                        rs.getInt("CorridaAtual"),
                        rs.getString("Categoria"),
                        equipas,corridas);
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return c;
    }

    private void putCorrida(Corrida c, int index, Statement stm){

    }

    private void putEquipa(Equipa e, Statement stm){

    }


    private void apagaEquipas(String key, Statement stm) throws SQLException{
        ResultSet rsa = stm.executeQuery("SELECT * FROM equipas WHERE NomeCampeonato='"+key+"'");
        while (rsa.next()) {
            stm.executeUpdate("UPDATE carros SET Equipa=NULL WHERE Id = "+rsa.getString("Carro1"));
            stm.executeUpdate("UPDATE carros SET Equipa=NULL WHERE Id = "+rsa.getString("Carro2"));
        }
        stm.executeUpdate("DELETE FROM equipas WHERE NomeCampeonato='"+key+"'");
    }

    @Override
    public Campeonato put(String key, Campeonato value) {
        Campeonato res;
        Map<String,Equipa> equipas = value.getEquipas();
        List<Corrida> corridas = value.getCorridas();
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {

            // Actualizar a campeonato
            stm.executeUpdate(
                    "INSERT INTO campeonatos " +
                            "VALUES ('"+ value.getNome()+ "', "+
                            value.getCorridaAtual()+", '"+
                            value.getCategoria()+"') " +
                            "ON DUPLICATE KEY UPDATE CorridaAtual=Values(CorridaAtual), " +
                            "Categoria=Values(Categoria)");

            //apagar corridas
            stm.executeUpdate("DELETE FROM corridas WHERE NomeCampeonato='"+key+"'");
            //apagar equipas
            apagaEquipas(key,stm);

            //atualizar corridas
            for (int i=0;i<corridas.size();i++)
                putCorrida(corridas.get(i),i,stm);

            //atualizar equipas
            for (Equipa equipa: equipas.values())
                putEquipa(equipa,stm);

            res = get(key);


        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    @Override
    public Campeonato remove(Object key) {
        Campeonato t = this.get(key);
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()){
            // apagar a Corridas
            stm.executeUpdate("DELETE FROM corridas WHERE NomeCampeonato='"+key+"'");
            //apagar equipas
            apagaEquipas(key.toString(),stm);
            // apagar Campeonato
            stm.executeUpdate("DELETE FROM campeonatos WHERE Nome='"+key+"'");
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return t;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Campeonato> m) {
        for(Campeonato t : m.values()) this.put(t.getNome(), t);
    }

    @Override
    public void clear() {
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("TRUNCATE equipas");
            stm.executeUpdate("TRUNCATE corridas");
            stm.executeUpdate("TRUNCATE campeonatos");
            stm.executeUpdate("UPDATE carros SET Equipa=NULL ");
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }

    }

    @Override
    public Set<String> keySet() {
        Set<String> res= new HashSet<>();;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()){
            ResultSet rs = stm.executeQuery("SELECT Nome FROM campeonatos");
            while (rs.next()) {
                String idc = rs.getString("Nome");
                res.add(idc);
            }
        }
        catch (SQLException e){
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    @Override
    public Collection<Campeonato> values() {
        Collection<Campeonato> res = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT Nome FROM circuitos")) {
            while (rs.next()) {
                String idc = rs.getString("Nome");
                Campeonato c = this.get(idc);
                res.add(c);
            }
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    @Override
    public Set<Entry<String, Campeonato>> entrySet() {
        Map<String,Campeonato> res = new HashMap<>();
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()){
            ResultSet rs = stm.executeQuery("SELECT Nome FROM circuitos");
            while (rs.next()) {
                String idc = rs.getString("Nome");
                Campeonato c = get(idc);
                res.put(idc,c);
            }
        }
        catch (SQLException e){
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res.entrySet();    }
}
