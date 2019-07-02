/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import DB.ConnectionFactoryDs;
import dominio.Professor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author João Pedro
 */
public class ProfessorDao implements IProfessorDao{

    private ConnectionFactoryDs cf = new ConnectionFactoryDs();
    private Connection conn;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    
    @Override
    public void adiciona(Professor professor) throws SQLException {
        conn = cf.getConnection();
        
        String sql;

       
            
        // INSERINDO EMPREGADO
            sql = "Insert into professor(nome,"
                    + "cpf, rg,email,registro,salario) "
                    + "VALUES (?,?,?,?,?,?)";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, professor.getNome());
            pstmt.setString(2, professor.getCpf());
            pstmt.setString(3, professor.getRg());
            pstmt.setString(4, professor.getEmail());
            pstmt.setInt(5, professor.getRegistro());
            pstmt.setDouble(6, professor.getSalario());

            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
 
    }

    @Override
    public void altera(Professor professor) throws SQLException {
          conn = cf.getConnection();

        //desativar o commit implicito
        conn.setAutoCommit(false);

        String sql;

        try {


            // INSERINDO EMPREGADO
            sql = "UPDATE professor SET nome=?,"
                    + "cpf=?, rg=?,email=?,"
                    + "registro=?,salario=? "
                    + "WHERE id_professor=?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, professor.getNome());
            pstmt.setString(2, professor.getCpf());
            pstmt.setString(3, professor.getRg());
            pstmt.setString(4, professor.getEmail());
            pstmt.setInt(5, professor.getRegistro());
            pstmt.setDouble(6, professor.getSalario());
            pstmt.setInt(7, professor.getId());


            pstmt.executeUpdate();

            conn.commit();

            pstmt.close();
            conn.close();
        } catch (SQLException ex) {

            conn.rollback();
            pstmt.close();
            conn.close();

        }
    }

    @Override
    public void remove(Professor professor) throws SQLException {
        
         conn = cf.getConnection();

        pstmt = conn.prepareStatement(
                "delete from professor where id_professor=?");

        pstmt.setInt(1, professor.getId());

        pstmt.executeUpdate();
        pstmt.close();
        conn.close();

    }

   

    @Override
    public Professor getProfessor(Integer id) throws SQLException {
         conn = cf.getConnection();

        String sql = "select * from professor where id_professor=?";

        pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);

        rs = pstmt.executeQuery();

        //declarando os objetos
        Professor professor = new Professor();
       

        while (rs.next()) {

            // setando os atributos do objeto Empregado
            professor.setId(rs.getInt("id_professor"));
            professor.setNome(rs.getString("nome"));
            professor.setCpf(rs.getString("cpf"));
            professor.setRg(rs.getString("rg"));
            professor.setEmail(rs.getString("email"));
            professor.setRegistro(rs.getInt("registro"));
            professor.setSalario(rs.getDouble("salario"));
           

        }

        rs.close();
        pstmt.close();
        conn.close();

        return professor;
        
    }

    @Override
    public List<Professor> listaProfessores() throws SQLException {
       List<Professor> professores = new ArrayList<>();

        conn = cf.getConnection();

        pstmt = conn.prepareStatement(
                "select * from professor");

        rs = pstmt.executeQuery();

        while (rs.next()) {
            // criando o objeto Professor
            Professor professor = new Professor();
            professor.setId(rs.getInt("id_professor"));
            professor.setNome(rs.getString("nome"));
            professor.setCpf(rs.getString("cpf"));
            professor.setRg(rs.getString("rg"));
            professor.setEmail(rs.getString("email"));
            professor.setRegistro(rs.getInt("registro"));
            professor.setSalario(rs.getDouble("salario"));
            
            //adiciona o objeto na lista
            professores.add(professor);
        }
        rs.close();
        pstmt.close();
        conn.close();

        return professores;
    }
        
    
    
}
