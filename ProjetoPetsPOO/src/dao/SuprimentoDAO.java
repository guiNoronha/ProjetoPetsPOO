package dao;

import java.sql.Connection;
import banco.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import classes .*;

public class SuprimentoDAO {
  private Connection connection;

  public SuprimentoDAO() throws Exception {
//	  connection 
	  connection = new Conexao().getConnection();
  }

  public Suprimento inserir(Suprimento s) {
    String sql = "INSERT INTO suprimento (sup_tipo, sup_origem, sup_descricao) VALUES (?, ?, ?);";
    try {
      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      stmt.setString(1, s.getSupTipo().getValue());
      stmt.setString(2, s.getSupOrigem().getValue());
      stmt.setString(3, s.getSupDescricao().getValue());

      stmt.execute();

      try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          s.setSupId(generatedKeys.getInt(1));
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
        suprimento.setSupId(rs.getInt("sup_id"));
        suprimento.setSupTipo(rs.getString("sup_tipo"));
        suprimento.setSupOrigem(rs.getString("sup_origem"));
        suprimento.setSupDescricao(rs.getString("sup_descricao"));

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
      stmt.setInt(1, s.getSupId());
      stmt.execute();
      stmt.close();
      System.out.println("Removido!");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void alterar(Suprimento s) {
    String sql = "UPDATE suprimento SET sup_tipo = ?, sup_origem = ?, sup_descricao = ? WHERE sup_id = ?";

    try {
      PreparedStatement stmt = connection.prepareStatement(sql);

      stmt.setString(1, s.getSupTipo().getValue());
      stmt.setString(2, s.getSupOrigem().getValue());
      stmt.setString(3, s.getSupDescricao().getValue());
      stmt.setInt(4, s.getSupId());

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
    	  suprimento.setSupId(rs.getInt("sup_id"));
          suprimento.setSupTipo(rs.getString("sup_tipo"));
          suprimento.setSupOrigem(rs.getString("sup_origem"));
          suprimento.setSupDescricao(rs.getString("sup_descricao"));
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