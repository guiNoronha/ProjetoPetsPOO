package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class CandidatoDAO {
  private Connection connection;

  public CandidatoDAO() throws Exception {
    connection = new ConnectionFactory().getConnection();
  }

  public Candidato inserir(Candidato c) {
    String sql = "INSERT INTO candidato (cand_valido, pes_id) VALUES (?, ?);";
    try {
      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      stmt.setInt(1, c.getCandidatoValido());
      stmt.setInt(2, c.getPessoa().getPessoaId());

      stmt.execute();

      try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          c.setCandidatoId(generatedKeys.getInt(1));
        } else {
          throw new SQLException("Creating candidate failed, no ID obtained.");
        }
      }

      stmt.close();
      System.out.println("Gravado!");

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return s;
  }

  public ArrayList<Candidato> getLista() throws Exception {
    try {
      String sql = "SELECT * FROM candidato";
      PreparedStatement stmt = connection.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();

      ArrayList<Candidato> candidatos = new ArrayList();

      while (rs.next()) {

        Candidato candidato = new Candidato();

        candidato.setCandidatoId(rs.getString("cand_vid"));
        candidato.setCandidatoValido(rs.getString("cand_valido"));

        Pessoa pes = new Pessoa();
        PessoaDAO pDAO = new PessoaDAO();
        pes = pDAO.buscar(rs.getInt("pes_id"));

        candidato.setPessoa(pes);

        candidatos.add(candidato);
      }

      rs.close();
      stmt.close();
      return candidatos;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void remover(Candidato c) {
    try {
      String sql = "DELETE FROM candidato WHERE cand_id = ?";
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setInt(1, c.getCandidatoId());
      stmt.execute();
      stmt.close();
      System.out.println("Removido!");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void alterar(Candidato c) {
    String sql = "UPDATE candidato SET cand_valido = ?, pes_id = ? WHERE cand_id = ?";

    try {
      PreparedStatement stmt = connection.prepareStatement(sql);

      stmt.setString(1, c.getCandidatoValido());
      stmt.setInt(2, c.getPessoa().getPessoaId());
      stmt.setInt(3, c.getCandidatoValido());

      stmt.execute();
      stmt.close();
      System.out.println("Alterado!");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Candidato buscar(Integer cand_id) throws Exception {
    try {
      String sql = "SELECT * FROM candidato where cand_id = ?";
      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      stmt.setInt(1, cand_id);
      stmt.execute();

      ResultSet rs = stmt.executeQuery();

      Candidato candidato = new Candidato();

      while (rs.next()) {
        candidato.setCandidatoValido(rs.getString("cand_valido"));

        Pessoa pes = new Pessoa();
        PessoaDAO pDAO = new PessoaDAO();
        pes = pDAO.buscar(rs.getInt("pes_id"));

        candidato.setPessoa(pes);
      }

      rs.close();
      stmt.close();

      return candidato;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}