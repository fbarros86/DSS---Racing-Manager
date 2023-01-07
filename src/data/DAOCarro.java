package data;


import business.*;

import java.io.DataOutputStream;
import java.sql.*;
import java.util.*;

import static java.util.stream.Collectors.toList;

import static java.util.stream.Collectors.*;

public class DAOCarro implements Map<String,Carro>{
    private static DAOCarro singleton = null;

    private DAOCarro() {
        DAOCampeonato.getInstance();
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

        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM carros")) {
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
             ResultSet rs = stm.executeQuery("SELECT Id FROM carros WHERE Id='"+key.toString()+"'")) {
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

        Carro t = (Carro) value;
        return this.containsKey(t.getId());
    }

    @Override
    public Carro get(Object key) {
        Carro t = null;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM carros WHERE Nome='"+key+"'")) {
            if (rs.next()) {  // A chave existe na tabela
                //
                //reconstruir circuito

                DAOPiloto p = DAOPiloto.getInstance();
                Piloto piloto  = p.get(rs.getString("Piloto"));
                switch (rs.getString("TipoSegmento")){
                    case "C1":{
                        t = new C1(rs.getString("Id"),
                                rs.getInt("Cilindrada"),
                                rs.getInt("Fiabilidade"),
                                rs.getString("Marca"),
                                rs.getString("Modelo"),
                                rs.getString("Pneus"),
                                rs.getInt("PotenciaMC"),
                                rs.getString("Equipa"),
                                rs.getInt("ModoMotor"),
                                rs.getFloat("Downforce"),
                                rs.getInt("Afinacoes"),
                               piloto);
                        break;}
                    case "C1H":{
                        t = new C1H(rs.getString("Id"),
                                rs.getInt("Cilindrada"),
                                rs.getInt("Fiabilidade"),
                                rs.getString("Marca"),
                                rs.getString("Modelo"),
                                rs.getString("Pneus"),
                                rs.getInt("PotenciaMC"),
                                rs.getString("Equipa"),
                                rs.getInt("ModoMotor"),
                                rs.getFloat("Downforce"),
                                rs.getInt("Afinacoes"),
                                rs.getInt("PotenciaEletrica"),
                                piloto);
                        break;}
                    case "C2":{
                        t = new C2(rs.getString("Id"),
                                rs.getInt("Cilindrada"),
                                rs.getInt("Fiabilidade"),
                                rs.getString("Marca"),
                                rs.getString("Modelo"),
                                rs.getString("Pneus"),
                                rs.getInt("PotenciaMC"),
                                rs.getString("Equipa"),
                                rs.getInt("ModoMotor"),
                                rs.getFloat("Downforce"),
                                rs.getInt("Afinacoes"),
                                piloto);
                        break;}
                    case "C2H":{
                        t = new C2H(rs.getString("Id"),
                                rs.getInt("Cilindrada"),
                                rs.getInt("Fiabilidade"),
                                rs.getString("Marca"),
                                rs.getString("Modelo"),
                                rs.getString("Pneus"),
                                rs.getInt("PotenciaMC"),
                                rs.getString("Equipa"),
                                rs.getInt("ModoMotor"),
                                rs.getFloat("Downforce"),
                                rs.getInt("Afinacoes"),
                                rs.getInt("PotenciaEletrica"),
                                piloto);
                        break;}
                    case "SC":{
                        t = new SC(rs.getString("Id"),
                                rs.getInt("Cilindrada"),
                                rs.getInt("Fiabilidade"),
                                rs.getString("Marca"),
                                rs.getString("Modelo"),
                                rs.getString("Pneus"),
                                rs.getInt("PotenciaMC"),
                                rs.getString("Equipa"),
                                rs.getInt("ModoMotor"),
                                rs.getFloat("Downforce"),
                                rs.getInt("Afinacoes"),
                                piloto);
                        break;}
                    case "GT":{
                        t = new GT(rs.getString("Id"),
                                rs.getInt("Cilindrada"),
                                rs.getInt("Fiabilidade"),
                                rs.getString("Marca"),
                                rs.getString("Modelo"),
                                rs.getString("Pneus"),
                                rs.getInt("PotenciaMC"),
                                rs.getString("Equipa"),
                                rs.getInt("ModoMotor"),
                                rs.getFloat("Downforce"),
                                rs.getInt("Afinacoes"),
                                piloto);
                        break;}
                    case "GTH":{
                        t = new GTH(rs.getString("Id"),
                                rs.getInt("Cilindrada"),
                                rs.getInt("Fiabilidade"),
                                rs.getString("Marca"),
                                rs.getString("Modelo"),
                                rs.getString("Pneus"),
                                rs.getInt("PotenciaMC"),
                                rs.getString("Equipa"),
                                rs.getInt("ModoMotor"),
                                rs.getFloat("Downforce"),
                                rs.getInt("Afinacoes"),
                                rs.getInt("PotenciaEletrica"),
                                piloto);
                        break;}


                }
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return t;
    }

    @Override
    public Carro put(String key, Carro value) {
        Carro res;
        Piloto p = value.getPiloto();
        String nomePiloto  = p.getNome();
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            // Actualizar a circuito
            String tipo=null;
            Integer potenciaEletrica = null;
            if (value instanceof C1) tipo ="C1";
            else if (value instanceof C2) tipo="C2";
            else if (value instanceof GT) tipo="GT";
            else if (value instanceof SC) tipo="SC";
            else if (value instanceof C1H){
                tipo="C1H";
                C1H carro = (C1H) value;
                potenciaEletrica = carro.getPotencia();
            }
            else if (value instanceof C2H){
                tipo="C2H";
                C2H carro = (C2H) value;
                potenciaEletrica = carro.getPotencia();
            }
            else if (value instanceof GTH){
                tipo="GTH";
                GTH carro = (GTH) value;
                potenciaEletrica = carro.getPotencia();
            }
            stm.executeUpdate(
                    "INSERT INTO carros " +
                            "VALUES ('"+ value.getID()+ "', "+
                            value.getCilindrada()+", "+
                            value.getFiabilidade()+", '"+
                            value.getMarca()+"', '"+
                            value.getModelo()+"', '"+
                            value.getPneus()+"', "+
                            value.getPotenciaMC()+",' "+
                            value.getEquipa()+"', "+
                            value.getModoMotor()+", "+
                            value.getDownforce()+", "+
                            value.getnAfinacoes()+", "+
                            potenciaEletrica+",' "+
                            tipo+"', '"+
                            nomePiloto +
                            "') " +
                            "ON DUPLICATE KEY UPDATE Cilindrada=Values(Cilindrada), " +
                            "Fiabilidade=Values(Fiabilidade)," +
                            "Marca=Values(Marca)," +
                            "Modelo=Values(Modelo)," +
                            "Pneus=Values(Pneus)," +
                            "PotenciaMC=Values(PotenciaMC)," +
                            "Equipa=Values(Equipa)," +
                            "ModoMotor=Values(ModoMotor)," +
                            "Downforce=Values(Downforce)," +
                            "Afinacoes=Values(Afinacoes)," +
                            "PotenciaEletrica=Values(PotenciaEletrica)," +
                            "Tipo=Values(Tipo)," +
                            "Piloto=Values(Piloto)");
            DAOPiloto pi = DAOPiloto.getInstance();
            pi.put(nomePiloto,p);
            res = get(key);



        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    @Override
    public Carro remove(Object key) {
        Carro t = this.get(key);
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()){
            // apagar a carro
            stm.executeUpdate("DELETE FROM carros WHERE Id='"+key+"'");
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return t;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Carro> m) {
        for(Carro t : m.values()) this.put(t.getID(), t);
    }

    @Override
    public void clear() {
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("TRUNCATE carros");
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
            ResultSet rs = stm.executeQuery("SELECT Nome FROM carros");
            while (rs.next()) {
                String idc = rs.getString("Id");
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
    public Collection<Carro> values() {
        Collection<Carro> res = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT Nome FROM carros")) {
            while (rs.next()) {
                String idc = rs.getString("Id");
                Carro c = this.get(idc);
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
    public Set<Entry<String, Carro>> entrySet() {
        Map<String,Carro> res = new HashMap<>();
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()){
            ResultSet rs = stm.executeQuery("SELECT Nome FROM carros");
            while (rs.next()) {
                String idc = rs.getString("Id");
                Carro c = get(idc);
                res.put(idc,c);
            }
        }
        catch (SQLException e){
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res.entrySet();
    }
}
