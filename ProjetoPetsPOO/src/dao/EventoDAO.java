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

public class EventoDAO {
  private Connection connection;

  public EventoDAO() throws Exception {
    connection = new Conexao().getConnection();
  }

  public Evento inserir(Evento e) {
    String sql = "INSERT INTO evento (ev_nome, ev_data, ev_descricao, ev_local, col_id_resp) VALUES (?, ?, ?, ?, ?);";
    try {
      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      stmt.setString(1, e.getEventoNome().getValue());
      stmt.setString(2, e.getEventoData().getValue());
      stmt.setString(3, e.getEventoDescricao());
      stmt.setString(4, e.getEventoLocal().getValue());
      stmt.setInt(5, e.getPesIdResp().getColId());
      stmt.execute();

      try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          e.setEventoId(generatedKeys.getInt(1));
        } else {
          throw new SQLException("Creating adopt failed, no ID obtained.");
        }
      }

      stmt.close();
      System.out.println("Gravado!");

    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return e;
  }

  public ArrayList<Evento> getLista() throws Exception {
    try {
      String sql = "SELECT * FROM evento";
      PreparedStatement stmt = connection.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();

      ArrayList<Evento> lista_evento = new ArrayList();

      while (rs.next()) {

        Evento evento = new Evento();

        evento.setEventoId(rs.getInt("ev_id"));
        evento.setEventoNome(rs.getString("ev_nome"));
        evento.setEventoData(rs.getString("ev_data"));
        evento.setEventoDescricao(rs.getString("ev_descricao"));
        evento.setEventoLocal(rs.getString("ev_local"));

        Colaborador col = new Colaborador();
        ColaboradorDAO cDAO = new ColaboradorDAO();
        col = cDAO.buscar(rs.getInt("col_id_resp"));
        
        evento.setPesIdResp(col);

        lista_evento.add(evento);
      }

      rs.close();
      stmt.close();
      return lista_evento;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void remover(Evento a) {
    try {
      String sql = "DELETE FROM evento WHERE ev_id = ?";
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setInt(1, a.getEventoId());
      stmt.execute();
      stmt.close();
      System.out.println("Removido!");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void alterar(Evento a) {
    String sql = "UPDATE evento SET ev_nome = ?, ev_data = ?, ev_descricao = ?, ev_local = ?, col_id_resp = ?  WHERE ev_id = ?";

    try {
      PreparedStatement stmt = connection.prepareStatement(sql);

      stmt.setString(1, a.getEventoNome().getValue());
      stmt.setString(2, a.getEventoData().getValue());
      stmt.setString(3, a.getEventoDescricao());
      stmt.setString(4, a.getEventoLocal().getValue());
      stmt.setInt(5, a.getPesIdResp().getColId());
      stmt.setInt(6, a.getEventoId());

      stmt.execute();
      stmt.close();
      System.out.println("Alterado!");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Evento buscar(Integer ev_id) throws Exception {
    try {
      String sql = "SELECT * FROM evento where ev_id = ?";
      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      stmt.setInt(1, ev_id);
      stmt.execute();

      ResultSet rs = stmt.executeQuery();

      Evento evento = new Evento();

      while (rs.next()) {

          evento.setEventoId(rs.getInt("ev_id"));
          evento.setEventoNome(rs.getString("ev_nome"));
          evento.setEventoData(rs.getString("ev_data"));
          evento.setEventoDescricao(rs.getString("ev_descricao"));
          evento.setEventoLocal(rs.getString("ev_local"));

          Colaborador col = new Colaborador();
          ColaboradorDAO cDAO = new ColaboradorDAO();
          col = cDAO.buscar(rs.getInt("col_id_resp"));
          
          evento.setPesIdResp(col);
      }

      rs.close();
      stmt.close();

      return evento;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}