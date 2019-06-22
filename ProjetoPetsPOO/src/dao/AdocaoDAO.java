package dao;


import classes.*;
import banco.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class AdocaoDAO {
  private Connection connection;

  public AdocaoDAO() throws Exception {
    connection = new Conexao().getConnection();
  }

  public Adocao inserir(Adocao a) {
    String sql = "INSERT INTO adocao (cand_id, ani_id) VALUES (?, ?);";
    try {
      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      stmt.setInt(1, a.getCand().getCandId());
      stmt.setInt(2, a.getAni().getAniId());


      stmt.execute();

      try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          a.setAdocaoId(generatedKeys.getInt(1));
        } else {
          throw new SQLException("Creating adopt failed, no ID obtained.");
        }
      }

      stmt.close();
      System.out.println("Gravado!");

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return a;
  }

  public ArrayList<Adocao> getLista() throws Exception {
    try {
      String sql = "SELECT * FROM adocao";
      PreparedStatement stmt = connection.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();

      ArrayList<Adocao> lista_adocao = new ArrayList();

      while (rs.next()) {

        Adocao adocao = new Adocao();

        adocao.setAdocaoId(rs.getInt("ad_id"));

        Candidato cand = new Candidato();
        CandidatoDAO cDAO = new CandidatoDAO();
        cand = cDAO.buscar(rs.getInt("cand_id"));

        Animal ani = new Animal();
        AnimalDAO aDAO = new AnimalDAO();
        ani = aDAO.buscar(rs.getInt("ani_id"));

        adocao.setCand(cand);
        adocao.setAni(ani);

        lista_adocao.add(adocao);
      }

      rs.close();
      stmt.close();
      return lista_adocao;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void remover(Adocao a) {
    try {
      String sql = "DELETE FROM adocao WHERE ad_id = ?";
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setInt(1, a.getAdocaoId());
      stmt.execute();
      stmt.close();
      System.out.println("Removido!");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void alterar(Adocao a) {
    String sql = "UPDATE adocao SET cand_id = ?, ani_id = ? WHERE ad_id = ?";

    try {
      PreparedStatement stmt = connection.prepareStatement(sql);

      stmt.setInt(1, a.getCand().getCandId());
      stmt.setInt(2, a.getAni().getAniId());
      stmt.setInt(3, a.getAdocaoId());

      stmt.execute();
      stmt.close();
      System.out.println("Alterado!");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Adocao buscar(Integer ad_id) throws Exception {
    try {
      String sql = "SELECT * FROM adocao where ad_id = ?";
      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      stmt.setInt(1, ad_id);
      stmt.execute();

      ResultSet rs = stmt.executeQuery();

      Adocao adocao = new Adocao();

      while (rs.next()) {

        adocao.setAdocaoId(rs.getInt("ad_id"));

        Candidato cand = new Candidato();
        CandidatoDAO cDAO = new CandidatoDAO();
        cand = cDAO.buscar(rs.getInt("cand_id"));

        Animal ani = new Animal();
        AnimalDAO aDAO = new AnimalDAO();
        ani = aDAO.buscar(rs.getInt("ani_id"));
        
        adocao.setCand(cand);
        adocao.setAni(ani);
      }

      rs.close();
      stmt.close();

      return adocao;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}