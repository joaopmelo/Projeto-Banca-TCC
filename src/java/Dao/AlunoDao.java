/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import DB.ConnectionFactoryDs;
import dominio.Aluno;
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
public class AlunoDao implements IAlunoDao {
    
    private ConnectionFactoryDs cf = new ConnectionFactoryDs();
    private Connection conn;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
   
    String sql = null;
    
    @Override
    public void adiciona(Aluno aluno) throws SQLException {
      
        conn = cf.getConnection();
      
         //desativar o commit implicito
        conn.setAutoCommit(false);
        
        String sql;

        try {

            
        // INSERINDO Projeto
            sql = "insert into aluno(nome,"
                    + "cpf,rg,email,"
                    + "matricula) "
                    + "VALUES (?,?,?,?,?)";
            
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, aluno.getNome());
            pstmt.setString(2, aluno.getCpf());
            pstmt.setString(3, aluno.getRg());
            pstmt.setString(4, aluno.getEmail());
            pstmt.setInt(5, aluno.getMatricula());

            pstmt.executeUpdate();
//confirma a operação
            conn.commit();

            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
//desfaz a operação
            conn.rollback();
            pstmt.close();
            conn.close();

        }
    }

    @Override
    public void altera(Aluno aluno) throws SQLException {
        conn = cf.getConnection();
      
         //desativar o commit implicito
        conn.setAutoCommit(false);

        String sql;

        try {


            // INSERINDO EMPREGADO
            sql = "UPDATE aluno SET nome=?,cpf=?,rg=?,email=?,matricula=? WHERE id_aluno=?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, aluno.getNome());
            pstmt.setString(2, aluno.getCpf());
            pstmt.setString(3, aluno.getRg());
            pstmt.setString(4, aluno.getEmail());
            pstmt.setInt(5, aluno.getMatricula());
            pstmt.setInt(6, aluno.getId());

            pstmt.executeUpdate();
//confirma a operação
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
    public void remove(Aluno aluno) throws SQLException {
        

        conn = cf.getConnection();

        pstmt = conn.prepareStatement(
                "delete from aluno where id_aluno=?");

        pstmt.setInt(1, aluno.getId());

        pstmt.executeUpdate();
        pstmt.close();
        conn.close();

    }

    @Override
    public Aluno getAluno(Integer id) throws SQLException {
        
        conn = cf.getConnection();

        sql = "select * from aluno where id_aluno=?";

        pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);

        rs = pstmt.executeQuery();

        //declarando os objetos
        Aluno aluno = new Aluno();
       

        while (rs.next()) {

            // setando os atributos do objeto Empregado
            aluno.setId(rs.getInt("id_aluno"));
            aluno.setNome(rs.getString("nome"));
            aluno.setCpf(rs.getString("cpf"));
            aluno.setRg(rs.getString("rg"));
            aluno.setEmail(rs.getString("email"));
            aluno.setMatricula(rs.getInt("matricula"));
           

        }

        rs.close();
        pstmt.close();
        conn.close();

        return aluno;
    }

    @Override
    public List<Aluno> listaAlunos() throws SQLException {
        
        List<Aluno> alunos = new ArrayList<>();

        conn = cf.getConnection();

        pstmt = conn.prepareStatement("select * from aluno");

        rs = pstmt.executeQuery();

        while (rs.next()) {
            // criando o objeto Aluno
            Aluno aluno = new Aluno();
            
            aluno.setId(rs.getInt("id_aluno"));
            aluno.setNome(rs.getString("nome"));
            aluno.setCpf(rs.getString("cpf"));
            aluno.setRg(rs.getString("rg"));
            aluno.setEmail(rs.getString("email"));
            aluno.setMatricula(rs.getInt("matricula"));
            
            //adiciona o objeto na lista
            alunos.add(aluno);
        }
        rs.close();
        pstmt.close();
        conn.close();

        return alunos;
    }

   
    
}
