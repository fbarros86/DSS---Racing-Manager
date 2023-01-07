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
                    "FOREIGN KEY (Piloto) REFERENCES pilotos(Nome))";
            stm.executeUpdate(carro);
            String piloto ="CREATE TABLE IF NOT EXISTS pilotos (" +
                    "Nome varchar(15) NOT NULL PRIMARY KEY," +
                    "Sva float(8) NOT NULL," +
                    "Cts float(8) NOT NULL)";
            stm.executeUpdate(piloto);
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

    @Override
    public Campeonato get(Object key) {
        return null;
    }

    @Override
    public Campeonato put(String key, Campeonato value) {
        return null;
    }

    @Override
    public Campeonato remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Campeonato> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<Campeonato> values() {
        return null;
    }

    @Override
    public Set<Entry<String, Campeonato>> entrySet() {
        return null;
    }
}
