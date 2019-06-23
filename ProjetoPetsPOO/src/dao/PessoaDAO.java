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

public class PessoaDAO {
  private Connection connection;

  public PessoaDAO() throws Exception {
    connection = new Conexao().getConnection();
  }

  public Pessoa inserir(Pessoa p) {
    String sql = "INSERT INTO pessoa (pes_nome, pes_cep, pes_telefone, pes_cpf, pes_numero, pes_tipo, pes_senha) VALUES (?, ?, ?, ?, ?, ?, ?);";
    try {
      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      stmt.setString(1, p.getPesNome().getValue());
      stmt.setString(2, p.getPesCep());
      stmt.setString(3, p.getPesTelefone().getValue());
      stmt.setString(4, p.getPesCpf().getValue());
      stmt.setString(5, p.getPesNumero());
      stmt.setInt(6, p.getPesTipo());
      stmt.setString(7, p.getPesSenha());

      stmt.execute();

      try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          p.setPesId(generatedKeys.getInt(1));
        } else {
          throw new SQLException("Creating person failed, no ID obtained.");
        }
      }

      stmt.close();
      System.out.println("Gravado!");

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return p;
  }

  public ArrayList<Pessoa> getLista() throws Exception {
    try {
      String sql = "SELECT * FROM pessoa";
      PreparedStatement stmt = connection.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();

      ArrayList<Pessoa> pessoas = new ArrayList();

      while (rs.next()) {

        Pessoa pessoa = new Pessoa();
        pessoa.setPesId(rs.getInt("pes_id"));
        pessoa.setPesNome(rs.getString("pes_nome"));
        pessoa.setPesCep(rs.getString("pes_cep"));
        pessoa.setPesTelefone(rs.getString("pes_telefone"));
        pessoa.setPesCpf(rs.getString("pes_cpf"));
        pessoa.setPesNumero(rs.getString("pes_numero"));
        pessoa.setPesSenha(rs.getString("pes_senha"));
        pessoas.add(pessoa);
        
      }

      rs.close();
      stmt.close();
      return pessoas;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void remover(Pessoa p) {
    try {
      String sql = "DELETE FROM pessoa WHERE pes_id = ?";
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setInt(1, p.getPesId());
      stmt.execute();
      stmt.close();
      System.out.println("Removido!");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Pessoa alterar(Pessoa p) {
    String sql = "UPDATE pessoa SET pes_nome = ?, pes_cep = ?, pes_telefone = ?, pes_cpf = ?, pes_numero = ?, pes_senha = ? WHERE pes_id = ?";

    try {
      PreparedStatement stmt = connection.prepareStatement(sql);

      stmt.setString(1, p.getPesNome().getValue());
      stmt.setString(2, p.getPesCep());
      stmt.setString(3, p.getPesTelefone().getValue());
      stmt.setString(4, p.getPesCpf().getValue());
      stmt.setString(5, p.getPesNumero());
      stmt.setString(6, p.getPesSenha());
      stmt.setInt(7, p.getPesId());
      
      stmt.execute();
      stmt.close();
      System.out.println("Alterado!");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return p;
  }

  public Pessoa buscar(Integer pes_id) throws Exception {
    try {
      String sql = "SELECT * FROM pessoa where pes_id = ?";
      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      stmt.setInt(1, pes_id);
      stmt.execute();

      ResultSet rs = stmt.executeQuery();

      Pessoa pessoa = new Pessoa();

      while (rs.next()) {
        pessoa.setPesId(rs.getInt("pes_id"));
        pessoa.setPesNome(rs.getString("pes_nome"));
        pessoa.setPesCep(rs.getString("pes_cep"));
        pessoa.setPesTelefone(rs.getString("pes_telefone"));
        pessoa.setPesCpf(rs.getString("pes_cpf"));
        pessoa.setPesNumero(rs.getString("pes_numero"));
        pessoa.setPesSenha(rs.getString("pes_senha"));
      }

      rs.close();
      stmt.close();

      return pessoa;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public Pessoa buscarDadosLogin(Pessoa pes) throws Exception {
	    try {
	      String sql = "SELECT * FROM pessoa where pes_cpf = ? and pes_senha = ?";
	      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

	      stmt.setString(1, pes.getPesCpf().getValue());
	      stmt.setString(2, pes.getPesSenha());
	      stmt.execute();

	      ResultSet rs = stmt.executeQuery();

	      Pessoa pessoa = new Pessoa();

	      while (rs.next()) {
	        pessoa.setPesId(rs.getInt("pes_id"));
	        pessoa.setPesNome(rs.getString("pes_nome"));
	        pessoa.setPesCep(rs.getString("pes_cep"));
	        pessoa.setPesTelefone(rs.getString("pes_telefone"));
	        pessoa.setPesCpf(rs.getString("pes_cpf"));
	        pessoa.setPesNumero(rs.getString("pes_numero"));
	        pessoa.setPesTipo(rs.getInt("pes_tipo"));
	        pessoa.setPesSenha(rs.getString("pes_senha"));
	      }

	      rs.close();
	      stmt.close();

	      return pessoa;
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    return null;
	  }
}