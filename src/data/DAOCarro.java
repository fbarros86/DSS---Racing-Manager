package data;


import business.*;

import java.sql.*;
import java.util.*;

import static java.util.stream.Collectors.toList;

import static java.util.stream.Collectors.*;

public class DAOCarro implements Map<String,Carro>{
    private static DAOCarro singleton = null;

    private DAOCarro() {
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String carro ="CREATE TABLE IF NOT EXISTS carros (" +
                    "Id varchar(15) NOT NULL PRIMARY KEY," +
                    "Cilindrada int NOT NULL," +
                    "Fiabilidade int NOT NULL," +
                    "Marca varchar(15) NOT NULL," +
                    "Modelo varchar(15) NOT NULL," +
                    "Tipo ENUM('C1','C2','GT','SC','C1H','C2H','GTH') NOT NULL)";
            stm.executeUpdate(carro);
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
    public static DAOCarro getInstance() {
        if (DAOCarro.singleton == null) {
            DAOCarro.singleton = new DAOCarro();
        }
        return DAOCarro.singleton;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Carro get(Object key) {
        return null;
    }

    @Override
    public Carro put(String key, Carro value) {
        return null;
    }

    @Override
    public Carro remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Carro> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<Carro> values() {
        return null;
    }

    @Override
    public Set<Entry<String, Carro>> entrySet() {
        return null;
    }
}
