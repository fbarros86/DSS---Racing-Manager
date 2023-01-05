package data;

/*
 *  DISCLAIMER: Este código foi criado para discussão e edição durante as aulas práticas de DSS, representando
 *  uma solução em construção. Como tal, não deverá ser visto como uma solução canónica, ou mesmo acabada.
 *  É disponibilizado para auxiliar o processo de estudo. Os alunos são encorajados a testar adequadamente o
 *  código fornecido e a procurar soluções alternativas, à medida que forem adquirindo mais conhecimentos.
 */

public class DAOConfig {
    static final String USERNAME = "user";
    static final String PASSWORD = "user";
    private static final String DATABASE = "´racing´";          // Actualizar
    private static final String DRIVER = "jdbc:mariadb";        // Usar para MariaDB
    static final String URL = DRIVER+"://localhost:3306/"+DATABASE;
}

