package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class SuprimentoDAO {
  private Connection connection;

  public SuprimentoDAO() throws Exception {
    connection = new ConnectionFactory().getConnection();
  }

  public Suprimento inserir(Suprimento s) {
    String sql = "INSERT INTO suprimento (sup_nome, sup_validade, sup_quantidade) VALUES (?, ?, ?);";
    try {
      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      java.sql.Date validade = new java.sql.Date(s.getSuprimentoValidade().getTime());

      stmt.setString(1, s.getSuprimentoNome());
      
      if (s.getSuprimentoValidade() != null) {
        stmt.setDate(2, validade);
      } else {
        stmt.setDate(2, null);
      }

      stmt.setInt(3, s.getSuprimentoQuantidade());

      stmt.execute();

      try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          s.setSuprimentoId(generatedKeys.getInt(1));
        } else {
          throw new SQLException("Creating sup failed, no ID obtained.");
        }
      }

      stmt.close();
      System.out.println("Gravado!");

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return s;
  }

  public ArrayList<Suprimento> getLista() throws Exception {
    try {
      String sql = "SELECT * FROM suprimento";
      PreparedStatement stmt = connection.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();

      ArrayList<Suprimento> suprimentos = new ArrayList();

      while (rs.next()) {

        Suprimento suprimento = new Suprimento();
        suprimento.setSuprimentoId(rs.getInt("sup_id"));
        suprimento.setSuprimentoNome(rs.getString("sup_nome"));
        suprimento.setSuprimentoValidade(rs.getDate("sup_validade"));
        suprimento.setSuprimentoQuantidade(rs.getInt("sup_quantidade"));

        suprimentos.add(suprimento);
      }

      rs.close();
      stmt.close();
      return suprimentos;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void remover(Suprimento s) {
    try {
      String sql = "DELETE FROM suprimento WHERE sup_id = ?";
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setInt(1, s.getSuprimentoId());
      stmt.execute();
      stmt.close();
      System.out.println("Removido!");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void alterar(Suprimento s) {
    String sql = "UPDATE suprimento SET sup_nome = ?, sup_validade = ?, sup_quantidade = ? WHERE sup_id = ?";

    try {
      PreparedStatement stmt = connection.prepareStatement(sql);

      java.sql.Date validade = new java.sql.Date(s.getSuprimentoValidade().getTime());

      stmt.setString(1, s.getSuprimentoNome());
      
      if (s.getSuprimentoValidade() != null) {
        stmt.setDate(2, validade);
      } else {
        stmt.setDate(2, null);
      }

      stmt.setInt(3, s.getSuprimentoQuantidade());
      stmt.setInt(4, s.getSuprimentoId());

      stmt.execute();
      stmt.close();
      System.out.println("Alterado!");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Suprimento buscar(Integer sup_id) throws Exception {
    try {
      String sql = "SELECT * FROM suprimento where sup_id = ?";
      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      stmt.setInt(1, sup_id);
      stmt.execute();

      ResultSet rs = stmt.executeQuery();

      Suprimento suprimento = new Suprimento();

      while (rs.next()) {
        suprimento.setSuprimentoId(rs.getInt("sup_id"));
        suprimento.setSuprimentoNome(rs.getString("sup_nome"));
        suprimento.setSuprimentoValidade(rs.getDate("sup_validade"));
        suprimento.setSuprimentoQuantidade(rs.getInt("sup_quantidade"));
      }

      rs.close();
      stmt.close();

      return suprimento;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}