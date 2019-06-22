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

public class ColaboradorDAO {
  private Connection connection;

  public ColaboradorDAO() throws Exception {
    connection = new Conexao().getConnection();
  }

  public Colaborador inserir(Colaborador c) throws Exception {
    String sql = "INSERT INTO colaborador (col_funcao, pes_id) VALUES (?, ?);";
    try {
      PessoaDAO pDAO = new PessoaDAO();
      c = (Colaborador) pDAO.inserir((Pessoa) c);
      
      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      stmt.setString(1, c.getColFuncao().getValue());
      stmt.setInt(2, c.getPesId());

      stmt.execute();

      try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          c.setColId(generatedKeys.getInt(1));
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

  public ArrayList<Colaborador> getLista() throws Exception {
    try {
      String sql = "SELECT * FROM colaborador join pessoa on pessoa.pes_id = colaborador.pes_id";
      PreparedStatement stmt = connection.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();

      ArrayList<Colaborador> colaboradors = new ArrayList();

      while (rs.next()) {

        Colaborador colaborador = new Colaborador();

        colaborador.setColId(rs.getInt("col_id"));
        colaborador.setColFuncao(rs.getString("col_funcao"));
        colaborador.setPesId(rs.getInt("pes_id"));
        colaborador.setPesNome(rs.getString("pes_nome"));
        colaborador.setPesCep(rs.getString("pes_cep"));
        colaborador.setPesTelefone(rs.getString("pes_telefone"));
        colaborador.setPesCpf(rs.getString("pes_cpf"));
        colaborador.setPesNumero(rs.getString("pes_numero"));
        colaboradors.add(colaborador);
      }

      rs.close();
      stmt.close();
      return colaboradors;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void remover(Colaborador c) {
    try {
      String sql = "DELETE FROM colaborador WHERE col_id = ?";
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setInt(1, c.getColId());
      stmt.execute();
      stmt.close();
      System.out.println("Removido!");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void alterar(Colaborador c) throws Exception {
    String sql = "UPDATE colaborador SET col_funcao = ?, pes_id = ? WHERE col_id = ?";

    try {
      PessoaDAO pDAO = new PessoaDAO();
      c = (Colaborador) pDAO.alterar((Pessoa) c);
      
      PreparedStatement stmt = connection.prepareStatement(sql);

      stmt.setString(1, c.getColFuncao().getValue());
      stmt.setInt(2, c.getPesId());
      stmt.setInt(3, c.getColId());

      stmt.execute();
      stmt.close();
      System.out.println("Alterado!");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Colaborador buscar(Integer col_id) throws Exception {
    try {
      String sql = "SELECT * FROM colaborador where col_id = ?";
      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      stmt.setInt(1, col_id);
      stmt.execute();

      ResultSet rs = stmt.executeQuery();

      Colaborador colaborador = new Colaborador();

      while (rs.next()) {
    	  colaborador.setColId(rs.getInt("col_id"));
          colaborador.setColFuncao(rs.getString("col_funcao"));
          colaborador.setPesId(rs.getInt("pes_id"));
          colaborador.setPesNome(rs.getString("pes_nome"));
          colaborador.setPesCep(rs.getString("pes_cep"));
          colaborador.setPesTelefone(rs.getString("pes_telefone"));
          colaborador.setPesCpf(rs.getString("pes_cpf"));
          colaborador.setPesNumero(rs.getString("pes_numero"));
      }

      rs.close();
      stmt.close();

      return colaborador;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}