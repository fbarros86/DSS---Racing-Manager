package business;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Corrida {

    private int voltasOcorridas;
    private String metereologia;
    private int nPilotosInativos;
    private Circuito circuito;
    private List<Carro> carros;

    public Corrida(String metereologia, int voltasOcorridas, int nPilotosInativos, Circuito c, List<Carro> carros){
        this.carros = carros;
        this.circuito  = c;
        this.nPilotosInativos = nPilotosInativos;
        this.metereologia = metereologia;
        this.voltasOcorridas = voltasOcorridas;
    }

    public Corrida(Circuito c){
        this.carros = new ArrayList<>();
        this.circuito = c;
        this.voltasOcorridas = 0;
        this.nPilotosInativos = 0;
        this.metereologia = escolheMeteorologia();
    }

    public String escolheMeteorologia(){
        Random rand = new Random();
        if(rand.nextBoolean()) return "Chuva";
        else return "Seco";
    }

    public int getVoltasOcorridas(){
        return this.voltasOcorridas;
    }

    public Circuito getCircuito(){
        return this.circuito;
    }

    public String getMeteorologia(){
       return this.metereologia;
    }

    public List<Carro> getClassificacao(){
        return this.carros;
    }

    public int getNPilotosInativos(){
        return this.nPilotosInativos;
    }

    /**
     *
     * @param c
     */
    // Adicionar carro? classificacao ver melhor!
    public void addCarro(Carro c){
        carros.add(c);
    }

    public void addCarros(List<Carro> carros){
        this.carros = carros;
    }

    // ver melhor de é assim o instanceof
    public void diminuiFiabilidade(){
        for(Carro c : carros){
            if(c instanceof GT ) ((GT) c).diminuirFiabilidade();
        }
    }

    public void calcUltrapassagem(float gdu){
        int ncarros = carros.size();
        Carro c1 = carros.get(0);
        int posC1 = 0;
        for(int i=1; i < ncarros; i++){
            Carro c2 = carros.get(1);
            //carro1
            int potencia1 = c1.getPotencia();
            float cilindrada1 = c1.getCilindrada();
            Piloto p1 = c1.getPiloto();
            String pneu1 = c1.getPneus();
            int fiabilidade1 = c1.getFiabilidade();
            int modoMotor1 = c1.gettModoMotor();
            float downforce1 = c1.getDownforce();
            float sva1 = p1.getSva();
            float cts1 = p1.getCts();
            //carro2
            int potencia2 = c2.getPotencia();
            float cilindrada2 = c2.getCilindrada();
            Piloto p2 = c2.getPiloto();
            String pneu2 = c2.getPneus();
            int fiabilidade2 = c2.getFiabilidade();
            int modoMotor2 = c2.gettModoMotor();
            float downforce2 = c2.getDownforce();
            float sva2 = p2.getSva();
            float cts2 = p2.getCts();

            float prob=0;

            prob += probPneus( pneu1, pneu2, cts1, cts2, metereologia);
            prob += 0.14 * gdu;

            if(cilindrada1<cilindrada2) prob+=0.14;
            else if (cilindrada1 == cilindrada2) prob+=0.07;

            if(potencia1<potencia2) prob+=0.14;
            else if (potencia1==potencia2) prob+=0.07;

            if(sva1<sva2) prob+=0.14;
            else if (potencia1==potencia2) prob+=0.07;

            if(downforce2<downforce1) prob+=0.14;
            else if (downforce1==downforce2) prob+=0.07;

            if(modoMotor1 ==modoMotor2) prob+=0.07;
            else if (modoMotor2 == -1) prob+=0.14;
            else if (modoMotor2==0 && modoMotor1 == 1) prob+=0.14;

            boolean randomResult = aconteceuEvento(prob);
            if(randomResult){
                carros.remove(i);
                carros.add(posC1,c2);
            }
            else posC1=i;

            c1=c2;
        }
    }

    public void calcAvaria(){

        for (Carro c : carros){
            float prob = 0;
            int fiabilidade = c.getFiabilidade();
            int modoMotor= c.gettModoMotor();
            if(modoMotor== 1) prob = (float) (( fiabilidade / 100) * 0.5 + 0.5*1);
            else if (modoMotor== 0) prob = (float) (( fiabilidade / 100) * 0.5 + 0.5*0.9);
            else if (modoMotor==-1) prob = (float) (( fiabilidade / 100) * 0.5 + 0.5*0.8);

            boolean randomResult = aconteceuEvento(prob);
            if(randomResult){
                String id = c.getID();
                removeCarro(id);

            }
        }
    }

    public void calcDespiste(){

        String tempo= getMeteorologia();
        for ( Carro c : carros){
            float prob =0;
            Piloto p = c.getPiloto();
            String pneus = c.getPneus();
            float downforce = c.getDownforce();
            float sva = p.getSva();
            float cts = p.getCts();

            switch (pneus) {
                case "macio" -> {
                    if (tempo.equals("chuva") && cts >= 0.5)
                        prob = (float) (1 - 0.3 - (0.25 * downforce) + (0.25 * sva) + (voltasOcorridas * 0.02));
                    else if (tempo.equals("chuva") && cts < 0.5)
                        prob = (float) (1 - 0.4 - (0.25 * downforce) + (0.25 * sva) + (voltasOcorridas * 0.02));
                    else if (tempo.equals("seco") && cts < 0.5)
                        prob = (float) (1 - 0.4 - (0.25 * downforce) + (0.25 * sva) + (voltasOcorridas * 0.02));
                    else if (tempo.equals("seco") && cts >= 0.5)
                        prob = (float) (1 - 0.5 - (0.25 * downforce) + (0.25 * sva) + (voltasOcorridas * 0.02));
                }
                case "duro" -> {
                    if (tempo.equals("chuva") && cts >= 0.5)
                        prob = (float) (1 - 0.3 - (0.25 * downforce) + (0.25 * sva) + (voltasOcorridas * 0.01));
                    else if (tempo.equals("chuva") && cts < 0.5)
                        prob = (float) (1 - 0.4 - (0.25 * downforce) + (0.25 * sva) + (voltasOcorridas * 0.01));
                    else if (tempo.equals("seco") && cts < 0.5)
                        prob = (float) (1 - 0.4 - (0.25 * downforce) + (0.25 * sva) + (voltasOcorridas * 0.01));
                    else if (tempo.equals("seco") && cts >= 0.5)
                        prob = (float) (1 - 0.5 - (0.25 * downforce) + (0.25 * sva) + (voltasOcorridas * 0.01));
                }
                case "chuva" -> {
                    if (tempo.equals("chuva") && cts >= 0.5)
                        prob = (float) (1 - 0.4 - (0.25 * downforce) + (0.25 * sva) + (voltasOcorridas * 0.01));
                    else if (tempo.equals("chuva") && cts < 0.5)
                        prob = (float) (1 - 0.5 - (0.25 * downforce) + (0.25 * sva) + (voltasOcorridas * 0.01));
                    else if (tempo.equals("seco") && cts < 0.5)
                        prob = (float) (1 - 0.4 - (0.25 * downforce) + (0.25 * sva) + (voltasOcorridas * 0.01));
                    else if (tempo.equals("seco") && cts >= 0.5)
                        prob = (float) (1 - 0.3 - (0.25 * downforce) + (0.25 * sva) + (voltasOcorridas * 0.01));
                }
            }

            boolean randomResult = aconteceuEvento(prob);
            if(randomResult){
                String id = c.getID();
                removeCarro(id);

            }
        }
    }

    /**
     *
     * @param idCarro
     */

    public void removeCarro(String idCarro){
        int i =0;
        Carro carro = null;
        for (Carro c : carros){
            if (c.getID().equals(idCarro)) {
                carro= carros.remove(i);
            }
            i++;
        }
        carros.add(carro);
    }

    public boolean aconteceuEvento(double probabilidade) {
        Random random = new Random();
        return random.nextFloat() < probabilidade;
    }

    public double probPneus(String pneu1, String pneu2, float cts1, float cts2, String metereologia){
        double prob=0;
        switch (pneu1) {
            case "macio" -> {
                switch (pneu2) {
                    case "macio" -> {
                        if (cts1 == cts2) prob += 0.07;
                        if (metereologia.equals("seco") && cts1 < cts2) prob += 0.14;
                        else if (metereologia.equals("chuva") && cts1 > cts2) prob += 0.14;
                    }
                    case "chuva" -> {
                        if (metereologia.equals("chuva") && cts1 >= cts2) prob += 0.14;
                        else if (metereologia.equals("seco") && cts1 < cts2) prob += 0.07;
                    }
                    case "duro" -> {
                        if (metereologia.equals("chuva") && cts1 > cts2) prob += 0.14;
                        else if (metereologia.equals("seco") && cts1 < cts2) prob += 0.14;
                    }
                }

            }
            case "duro" -> {
                switch (pneu2) {
                    case "macio" -> {
                        if (cts1 == cts2) prob += 0.7;
                        if (metereologia.equals("seco") && cts1 < cts2) prob += 0.14;
                        else if (metereologia.equals("chuva") && cts1 > cts2) prob += 0.14;
                    }
                    case "chuva" -> {
                        if (metereologia.equals("chuva") && cts1 >= cts2) prob += 0.14;
                        else if (metereologia.equals("seco") && cts1 < cts2) prob += 0.07;
                    }
                    case "duro" -> {
                        if (cts1 == cts2) prob += 0.7;
                        if (metereologia.equals("chuva") && cts1 > cts2) prob += 0.14;
                        else if (metereologia.equals("seco") && cts1 < cts2) prob += 0.14;
                    }
                }

            }
            case "chuva" -> {
                switch (pneu2) {
                    case "macio" -> {
                        if (metereologia.equals("seco") && cts1 <= cts2) prob += 0.14;
                        else if (metereologia.equals("chuva") && cts1 > cts2) prob += 0.7;
                    }
                    case "chuva" -> {
                        if (cts1 == cts2) prob += 0.7;
                        if (metereologia.equals("chuva") && cts1 > cts2) prob += 0.14;
                        else if (metereologia.equals("seco") && cts1 < cts2) prob += 0.14;
                    }
                    case "duro" -> {
                        if (metereologia.equals("chuva") && cts1 > cts2) prob += 0.7;
                        else if (metereologia.equals("seco") && cts1 < cts2) prob += 0.14;
                    }
                }
            }
        }

        return prob;
    }

    public List<Carro> simulaCorrida(){
        List<Segmento> p = circuito.getPercurso();
        for (Segmento s : p){
            calcUltrapassagem(s.getGDU());
            calcAvaria();
            calcDespiste();
        }
        return this.carros;
    }
}