import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Funcionario {
    private int id;
    private String nome;
    private String bi;
    private String cargo;
    private char sexo;
    private char estadoCivil;
    private LocalDate dataN;
    private String telefone;

    // Construtor padrão
    public Funcionario() {
    }

    // Construtor com parâmetros
    public Funcionario(int id, String nome, String bi, String cargo, char sexo, char estadoCivil, LocalDate dataN,
            String telefone) {
        this.id = id;
        this.nome = nome;
        this.bi = bi;
        this.cargo = cargo;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.dataN = dataN;
        this.telefone = telefone;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBi() {
        return bi;
    }

    public void setBi(String bi) {
        this.bi = bi;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public char getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(char estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public LocalDate getDataN() {
        return dataN;
    }

    public void setDataN(LocalDate dataN) {
        this.dataN = dataN;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelone(String telefone) {
        this.telefone = telefone;
    }

    public boolean cadastroFuncionario(String nome, String bi, String cargo, char sexo, char estadoCivil,
            LocalDate dataN, String telefone) {
        Connection conectar = null;
        PreparedStatement pr = null;

        try {
            conectar = Conection.getConnection(); // Obter a conexão
            String sql = "INSERT INTO funci(bi, nome, cargo, dataNascimento, estadoCivil, sexo, telefone) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pr = conectar.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pr.setString(1, bi);
            pr.setString(2, nome);
            pr.setString(3, cargo);
            pr.setDate(4, java.sql.Date.valueOf(dataN));
            pr.setString(5, String.valueOf(estadoCivil));
            pr.setString(6, String.valueOf(sexo));
            pr.setString(7, telefone);

            System.out.println("Executando query: " + pr.toString());
            
           int linhas = pr.executeUpdate();
           System.out.println(linhas);
         
            
                System.out.println("Nenhuma linha afetada.");
                return false; // Falha
            }
        catch (SQLException e) {

            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pr != null)
                    pr.close();
                if (conectar != null && !conectar.isClosed())
                    conectar.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
         
    }

    public static List<Funcionario> listarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        Connection conectar = null;
        PreparedStatement pr = null;
        ResultSet rs = null;

        try {
            conectar = Conection.getConnection();
            if (conectar != null) {
                System.out.println("Conexão com o banco de dados estabelecida com sucesso.");
            } else {
                System.out.println("Falha ao conectar ao banco de dados.");
            }

            String sql = "SELECT * FROM funci";
            pr = conectar.prepareStatement(sql);
            rs = pr.executeQuery();

            while (rs.next()) {
                // Usar o construtor com parâmetros
                Funcionario funcionario = new Funcionario(rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("bi"),
                        rs.getString("cargo"),
                        rs.getString("sexo").charAt(0),
                        rs.getString("estadoCivil").charAt(0),
                        rs.getDate("dataNascimento").toLocalDate(),
                        rs.getString("telefone"));
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao executar a consulta SQL: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pr != null)
                    pr.close();
                if (conectar != null && !conectar.isClosed())
                    conectar.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
                e.printStackTrace();
            }
        }

        return funcionarios;
    }
    public String contagem() throws SQLException{
        String n="";
       
        PreparedStatement pr = null;
        Conection.getConnection(); // Obter a conexão
        String sql = "Select count(*) as tot from funci;";
        pr = Conection.getConnection().prepareStatement(sql);
        ResultSet rs=pr.executeQuery();
        if(rs.next()){
            n= rs.getString("tot");
        }

        return n;
    }
}
