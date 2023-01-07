package data;


import business.*;

import java.sql.*;
import java.util.*;

import static java.util.stream.Collectors.toList;

import static java.util.stream.Collectors.*;

public class DAOCircuito implements Map<String,Circuito>{
    private static DAOCircuito singleton = null;

    private DAOCircuito() {
        DAOCampeonato.getInstance();
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

        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM circuitos")) {
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
             ResultSet rs = stm.executeQuery("SELECT Nome FROM circuitos WHERE Nome='"+key.toString()+"'")) {
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
        Circuito t = (Circuito) value;
        return this.containsKey(t.getNome());
    }

    private List<Segmento> getSegmentosCircuito(String key, Statement stm) throws SQLException{
        List<Segmento> r = new ArrayList<>();
        // preencher com o conteúdo da base de dados
        try (ResultSet rsa = stm.executeQuery("SELECT * FROM segmentos WHERE NomeCircuito='"+key+"'")) {
            while (rsa.next()) {
                Segmento seg = new Segmento(rsa.getString("Tipo"), rsa.getInt("Gdu"));
                r.add(rsa.getInt("Indice"), seg);
            }
        }
        return r;
    }

    @Override
    public Circuito get(Object key) {
        Circuito t = null;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM circuitos WHERE Nome='"+key+"'")) {
            if (rs.next()) {  // A chave existe na tabela
                // Reconstruir a os segmentos de pista
                List<Segmento> segmentos = getSegmentosCircuito(key.toString(), stm);


                //reconstruir circuito
                t = new Circuito(rs.getString("Nome"),
                        rs.getFloat("Distancia"),
                        rs.getInt("Voltas"),
                        segmentos);
                }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return t;

    }

    @Override
    public Circuito put(String key, Circuito value) {
        Circuito res;
        List<Segmento> s = value.getPercurso();
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {

            // Actualizar a circuito
            stm.executeUpdate(
                    "INSERT INTO circuitos " +
                            "VALUES ('"+ value.getNome()+ "', "+
                            value.getDist()+", "+
                            value.getNrVoltas()+") " +
                            "ON DUPLICATE KEY UPDATE Distancia=Values(Distancia), " +
                            "Voltas=Values(Voltas)");

            // Actualizar os segmentos do circuito
            //Eliminar segmentos cujo nome do circuito corresponde
            stm.executeUpdate("DELETE FROM segmentos WHERE NomeCircuito='"+key+"'");
            //Adicionar segmentos

            for (int i=0; i<s.size();i++){
                Segmento seg = s.get(i);
                stm.executeUpdate("INSERT INTO segmentos (Indice,NomeCircuito,Tipo,Gdu) " +
                        "VALUES ("+ i+ ",'"+
                        value.getNome()+"','"+
                        seg.getTipo()+"', "+
                        seg.getGDU()+") ");
            }
            res = get(key);



        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    @Override
    public Circuito remove(Object key) {
        Circuito t = this.get(key);
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()){
            // apagar a segmentos
            stm.executeUpdate("DELETE FROM segmentos WHERE NomeCircuito='"+key+"'");
            // apagar circuito
            stm.executeUpdate("DELETE FROM circuitos WHERE Nome='"+key+"'");
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return t;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Circuito> m) {
        for(Circuito t : m.values()) this.put(t.getNome(), t);
    }

    @Override
    public void clear() {
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("TRUNCATE segmentos");
            stm.executeUpdate("TRUNCATE circuitos");
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
            ResultSet rs = stm.executeQuery("SELECT Nome FROM circuitos");
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
    public Collection<Circuito> values() {
        Collection<Circuito> res = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT Nome FROM circuitos")) {
            while (rs.next()) {
                String idc = rs.getString("Nome");
                Circuito c = this.get(idc);
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
    public Set<Entry<String, Circuito>> entrySet() {
        Map<String,Circuito> res = new HashMap<>();
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()){
            ResultSet rs = stm.executeQuery("SELECT Nome FROM circuitos");
            while (rs.next()) {
                String idc = rs.getString("Nome");
                Circuito c = get(idc);
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
