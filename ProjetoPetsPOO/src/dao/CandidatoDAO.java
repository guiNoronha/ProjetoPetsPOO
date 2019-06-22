package dao;

import dao.*;
import classes.*;
import banco.*;
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
    connection = new Conexao().getConnection();
  }

  public Candidato inserir(Candidato c) throws Exception {
    String sql = "INSERT INTO candidato (cand_valido, pes_id) VALUES (?, ?);";
    try {
      PessoaDAO pDAO = new PessoaDAO();
      c = (Candidato) pDAO.inserir((Pessoa) c);
      
      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      stmt.setBoolean(1, c.getCandValido());
      stmt.setInt(2, c.getPesId());

      stmt.execute();

      try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          c.setCandId(generatedKeys.getInt(1));
        } else {
          throw new SQLException("Creating candidate failed, no ID obtained.");
        }
      }

      stmt.close();
      System.out.println("Gravado!");

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return c;
  }

  public ArrayList<Candidato> getLista() throws Exception {
    try {
      String sql = "SELECT * FROM candidato join pessoa on pessoa.pes_id = candidato.pes_id";
      PreparedStatement stmt = connection.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();

      ArrayList<Candidato> candidatos = new ArrayList();

      while (rs.next()) {

        Candidato candidato = new Candidato();

        candidato.setCandId(rs.getInt("cand_id"));
        candidato.setCandValido(rs.getBoolean("cand_valido"));
        candidato.setPesId(rs.getInt("pes_id"));
        candidato.setPesNome(rs.getString("pes_nome"));
        candidato.setPesCep(rs.getString("pes_cep"));
        candidato.setPesTelefone(rs.getString("pes_telefone"));
        candidato.setPesCpf(rs.getString("pes_cpf"));
        candidato.setPesNumero(rs.getString("pes_numero"));
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
      stmt.setInt(1, c.getCandId());
      stmt.execute();
      stmt.close();
      System.out.println("Removido!");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void alterar(Candidato c) throws Exception {
    String sql = "UPDATE candidato SET cand_valido = ?, pes_id = ? WHERE cand_id = ?";

    try {
      PessoaDAO pDAO = new PessoaDAO();
      c = (Candidato) pDAO.alterar((Pessoa) c);
      
      PreparedStatement stmt = connection.prepareStatement(sql);

      stmt.setBoolean(1, c.getCandValido());
      stmt.setInt(2, c.getPesId());
      stmt.setInt(3, c.getCandId());

      stmt.execute();
      stmt.close();
      System.out.println("Alterado!");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Candidato buscar(Integer cand_id) throws Exception {
    try {
      String sql = "SELECT * FROM candidato join pessoa on pessoa.pes_id = candidato.pes_id where cand_id = ?";
      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      stmt.setInt(1, cand_id);
      stmt.execute();

      ResultSet rs = stmt.executeQuery();

      Candidato candidato = new Candidato();

      while (rs.next()) {
    	  candidato.setCandId(rs.getInt("cand_id"));
          candidato.setCandValido(rs.getBoolean("cand_valido"));
          candidato.setPesId(rs.getInt("pes_id"));
          candidato.setPesNome(rs.getString("pes_nome"));
          candidato.setPesCep(rs.getString("pes_cep"));
          candidato.setPesTelefone(rs.getString("pes_telefone"));
          candidato.setPesCpf(rs.getString("pes_cpf"));
          candidato.setPesNumero(rs.getString("pes_numero"));
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