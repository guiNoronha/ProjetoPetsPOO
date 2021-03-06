package dao;//test

import java.sql.Connection;
import banco.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import classes.*;

public class FuncionarioDAO {
  private Connection connection;

  public FuncionarioDAO() throws Exception {
    connection = new Conexao().getConnection();
  }

  public Funcionario inserir(Funcionario f) throws Exception {
    String sql = "INSERT INTO funcionario (fun_salario, pes_id) VALUES (?, ?);";
    try {
      PessoaDAO pDAO = new PessoaDAO();
      f = (Funcionario) pDAO.inserir((Pessoa) f);
    	
      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      stmt.setString(1, f.getFuncSalario().getValue());
      stmt.setInt(2, f.getPesId());

      stmt.execute();

      try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          f.setFuncId(generatedKeys.getInt(1));
        } else {
          throw new SQLException("Creating employee failed, no ID obtained.");
        }
      }

      stmt.close();
      System.out.println("Gravado!");

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return f;
  }

  public ArrayList<Funcionario> getLista() throws Exception {
    try {
      String sql = "SELECT * FROM funcionario JOIN pessoa ON pessoa.pes_id = funcionario.pes_id";
      PreparedStatement stmt = connection.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();

      ArrayList<Funcionario> funcionarios = new ArrayList();//

      while (rs.next()) {

        Funcionario funcionario = new Funcionario();

        funcionario.setFuncId(rs.getInt("fun_id"));
        funcionario.setFuncSalario(rs.getString("fun_salario"));
        
        funcionario.setPesId(rs.getInt("pes_id"));
        funcionario.setPesNome(rs.getString("pes_nome"));
        funcionario.setPesCep(rs.getString("pes_cep"));
        funcionario.setPesTelefone(rs.getString("pes_telefone"));
        funcionario.setPesCpf(rs.getString("pes_cpf"));
        funcionario.setPesNumero(rs.getString("pes_numero"));
        funcionario.setPesSenha(rs.getString("pes_senha"));

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
      String sql = "DELETE FROM funcionario WHERE fun_id = ?";
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setInt(1, f.getFuncId());
      stmt.execute();
      stmt.close();
      System.out.println("Removido!");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void alterar(Funcionario f) throws Exception {
    String sql = "UPDATE funcionario SET fun_salario = ?, pes_id = ? WHERE fun_id = ?";

    try {
      PessoaDAO pDAO = new PessoaDAO();
      f = (Funcionario) pDAO.alterar((Pessoa) f);
        
      PreparedStatement stmt = connection.prepareStatement(sql);

      stmt.setString(1, f.getFuncSalario().getValue());
      stmt.setInt(2, f.getPesId());
      stmt.setInt(3, f.getFuncId());

      stmt.execute();
      stmt.close();
      System.out.println("Alterado!");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Funcionario buscar(Integer fun_id) throws Exception {
    try {
      String sql = "SELECT * FROM funcionario where fun_id = ?";
      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      stmt.setInt(1, fun_id);
      stmt.execute();

      ResultSet rs = stmt.executeQuery();

      Funcionario funcionario = new Funcionario();

      while (rs.next()) {
        funcionario.setFuncId(rs.getInt("fun_id"));
        funcionario.setFuncSalario(rs.getString("fun_salario"));

        funcionario.setPesId(rs.getInt("pes_id"));
        funcionario.setPesNome(rs.getString("pes_nome"));
        funcionario.setPesCep(rs.getString("pes_cep"));
        funcionario.setPesTelefone(rs.getString("pes_telefone"));
        funcionario.setPesCpf(rs.getString("pes_cpf"));
        funcionario.setPesNumero(rs.getString("pes_numero"));

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