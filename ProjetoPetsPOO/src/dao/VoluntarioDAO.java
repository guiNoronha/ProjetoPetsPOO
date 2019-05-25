package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class VoluntarioDAO {
  private Connection connection;

  public VoluntarioDAO() throws Exception {
    connection = new ConnectionFactory().getConnection();
  }

  public Voluntario inserir(Voluntario v) {
    String sql = "INSERT INTO voluntario (vol_atuacao, vol_area, pes_id) VALUES (?, ?, ?);";
    try {
      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      stmt.setString(1, v.getVoluntarioAtuacao());
      stmt.setString(2, v.getVoluntarioArea());
      stmt.setInt(3, v.getPessoa().getPessoaId());

      stmt.execute();

      try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          v.setVoluntarioId(generatedKeys.getInt(1));
        } else {
          throw new SQLException("Creating vol failed, no ID obtained.");
        }
      }

      stmt.close();
      System.out.println("Gravado!");

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return s;
  }

  public ArrayList<Voluntario> getLista() throws Exception {
    try {
      String sql = "SELECT * FROM voluntario";
      PreparedStatement stmt = connection.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();

      ArrayList<Voluntario> voluntarios = new ArrayList();

      while (rs.next()) {

        Voluntario voluntario = new Voluntario();

        voluntario.setVoluntarioId(rs.getInt("vol_id"));
        voluntario.setVoluntarioAtuacao(rs.getString("vol_atuacao"));
        voluntario.setVoluntarioArea(rs.getString("vol_area"));

        Pessoa pes = new Pessoa();
        PessoaDAO pDAO = new PessoaDAO();
        pes = pDAO.buscar(rs.getInt("pes_id"));

        voluntario.setPessoa(pes);

        voluntarios.add(voluntario);
      }

      rs.close();
      stmt.close();
      return voluntarios;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void remover(Voluntario v) {
    try {
      String sql = "DELETE FROM voluntario WHERE vol_id = ?";
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setInt(1, v.getVoluntarioId());
      stmt.execute();
      stmt.close();
      System.out.println("Removido!");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void alterar(Voluntario v) {
    String sql = "UPDATE voluntario SET vol_atuacao = ?, vol_area = ?, pes_id = ? WHERE vol_id = ?";

    try {
      PreparedStatement stmt = connection.prepareStatement(sql);

      stmt.setString(1, v.getVoluntarioAtuacao());
      stmt.setString(2, v.getVoluntarioArea());
      stmt.setInt(3, v.getPessoa().getPessoaId());
      stmt.setInt(4, v.getVoluntarioId());

      stmt.execute();
      stmt.close();
      System.out.println("Alterado!");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Voluntario buscar(Integer vol_id) throws Exception {
    try {
      String sql = "SELECT * FROM voluntario where vol_id = ?";
      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      stmt.setInt(1, vol_id);
      stmt.execute();

      ResultSet rs = stmt.executeQuery();

      Voluntario voluntario = new Voluntario();

      while (rs.next()) {
        voluntario.setVoluntarioId(rs.getInt("vol_id"));
        voluntario.setVoluntarioAtuacao(rs.getString("vol_atuacao"));
        voluntario.setVoluntarioArea(rs.getString("vol_area"));

        Pessoa pes = new Pessoa();
        PessoaDAO pDAO = new PessoaDAO();
        pes = pDAO.buscar(rs.getInt("pes_id"));

        voluntario.setPessoa(pes);
      }

      rs.close();
      stmt.close();

      return voluntario;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}