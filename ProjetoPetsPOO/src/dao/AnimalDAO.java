package dao;

import classes.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class AnimalDAO {
  private Connection connection;

  public AnimalDAO() throws Exception {
    connection = new ConnectionFactory().getConnection();
  }

  public Animal inserir(Animal a) {
    String sql = "INSERT INTO animal (ani_nome, ani_entrada, ani_saida, ani_raca, ani_tipo) VALUES (?, ?, ?, ?, ?);";
    try {
      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      java.sql.Date data_entrada = new java.sql.Date(a.getAniEntrada().getTime());
      java.sql.Date data_saida = new java.sql.Date(a.getAniSaida().getTime());

      stmt.setString(1, a.getAniNome());
      
      if (a.getAniEntrada() != null) {
        stmt.setDate(2, data_entrada);
      } else {
        stmt.setDate(2, null);
      }

      if (a.getAniSaida() != null) {
        stmt.setDate(3, data_saida);
      } else {
        stmt.setDate(3, null);
      }

      stmt.setString(4, a.getAniRaca());
      stmt.setString(5, a.getAniTipo());

      stmt.execute();

      try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          a.setAniId(generatedKeys.getInt(1));
        } else {
          throw new SQLException("Creating animal failed, no ID obtained.");
        }
      }

      stmt.close();
      System.out.println("Gravado!");

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return a;
  }

  public ArrayList<Animal> getLista() throws Exception {
    try {
      String sql = "SELECT * FROM animal";
      PreparedStatement stmt = connection.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();

      ArrayList<Animal> animais = new ArrayList();

      while (rs.next()) {

        Animal animal = new Animal();
        animal.setAniId(rs.getInt("ani_id"));
        animal.setAniNome(rs.getString("ani_nome"));
        animal.setAniEntrada(rs.getDate("ani_entrada"));
        animal.setAniSaida(rs.getDate("ani_saida"));
        animal.setAniRaca(rs.getString("ani_raca"));
        animal.setAniTipo(rs.getString("ani_tipo"));

        animais.add(animal);
      }

      rs.close();
      stmt.close();
      return animais;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void remover(Animal a) {
    try {
      String sql = "DELETE FROM animal WHERE ani_id = ?";
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setInt(1, a.getAniId());
      stmt.execute();
      stmt.close();
      System.out.println("Removido!");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void alterar(Animal a) {
    String sql = "UPDATE animal SET ani_nome = ?, ani_entrada = ?, ani_saida = ?, ani_raca = ?, ani_tipo = ? WHERE ani_id = ?";

    try {
      PreparedStatement stmt = connection.prepareStatement(sql);

      java.sql.Date data_entrada = new java.sql.Date(a.getAniEntrada().getTime());
      java.sql.Date data_saida = new java.sql.Date(a.getAniSaida().getTime());

      stmt.setString(1, a.getAniNome());

      if (a.getAniEntrada() != null) {
        stmt.setDate(2, data_entrada);
      } else {
        stmt.setDate(2, null);
      }

      if (a.getAniSaida() != null) {
        stmt.setDate(3, data_saida);
      } else {
        stmt.setDate(3, null);
      }

      stmt.setString(4, a.getAniRaca());
      stmt.setString(5, a.getAniTipo());
      stmt.setInt(6, a.getAniId());

      stmt.execute();
      stmt.close();
      System.out.println("Alterado!");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Animal buscar(Integer ani_id) throws Exception {
    try {
      String sql = "SELECT * FROM animal where ani_id = ?";
      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      stmt.setInt(1, ani_id);
      stmt.execute();

      ResultSet rs = stmt.executeQuery();

      Animal animal = new Animal();

      while (rs.next()) {
        animal.setAniId(rs.getInt("ani_id"));
        animal.setAniNome(rs.getString("ani_nome"));
        animal.setAniEntrada(rs.getDate("ani_entrada"));
        animal.setAniSaida(rs.getDate("ani_saida"));
        animal.setAniRaca(rs.getString("ani_raca"));
        animal.setAniTipo(rs.getString("ani_tipo"));
      }

      rs.close();
      stmt.close();

      return animal;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}