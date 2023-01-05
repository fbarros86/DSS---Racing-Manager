package data;


import business.*;

import java.sql.*;
import java.util.*;

import static java.util.stream.Collectors.toList;

import static java.util.stream.Collectors.*;

public class DAOUtilizador implements Map<String,Utilizador>{
    private static DAOUtilizador singleton = null;

    private DAOUtilizador() {
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String utilizador ="CREATE TABLE IF NOT EXISTS utilizadores (" +
                    "Nome varchar(15) NOT NULL PRIMARY KEY," +
                    "Pass varchar(15) NOT NULL," +
                    "Tipo varchar(15) NOT NULL," +
                    "Classificacao int NOT NULL," +
                    "Pontuacao int NOT NULL)";
            stm.executeUpdate(utilizador);
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
    public static DAOUtilizador getInstance() {
        if (DAOUtilizador.singleton == null) {
            DAOUtilizador.singleton = new DAOUtilizador();
        }
        return DAOUtilizador.singleton;
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
    public Utilizador get(Object key) {
        return null;
    }

    @Override
    public Utilizador put(String key, Utilizador value) {
        return null;
    }

    @Override
    public Utilizador remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Utilizador> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<Utilizador> values() {
        return null;
    }

    @Override
    public Set<Entry<String, Utilizador>> entrySet() {
        return null;
    }
}
