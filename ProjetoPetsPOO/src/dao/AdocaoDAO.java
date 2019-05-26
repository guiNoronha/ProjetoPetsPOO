package dao;


import classes.*;
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
    connection = new ConnectionFactory().getConnection();
  }

  public Adocao inserir(Adocao a) {
    String sql = "INSERT INTO adocao (adoc_data, cand_id, ani_id, vol_id) VALUES (?, ?, ?, ?);";
    try {
      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      java.sql.Date data = new java.sql.Date(a.getAdocaoData().getTime());
      
      if (a.getAdocaoData() != null) {
        stmt.setDate(1, data);
      } else {
        stmt.setDate(1, null);
      }

      stmt.setInt(2, a.getCandidato().getCandidatoId());
      stmt.setInt(3, a.getAnimal().getAnimalId());
      stmt.setInt(4, a.getVoluntario().getVoluntarioId());


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

        adocao.setAdocaoId(rs.getInt("adoc_id"));
        adocao.setAdocaoData(rs.getDate("adoc_data"));

        Candidato cand = new Candidato();
        CandidatoDAO cDAO = new CandidatoDAO();
        cand = cDAO.buscar(rs.getInt("cand_id"));

        Animal ani = new Animal();
        AnimalDAO aDAO = new AnimalDAO();
        ani = aDAO.buscar(rs.getInt("ani_id"));

        Voluntario vol = new Voluntario();
        VoluntarioDAO vDAO = new VoluntarioDAO();
        vol = vDAO.buscar(rs.getInt("vol_id"));

        adocao.setCandidato(cand);
        adocao.setAnimal(ani);
        adocao.setVoluntario(vol);

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
      String sql = "DELETE FROM adocao WHERE adoc_id = ?";
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
    String sql = "UPDATE adocao SET adoc_data = ?, cand_id = ?, ani_id = ?, vol_id = ? WHERE adoc_id = ?";

    try {
      PreparedStatement stmt = connection.prepareStatement(sql);

      java.sql.Date data = new java.sql.Date(a.getAdocaoDate().getTime());

      if (a.getAdocaoData() != null) {
        stmt.setDate(1, data);
      } else {
        stmt.setDate(1, null);
      }

      stmt.setInt(2, a.getCandidato().getCandidatoId());
      stmt.setInt(3, a.getAnimal().getAnimalId());
      stmt.setInt(4, a.getVoluntario().getVoluntarioId());
      stmt.setInt(5, a.getAdocaoId());

      stmt.execute();
      stmt.close();
      System.out.println("Alterado!");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Adocao buscar(Integer adoc_id) throws Exception {
    try {
      String sql = "SELECT * FROM adocao where adoc_id = ?";
      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      stmt.setInt(1, adoc_id);
      stmt.execute();

      ResultSet rs = stmt.executeQuery();

      Adocao adocao = new Adocao();

      while (rs.next()) {
        Adocao adocao = new Adocao();

        adocao.setAdocaoId(rs.getInt("adoc_id"));
        adocao.setAdocaoData(rs.getDate("adoc_data"));

        Candidato cand = new Candidato();
        CandidatoDAO cDAO = new CandidatoDAO();
        cand = cDAO.buscar(rs.getInt("cand_id"));

        Animal ani = new Animal();
        AnimalDAO aDAO = new AnimalDAO();
        ani = aDAO.buscar(rs.getInt("ani_id"));

        Voluntario vol = new Voluntario();
        VoluntarioDAO vDAO = new VoluntarioDAO();
        vol = vDAO.buscar(rs.getInt("vol_id"));

        adocao.setCandidato(cand);
        adocao.setAnimal(ani);
        adocao.setVoluntario(vol);
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