package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class FuncionarioDAO {
  private Connection connection;

  public FuncionarioDAO() throws Exception {
    connection = new ConnectionFactory().getConnection();
  }

  public Funcionario inserir(Funcionario f) {
    String sql = "INSERT INTO funcionario (func_salario, pes_id) VALUES (?, ?);";
    try {
      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      stmt.setInt(1, f.getFuncionarioSalario());
      stmt.setInt(2, f.getPessoa().getPessoaId());

      stmt.execute();

      try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          f.setFuncionarioId(generatedKeys.getInt(1));
        } else {
          throw new SQLException("Creating employee failed, no ID obtained.");
        }
      }

      stmt.close();
      System.out.println("Gravado!");

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return s;
  }

  public ArrayList<Funcionario> getLista() throws Exception {
    try {
      String sql = "SELECT * FROM funcionario";
      PreparedStatement stmt = connection.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();

      ArrayList<Funcionario> funcionarios = new ArrayList();

      while (rs.next()) {

        Funcionario funcionario = new Funcionario();

        funcionario.setFuncionarioId(rs.getString("func_id"));
        funcionario.setFuncionarioSalario(rs.getString("func_salario"));

        Pessoa pes = new Pessoa();
        PessoaDAO pDAO = new PessoaDAO();
        pes = pDAO.buscar(rs.getInt("pes_id"));

        funcionario.setPessoa(pes);

        funcionarios.add(funcionario);
      }

      rs.close();
      stmt.close();
      return funcionarios;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void remover(Funcionario f) {
    try {
      String sql = "DELETE FROM funcionario WHERE func_id = ?";
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setInt(1, f.getFuncionarioId());
      stmt.execute();
      stmt.close();
      System.out.println("Removido!");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void alterar(Funcionario f) {
    String sql = "UPDATE funcionario SET func_salario = ?, pes_id = ? WHERE func_id = ?";

    try {
      PreparedStatement stmt = connection.prepareStatement(sql);

      stmt.setString(1, f.getFuncionarioSalario());
      stmt.setInt(2, f.getPessoa().getPessoaId());
      stmt.setInt(3, f.getFuncionarioId());

      stmt.execute();
      stmt.close();
      System.out.println("Alterado!");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Funcionario buscar(Integer func_id) throws Exception {
    try {
      String sql = "SELECT * FROM funcionario where func_id = ?";
      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      stmt.setInt(1, func_id);
      stmt.execute();

      ResultSet rs = stmt.executeQuery();

      Funcionario funcionario = new Funcionario();

      while (rs.next()) {
        funcionario.setFuncionarioId(rs.getString("func_id"));
        funcionario.setFuncionarioSalario(rs.getString("func_salario"));

        Pessoa pes = new Pessoa();
        PessoaDAO pDAO = new PessoaDAO();
        pes = pDAO.buscar(rs.getInt("pes_id"));

        funcionario.setPessoa(pes);
      }

      rs.close();
      stmt.close();

      return funcionario;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}