import java.util.Objects;

public class Piloto {
        private String nome;
        private float sva;
        private float cts;

        public Piloto(String name, float svA, float ctS){
            nome = name;
            sva = svA;
            cts = ctS;
        }

    public float getCts() {
        return cts;
    }

    public float getSva() {
        return sva;
    }

    public String getNome() {
        return nome;
    }

    public void setCts(float cts) {
        this.cts = cts;
    }

    public void setSva(float sva) {
        this.sva = sva;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piloto piloto = (Piloto) o;
        return Objects.equals(nome, piloto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}