package data;


import business.*;

import java.sql.*;
import java.util.*;

import static java.util.stream.Collectors.toList;

import static java.util.stream.Collectors.*;

public class DAOCircuito implements Map<String,Circuito>{
    private static DAOCircuito singleton = null;

    private DAOCircuito() {
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String circuito ="CREATE TABLE IF NOT EXISTS circuitos (" +
                    "Nome varchar(15) NOT NULL PRIMARY KEY," +
                    "Distancia float(8) NOT NULL," +
                    "Voltas int NOT NULL)";
            stm.executeUpdate(circuito);
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
    public static DAOCircuito getInstance() {
        if (DAOCircuito.singleton == null) {
            DAOCircuito.singleton = new DAOCircuito();
        }
        return DAOCircuito.singleton;
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
    public Circuito get(Object key) {
        return null;
    }

    @Override
    public Circuito put(String key, Circuito value) {
        return null;
    }

    @Override
    public Circuito remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Circuito> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<Circuito> values() {
        return null;
    }

    @Override
    public Set<Entry<String, Circuito>> entrySet() {
        return null;
    }
}
