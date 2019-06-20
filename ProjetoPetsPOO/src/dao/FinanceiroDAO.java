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

public class FinanceiroDAO {
  private Connection connection;

  public FinanceiroDAO() throws Exception {
//	  connection 
	  connection = new Conexao().getConnection();
  }

  public Financeiro inserir(Financeiro s) {
    String sql = "INSERT INTO financeiro (fin_data, fin_categoria, fin_descricao, fin_valor) VALUES (?, ?, ?);";
    try {
      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      
      if (s.getFinData() != null) {
    	  java.sql.Date data = new java.sql.Date(s.getFinData().getTime());
        stmt.setDate(1, data);
      } else {
        stmt.setDate(1, null);
      }
      
      stmt.setString(2, s.getFinCategoria().getValue());
      stmt.setString(3, s.getFinDescricao().getValue());
      stmt.setFloat(4, s.getFinValor());
      stmt.setInt(5, s.getFinId());

      stmt.execute();

      try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          s.setFinId(generatedKeys.getInt(1));
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

  public ArrayList<Financeiro> getLista() throws Exception {
    try {
      String sql = "SELECT * FROM financeiro";
      PreparedStatement stmt = connection.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();

      ArrayList<Financeiro> financeiros = new ArrayList();

      while (rs.next()) {

        Financeiro financeiro = new Financeiro();
        financeiro.setFinId(rs.getInt("fin_id"));
        financeiro.setFinData(rs.getDate("fin_data"));
        financeiro.setFinCategoria(rs.getString("fin_categoria"));
        financeiro.setFinDescricao(rs.getString("fin_descricao"));
        financeiro.setFinValor(rs.getFloat("fin_valor"));

        financeiros.add(financeiro);
      }

      rs.close();
      stmt.close();
      return financeiros;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void remover(Financeiro s) {
    try {
      String sql = "DELETE FROM financeiro WHERE fin_id = ?";
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setInt(1, s.getFinId());
      stmt.execute();
      stmt.close();
      System.out.println("Removido!");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void alterar(Financeiro s) {
    String sql = "UPDATE financeiro SET fin_data = ?, fin_categoria = ?, fin_descricao = ?, fin_valor = ? WHERE fin_id = ?";

    try {
      PreparedStatement stmt = connection.prepareStatement(sql);

      if (s.getFinData() != null) {
    	  java.sql.Date data = new java.sql.Date(s.getFinData().getTime());
        stmt.setDate(1, data);
      } else {
        stmt.setDate(1, null);
      }
      
      stmt.setString(2, s.getFinCategoria().getValue());
      stmt.setString(3, s.getFinDescricao().getValue());
      stmt.setFloat(4, s.getFinValor());
      stmt.setInt(5, s.getFinId());

      stmt.execute();
      stmt.close();
      System.out.println("Alterado!");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Financeiro buscar(Integer fin_id) throws Exception {
    try {
      String sql = "SELECT * FROM financeiro where fin_id = ?";
      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      stmt.setInt(1, fin_id);
      stmt.execute();

      ResultSet rs = stmt.executeQuery();

      Financeiro financeiro = new Financeiro();

      while (rs.next()) {
    	  financeiro.setFinId(rs.getInt("fin_id"));
    	  financeiro.setFinData(rs.getDate("fin_data"));
          financeiro.setFinCategoria(rs.getString("fin_categoria"));
          financeiro.setFinDescricao(rs.getString("fin_descricao"));
          financeiro.setFinValor(rs.getFloat("fin_valor"));
      }

      rs.close();
      stmt.close();

      return financeiro;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}