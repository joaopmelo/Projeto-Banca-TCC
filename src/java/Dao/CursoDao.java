/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import DB.ConnectionFactoryDs;
import dominio.Curso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jpedro
 */
public class CursoDao implements ICursoDao{

    private ConnectionFactoryDs cf = new ConnectionFactoryDs();
    private Connection conn;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
    
    String sql;
    
    @Override
    public void adicionar(Curso curso) throws SQLException {
       
        conn = cf.getConnection();
        
        // desativando o commit
        conn.setAutoCommit(false);
        
        
        
        try {
            
            sql = "insert into curso(nome,instituicao, carga_horaria)"
                    + "values(?,?,?)";
            
            pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, curso.getNome());
            pstm.setString(2, curso.getInstituicao());
            pstm.setInt(3, curso.getCargaHoraria());
            
            pstm.executeUpdate();
            
            // confirma
                conn.commit();
            
            // fecha as conexões
                 pstm.close();
                conn.close();
            
        } catch (Exception e) {
            
            // desfazer
                conn.rollback();
            
             // fecha as conexões
                pstm.close();
                conn.close();
            
        }
        
    }

    @Override
    public void altera(Curso curso) throws SQLException {
        
        conn = cf.getConnection();
        
        // desativando o commit
            conn.setAutoCommit(false);
      
        try {
            
            sql = "update curso set  nome=?,instituicao=?, carga_horaria=? where id_curso=?";
        
        pstm = conn.prepareStatement(sql);
        
        pstm.setString(1, curso.getNome());        
        pstm.setString(2, curso.getInstituicao());
        pstm.setInt(3, curso.getCargaHoraria());
        pstm.setInt(4, curso.getId());
        
        pstm.executeUpdate();
        
        // confirma
            conn.commit();
        
        // fecha as conexões
            pstm.close();
            conn.close();
            
        } catch (Exception e) {
        // desfaz   
            conn.rollback();
        
        // fecha as conexões
            pstm.close();
            conn.close();
        }
        
        
    }

    @Override
    public void remove(Curso curso) throws SQLException {
     
        conn = cf.getConnection();
     
            
            sql = "delete from curso where id_curso=? ";
            
            pstm = conn.prepareStatement(sql);
            
            pstm.setInt(1, curso.getId());
            
            pstm.executeUpdate();
            
            pstm.close();
            conn.close();
            
        
    }

    @Override
    public Curso getCurso(Integer id) throws SQLException {
        
        conn = cf.getConnection();
        
        sql = "select * from curso where id_curso=?";
        
        pstm = conn.prepareStatement(sql);
        
        pstm.setInt(1, id);
        
        rs = pstm.executeQuery();
        
        Curso curso = new Curso();
        
        while (rs.next()) {
           
            // Populando o objeto curso
            curso.setId(rs.getInt("id_curso"));
            curso.setNome(rs.getString("nome"));
            curso.setInstituicao(rs.getString("instituicao"));
            curso.setCargaHoraria(rs.getInt("carga_horaria"));
            
        }
        
        rs.close();
        pstm.close();
        conn.close();
        
        return curso;
        
    }

    @Override
    public List<Curso> listaCurso() throws SQLException {
        
        List<Curso> cursos = new ArrayList<>();
        
        conn = cf.getConnection();
        
        sql = "select * from curso";
        
        pstm = conn.prepareStatement(sql);
        
        rs = pstm.executeQuery();
        
        Curso curso = new Curso();
        
        while (rs.next()) {
           
            // Populando o objeto curso
            curso.setId(rs.getInt("id_curso"));
            curso.setNome(rs.getString("nome"));
            curso.setInstituicao(rs.getString("instituicao"));
            curso.setCargaHoraria(rs.getInt("carga_horaria"));
            
            cursos.add(curso);
        }
        
        rs.close();
        pstm.close();
        conn.close();
        
        return cursos;
       
    }

    
   
    
}
