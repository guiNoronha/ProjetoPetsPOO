package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class PessoaDAO {
  private Connection connection;

  public PessoaDAO() throws Exception {
    connection = new ConnectionFactory().getConnection();
  }

  public Pessoa inserir(Pessoa p) {
    String sql = "INSERT INTO pessoa (pes_nome, pes_endereco, pes_telefone, pes_cpf, pes_email) VALUES (?, ?, ?, ?, ?);";
    try {
      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      stmt.setString(1, p.getPessoaNome());
      stmt.setString(2, p.getPessoaEndereco());
      stmt.setString(3, p.getPessoaTelefone());
      stmt.setStrin(4, p.getPessoaCPF());
      stmt.setString(5, p.getPessoaEmail());

      stmt.execute();

      try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          p.setPessoaId(generatedKeys.getInt(1));
        } else {
          throw new SQLException("Creating person failed, no ID obtained.");
        }
      }

      stmt.close();
      System.out.println("Gravado!");

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return a;
  }

  public ArrayList<Pessoa> getLista() throws Exception {
    try {
      String sql = "SELECT * FROM pessoa";
      PreparedStatement stmt = connection.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();

      ArrayList<Pessoa> pessoas = new ArrayList();

      while (rs.next()) {

        Pessoa pessoa = new Pessoa();
        pessoa.setPessoaId(rs.getInt("pes_id"));
        pessoa.setPessoaNome(rs.getString("pes_nome"));
        pessoa.setPessoaEndereco(rs.getString("pes_endereco"));
        pessoa.setPessoaTelefone(rs.getString("pes_telefone"));
        pessoa.setPessoaCPF(rs.getString("pes_cpf"));
        pessoa.setPessoaEmail(rs.getString("pes_email"));

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
      stmt.setInt(1, p.getPessoaId());
      stmt.execute();
      stmt.close();
      System.out.println("Removido!");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void alterar(Pessoa p) {
    String sql = "UPDATE pessoa SET pes_nome = ?, pes_endereco = ?, pes_telefone = ?, pes_cpf = ?, pes_email = ? WHERE pes_id = ?";

    try {
      PreparedStatement stmt = connection.prepareStatement(sql);

      stmt.setString(1, p.getPessoaNome());
      stmt.setString(2, p.getPessoaEndereco());
      stmt.setString(3, p.getPessoaTelefone());
      stmt.setStrin(4, p.getPessoaCPF());
      stmt.setString(5, p.getPessoaEmail());
      stmt.setString(6, p.getPessoaId());
      
      stmt.execute();
      stmt.close();
      System.out.println("Alterado!");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
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
        pessoa.setPessoaId(rs.getInt("pes_id"));
        pessoa.setPessoaNome(rs.getString("pes_nome"));
        pessoa.setPessoaEndereco(rs.getString("pes_endereco"));
        pessoa.setPessoaTelefone(rs.getString("pes_telefone"));
        pessoa.setPessoaCPF(rs.getString("pes_cpf"));
        pessoa.setPessoaEmail(rs.getString("pes_email"));
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