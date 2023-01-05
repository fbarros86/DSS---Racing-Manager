package data;


import business.*;

import java.sql.*;
import java.util.*;

import static java.util.stream.Collectors.toList;

import static java.util.stream.Collectors.*;

public class DAOPiloto implements Map<String,Piloto>{
    private static DAOPiloto singleton = null;

    private DAOPiloto() {
        DAOCampeonato.getInstance();
    }

    /**
     * Implementação do padrão Singleton
     *
     * @return devolve a instância única desta classe
     */
    public static DAOPiloto getInstance() {
        if (DAOPiloto.singleton == null) {
            DAOPiloto.singleton = new DAOPiloto();
        }
        return DAOPiloto.singleton;
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
    public Piloto get(Object key) {
        return null;
    }

    @Override
    public Piloto put(String key, Piloto value) {
        return null;
    }

    @Override
    public Piloto remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Piloto> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<Piloto> values() {
        return null;
    }

    @Override
    public Set<Entry<String, Piloto>> entrySet() {
        return null;
    }
}
